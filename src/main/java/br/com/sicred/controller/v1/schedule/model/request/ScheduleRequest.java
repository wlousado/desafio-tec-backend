package br.com.sicred.controller.v1.schedule.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequest {

    private String id;
    @NotBlank(message = "name cannot be null")
    private String name;

    @Min(1)
    @Max(60)
    private Integer duration;
}

