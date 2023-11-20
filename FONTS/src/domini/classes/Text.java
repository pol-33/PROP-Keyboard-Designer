package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Text. Representa un text associat a una entrada, amb contingut textual i associacions a alfabet i teclats.
 */
public class Text extends Entrada{

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String text;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructora de Text.
     * @param nomEntrada Nom de l'entrada associada al text
     * @param id Identificador del text
     * @param lletres Llista de caràcters (pot ser utilitzada per funcionalitats específiques)
     * @param contingutEntrada Contingut textual del text
     * @param idAlfabet Identificador de l'alfabet associat al text
     * @throws Exception Si el contingut del text és null
     */
    public Text(String nomEntrada, Integer id, ArrayList<Character> lletres, String contingutEntrada, Integer idAlfabet) throws Exception {
        if(contingutEntrada == null) throw new Exception("ERROR: El contingut del text no pot ser nul");
        this.nom = nomEntrada;
        this.id = id;
        this.text = contingutEntrada;
        this.idAlfabet = idAlfabet;
        this.idTeclats = new ArrayList<>();
        calculateLPF();
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     * Retorna el contingut del text.
     * @return String amb el contingut del text.
     */
    public String getText() {
        return text;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //

    /**
     * Estableix o modifica el contingut del text.
     * @param nouText Nou contingut textual a establir.
     * @throws IllegalArgumentException Si el nou text és null.
     */
    public void setText(String nouText) throws Exception {
        if (nouText == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.text = nouText;
        calculateLPF();
    }

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //


    /**
     * Calcula la Freqüència de les Paraules (LPF) del contingut textual.
     */
    private void calculateLPF() {
        //Converteix el text en un array de Strings (totes les paraules)
        String[] paraules = this.text.split(" ");

        HashMap<String, Integer> lpf = new HashMap<>();

        for (String paraula : paraules) {
            // Neteja la paraula de signes de puntuació y la converteix minúscules
            paraula = paraula.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (!paraula.isEmpty()) { //ignorem les paraules buides
                //si la paraula encara no té frequencia li assigna frequencia 1, sino li assigna el valor que tenia +1
                lpf.merge(paraula, 1, Integer::sum);
            }
        }
        this.lpf = lpf;
    }
}
