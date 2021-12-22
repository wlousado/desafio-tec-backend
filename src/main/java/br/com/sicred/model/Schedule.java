package br.com.sicred.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    @Id
    private String id;
    @Indexed(unique=true)
    private String name;
    private Integer duration;
    private LocalDateTime initDate;
    private LocalDateTime endDate;
}
