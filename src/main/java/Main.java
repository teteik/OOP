import Calculator.src.main.java.Calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        if (args.length == 0) {
            calculator.run(null);
            return;
        }
        calculator.run(args[0]);
    }
}
