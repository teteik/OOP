package Calculator.src.main.java.command;

import Calculator.src.main.java.command.exception.InvalidCountArgumentException;

import java.util.List;

public class Addition extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        if (args.size() != 1) {
            throw new InvalidCountArgumentException("Incorrect number of arguments");
        }
        try {
            double a = context.pop();
            double b = context.pop();
            Double c = a + b;
            context.push(c.toString());
        } catch(RuntimeException e) {
            throw new RuntimeException("Argument is not a number! (" + e.getMessage() + ")");
        }
    }
}