package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Alfabet {
    //class attributes
    private ArrayList<Character> lletres;
    private String idioma;
    private ArrayList<Integer> textos;
    private ArrayList<Integer> lpfs;


    // ---------------------------------------------------------------------------- //
    //                              Constructora                                    //
    // ---------------------------------------------------------------------------- //
    public Alfabet(String idioma, String lletres_separades_comes) throws Exception {
        this.lletres = obtenir_lletres(lletres_separades_comes);
        this.idioma = idioma;
    }

    // ---------------------------------------------------------------------------- //
    //                                 Getters                                      //
    // ---------------------------------------------------------------------------- //
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

    public ArrayList<Integer> getTextos() {
        return textos;
    }

    public ArrayList<Integer> getLPFs() {
        return lpfs;
    }

    // ---------------------------------------------------------------------------- //
    //                                 Setters                                      //
    // ---------------------------------------------------------------------------- //
    public void setLletres(ArrayList<Character> lletres) {
        this.lletres = lletres;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    // ---------------------------------------------------------------------------- //
    //                       Private class methods                                  //
    // ---------------------------------------------------------------------------- //
    // Obte un ArrayList de lletres a partir d'un String de lletres separades per comes.
    private ArrayList<Character> obtenir_lletres(String lletres_separades_comes) throws Exception {
        String[] lletres_separades = lletres_separades_comes.split(",");
        ArrayList<Character> lletres = new ArrayList<Character>();

        for (String s : lletres_separades) {
            if (s.length() > 1) throw new Exception("ERROR: les lletres només poden tenir un caràcter");
            if (s.length() != 0) lletres.add(s.charAt(0));
        }

        if (lletres.isEmpty()) throw new Exception("ERROR: l'alfabet ha de tenir almenys una lletra");

        // Verifica si hi ha lletres repetides.
        if(lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduït lletres repetides");
        return lletres;
    }

    // Comprova si hi ha lletres repetides a l'alfabet.
    private boolean lletres_repetides(ArrayList<Character> lletres) {
        HashSet<Character> set = new HashSet<Character>(lletres);
        return set.size() != lletres.size();
    }


    // ---------------------------------------------------------------------------- //
    //                       Public class methods                                  //
    // ---------------------------------------------------------------------------- //
    // Crear un text a partir d'un String.
    public void afegirText(Integer idText) throws Exception {
        if (textos.contains(idText)) throw new Exception("ERROR: el text ja pertany a l'alfabet");
        textos.add(idText);
    }

    public void afegirLPF(Integer idLPF) throws Exception {
        if (lpfs.contains(idLPF)) throw new Exception("ERROR: la LPF ja pertany a l'alfabet");
        lpfs.add(idLPF);
    }

    // Eliminar un text de l'alfabet.
    public void eliminarText(Integer idText) throws Exception {
        if (textos.contains(idText)) throw new Exception("ERROR: el text no pertany a l'alfabet");
        textos.remove(idText);
    }

    // Eliminar una LPF de l'alfabet.
    public void eliminarLPF(Integer idLPF) throws Exception {
        if (lpfs.contains(idLPF)) throw new Exception("ERROR: la LPF no pertany a l'alfabet");
        lpfs.remove(idLPF);
    }
}