package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Alfabet {
    //class attributes
    private ArrayList<Character> lletres;
    private String idioma;
    private HashMap<String, Text> textos;
    private HashMap<String, LPF> lpfs;

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
    public ArrayList<Character> getLletres() {
        return lletres;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getNumLletres() {
        return lletres.size();
    }

    public int getNumTextos() {
        return textos.size();
    }

    public int getNumLPFs() {
        return lpfs.size();
    }

    public Text getText(String nom) throws Exception {
        Text text = textos.get(nom);
        if (text == null) throw new Exception("ERROR: no existeix cap text amb aquest nom");
        return text;
    }

    public LPF getLPF(String nom) throws Exception {
        LPF lpf = lpfs.get(nom);
        if (lpf == null) throw new Exception("ERROR: no existeix cap LPF amb aquest nom");
        return lpf;
    }

    public HashMap<String, Text> getTextos() {
        return textos;
    }

    public HashMap<String, LPF> getLPFs() {
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

    // Comprova si hi ha lletres d'una LPF no contingudes a l'alfabet.
    private boolean lletres_no_contingudes(HashMap<String, Integer> lpf_map) {
        for (String paraula : lpf_map.keySet()) {
            for (int i = 0; i < paraula.length(); i++) {
                if (!lletres.contains(paraula.charAt(i))) return true;
            }
        }
        return false;
    }

    // public class methods

    // Afegeix un caracter addicional a l'alfabet.
    public void afegirCaracter(Character caracter) {
        lletres.add(caracter);
    }

    // Crear un text a partir d'un String.
    public Text crearText(String nom, String text) throws Exception {
        Text t = new Text(nom, text);
        HashMap<String, Integer> lpf_map = t.getLPF();
        if (lletres_no_contingudes(lpf_map)) throw new Exception("ERROR: el text conté lletres no contingudes a l'alfabet");
        textos.put(nom, t);
        return t;
    }

    // Crear una LPF a partir d'un HashMap<String, int>.
    public LPF crearLPF(String nom, HashMap<String, Integer> lpf) throws Exception {
        if (lletres_no_contingudes(lpf)) throw new Exception("ERROR: la LPF conté lletres no contingudes a l'alfabet");
        LPF l = new LPF(nom, lpf);
        lpfs.put(nom, l);
        return l;
    }

    // Eliminar un text de l'alfabet.
    public void eliminarText(String nom) {
        // eliminar Text amb nom = nom de textos
        textos.remove(nom);
    }

    // Eliminar una LPF de l'alfabet.
    public void eliminarLPF(String nom) {
        lpfs.remove(nom);
    }

}