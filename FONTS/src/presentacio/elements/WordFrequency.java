package presentacio.elements;

/**
 * Classe WordFrequency. Representa una paraula i la seva freqüència.
 */
public class WordFrequency {
    private String word;
    private Integer frequency;

    /**
     * Constructora de la classe WordFrequency.
     * @param word Paraula.
     * @param frequency Freqüència.
     */
    public WordFrequency(String word, Integer frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /**
     * Obté la paraula.
     * @return La paraula.
     */
    public String getWord() {
        return word;
    }

    /**
     * Obté la freqüència.
     * @return La freqüència.
     */
    public Integer getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return word + "    " + frequency;
    }
}
