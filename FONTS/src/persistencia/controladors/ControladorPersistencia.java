package persistencia.controladors;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;

    private ControladorPersistencia() {
    }

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


    void crearUsuari(String username) {}

    ArrayList<String> obtenirUsernames() {
        return null;
    }

    ArrayList<String> obtenirPasswordUsuari(String username) {
        return null;
    }

    void modificarPasswordUsuari(String username, String password) {}

    void eliminarUsuari(String username) {}


    //--------------------------------Alfabet---------------------------------//

    void crearAlfabet(String username, Integer idAlfabet, String nom, ArrayList<String> lletres){}

    ArrayList<Integer> obtenirIdsAlfabetsxUsuari(String username) {
        return null;
    }

    ArrayList<Integer> obtenirIdsAlfabets() {
        return null;
    }

    ArrayList<String> obtenirInfoAlfabet(Integer idAlfabet) {
        return null;
    }

    void afegirLletraAlfabet(Integer idAlfabet) {}

    void eliminarAlfabet(Integer idAlfabet) {}


    //--------------------------------Entrada---------------------------------//

    void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text ) {}

    ArrayList<Integer> obtenirIdsEntradesxAlfabet(Integer idAlfabet) {
        return null;
    }

    ArrayList<Integer> obtenirIdsEntrades() {
        return null;
    }

    ArrayList<Integer> obtenirInfoEntrada(Integer idEntrada) {
        return null;
    }

    void modificarContingutEntrada(Integer idEntrada, HashMap<String, Integer> lpf, String text) {}

    void elimanrEntrada(Integer idEntrada) {}



    //--------------------------------Teclat---------------------------------//

    void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio){}


    ArrayList<Integer> obtenirIdsTeclatsxEntrada(Integer idEntrada) {
        return null;
    }

    ArrayList<Integer> obtenirIdsTeclats() {
        return null;
    }

    ArrayList<Integer> obtenirInfoTeclat(Integer idTeclat) {
        return null;
    }

    void modifcarNumFilesTeclat(Integer idTeclat, Integer numFiles) {}

    void modificarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {}

    void modificarDistribucio(Integer idTeclat, ArrayList<String> distribucio) {}

    void eliminarTeclat(Integer idTeclat) {}
}

