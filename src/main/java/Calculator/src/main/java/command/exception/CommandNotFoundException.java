package Calculator.src.main.java.command.exception;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(String message) {
    super(message);
  }
}
