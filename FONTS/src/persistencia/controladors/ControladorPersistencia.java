package persistencia.controladors;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;
    
    private ControladorPersistencia() {
    }

    //Metode per obtenir l'instància singleton
    public static ControladorPersistencia obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPersistencia();
        }
        return ctrl;
    }

    //--------------------------------Usuari---------------------------------//
    public ArrayList<String> getUsuarisExistents() {
        return new ArrayList<String>();
    }


    public HashMap<String, String> getUsuarisContrasenyes() {
        return new HashMap<String, String>();
    }

    public void guardarUsuari(String nomUsuari, String contrasenya) {

    }

    public void eliminarUsuari(String nomUsuari) {

    }

    //--------------------------------Alfabet---------------------------------//
    public ArrayList<String> getAlfabetsUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getAlfabet(Integer id) {
        return new String();
    }

    public void guardarAlfabet(Integer id, String nomAlfabet, ArrayList<Character> lletres, ArrayList<Integer> idEntrades) {
    }

    public void eliminarAlfabet(Integer id) {
    }

    //--------------------------------Entrada---------------------------------//
    public ArrayList<String> getEntradesUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getEntrada(Integer id) {
        return new String();
    }

    public void guardarText(Integer id, String nomEntrada, String text, ArrayList<Integer> idTeclats) {
    }

    public void guardarLPF(Integer id, String nomEntrada, HashMap<String, Integer> lpf, ArrayList<Integer> idTeclats) {
    }

    public void eliminarEntrada(Integer id) {
    }

    //--------------------------------Teclat---------------------------------//
    public ArrayList<String> getTeclatsUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getTeclat(Integer id) {
        return new String();
    }

    public void guardarTeclat(Integer id, String nom, Integer numFiles, Integer numColumnes, ArrayList<Character> distribucio, Integer idEntrada) {
    }

    public void eliminarTeclat(Integer id) {
    }

}
