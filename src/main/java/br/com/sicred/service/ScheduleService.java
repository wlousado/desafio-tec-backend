package br.com.sicred.service;

import br.com.sicred.controller.v1.builder.ScheduleBuilder;
import br.com.sicred.dateformat.Formatter;
import br.com.sicred.exception.*;
import br.com.sicred.facade.ScheduleFacade;
import br.com.sicred.model.Schedule;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.repository.ScheduleRepository;
import br.com.sicred.repository.VoteRepository;
import br.com.sicred.timer.ScheduleTimer;
import br.com.sicred.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ScheduleService {

    @Autowired
    private ScheduleFacade scheduleFacade;

    public Mono<Schedule> createAnSchedule(Schedule schedule) {
        log.info("[SCHEDULE SERVICE] Create An Schedule {}", JsonUtil.convertToJson(schedule));
        return scheduleFacade.findByName(schedule.getName())
                .hasElement()
                .flatMap(bool -> {
                    if(bool){
                        log.info("[SCHEDULE SERVICE] Schedule {} already exists", schedule.getName());
                        return Mono.error(new ScheduleAlreadyEndedException(String.format("schedule %1s already exist", schedule.getName())));
                    }
                    return Mono.just(schedule);
                })
                .flatMap(saveSchadule -> {
                    if(Objects.isNull(schedule.getDuration()) || schedule.getDuration() <= 0)
                        schedule.setDuration(1);
                    schedule.setInitDate(LocalDateTime.now());
                    return Mono.just(schedule);
                })
                .flatMap(saveSchedule -> scheduleFacade.save(saveSchedule).doOnNext(this::scheduleTimer));
    }

    public Mono<Schedule> getScheduleByName(String name){
        log.info("[SCHEDULE SERVICE] Get An Schedule by name {}", name);
        return scheduleFacade.findByName(name)
                .switchIfEmpty(Mono.error(new EntityNotExistsException("This entity not exists in database")));
    }

    public Mono<Schedule> getSchedulyById(String id) {
        log.info("[SCHEDULE SERVICE] Get An Schedule by id {}", id);
        return scheduleFacade.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotExistsException("This entity not exists in database")));
    }

    public Mono<ScheduleResult> getResult(String id) {
        log.info("[SCHEDULE SERVICE] Get a result schedule {}", id);
        return scheduleFacade.findResult(id).switchIfEmpty(Mono.error(new ScheduleResultInProgressException("Schedule in progress")));
    }

    private void scheduleTimer(Schedule schedule){
        log.info("[SCHEDULE SERVICE] Start Schadule: {} - at: {}", schedule.getName(), Formatter.dateToString(schedule.getInitDate()));
        new Timer().schedule(new ScheduleTimer(schedule, scheduleFacade), schedule.getDuration() * 60000);
    }

}
