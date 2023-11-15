package ControladorsDomini;

import Domini.Usuari;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe ControladorUsuari. Gestiona un conjunt d'usuaris, encarregada de
 * crear, modificar i eliminar usuaris. Conté funcions per comprovar
 * la validesa de les credencials dels usuaris.
 */

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

    /**
     *
     * Constructora de la clase.
     *
     */
    public ControladorUsuari() {
        usuaris = new HashMap<>();
    }

    /**
     *
     * Constructor Singleton de la clase.
     *
     */
    public static ControladorUsuari obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorUsuari();
        }
        return ctrl;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     *
     * Retorna una llista de noms d'usuaris existents.
     *
     * @return ArrayList<String> que representa la llista de noms d'usuaris.
     */
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

    /**
     *
     * Crear un nou usuari amb un nom i contrasenya.
     *
     * @param nomUsuari  Nom de l'usuari a crear
     * @param contrasenya  Contrasenya de l'usuari
     * @return String que representa el nom de l'usuari creat
     * @throws   Exception Si ja existeix un usuari amb aquest nom.
     */
    public String crearUsuari(String nomUsuari, String contrasenya) throws Exception{

        if(usuaris.containsKey(nomUsuari))
            throw new Exception("Ja existeix un usuari amb aquest nom");

        Usuari nouUsuari = new Usuari(nomUsuari, contrasenya);
        usuaris.put(nomUsuari, nouUsuari);

        return nomUsuari;
    }

    /**
     *
     * Elimina un usuari existent.
     *
     * @param nomUsuari  Nom de l'usuari a eliminar
     * @throws   Exception Si el nom d'usuari és null o no existeix.
     */
    public void eliminarUsuari(String nomUsuari) throws Exception{
        if(nomUsuari == null) throw new Exception("El nom d'usuari no pot ser null");
        if(!usuaris.containsKey(nomUsuari)) throw new Exception("No existeix cap usuari amb aquest nom");
        usuaris.remove(nomUsuari);
    }

    /**
     *
     * Comprova si la contrasenya d'un usuari és correcta.
     *
     * @param nomUsuari  Nom de l'usuari
     * @param contrasenya  Contrasenya per comprovar
     * @return Boolean que indica si la contrasenya és correcta o no
     * @throws   Exception Si el nom d'usuari és null o no existeix.
     */
    public Boolean comprovaContrasenya(String nomUsuari, String contrasenya) throws Exception {
        if(nomUsuari == null) throw new Exception("El nom d'usuari no pot ser null");
        Usuari usuari = usuaris.get(nomUsuari);
        if(usuari == null) throw new Exception("No existeix cap usuari amb aquest nom");

        return usuari.contrasenyaCorrecta(contrasenya);
    }
}
