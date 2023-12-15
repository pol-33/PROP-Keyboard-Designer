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


    public void crearUsuari(String username) {}

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

    public ArrayList<String> obtenirAlfabetsxUsuari(String username) {
        return null;
    }

    public ArrayList<Integer> obtenirIdsAlfabets() {
        return null;
    }

    public ArrayList<String> obtenirInfoAlfabet(Integer idAlfabet) {
        return null;
    }

    public void afegirLletraAlfabet(Integer idAlfabet) {}

    public void eliminarAlfabet(Integer idAlfabet) {}


    //--------------------------------Entrada---------------------------------//

    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text ) {}

    public ArrayList<String> obtenirEntradesxAlfabet(Integer idAlfabet) {
        return null;
    }

    public ArrayList<Integer> obtenirIdsEntrades() {
        return null;
    }

    public ArrayList<Integer> obtenirInfoEntrada(Integer idEntrada) {
        return null;
    }

    public void modificarContingutEntrada(Integer idEntrada, HashMap<String, Integer> lpf, String text) {}

    public void elimanrEntrada(Integer idEntrada) {}



    //--------------------------------Teclat---------------------------------//

    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio){}


    public ArrayList<String> obtenirTeclatsxEntrada(Integer idEntrada) {
        return null;
    }

    public ArrayList<Integer> obtenirIdsTeclats() {
        return null;
    }

    public ArrayList<Integer> obtenirInfoTeclat(Integer idTeclat) {
        return null;
    }

    public void modifcarNumFilesTeclat(Integer idTeclat, Integer numFiles) {}

    public void modificarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {}

    public void modificarDistribucio(Integer idTeclat, ArrayList<String> distribucio) {}

    public void eliminarTeclat(Integer idTeclat) {}
}

