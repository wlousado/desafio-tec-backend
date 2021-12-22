package br.com.sicred.controller.v1.builder;

import br.com.sicred.controller.v1.schedule.model.request.ScheduleRequest;
import br.com.sicred.controller.v1.schedule.model.response.ScheduleResponse;
import br.com.sicred.controller.v1.schedule.model.response.ScheduleResultResponse;
import br.com.sicred.enums.ScheduleResultEnum;
import br.com.sicred.model.Schedule;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.model.kafka.model.ResultEnum;
import br.com.sicred.model.kafka.model.ScheduleResultAvro;
import reactor.core.publisher.Mono;

public class ScheduleBuilder {

    public static Schedule toSchedule(ScheduleRequest request){
        return Schedule.builder()
                .name(request.getName())
                .duration(request.getDuration())
                .build();
    }

    public static Mono<ScheduleResponse> toMonoResponse(Mono<Schedule> schedule) {
        return schedule.map(data -> ScheduleResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .duration(data.getDuration())
                .build());
    }

    public static Mono<ScheduleResultResponse> toResultMonoResponse(Mono<ScheduleResult> result) {
        return result.map(data -> ScheduleResultResponse.builder()
                .votesNao(data.getVotesNao())
                .votesSim(data.getVotesSim())
                .result(data.getResult())
                .build());
    }

    public static Mono<ScheduleResultAvro> toAvro(Mono<ScheduleResult> result) {
        return result.map(data -> ScheduleResultAvro.newBuilder()
                .setIdSchedule(data.getIdSchedule())
                .setResult(getEnum(data.getResult()))
                .setVoteSim(data.getVotesSim())
                .setVoteNao(data.getVotesNao())
                .build());
    }

    public static ScheduleResultAvro toAvro(ScheduleResult data) {
        return ScheduleResultAvro.newBuilder()
                .setIdSchedule(data.getIdSchedule())
                .setResult(getEnum(data.getResult()))
                .setVoteSim(data.getVotesSim())
                .setVoteNao(data.getVotesNao())
                .build();
    }

    private static ResultEnum getEnum(ScheduleResultEnum resultEnum){
        return ResultEnum.valueOf(resultEnum.name());
    }
}
