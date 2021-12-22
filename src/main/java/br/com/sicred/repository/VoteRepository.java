package br.com.sicred.repository;

import br.com.sicred.model.Vote;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VoteRepository extends ReactiveMongoRepository<Vote, String> {
    @Query("{'idAssociate' : ?0, 'idSchedule': ?1}")
    Mono<Vote> findByIdAssociateAndIdSchedule(String idAssociate, String idSchedule);

    Flux<Vote> findAllByIdSchedule(String idSchedule);
}
