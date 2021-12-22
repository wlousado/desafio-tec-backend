package br.com.sicred.controller.v1.schedule;


import br.com.sicred.repository.ScheduleRepository;
import br.com.sicred.stub.schedule.ScheduleStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ScheduleController.class)
@ContextConfiguration(classes = {ScheduleController.class, ScheduleControllerFacade.class})
public class ScheduleControllerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ScheduleControllerFacade scheduleControllerFacade;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    void beforeEach() {
        this.webTestClient =
                WebTestClient.bindToApplicationContext(applicationContext)
                        .configureClient()
                        .build();
    }

    @Test
    void mustBeReturnOk(){
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/schedule/create").build())
                .body(BodyInserters.fromValue(ScheduleStub.createRequest()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void mustBeReturnBadRequest(){
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/schedule/create").build())
                .body(BodyInserters.fromValue(ScheduleStub.createRequestEmpty()))
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}
