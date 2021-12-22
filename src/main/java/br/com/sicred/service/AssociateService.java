package br.com.sicred.service;

import br.com.sicred.exception.AssociateAlreadyExistsException;
import br.com.sicred.exception.AssociateNotExistsException;
import br.com.sicred.exception.AssociateUnableToVoteException;
import br.com.sicred.integration.cpf.CpfIntegration;
import br.com.sicred.integration.cpf.enums.EnumCpf;
import br.com.sicred.integration.cpf.model.CpfModel;
import br.com.sicred.model.Associate;
import br.com.sicred.repository.AssociateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AssociateService {

    @Autowired
    private CpfIntegration cpfIntegration;

    @Autowired
    private AssociateRepository associateRepository;

    public Mono<Associate> createAssociate(String cpf) {
        return cpfIntegration.doVerification(cpf)
                .flatMap(assocCpf -> {
                    if(assocCpf.getStatus().equals(EnumCpf.UNABLE_TO_VOTE)){
                        log.info("[ASSOCIATE SERVICE] - cpf {} unable to vote", cpf);
                        return Mono.error(new AssociateUnableToVoteException(String.format("cpf %1s is unable to vote", cpf)));
                    }
                    return Mono.just(assocCpf);
                })
                .flatMap(getCpf -> associateRepository.findByCpf(cpf)
                        .hasElement()
                        .flatMap(element -> {
                            if(element) {
                                log.info("[ASSOCIATE SERVICE] - cpf {} already exist", cpf);
                                return Mono.error(new AssociateAlreadyExistsException(String.format("%1s already exist in database", cpf)));
                            }
                            return Mono.just(Associate.builder().cpf(cpf).build());
                        })
                        .flatMap(associateRepository::save));
    }

    public Mono<Associate> getAssociateByCpf(String cpf) {
        return associateRepository.findByCpf(cpf)
                .switchIfEmpty(Mono.error(new AssociateNotExistsException(String.format("%1s not exist", cpf))));
    }
}
