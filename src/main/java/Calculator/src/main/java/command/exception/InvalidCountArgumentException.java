package Calculator.src.main.java.command.exception;

public class InvalidCountArgumentException extends RuntimeException {
    public InvalidCountArgumentException(String message) {
        super(message);
    }
}
