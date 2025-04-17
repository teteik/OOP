package Calculator.src.main.java;

import java.util.List;

public class Define extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        if (args.size() != 3) {

        }
        context.define(args);
    }
}
