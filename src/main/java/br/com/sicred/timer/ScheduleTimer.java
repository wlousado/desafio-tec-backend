package br.com.sicred.timer;

import br.com.sicred.dateformat.Formatter;
import br.com.sicred.enums.ScheduleResultEnum;
import br.com.sicred.enums.VoteEnum;
import br.com.sicred.facade.ScheduleFacade;
import br.com.sicred.model.Schedule;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.repository.ScheduleRepository;
import br.com.sicred.repository.VoteRepository;
import br.com.sicred.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.TimerTask;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
public class ScheduleTimer extends TimerTask implements Runnable {

    private final ScheduleFacade scheduleFacade;
    private final Schedule schedule;

    public ScheduleTimer(Schedule schedule, ScheduleFacade scheduleFacade){
        this.schedule = schedule;
        this.scheduleFacade = scheduleFacade;
    }

    @Override
    public void run() {
        scheduleFacade.findById(schedule.getId())
                        .map(data -> {
                            data.setEndDate(LocalDateTime.now());
                            return data;
                        }).flatMap(scheduleFacade::save)
                .flatMap(schedule -> scheduleFacade.findAllByIdSchedule(schedule.getId())
                        .collectList()
                        .flatMap(listVotes -> {

                            Long sim = listVotes.stream().filter(vote -> vote.getVote().equals(VoteEnum.SIM)).count();
                            Long nao = listVotes.stream().filter(vote -> vote.getVote().equals(VoteEnum.NAO)).count();

                            ScheduleResultEnum resultEnum;
                            if(sim > nao){
                                resultEnum = ScheduleResultEnum.APROVED;
                            } else if(sim.equals(nao))  {
                                resultEnum = ScheduleResultEnum.TIE;
                            }else {
                                resultEnum = ScheduleResultEnum.REPROVED;
                            }
                            return Mono.just(ScheduleResult.builder()
                                            .idSchedule(schedule.getId())
                                            .votesSim(sim.intValue())
                                            .votesNao(nao.intValue())
                                            .result(resultEnum)
                                    .build());
                        })
                        .flatMap(scheduleFacade::saveResult))
                .doOnNext(scheduleFacade::sendToKafka)
                .subscribe(result -> log.info("[SCHEDULE TIMER] - end schedule {} - at {}", JsonUtil.convertToJson(result), Formatter.dateToString(LocalDateTime.now())));
    }
}
