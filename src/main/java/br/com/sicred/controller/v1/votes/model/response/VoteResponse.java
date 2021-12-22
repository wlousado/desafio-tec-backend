package br.com.sicred.controller.v1.votes.model.response;

import br.com.sicred.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteResponse {
    private String id;
    private VoteEnum vote;
}
