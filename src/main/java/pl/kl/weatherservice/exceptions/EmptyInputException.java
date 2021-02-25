package pl.kl.weatherservice.exceptions;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}
