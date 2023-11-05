package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Alfabet {
    //class attributes
    private ArrayList<Character> lletres;
    private String idioma;
    private ArrayList<Text> textos;
    private ArrayList<LPF> lpfs;

    //constructora
    public Alfabet(String idioma, ArrayList<Character> lletres) throws Exception {
        if (lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduït lletres repetides");
        this.lletres = lletres;
        this.idioma = idioma;
    }

    public Alfabet(String idioma, String lletres_separades_comes) throws Exception {
        this.lletres = obtenir_lletres(lletres_separades_comes);
        this.idioma = idioma;
    }

    //getters
    public ArrayList<Character> getCaracters() {
        return lletres;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getNumLletres() {
        return lletres.size();
    }

    public ArrayList<Text> getTextos() {
        return textos;
    }

    public ArrayList<LPF> getLPFs() {
        return lpfs;
    }

    //setters
    public void setLletres(ArrayList<Character> lletres) {
        this.lletres = lletres;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    // private class methods

    // Obte un ArrayList de lletres a partir d'un String de lletres separades per comes.
    private ArrayList<Character> obtenir_lletres(String lletres_separades_comes) throws Exception {
        String[] lletres_separades = lletres_separades_comes.split(",");
        ArrayList<Character> lletres = new ArrayList<Character>();

        for (String s : lletres_separades) {
            if (s.length() != 1) throw new Exception("ERROR: les lletres només poden tenir un caràcter");
            lletres.add(s.charAt(0));
        }

        // Verifica si hi ha lletres repetides.
        if(lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduït lletres repetides");
        return lletres;
    }

    // Comprova si hi ha lletres repetides a l'alfabet.
    private boolean lletres_repetides(ArrayList<Character> lletres) {
        HashSet<Character> set = new HashSet<Character>(lletres);
        return set.size() != lletres.size();
    }

    // public class methods

    // Afegeix un caracter addicional a l'alfabet.
    public void afegirCaracter(Character caracter) {
        lletres.add(caracter);
    }

    // Crear un text a partir d'un String.
    public Text crearText(String text) {
        Text t = new Text(text);
        textos.add(t);
        return t;
    }

    // Crear una LPF a partir d'un HashMap<String, int>.
    public LPF crearLPF(HashMap<String, Integer> lpf) {
        LPF l = new LPF(lpf);
        lpfs.add(l);
        return l;
    }

    // Eliminar un text de l'alfabet.
    public void eliminarText(Text text) {
        textos.remove(text);
    }

    // Eliminar una LPF de l'alfabet.
    public void eliminarLPF(LPF lpf) {
        lpfs.remove(lpf);
    }

}