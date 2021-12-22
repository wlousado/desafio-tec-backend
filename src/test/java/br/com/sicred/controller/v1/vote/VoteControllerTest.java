package br.com.sicred.controller.v1.vote;

import br.com.sicred.controller.v1.schedule.ScheduleController;
import br.com.sicred.controller.v1.schedule.ScheduleControllerFacade;
import br.com.sicred.controller.v1.votes.VoteController;
import br.com.sicred.controller.v1.votes.VoteControllerFacade;
import br.com.sicred.repository.ScheduleRepository;
import br.com.sicred.repository.VoteRepository;
import br.com.sicred.stub.schedule.ScheduleStub;
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
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@WebFluxTest(VoteController.class)
@ContextConfiguration(classes = {VoteController.class, VoteControllerFacade.class})
public class VoteControllerTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private VoteControllerFacade voteControllerFacade;

    @MockBean
    private VoteRepository voteRepository;

    @BeforeEach
    void beforeEach() {
        this.webTestClient =
                WebTestClient.bindToApplicationContext(applicationContext)
                        .configureClient()
                        .build();
    }

    @Test
    void mustBeReturnBadRequestSim(){
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/vote/"
                                .concat("123")
                                .concat("123")
                                .concat("/sim")).build())
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    void mustBeReturnBadRequestNao(){
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/vote/"
                                .concat("123")
                                .concat("123")
                                .concat("/nao")).build())
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}
