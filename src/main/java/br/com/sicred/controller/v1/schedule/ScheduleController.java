package br.com.sicred.controller.v1.schedule;

import br.com.sicred.controller.v1.schedule.model.request.ScheduleRequest;
import br.com.sicred.controller.v1.schedule.model.response.ScheduleResponse;
import br.com.sicred.controller.v1.schedule.model.response.ScheduleResultResponse;
import br.com.sicred.exception.model.ErrorModel;
import br.com.sicred.model.ScheduleResult;
import br.com.sicred.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/schedule")
@Slf4j
@Api(tags = "Schedule controller")
@AllArgsConstructor
public class ScheduleController {

    private ScheduleControllerFacade scheduleControllerFacade;

    @ApiOperation("Create An Schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @PostMapping("/create")
    public Mono<ScheduleResponse> createSchedule(@RequestBody @Valid ScheduleRequest schedule){
        log.info("[SCHEDULE CONTROLLER] - Create An Schedule - {}", JsonUtil.convertToJson(schedule));
        return scheduleControllerFacade.createSchedule(schedule);
    }

    @ApiOperation("Get An Schedule by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @GetMapping
    public Mono<ScheduleResponse> getScheduleName(@RequestBody @Valid ScheduleRequest schedule){
        log.info("[SCHEDULE CONTROLLER] - Get An Schedule - {}", JsonUtil.convertToJson(schedule));
        return scheduleControllerFacade.getScheduleByName(schedule);
    }

    @ApiOperation("Get An Schedule by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @GetMapping("/{id}")
    public Mono<ScheduleResponse> getScheduleId(@PathVariable String id){
        log.info("[SCHEDULE CONTROLLER] - Get An Schedule - {}", id);
        return scheduleControllerFacade.getScheduleById(id);
    }

    @ApiOperation("Get result of schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleResultResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @GetMapping("/{id}/result")
    public Mono<ScheduleResultResponse> getResultSchedule(@PathVariable String id){
        log.info("[SCHEDULE CONTROLLER] Get a Result from Schedule {}", id);
        return scheduleControllerFacade.getResult(id);
    }
}
