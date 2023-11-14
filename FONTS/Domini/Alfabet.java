package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Classe Alfabet. Representa l'alfabet d'un idioma, as a dir, un conjunt de lletres no repetides d'un sol caracter.
 */

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

    /**
     * Retorna les lletres de l'alfabet.
     * @return ArrayList amb les lletres de l'alfabet.
     */
    public ArrayList<Character> getLletres() {
        return lletres;
    }

    /**
     * Retorna l'idioma de l'alfabet.
     * @return String amb l'idioma de l'alfabet.
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Retorna l'identificador de l'alfabet.
     * @return Integer amb l'identificador de l'alfabet.
     */
    public Integer getIdAlfabet() {
        return idAlfabet;
    }

    /**
     * Retorna els identificadors de les entrades associades a l'alfabet.
     * @return ArrayList amb els identificadors de les entrades associades a l'alfabet.
     */
    public ArrayList<Integer> getEntrades() {
        return idEntrades;
    }


    // ---------------------------------------------------------------------------- //
    //                                 Setters                                      //
    // ---------------------------------------------------------------------------- //
    /**
     * Modifica l'idioma de l'alfabet.
     * @param idioma Nou idioma de l'alfabet.
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    // ---------------------------------------------------------------------------- //
    //                       Private class methods                                  //
    // ---------------------------------------------------------------------------- //

    /**
     * Obte un ArrayList de lletres a partir d'un String no buit de lletres no repetides separades per comes.
     * @param lletres_separades_comes String no buit de lletres no repetides separades per comes.
     * @return ArrayList de lletres a partir d'un String no buit de lletres no repetides separades per comes.
     * @throws Exception Si les lletres son de multiples caracters
     * @throws Exception Si l'alfabet no ta cap lletra
     * @throws Exception Si s'han introduit lletres repetides
     */
    private ArrayList<Character> obtenir_lletres(String lletres_separades_comes) throws Exception {
        String[] lletres_separades = lletres_separades_comes.split(",");
        ArrayList<Character> lletres = new ArrayList<Character>();

        for (String s : lletres_separades) {
            if (s.length() > 1) throw new Exception("ERROR: les lletres nomas poden tenir un caracter");
            if (s.length() != 0) lletres.add(s.charAt(0));
        }

        if (lletres.isEmpty()) throw new Exception("ERROR: l'alfabet ha de tenir almenys una lletra");

        // Verifica si hi ha lletres repetides.
        if(lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduit lletres repetides");
        return lletres;
    }

    /**
     * Comprova si hi ha lletres repetides en un conjunt de caracters.
     * @param lletres ArrayList de lletres a comprovar.
     * @return True si hi ha lletres repetides en un conjunt de caracters, false altrament.
     */
    private boolean lletres_repetides(ArrayList<Character> lletres) {
        HashSet<Character> set = new HashSet<>(lletres);
        return set.size() != lletres.size();
    }

    // ---------------------------------------------------------------------------- //
    //                       Public class methods                                   //
    // ---------------------------------------------------------------------------- //
    /**
     * Associa una entrada a l'alfabet.
     * @param idEntrada Identificador de l'entrada a associar.
     * @throws Exception Si l'entrada ja pertany a l'alfabet.
     */
    public void associarEntrada(Integer idEntrada) throws Exception {
        if (idEntrades.contains(idEntrada)) throw new Exception("ERROR: l'entrada ja pertany a l'alfabet");
        idEntrades.add(idEntrada);
    }

    /**
     * Desvincula una entrada de l'alfabet.
     * @param idEntrada Identificador de l'entrada a desvincular.
     * @throws Exception Si l'entrada no pertany a l'alfabet.
     */
    public void desvincularEntrada(Integer idEntrada) throws Exception {
        if (!idEntrades.contains(idEntrada)) throw new Exception("ERROR: l'entrada no pertany a l'alfabet");
        idEntrades.remove(idEntrada);
    }

    /**
     * Afegeix una nova lletra a l'alfabet.
     * @param lletra Lletra a afegir.
     * @throws Exception Si la lletra ja pertany a l'alfabet.
     * @throws Exception Si la lletra as null.
     */
    public void afegirLletra(Character lletra) throws Exception {
        if (lletra == null) throw new Exception("ERROR: la lletra no pot ser null");
        if (lletres.contains(lletra)) throw new Exception("ERROR: la lletra ja pertany a l'alfabet");
        lletres.add(lletra);
    }
}