package Calculator.src.main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Parser {
    private BufferedReader reader;

    public Parser() {
        InputStream in = Parser.class.getResourceAsStream("/input.txt");
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public Parser(String pathFile) {
        InputStream in = Parser.class.getResourceAsStream(pathFile);
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public List<String> parseLine() {
        List<String> instruction = new ArrayList<>();


        String line;
        try {
            if((line = reader.readLine()) != null) {
                boolean comment = false;
                Reader parseReader = new StringReader(line);
                StringBuilder stringBuilder = new StringBuilder();
                int symbol;
                while ((symbol = parseReader.read()) != -1) {
                    char ch = (char) symbol;
                    if (ch != ' ' && ch != '#' && ch != '\n' && !comment) {
                        stringBuilder.append(ch);
                    }
                    else if (stringBuilder.length() > 0) {
                        String word = stringBuilder.toString();
                        stringBuilder.setLength(0);
                        instruction.add(word);
                        System.out.println(word);
                    }
                    if (ch == '#' || (ch == '\n' && comment)) {
                        comment = !comment;
                    }
                }
                if (stringBuilder.length() > 0) {
                    String word = stringBuilder.toString();
                    stringBuilder.setLength(0);
                    instruction.add(word);
                    System.out.println(word);
                }
                if (instruction.isEmpty()) {
                    instruction.add("#");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return instruction;
    }
}
