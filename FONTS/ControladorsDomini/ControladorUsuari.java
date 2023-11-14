package ControladorsDomini;

import Domini.Usuari;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControladorUsuari {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private static ControladorUsuari ctrl;

    // Mapa per guardar els usuaris
    private HashMap<String, Usuari> usuaris;

    // ---------------------------------------------------------------------------- //
    //                                   Creadora
    // ---------------------------------------------------------------------------- //

    // Constructora de la classe
    public ControladorUsuari() {
        usuaris = new HashMap<>();
    }

    public static ControladorUsuari obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorUsuari();
        }
        return ctrl;
    }

    // Mètode per afegir un usuari
    public String crearUsuari(String nomUsuari, String contrasenya) throws Exception{

        if(usuaris.containsKey(nomUsuari))
            throw new Exception("Ja existeix un usuari amb aquest nom");
        
        Usuari nouUsuari = new Usuari(nomUsuari, contrasenya);
        usuaris.put(nomUsuari, nouUsuari);

        return nomUsuari;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters                             
    // ---------------------------------------------------------------------------- //
    public ArrayList<String> getLlistaUsuaris() {
        ArrayList<String> llistaUsuaris = new ArrayList<>();

        // Iterate through the keys (usernames) in the usuaris map
        for (String username : usuaris.keySet()) {
            llistaUsuaris.add(username);
        }

        return llistaUsuaris;
    }
    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //

    // Mètode per eliminar un usuari
    public void eliminarUsuari(String nomUsuari) throws Exception{
        if(nomUsuari == null) throw new Exception("El nom d'usuari no pot ser null");
        if(!usuaris.containsKey(nomUsuari)) throw new Exception("No existeix cap usuari amb aquest nom");
        usuaris.remove(nomUsuari);
    }

    // Mètode per obtenir un usuari
    public Boolean comprovaContrasenya(String nomUsuari, String contrasenya) throws Exception {
        if(nomUsuari == null) throw new Exception("El nom d'usuari no pot ser null");
        Usuari usuari = usuaris.get(nomUsuari);
        if(usuari == null) throw new Exception("No existeix cap usuari amb aquest nom");

        return usuari.contrasenyaCorrecta(contrasenya);
    }
    
}
