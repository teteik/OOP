package Calculator.src.main.java.command;

import java.util.List;

public abstract class Operation {
    public abstract void execute(CalculatorStack context, List<String> args);
}
