package br.com.sicred.service;

import br.com.sicred.dateformat.Formatter;
import br.com.sicred.exception.AssociateAlreadyVoteException;
import br.com.sicred.exception.AssociateNotExistsException;
import br.com.sicred.exception.ScheduleAlreadyEndedException;
import br.com.sicred.exception.ScheduleNotExistsException;
import br.com.sicred.integration.cpf.CpfIntegration;
import br.com.sicred.model.Vote;
import br.com.sicred.repository.AssociateRepository;
import br.com.sicred.repository.ScheduleRepository;
import br.com.sicred.repository.VoteRepository;
import br.com.sicred.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@Slf4j
public class VoteService {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CpfIntegration cpfIntegration;

    public Mono<Vote> doVote(Vote vote){
        log.info("[VOTE SERVICE] - doVote in Schedule vote: {}",JsonUtil.convertToJson(vote));

        return scheduleRepository.findById(vote.getIdSchedule())
                .hasElement()
                .flatMap(schedule -> {
                    if (!schedule) {
                        log.info("[ASSOCIATE SERVICE] - schedule {} not exists", vote.getIdAssociate());
                        return Mono.error(new ScheduleNotExistsException(String.format("schedule %1s not exists", vote.getIdSchedule())));
                    }
                    return Mono.just(vote);
                })
                .flatMap(voteVerify -> scheduleRepository.findById(voteVerify.getIdSchedule())
                        .flatMap(schedule -> {
                            if(!Objects.isNull(schedule.getEndDate())){
                                log.info("[ASSOCIATE SERVICE] - schedule is closed at {}", Formatter.dateToString(schedule.getEndDate()));
                                return Mono.error(new ScheduleAlreadyEndedException(String.format("schedule %1s already closed at %2s", schedule.getName(), Formatter.dateToString(schedule.getEndDate()))));
                            }
                            return Mono.just(vote);
                        }))
                .flatMap(voteVerify -> associateRepository.findById(vote.getIdAssociate())
                        .hasElement()
                        .flatMap(bool -> {
                            if(!bool) {
                                log.info("[ASSOCIATE SERVICE] - associate {} not exists", vote.getIdAssociate());
                                return Mono.error(new AssociateNotExistsException(String.format("associate %1s not exists",vote.getIdAssociate())));
                            }
                            return Mono.just(vote);
                        }))
                .flatMap(voteVerify -> voteRepository.findByIdAssociateAndIdSchedule(voteVerify.getIdAssociate(), voteVerify.getIdSchedule())
                        .hasElement()
                        .flatMap(bool -> {
                            if(bool) {
                                log.info("[ASSOCIATE SERVICE] - associate {} already vote to this schedule", vote.getIdAssociate());
                                return Mono.error(new AssociateAlreadyVoteException("associate already vote to this schedule"));
                            }
                            return Mono.just(vote);
                        }))
                .flatMap(voteRepository::save)
                .onErrorResume(Mono::error);
    }
}
