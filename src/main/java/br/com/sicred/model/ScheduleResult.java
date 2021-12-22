package br.com.sicred.model;

import br.com.sicred.enums.ScheduleResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class ScheduleResult {

    @Id
    private String id;
    private String idSchedule;
    private Integer votesSim;
    private Integer votesNao;
    private ScheduleResultEnum result;
}
