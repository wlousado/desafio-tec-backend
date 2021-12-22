package br.com.sicred.exception;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;


public class AssociateAlreadyExistsException extends RuntimeException {
    public AssociateAlreadyExistsException(String message){ super(message);}
}
