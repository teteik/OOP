import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nsu.fit.Artemev.oop.lab1.WordFrequency;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter the filename next time");
            return;
        }

        String filename = args[0];
        Map<String, WordFrequency> wordFrequencyMap = new HashMap<>();

        try {
            Reader reader = new InputStreamReader(new FileInputStream(filename));
            StringBuilder stringBuilder = new StringBuilder();
            int symbol;
            while ((symbol = reader.read()) != -1) {
                char ch = (char) symbol;
                if (Character.isLetterOrDigit(ch)) {
                    stringBuilder.append(ch);
                }
                else if (stringBuilder.length() > 0) {
                    String word = stringBuilder.toString().toLowerCase();
                    wordFrequencyMap.putIfAbsent(word, new WordFrequency(word));
                    wordFrequencyMap.get(word).increase();
                    stringBuilder.setLength(0);
                }
            }
            if (stringBuilder.length() > 0) {
                String word = stringBuilder.toString().toLowerCase();
                wordFrequencyMap.putIfAbsent(word, new WordFrequency(word));
                wordFrequencyMap.get(word).increase();
                stringBuilder.setLength(0);
            }

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<WordFrequency> wordFrequencies = new ArrayList<>(wordFrequencyMap.values());
        wordFrequencies.sort((wf1, wf2) -> Integer.compare(wf2.getFrequency(), wf1.getFrequency()));

        // Подсчет общего количества слов
        int totalWords = wordFrequencies.stream().mapToInt(wf -> wf.getFrequency()).sum();

        // Запись в CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.csv"))) {
            writer.println("Слово;Частота;Частота (%)");
            for (WordFrequency wf : wordFrequencies) {
                double percentage = (double) wf.getFrequency() / totalWords * 100;
                writer.printf("%s;%d;%.2f%%%n", wf.getWord(), wf.getFrequency(), percentage);
            }
        } catch (IOException e) {
            System.err.println("Error while writing to CSV file: " + e.getLocalizedMessage());
        }
    }
}