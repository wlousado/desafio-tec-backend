package br.com.sicred.integration.cpf.enums;

public enum EnumCpf {

    UNABLE_TO_VOTE(0),
    ABLE_TO_VOTE(1);

    private final Integer value;

    EnumCpf(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
