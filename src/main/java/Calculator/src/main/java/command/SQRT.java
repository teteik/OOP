package Calculator.src.main.java.command;

import Calculator.src.main.java.command.exception.InvalidCountArgumentException;

import java.util.List;

import static java.lang.Math.sqrt;

public class SQRT extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        if (args.size() != 1) {
            throw new InvalidCountArgumentException("Incorrect number of arguments");
        }
        try {
            double a = context.pop();
            Double c = sqrt(a);
            context.push(c.toString());
        } catch(RuntimeException e) {
            throw new RuntimeException("Argument is not a number! (" + e.getMessage() + ")");
        }
    }
}
