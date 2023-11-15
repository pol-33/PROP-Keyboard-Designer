package Domini;

import java.util.HashMap;

public abstract class Entrada {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    protected String nom;

    protected Integer id;
    protected HashMap<String, Integer> lpf;
    protected Integer idTeclat;

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //
    public String getNom() {
        return this.nom;
    }

    public HashMap<String, Integer> getLPF() {
        return this.lpf;
    }
}
