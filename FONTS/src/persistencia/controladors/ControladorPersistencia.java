package persistencia.controladors;

import persistencia.classes.GestorAlfabets;
import persistencia.classes.GestorUsuaris;
import persistencia.classes.GestorTeclats;
import persistencia.classes.GestorEntrades;


import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;
    private GestorUsuaris gestorUsuaris = new GestorUsuaris();
    private GestorAlfabets gestorAlfabets = new GestorAlfabets();

    private GestorEntrades gestorEntrades = new GestorEntrades();
    private GestorTeclats gestorTeclats = new GestorTeclats();

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

    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        gestorEntrades.crearEntrada(idAlfabet, idEntrada, nom, lpf, text);
    }

    public ArrayList<String[]> carregarEntrades(Integer idAlfabet) {
        return gestorEntrades.carregarEntrades(idAlfabet);
    }
    public void modificarContingutEntrada(Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        gestorEntrades.actualizarEntrada(idEntrada, nom, lpf, text);
    }
    public void eliminarEntrada(Integer idEntrada) {
        gestorEntrades.eliminarEntrada(idEntrada);
    }


    //--------------------------------Teclat---------------------------------//

    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio) {
        gestorTeclats.crearTeclat(idEntrada, idTeclat, nom, numFiles, numColumnes, distribucio);
    }

    public ArrayList<String[]> carregarTeclats(Integer idEntrada) {
        return gestorTeclats.carregarTeclats(idEntrada);
    }
    public void modificarNumFilesTeclat(Integer idTeclat, Integer numFiles) {
        gestorTeclats.actualizarNumFilesTeclat(idTeclat, numFiles);
    }

    public void modificarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {
        gestorTeclats.actualizarNumColumnesTeclat(idTeclat, numColumnes);
    }

    public void modificarDistribucio(Integer idTeclat, ArrayList<String> distribucio) {
        gestorTeclats.actualizarDistribucioTeclat(idTeclat, distribucio);
    }

    public void eliminarTeclat(Integer idTeclat) {
        gestorTeclats.eliminarTeclat(idTeclat);
    }
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

