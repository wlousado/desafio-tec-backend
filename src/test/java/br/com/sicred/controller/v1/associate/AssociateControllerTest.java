package br.com.sicred.controller.v1.associate;

import br.com.sicred.controller.v1.votes.VoteController;
import br.com.sicred.controller.v1.votes.VoteControllerFacade;
import br.com.sicred.repository.AssociateRepository;
import br.com.sicred.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AssociateController.class)
@ContextConfiguration(classes = {AssociateController.class, AssociateControllerFacade.class})
public class AssociateControllerTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AssociateControllerFacade associateControllerFacadeControllerFacade;

    @MockBean
    private AssociateRepository associateRepository;

    @BeforeEach
    void beforeEach() {
        this.webTestClient =
                WebTestClient.bindToApplicationContext(applicationContext)
                        .configureClient()
                        .build();
    }

    @Test
    void mustBeReturnBadRequestCpf(){
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/associate/".concat("19839091069")).build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}
