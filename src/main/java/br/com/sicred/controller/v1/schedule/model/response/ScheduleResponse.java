package br.com.sicred.controller.v1.schedule.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleResponse {
    public String id;
    public String name;
    public Integer duration;
}
