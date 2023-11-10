package ControladorsDomini;

import java.util.ArrayList;
import java.util.HashMap;

import Domini.Teclat;

public class ControladorTeclats {
    private HashMap<Integer, Teclat> conjuntTeclats;

    //Instancia singleton del Controlador de Teclats
    private static ControladorPerfil ctrlTeclats;

    //Metode per obtenir l'instància singleton
    public static ControladorPerfil obtenInstancia() {
        if (ctrlTeclats == null) {
            ctrlTeclats = new ControladorPerfil();
        }
        return ctrlTeclats;
    }

    // Funcions de gestio del conjunt de teclats
    public Integer crearTeclatDuesMans(HashMap<String, Integer> lpf, ArrayList<Character> alfabet,
     Integer uidEntrada, Integer files, Integer columnes) {
        // creem un identificador per al nou teclat
        Integer idTeclat = conjuntTeclats.size();   // de moment estaran ordenats

        // necessitem saber un ArrayList de <Character> per a crear el teclar
        ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
        ArrayList<Character> teclesAmbLletres = ctrlAlgoritme.calcularDistribucioDuesMans(lpf, alfabet, files, columnes);

        // creem un teclat nou
        Teclat nouTeclat = new Teclat(teclesAmbLletres, uidEntrada, files, columnes, idTeclat);

        // afegim el teclat al conjunt
        conjuntTeclats.put(idTeclat, nouTeclat);

        return idTeclat;
    }
    
    public Integer crearTeclatPolzes(HashMap<String, Integer> lpf, ArrayList<Character> alfabet,
     Integer uidEntrada, Integer files, Integer columnes) {
        // creem un identificador per al nou teclat
        Integer idTeclat = conjuntTeclats.size();   // de moment estaran ordenats

        // necessitem saber un ArrayList de <Character> per a crear el teclar
        ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
        ArrayList<Character> teclesAmbLletres = ctrlAlgoritme.calcularDistribucioPolzes(lpf, alfabet, files, columnes);

        // creem un teclat nou
        Teclat nouTeclat = new Teclat(teclesAmbLletres, uidEntrada, files, columnes, idTeclat);

        // afegim el teclat al conjunt
        conjuntTeclats.put(idTeclat, nouTeclat);

        return idTeclat;
    }
    
    public Integer eliminarTeclat(int idTeclat) {
        if (conjuntTeclats.containsKey(idTeclat)) {
            conjuntTeclats.remove(idTeclat);
            return 0;   // ha funcionat
        }
        else return 1;  // no existeix el teclat
    }
    

    // Fa falta modificarTeclat, i que hauria de fer??
}
