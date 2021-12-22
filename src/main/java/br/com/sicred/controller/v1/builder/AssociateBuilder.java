package br.com.sicred.controller.v1.builder;

import br.com.sicred.controller.v1.associate.model.response.AssociateResponse;
import br.com.sicred.model.Associate;
import reactor.core.publisher.Mono;

public class AssociateBuilder {
    public static Mono<AssociateResponse> toResponse(Mono<Associate> associate) {
        return associate
                .flatMap(assoc -> Mono.just(AssociateResponse.builder()
                        .yourId(assoc.getId())
                        .build()));
    }
}
