package br.com.sicred.enums;

public enum VoteEnum {

    SIM(1),
    NAO(0);

    private final Integer valueVote;

    VoteEnum(int value) {
        this.valueVote = value;
    }

    public Integer getValuevote(){
        return this.valueVote;
    }
}
