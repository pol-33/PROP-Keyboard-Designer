package Domini;

import java.util.ArrayList;

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
    public Integer getIdEntrada() {
        return this.idEntrada;
    }
}
