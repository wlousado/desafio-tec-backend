package br.com.sicred.enums;

public enum ScheduleResultEnum {

    TIE(2),
    APROVED(1),
    REPROVED(0);

    private final Integer value;

    ScheduleResultEnum(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
