package Domini;

import java.util.ArrayList;

/**
 * Representa un teclat.
 */
public class Teclat {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    protected ArrayList<Character> assignacions;
    protected Integer numFiles;
    protected Integer numColumnes;
    protected Integer idEntrada;
    protected Integer id;

    // ---------------------------------------------------------------------------- //
    //                                   Creadora
    // ---------------------------------------------------------------------------- //

    /**
     * 
     * @param assignacions ArrayList de l'ordre de les lletres en el teclat
     * @param idEntrada Identificador d l'entrada a la que esta asociat
     * @param numFiles Numero de files del layout
     * @param numColumnes Numero de columnes del layout
     * @param id Identificador del teclat
     */
    public Teclat(ArrayList<Character> assignacions, Integer idEntrada, Integer numFiles, Integer numColumnes, Integer id) {
        this.assignacions = assignacions;
        this.idEntrada = idEntrada;
        this.numFiles = numFiles;
        this.numColumnes = numColumnes;
        this.id = id;
    }

    
    /** 
     * @return ArrayList<Character>
     */
    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     * @return ArrayList<Character>
     */
    public ArrayList<Character> getAssignacions() {
        return this.assignacions;
    }
    
    /** 
     * @return Integer
     */
    public Integer getFiles() {
        return this.numFiles;
    }
    
    /** 
     * @return Integer
     */
    public Integer getColumnes() {
        return this.numColumnes;
    }
    
    /** 
     * @return Integer
     */
    public Integer getIdEntrada() {
        return this.idEntrada;
    }
}
