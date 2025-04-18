package Calculator.src.main.java;

import Calculator.src.main.java.command.CalculatorStack;
import Calculator.src.main.java.command.Operation;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Calculator {
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/calculator.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
        }
    }

    public void run(String args) {
        OperationFactory factory = new OperationFactory();
        CalculatorStack stack = new CalculatorStack();
        Parser parser = null;
        if(args == null) {
            parser = new Parser();
        }
        else {
            try {
                parser = new Parser(args);
            } catch (IllegalArgumentException e) {
                logger.severe("Error: " + e.getMessage());
                System.err.println(e.getMessage());
                return;
            }
        }
        List<String> instruction = parser.parseLine();

        logger.info("Starting calculator execution");

        boolean hasError = false;

        while (!instruction.isEmpty()) {
            if (instruction.get(0).equals("#")) {
                instruction.clear();
                instruction = parser.parseLine();
                continue;
            }

            try {
                Operation operation = factory.getOperation(instruction);
                operation.execute(stack, instruction);

                String fullCommand = String.join(" ", instruction);
                logger.info("Executed command: " + fullCommand);
            } catch (RuntimeException e) {
                String fullCommand = String.join(" ", instruction);
                logger.severe("Error executing command: " + fullCommand + " - " + e.getMessage());
                System.err.println("The command failed: " + fullCommand + " - " + e.getMessage());
                hasError = true;
            } catch (Exception e) {
                logger.severe("Unexpected error during execution: " + e.getMessage());
                System.err.println("Unexpected error: " + e.getMessage());
                hasError = true;
            } finally {
                instruction.clear();
                instruction = parser.parseLine();
            }
        }

        if (hasError) {
            logger.severe("Calculator execution completed with errors");
        } else {
            logger.info("Calculator execution completed successfully");
        }
    }
}
