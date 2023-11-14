package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Alfabet {
    //class attributes
    private ArrayList<Character> lletres;
    private String idioma;
    private Integer idAlfabet;
    private ArrayList<Integer> idEntrades;


    // ---------------------------------------------------------------------------- //
    //                              Constructora                                    //
    // ---------------------------------------------------------------------------- //
    public Alfabet(String idioma, Integer idAlfabet, String lletres_separades_comes) throws Exception {
        this.idioma = idioma;
        this.idAlfabet = idAlfabet;
        this.lletres = obtenir_lletres(lletres_separades_comes);
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
    public Integer getIdAlfabet() {
        return idAlfabet;
    }

    public ArrayList<Integer> getEntrades() {
        return idEntrades;
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
    // Obte un ArrayList de lletres a partir d'un String no buit de lletres no repetides separades per comes.
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
    //                       Public class methods                                   //
    // ---------------------------------------------------------------------------- //
    // Associar una entrada a l'alfabet.
    public void associarEntrada(Integer idEntrada) throws Exception {
        if (idEntrades.contains(idEntrada)) throw new Exception("ERROR: l'entrada ja pertany a l'alfabet");
        idEntrades.add(idEntrada);
    }

    // Desvincular una entrada de l'alfabet.
    public void desvincularEntrada(Integer idEntrada) throws Exception {
        if (!idEntrades.contains(idEntrada)) throw new Exception("ERROR: l'entrada no pertany a l'alfabet");
        idEntrades.remove(idEntrada);
    }

    public void afegirLletra(Character lletra) throws Exception {
        if (lletra == null) throw new Exception("ERROR: la lletra no pot ser null");
        if (lletres.contains(lletra)) throw new Exception("ERROR: la lletra ja pertany a l'alfabet");
        lletres.add(lletra);
    }
}