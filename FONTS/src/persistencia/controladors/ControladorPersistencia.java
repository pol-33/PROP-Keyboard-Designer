package persistencia.controladors;

import persistencia.classes.GestorAlfabets;
import persistencia.classes.GestorUsuaris;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;
    private GestorUsuaris gestorUsuaris = new GestorUsuaris();
    private GestorAlfabets gestorAlfabets = new GestorAlfabets();

    /**
     * Mètode per obtenir la instància singleton del controlador.
     * @return La instància única del ControladorPersistencia.
     */
    public static ControladorPersistencia obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPersistencia();
        }
        return ctrl;
    }

    //--------------------------------Usuari---------------------------------//


    public void crearUsuari(String username, String password) {
        gestorUsuaris.crearUsuari(username, password);
    }

    public ArrayList<String> obtenirUsernames() {
        return gestorUsuaris.obtenirUsernames();
    }

    public String obtenirPasswordUsuari(String username) {
        return gestorUsuaris.obtenirPasswordUsuari(username);
    }

    public void modificarPasswordUsuari(String username, String password) {
        gestorUsuaris.eliminarUsuari(username);
        gestorUsuaris.crearUsuari(username, password);
    }

    public void eliminarUsuari(String username) {
        gestorUsuaris.eliminarUsuari(username);
    }

    //--------------------------------Alfabet---------------------------------//

    public void crearAlfabet(String username, Integer idAlfabet, String nom, ArrayList<Character> lletres){
        gestorAlfabets.crearAlfabet(username, idAlfabet, nom, lletres);
    }

    public ArrayList<String> carregarAlfabets(String username) {
        return gestorAlfabets.carregarAlfabets(username);
    }

    public void afegirLletresAlfabet(Integer idAlfabet, ArrayList<Character> novesLletres) {
        String[] alfabet = gestorAlfabets.carregarAlfabet(idAlfabet);
        if (alfabet != null) {
            gestorAlfabets.eliminarAlfabet(idAlfabet);
            if (alfabet.length > 2) {
                alfabet[2] = alfabet[2] + "." + convertirArrayListToString(novesLletres);
            }

            gestorAlfabets.crearAlfabet(alfabet);
        }
    }

    public void eliminarAlfabet(Integer idAlfabet) {
        gestorAlfabets.eliminarAlfabet(idAlfabet);
    }


    //--------------------------------Entrada---------------------------------//

    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, String tipus, HashMap<String, Integer> lpf, String text ) {}

    public ArrayList<String> carregarEntrades(Integer idAlfabet) { return null; }

    public void modificarContingutEntrada(Integer idEntrada, HashMap<String, Integer> lpf, String text) {}

    public void elimanrEntrada(Integer idEntrada) {}



    //--------------------------------Teclat---------------------------------//

    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio){}


    public ArrayList<String> carregarTeclats(Integer idEntrada) { return null; }

    public void modifcarNumFilesTeclat(Integer idTeclat, Integer numFiles) {}

    public void modificarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {}

    public void modificarDistribucio(Integer idTeclat, ArrayList<Character> distribucio) {}

    public void eliminarTeclat(Integer idTeclat) {}

    //--------------------------------Privat---------------------------------//
    private static String convertirArrayListToString(ArrayList<Character> llista) {
        StringBuilder result = new StringBuilder();

        // Iterar sobre los elementos del ArrayList
        for (int i = 0; i < llista.size(); i++) {
            result.append(llista.get(i));

            // Agregar una coma si no es el último elemento
            if (i < llista.size() - 1) {
                result.append(".");
            }
        }

        return result.toString();
    }
}

