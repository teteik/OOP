package Calculator.src.main.java;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class OperationFactory {
    private static final Logger logger = Logger.getLogger(OperationFactory.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/calculator.log", true); // true - добавлять к существующему файлу
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
        }
    }

    private static String getOperation(String operation) {
        InputStream property = OperationFactory.class.getResourceAsStream("/Command-Class_Correspondence.txt");
        Scanner scanner = new Scanner(property);

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(" ");

            if (line[0].equals(operation)) {
                return line[1];
            }
        }
        throw new CommandNotFoundException("No such command");
    }

    public void calculate() {
        Parser parser = new Parser();
        Operation operation;
        List<String> instruction = parser.parseLine();
        CalculatorStack stack = new CalculatorStack();

        while (!instruction.isEmpty()) {
            if (instruction.get(0).equals("#")) {
                instruction.clear();
                instruction = parser.parseLine();
                continue;
            }
            String path = getOperation(instruction.get(0));
            try {
                operation = (Operation) Class.forName(path).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            operation.execute(stack, instruction);
//            if (instruction.size() > 3) {
//                throw new InvalidCountArgumentException("Too many arguments!");
//            }
            String fullCommand = String.join(" ", instruction);
            logger.info("Executed command: " + fullCommand);
            instruction.clear();
            instruction = parser.parseLine();
        }
    }
}