package br.com.sicred.controller.v1.builder;

import br.com.sicred.controller.v1.votes.model.response.VoteResponse;
import br.com.sicred.model.Vote;
import reactor.core.publisher.Mono;

public class VoteBuilder {
    public static Mono<VoteResponse> toMonoResponse(Mono<Vote> doVote) {
        return doVote.map(vote-> VoteResponse.builder()
                .id(vote.getId())
                .vote(vote.getVote())
                .build());
    }
}
