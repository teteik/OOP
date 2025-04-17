package Calculator.src.main.java;

import java.util.List;

public class Push extends Operation {
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        context.push(args.get(1));
    }
}
