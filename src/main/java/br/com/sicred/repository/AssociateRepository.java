package br.com.sicred.repository;

import br.com.sicred.model.Associate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AssociateRepository extends ReactiveMongoRepository<Associate, String> {
    Mono<Associate> findByCpf(String cpf);
}
