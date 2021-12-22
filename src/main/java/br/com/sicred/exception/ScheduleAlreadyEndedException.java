package br.com.sicred.exception;

public class ScheduleAlreadyEndedException extends RuntimeException {
    public ScheduleAlreadyEndedException(String message) {
        super(message);
    }
}
