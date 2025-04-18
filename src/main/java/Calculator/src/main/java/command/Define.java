package Calculator.src.main.java.command;

import Calculator.src.main.java.command.exception.InvalidCountArgumentException;

import java.util.List;

public class Define extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        if (args.size() != 3) {
            throw new InvalidCountArgumentException("Incorrect number of arguments");
        }
        context.define(args);
    }
}