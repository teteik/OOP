package nsu.fit.Artemev.oop.lab1;

public class WordFrequency {
    private String word;
    private int frequency;

    public WordFrequency(String word) {
        this.word = word;
        this.frequency = 0;
    }

    public int getFrequency() {
        return frequency;
    }
    public String getWord() {
        return word;
    }

    public void increase() {
        this.frequency++;
    }

    @Override
    public boolean equals(Object obj) {
        return this.word.equalsIgnoreCase((String) obj);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
