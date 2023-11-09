package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Text extends Entrada{
    private String text;

    // Constructora
    public Text(String nom, ArrayList<Character> lletres, String text) throws Exception {
        if(text == null) throw new Exception("ERROR: El text del text no pot ser null");
        this.nom = nom;
        this.text = text;
        calculateLPF();
        this.teclat = creaTeclat(lletres);
    }

    // Mètode per obtindre el text del text
    public String getText() {
        return text;
    }

    // Mètode per modificar el contingut del text
    public void setText(String nouText) throws Exception {
        if(nouText == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.text = nouText;
        calculateLPF();
    }

    // Mètode per afegir més contingut al text
    public void appendText(String additionalContent) throws Exception {
        if(additionalContent == null) throw new IllegalArgumentException("ERROR: El contingut adicioanl del text no pot ser null");
        this.text += additionalContent;
        calculateLPF();
    }

    // Mètode per obtindre la quantitat de lletres totals del text
    public int length() {
        return this.text.length();
    }

    // Mètode per mostrar el contingut del text
    public void imprimirEntrada() {
        System.out.println("Nom: " + this.nom);
        System.out.println(text);
    }

    //Funcio que calcula l'atribut lpf en funció del contigut del text
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
