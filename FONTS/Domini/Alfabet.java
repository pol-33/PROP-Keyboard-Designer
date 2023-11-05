package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Alfabet {
    //class attributes
    private ArrayList<String> lletres;
    private String idioma;

    //constructora
    public Alfabet(String idioma, ArrayList<String> lletres) throws Exception {
        if (lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduït lletres repetides");
        this.lletres = lletres;
        this.idioma = idioma;
    }

    public Alfabet(String idioma, String lletres_separades_comes) throws Exception {
        this.lletres = obtenir_lletres(lletres_separades_comes);
        this.idioma = idioma;
    }

    //getters
    public ArrayList<String> getCaracters() {
        return lletres;
    }

    public String getIdioma() {
        return idioma;
    }

    //setters
    public void setLletres(ArrayList<String> lletres) {
        this.lletres = lletres;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    //class methods

    // Afegeix un caracter addicional a l'alfabet.
    public void afegirCaracter(String caracter) {
        lletres.add(caracter);
    }

    // Crea un ArrayList de lletres a partir d'un String de lletres separades per comes.
    private ArrayList<String> obtenir_lletres(String lletres_separades_comes) throws Exception {
        String[] lletres_separades = lletres_separades_comes.split(",");
        ArrayList<String> lletres = new ArrayList<String>(Arrays.asList(lletres_separades));

        // Verifica si hi ha lletres repetides.
        if(lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduït lletres repetides");
        return lletres;
    }

    // Comprova si hi ha lletres repetides a l'alfabet.
    private boolean lletres_repetides(ArrayList<String> lletres) {
        HashSet<String> set = new HashSet<String>(lletres);
        return set.size() != lletres.size();
    }

    public void add(Alfabet noualfabet) {

    }
}