package br.com.sicred.repository;

import br.com.sicred.model.Schedule;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ScheduleRepository extends ReactiveMongoRepository<Schedule, String> {
    @Query("{ name: ?0 }")
    Mono<Schedule> findByName(String name);
}
