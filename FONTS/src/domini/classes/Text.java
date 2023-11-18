package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Text extends Entrada{
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String text;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //
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
    public String getText() {
        return text;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //
    public void setText(String nouText) throws Exception {
        if (nouText == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.text = nouText;
        calculateLPF();
    }

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //
    private void calculateLPF() {
        //Converteix el text en un array de Strings (totes les paraules)
        String[] paraules = this.text.split(" ");

        HashMap<String, Integer> lpf = new HashMap<>();

        for (String paraula : paraules) {
            // Neteja la paraula de signes de puntuació y la converteix minúsculas
            paraula = paraula.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (!paraula.isEmpty()) { //ignorem les paraules buides
                //si la paraula encara no té frequencia li assigna frequencia 1, sino li assigna el valor que tenia +1
                lpf.merge(paraula, 1, Integer::sum);
            }
        }
        this.lpf = lpf;
    }


}
