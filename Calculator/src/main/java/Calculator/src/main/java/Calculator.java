package Calculator.src.main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws IOException {
        OperationFactory factory = new OperationFactory();
        factory.calculate();
    }
}
