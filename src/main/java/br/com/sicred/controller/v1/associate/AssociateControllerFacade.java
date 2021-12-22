package br.com.sicred.controller.v1.associate;

import br.com.sicred.controller.v1.associate.model.response.AssociateResponse;
import br.com.sicred.controller.v1.builder.AssociateBuilder;
import br.com.sicred.integration.cpf.model.CpfModel;
import br.com.sicred.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AssociateControllerFacade {

    @Autowired
    private AssociateService associateService;

    public Mono<AssociateResponse> createAssociate(String cpf) {
        return AssociateBuilder.toResponse(associateService.createAssociate(cpf));
    }

    public Mono<AssociateResponse> getAssociateByCpf(String cpf) {
        return AssociateBuilder.toResponse(associateService.getAssociateByCpf(cpf));
    }
}
