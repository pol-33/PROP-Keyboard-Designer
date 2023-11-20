package domini.classes;

import java.util.ArrayList;

/**
 * Classe Teclat. Representa un teclat dins del sistema amb una distribució específica de caràcters,
 * així com informació relacionada amb el seu nom, dimensions, identificador i l'entrada vinculada.
 */
public class Teclat {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    protected ArrayList<Character> distribucio;
    protected String nom;
    protected Integer numFiles;
    protected Integer numColumnes;
    protected Integer idEntrada;
    protected Integer id;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructora de Teclat.
     * @param nom Nom del teclat
     * @param distribucio Distribució de caràcters del teclat ordenats per files
     * @param idEntrada Identificador de l'entrada vinculada al teclat
     * @param numFiles Nombre de files del teclat
     * @param numColumnes Nombre de columnes del teclat
     * @param id Identificador únic del teclat
     */
    public Teclat(String nom, ArrayList<Character> distribucio, Integer idEntrada, Integer numFiles, Integer numColumnes, Integer id) {
        this.distribucio = distribucio;
        this.idEntrada = idEntrada;
        this.numFiles = numFiles;
        this.numColumnes = numColumnes;
        this.id = id;
        this.nom = nom;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     * Retorna la distribució de caràcters del teclat.
     * @return ArrayList de caràcters amb la distribució del teclat.
     */
    public ArrayList<Character> getDistribucio() {
        return this.distribucio;
    }

    /**
     * Retorna el nombre de files del teclat.
     * @return Integer amb el nombre de files del teclat.
     */
    public Integer getFiles() {
        return this.numFiles;
    }

    /**
     * Retorna el nom del teclat.
     * @return String amb el nom del teclat.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna el nombre de columnes del teclat.
     * @return Integer amb el nombre de columnes del teclat.
     */
    public Integer getColumnes() {
        return this.numColumnes;
    }

    /**
     * Retorna l'identificador de l'entrada vinculada al teclat.
     * @return Integer amb l'identificador de l'entrada vinculada.
     */
    public Integer getIdEntradaVinculada() {
        return this.idEntrada;
    }
}
