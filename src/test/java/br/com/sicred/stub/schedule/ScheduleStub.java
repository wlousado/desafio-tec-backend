package br.com.sicred.stub.schedule;

import br.com.sicred.controller.v1.schedule.model.request.ScheduleRequest;
import br.com.sicred.model.Schedule;

public class ScheduleStub {

    public static Schedule createSchedule(){
        return Schedule.builder()
                .duration(1)
                .name("Schedule Test")
                .build();
    }

    public static ScheduleRequest createRequest(){
        return ScheduleRequest.builder()
                .duration(1)
                .name("Schedule Test")
                .build();
    }

    public static ScheduleRequest createRequestEmpty(){
        return ScheduleRequest.builder()
                .build();
    }
}
