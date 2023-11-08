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
        if (lletres.size() < 1) throw new Exception("ERROR: l'alfabet ha de tenir almenys una lletra");
        this.lletres = lletres;
        this.idioma = idioma;
    }

    public Alfabet(String idioma, String lletres_separades_comes) throws Exception {
        if (lletres.size() < 1) throw new Exception("ERROR: l'alfabet ha de tenir almenys una lletra");
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
        // obtenir numero textos a entrades
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
            if (s.length() != 1 && s != null) throw new Exception("ERROR: les lletres només poden tenir un caràcter");
            if (s != null) lletres.add(s.charAt(0));
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

    // Crear un text a partir d'un String.
    public void crearText(String nom, String text) throws Exception {
        Text t = new Text(nom, lletres, text);
        HashMap<String, Integer> lpf_map = t.getLPF();
        if (lletres_no_contingudes(lpf_map)) throw new Exception("ERROR: el text conté lletres no contingudes a l'alfabet");
        textos.put(nom, t);
        return;
    }

    // Crear una LPF a partir d'un HashMap<String, int>.
    public void crearLPF(String nom, HashMap<String, Integer> lpf) throws Exception {
        if (lletres_no_contingudes(lpf)) throw new Exception("ERROR: la LPF conté lletres no contingudes a l'alfabet");
        LPF l = new LPF(nom, lletres, lpf);
        lpfs.put(nom, l);
        return;
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

    // Imprimir les lletres de l'alfabet.
    public void imprimirLletres() {
        // Imprimir la primera lletra
        System.out.print(lletres.get(0));

        // Imprimir les lletres restants
        for (int i = 1; i < lletres.size(); i++) {
            System.out.print("," + lletres.get(i));
        }
    }

    // Imprimir textos de l'alfabet.
    public void imprimirTextos() {
        // Imprimir textos de l'alfabet.
        for (Text t : textos.values()) {
            t.imprimirEntrada();
        }
    }

    // Imprimir LPFs de l'alfabet.
    public void imprimirLPFs() {
        // Imprimir textos de l'alfabet.
        for (LPF f : lpfs.values()) {
            f.imprimirEntrada();
        }
    }

}