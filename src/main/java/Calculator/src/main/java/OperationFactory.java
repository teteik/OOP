package Calculator.src.main.java;

import Calculator.src.main.java.command.Operation;
import Calculator.src.main.java.command.exception.CommandNotFoundException;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class OperationFactory {
    public Operation getOperation(List<String> instruction) {
        InputStream property = OperationFactory.class.getResourceAsStream("/Command-Class_Correspondence.txt");
        Scanner scanner = new Scanner(property);
        Operation operation;
        String path = "";

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(" ");

            if (line[0].equals(instruction.get(0))) {
                path = line[1];
                break;
            }
        }
        if (path == "") throw new CommandNotFoundException("No such command");

        try {
            return operation = (Operation) Class.forName(path).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
