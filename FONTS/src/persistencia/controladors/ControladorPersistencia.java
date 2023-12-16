package persistencia.controladors;

import persistencia.classes.GestorUsuaris;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;
    private GestorUsuaris gestorUsuaris = new GestorUsuaris();

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
        return null;
    }

    public ArrayList<String> obtenirPasswordUsuari(String username) {
        return null;
    }

    public void modificarPasswordUsuari(String username, String password) {}

    public void eliminarUsuari(String username) {}


    //--------------------------------Alfabet---------------------------------//

    public void crearAlfabet(String username, Integer idAlfabet, String nom, ArrayList<String> lletres){}

    public ArrayList<String> carregarAlfabets(String username) {
        return null;
    }

    public void afegirLletresAlfabet(Integer idAlfabet, ArrayList<Character> novesLletres) {}

    public void eliminarAlfabet(Integer idAlfabet) {}


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
}

