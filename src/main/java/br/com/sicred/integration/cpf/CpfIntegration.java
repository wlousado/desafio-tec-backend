package br.com.sicred.integration.cpf;

import br.com.sicred.exception.AssociateUnableToVoteException;
import br.com.sicred.integration.cpf.enums.EnumCpf;
import br.com.sicred.integration.cpf.model.CpfModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CpfIntegration {

    @Value("${api.cpf-validation.url}")
    private String API_CPF_URL;

    private final WebClient client = WebClient.create(API_CPF_URL);

    public Mono<CpfModel> doVerification(String cpf) {
        log.info("[CPF INTEGRATION] - Do Verification to cpf {}", cpf);
        return client.get()
                .uri(API_CPF_URL+"/{cpf}", cpf)
                .retrieve()
                .bodyToMono(CpfModel.class)
                .onErrorReturn(CpfModel.builder().status(EnumCpf.UNABLE_TO_VOTE).build());
    }
}
