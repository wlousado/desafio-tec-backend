package br.com.sicred.exception;

public class ScheduleNotExistsException extends RuntimeException {
    public ScheduleNotExistsException(String message) {
        super(message);
    }
}
