package br.com.sicred.controller.v1.schedule;

import br.com.sicred.controller.v1.builder.ScheduleBuilder;
import br.com.sicred.controller.v1.schedule.model.request.ScheduleRequest;
import br.com.sicred.controller.v1.schedule.model.response.ScheduleResponse;
import br.com.sicred.controller.v1.schedule.model.response.ScheduleResultResponse;
import br.com.sicred.model.Schedule;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@AllArgsConstructor
public class ScheduleControllerFacade {
    @Autowired
    private ScheduleService scheduleService;

    public Mono<ScheduleResponse> createSchedule(ScheduleRequest schedule){
        return ScheduleBuilder.toMonoResponse(scheduleService.createAnSchedule(ScheduleBuilder.toSchedule(schedule)));
    }

    public Mono<ScheduleResponse> getScheduleByName(ScheduleRequest schedule) {
        return ScheduleBuilder.toMonoResponse(scheduleService.getScheduleByName(schedule.getName()));
    }

    public Mono<ScheduleResponse> getScheduleById(String id) {
        return ScheduleBuilder.toMonoResponse(scheduleService.getSchedulyById(id));
    }

    public Mono<ScheduleResultResponse> getResult(String id) {
        return ScheduleBuilder.toResultMonoResponse(scheduleService.getResult(id));
    }
}
