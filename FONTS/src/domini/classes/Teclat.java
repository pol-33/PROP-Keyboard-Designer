package domini.classes;

import java.util.ArrayList;

public class Teclat {

    protected ArrayList<Character> distribucio;
    protected String nom;
    protected Integer numFiles;
    protected Integer numColumnes;
    protected Integer idEntrada;
    protected Integer id;

    //------------------------------Constructora-----------------------------------//
    /**
     * Contructora de teclat
     * @param distribucio  Lletres ordenades per files
     * @param idEntrada Id de la entrada vinculada
     * @param numFiles Nombre de files
     * @param numColumnes Nombre de columnes
     * @param id Id del teclat
     */
    public Teclat(String nom, ArrayList<Character> distribucio, Integer idEntrada, Integer numFiles, Integer numColumnes, Integer id) {
        this.distribucio = distribucio;
        this.idEntrada = idEntrada;
        this.numFiles = numFiles;
        this.numColumnes = numColumnes;
        this.id = id;
        this.nom = nom;
    }

    //-----------------------------Getters------------------------------------//
    /** 
     * @return ArrayList<Character>
     */
    public ArrayList<Character> getDistribucio() {
        return this.distribucio;
    }
    
    /** 
     * @return Integer
     */
    public Integer getFiles() {
        return this.numFiles;
    }
    
    public String getNom() {
        return nom;
    }
    /** 
     * @return Integer
     */
    public Integer getColumnes() {
        return this.numColumnes;
    }
    
    /**
     * 
     * @return Integer
     */
    public Integer getIdEntradaVinculada() {
        return this.idEntrada;
    }
}
