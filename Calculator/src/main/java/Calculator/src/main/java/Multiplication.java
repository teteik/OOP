package Calculator.src.main.java;

import java.util.List;

public class Multiplication extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        double a = context.pop();
        double b = context.pop();
        Double c = a * b;
        context.push(c.toString());
    }
}