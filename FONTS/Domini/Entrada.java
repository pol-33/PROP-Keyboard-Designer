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

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //
    public void asociarTeclat(Integer idTeclat) throws Exception {
        if (this.idTeclat == null) {
            throw new Exception("L'entrada ja té un teclat associat");
        }
        this.idTeclat = idTeclat;
    }
}
