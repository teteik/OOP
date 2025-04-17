package Calculator.src.main.java;

import java.util.List;

public class Subtraction extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        double a = context.pop();
        double b = context.pop();
        Double c = b - a;
        context.push(c.toString());
    }
}
