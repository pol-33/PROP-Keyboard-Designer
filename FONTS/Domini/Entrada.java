package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Entrada {
    // Atributs
    protected String nom;
    protected HashMap<String, Integer> lpf;
    protected Teclat teclat;

    //Retorna un HashMap equivalent a la lpf de l'entrada
    public HashMap<String, Integer> getLPF() {
        return this.lpf;
    }

    // Crea un teclat per l'entrada
    protected Teclat creaTeclat(ArrayList<Character> lletres) {
        return new Teclat(lletres, this.lpf);
    }

    // Mostrar contingut
    public abstract void mostrarContingut();
}
