package Calculator.src.main.java.command;

import Calculator.src.main.java.command.exception.InvalidCountArgumentException;

import java.util.List;

public class Division extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        if (args.size() != 1) {
            throw new InvalidCountArgumentException("Incorrect number of arguments");
        }
        try {
            double a = context.pop();
            if (a == 0) {
                throw new ArithmeticException("");
            }
            double b = context.pop();
            Double c = b / a;
            context.push(c.toString());
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Division by zero!");
        } catch(RuntimeException e) {
        throw new RuntimeException("Argument is not a number! (" + e.getMessage() + ")");
        }
    }
}
