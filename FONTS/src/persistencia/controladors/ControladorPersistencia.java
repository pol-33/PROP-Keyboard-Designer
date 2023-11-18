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
        return null;
    }


    public HashMap<String, String> getUsuarisContrasenyes() {
        return null;
    }

    public void guardarUsuari(String nomUsuari, String contrasenya) {

    }

    public void eliminarUsuari(String nomUsuari) {

    }
}
