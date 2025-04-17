package Calculator.src.main.java;

import java.util.List;

public class Division extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        double a = context.pop();
        double b = context.pop();
        if (a == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        Double c = b / a;
        context.push(c.toString());
    }
}