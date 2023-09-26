package main.service;

public class DeathException extends RuntimeException {
    public DeathException(String message) {
        super(message);
    }
}
