package utils;

import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";

    public static void saveScore(String name, int score) {
        if (score <= 0) return; // Не сохраняем нулевые очки

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(name + "," + score + "\n");
        } catch (IOException e) {
            System.err.println("Не удалось сохранить рекорд.");
        }
    }

    public static List<ScoreEntry> loadScores() {
        List<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 2 && !parts[0].isEmpty() && isNumeric(parts[1])) {
                    int scoreValue = Integer.parseInt(parts[1]);
                    scores.add(new ScoreEntry(parts[0], scoreValue));
                }
            }
        } catch (Exception ignored) {}

        // Сортируем по убыванию счёта
        scores.sort((a, b) -> Integer.compare(b.score, a.score));

        // Ограничиваем до топ-10
        return scores.size() > 10 ? scores.subList(0, 10) : scores;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static class ScoreEntry {
        public final String name;
        public final int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + " — " + score;
        }
    }
}