package br.com.sicred.kafka.procuder;


import br.com.sicred.controller.v1.builder.ScheduleBuilder;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.model.kafka.model.ScheduleResultAvro;
import br.com.sicred.util.JsonUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
@EnableKafka
public class ScheduleProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic.name.schedule-result}")
    private String scheduleResultTopic;

    public void sendAnalysis(ScheduleResult resultMono) {
        ScheduleResultAvro avro = ScheduleBuilder.toAvro(resultMono);
        kafkaTemplate.send(scheduleResultTopic, avro).addCallback(
                success -> log.info("[SCHEDULE-PRODUCER-AVRO] - Schedule send to topic.: '{}'", JsonUtil.convertToJson(avro)),
                failure -> log.error("[SCHEDULE-PRODUCER-AVRO] - Failed sennd schedule to topic! '{}'", JsonUtil.convertToJson(avro))
        );
    }
}
