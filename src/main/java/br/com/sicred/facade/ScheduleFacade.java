package br.com.sicred.facade;

import br.com.sicred.kafka.procuder.ScheduleProducer;
import br.com.sicred.model.Schedule;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.model.Vote;
import br.com.sicred.repository.ScheduleRepository;
import br.com.sicred.repository.ScheduleResultRepository;
import br.com.sicred.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class ScheduleFacade {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleResultRepository scheduleResultRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ScheduleProducer scheduleProducer;

    public Mono<Schedule> findById(String id){
        return scheduleRepository.findById(id);
    }


    public Mono<Schedule> findByName(String name) {
        return scheduleRepository.findByName(name);
    }

    public Mono<Schedule> save(Schedule saveSchedule) {
        return scheduleRepository.save(saveSchedule);
    }

    public Flux<Vote> findAllByIdSchedule(String id) {
        return voteRepository.findAllByIdSchedule(id);
    }

    public Mono<ScheduleResult> saveResult(ScheduleResult scheduleResult) {
        return scheduleResultRepository.save(scheduleResult);
    }

    public Mono<ScheduleResult> findResult(String id) {
        return scheduleResultRepository.findByIdSchedule(id);
    }

    public void sendToKafka(ScheduleResult scheduleResult) {
        scheduleProducer.sendAnalysis(scheduleResult);
    }
}
