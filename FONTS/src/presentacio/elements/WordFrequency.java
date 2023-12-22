package presentacio.elements;

public class WordFrequency {
    private String word;
    private Integer frequency;

    public WordFrequency(String word, Integer frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public Integer getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return word + "    " + frequency;
    }
}
