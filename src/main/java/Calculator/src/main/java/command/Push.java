package Calculator.src.main.java.command;

import Calculator.src.main.java.command.exception.InvalidCountArgumentException;

import java.util.List;

public class Push extends Operation {
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        if (args.size() != 2) {
            throw new InvalidCountArgumentException("Incorrect number of arguments");
        }
        context.push(args.get(1));
    }
}