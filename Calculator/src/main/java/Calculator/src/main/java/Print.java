package Calculator.src.main.java;

import java.util.List;

public class Print extends Operation {
    @Override
    public void execute (CalculatorStack context, List<String> args) {
        context.print();
    }
}
