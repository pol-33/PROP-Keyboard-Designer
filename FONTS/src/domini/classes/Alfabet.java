package domini.classes;

import java.util.ArrayList;

/**
 * Classe Alfabet. Representa l'alfabet d'un idioma, as a dir, un conjunt de lletres no repetides d'un sol caracter.
 */

public class Alfabet {
    //class attributes
    private ArrayList<Character> lletres;
    private String nom;
    private Integer id;
    private ArrayList<Integer> idEntrades;


    //---------------------------Constructora-----------------------------//
    public Alfabet(String nomAlfabet, Integer idAlfabet, ArrayList<Character> lletresAlfabet) throws Exception {
        this.nom = nomAlfabet;
        this.id = idAlfabet;
        this.lletres = lletresAlfabet;
        this.idEntrades = new ArrayList<>();
    }

    //-----------------------------Getters--------------------------------//
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
    public String getNom() {
        return nom;
    }

    /**
     * Retorna l'identificador de l'alfabet.
     * @return Integer amb l'identificador de l'alfabet.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Retorna els identificadors de les entrades associades a l'alfabet.
     * @return ArrayList amb els identificadors de les entrades associades a l'alfabet.
     */
    public ArrayList<Integer> getEntrades() {
        return idEntrades;
    }


    //-----------------------------Setters---------------------------//
    /**
     * Modifica l'idioma de l'alfabet.
     * @param nomAlfabet Nou idioma de l'alfabet.
     */
    public void setNom(String nomAlfabet) {
        this.nom = nomAlfabet;
    }

    //---------------------------Metodes publics---------------------------//
    /**
     * Associa una entrada a l'alfabet.
     * @param idEntrada Identificador de l'entrada a associar.
     * @throws Exception Si l'entrada ja pertany a l'alfabet.
     */
    public void vincularEntrada(Integer idEntrada) throws Exception {
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