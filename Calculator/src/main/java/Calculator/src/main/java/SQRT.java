package Calculator.src.main.java;

import java.util.List;

import static java.lang.Math.sqrt;

public class SQRT extends Operation{
    @Override
    public void execute(CalculatorStack context, List<String> args) {
        double a = context.pop();

        Double c = sqrt(a);
        context.push(c.toString());
    }
}