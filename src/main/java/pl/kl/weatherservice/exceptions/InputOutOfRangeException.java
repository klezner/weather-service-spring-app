package pl.kl.weatherservice.exceptions;

public class InputOutOfRangeException extends RuntimeException {
    public InputOutOfRangeException(String message) {
        super(message);
    }
}
