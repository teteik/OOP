package Calculator.src.main.java;

import java.util.List;

public abstract class Operation {
    protected abstract void execute (CalculatorStack context, List<String> args);
}
