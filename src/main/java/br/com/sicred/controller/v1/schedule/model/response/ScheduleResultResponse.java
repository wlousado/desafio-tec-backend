package br.com.sicred.controller.v1.schedule.model.response;

import br.com.sicred.enums.ScheduleResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResultResponse {
    private Integer votesSim;
    private Integer votesNao;
    private ScheduleResultEnum result;
}
