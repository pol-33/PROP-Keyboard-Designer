package Domini;

import java.util.HashMap;

public class Text implements Entrada{
    private String nom;

    // Nom text
    private String contingut;
    private HashMap<String, Integer> lpf;

    // Constructor
    public Text(String nom, String contingut) throws Exception {
        if(contingut == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.nom = nom;
        this.contingut = contingut;
        calculateLPF();
    }

    // Mètode per obtindre el contingut del text
    public String getContingut() {
        return contingut;
    }

    // Mètode per modificar el contingut del text
    public void setContingut(String nouContingut) throws Exception {
        if(nouContingut == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.contingut = nouContingut;
        calculateLPF();
    }

    // Mètode per afegir més contingut al text
    public void appendContingut(String additionalContent) throws Exception {
        if(additionalContent == null) throw new IllegalArgumentException("ERROR: El contingut adicioanl del text no pot ser null");
        this.contingut += additionalContent;
        calculateLPF();
    }

    // Mètode per obtindre la longitud del text
    public int length() {
        return this.contingut.length();
    }

    // Mètode per fer print del contingut del text
    public void display() {
        System.out.println(contingut);
    }

    public HashMap<String, Integer> getLPF() {
        return this.lpf;
    }

    //Funcio que calcula l'atribut lpf en funció del contigut del text
    private void calculateLPF() {
        //Converteix el text en un array de Strings (totes les paraules)
        String[] paraules = this.contingut.split(" ");

        HashMap<String, Integer> lpf = new HashMap<>();

        for (String paraula: paraules) {
            // Neteja la paraula de signes de puntuació y la converteix minúsculas
            paraula = paraula.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (!paraula.isEmpty()) { //ignorem les paraules buides
                //si la paraula encara no té frequencia li assigna frequencia 1, sino li assigna el valor que tenia +1
                this.lpf.merge(paraula, 1, Integer::sum);
            }
        }

        this.lpf = lpf;
    }


}
