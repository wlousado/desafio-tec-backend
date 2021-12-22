package br.com.sicred.controller.v1.votes;

import br.com.sicred.controller.v1.builder.VoteBuilder;
import br.com.sicred.controller.v1.votes.model.response.VoteResponse;
import br.com.sicred.enums.VoteEnum;
import br.com.sicred.model.Vote;
import br.com.sicred.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class VoteControllerFacade {

    @Autowired
    private VoteService voteService;

    public Mono<VoteResponse> doVote(String idAssociate, String idSchedule, VoteEnum vote) {
        Vote newVote = Vote.builder()
                .idAssociate(idAssociate)
                .idSchedule(idSchedule)
                .vote(vote)
                .build();
        return VoteBuilder.toMonoResponse(voteService.doVote(newVote));
    }


    //public Mono<VoteResponse> doVote(String idAssociate, String idSchedule, VoteEnum vote) {
    //    return VoteBuilder.toMonoResponse(voteService.doVote(idSchedule, new Vote(idAssociate, vote)));
    //}


}
