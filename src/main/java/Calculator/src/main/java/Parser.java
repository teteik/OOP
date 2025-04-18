package Calculator.src.main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private BufferedReader reader;

    public Parser() {
        InputStream in = Parser.class.getResourceAsStream("/input.txt");
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public Parser(String pathFile) {
        InputStream in = Parser.class.getResourceAsStream(pathFile);
        if (in == null) {
            throw new IllegalArgumentException("File '" + pathFile + "' not found.");
        }
        try {
            reader = new BufferedReader(new InputStreamReader(in));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize parser for file: " + pathFile, e);
        }
    }

    public List<String> parseLine() {
        List<String> instruction = new ArrayList<>();
        String line;
        try {
            if ((line = reader.readLine()) != null) {
                boolean comment = false;
                Reader parseReader = new StringReader(line);
                StringBuilder stringBuilder = new StringBuilder();
                int symbol;
                while ((symbol = parseReader.read()) != -1) {
                    char ch = (char) symbol;
                    if (ch != ' ' && ch != '#' && ch != '\n' && !comment) {
                        stringBuilder.append(ch);
                    } else if (stringBuilder.length() > 0) {
                        String word = stringBuilder.toString();
                        stringBuilder.setLength(0);
                        instruction.add(word);
                    }
                    if (ch == '#') {
                        comment = true;
                    }
                }
                if (stringBuilder.length() > 0) {
                    String word = stringBuilder.toString();
                    stringBuilder.setLength(0);
                    instruction.add(word);
                }
                if (instruction.isEmpty()) {
                    instruction.add("#");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading input: " + e.getMessage(), e);
        }
        return instruction;
    }
}
