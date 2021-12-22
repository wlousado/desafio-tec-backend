package br.com.sicred.exception;

public class AssociateAlreadyVoteException extends RuntimeException {
    public AssociateAlreadyVoteException(String message) {
        super(message);
    }
}
