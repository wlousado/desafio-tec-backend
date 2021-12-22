package br.com.sicred.controller.v1.votes;

import br.com.sicred.controller.v1.schedule.model.response.ScheduleResultResponse;
import br.com.sicred.controller.v1.votes.model.response.VoteResponse;
import br.com.sicred.enums.VoteEnum;
import br.com.sicred.exception.model.ErrorModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/v1/vote")
@Api(tags = "Vote controller")
@RestController
@Slf4j
public class VoteController {

    @Autowired
    private VoteControllerFacade voteControllerFacade;

    @ApiOperation("Vote to SIM in schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = VoteResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @PostMapping("/{idAssociate}/{idSchedule}/sim")
    public Mono<VoteResponse> doVoteYes(@PathVariable String idAssociate, @PathVariable String idSchedule){
        log.info("[VOTE CONTROLLER] - Do Vote assoc: {} | schedule: {} | vote: sim",idSchedule, idSchedule);
        return voteControllerFacade.doVote(idAssociate, idSchedule, VoteEnum.SIM);
    }

    @ApiOperation("Vote to NAO in schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = VoteResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @PostMapping("/{idAssociate}/{idSchedule}/nao")
    public Mono<VoteResponse> doVoteNo(@PathVariable String idAssociate, @PathVariable String idSchedule){
        log.info("[VOTE CONTROLLER] - Do Vote assoc: {} | schedule: {} | vote: nao",idSchedule, idSchedule);
        return voteControllerFacade.doVote(idAssociate, idSchedule, VoteEnum.NAO);
    }
}
