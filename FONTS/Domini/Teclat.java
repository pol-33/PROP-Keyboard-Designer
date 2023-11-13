package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Teclat {

    protected ArrayList<Character> tecles;
    protected Integer numFiles;
    protected Integer numColumnes;
    protected Integer idEntrada;
    protected Integer id;

    // Creadora
    public Teclat(ArrayList<Character> lletres, Integer idEntrada, Integer num_files, Integer num_columnes, Integer id) {
        this.idEntrada = idEntrada;
        this.numFiles = num_files;
        this.numColumnes = num_columnes;
        this.id = id;
    }

    // Getters
    public ArrayList<Character> getTecles() {
        return this.tecles;
    }
    public Integer getFiles() {
        return this.numFiles;
    }
    public Integer getColumnes() {
        return this.numColumnes;
    }
    public Integer getIdEntrada() {
        return this.idEntrada;
    }
}
