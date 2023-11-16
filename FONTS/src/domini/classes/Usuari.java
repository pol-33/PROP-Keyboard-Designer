package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Usuari. Representa un usuari en el sistema, amb els seus detalls com
 * el nom, la contrasenya i els alfabetes associats. Permet la creació, modificació
 * i gestió de les dades personals de l'usuari.
 */
public class Usuari {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    // Longitud màxima del nom d'usuari i contrasenya
    private static final int MAX_LENGTH = 15;

    // Nom d'usuari
    private String nomUsuari;

    // Contrasenya d'usuari
    private String contrasenya;


    // ---------------------------------------------------------------------------- //
    //                                   Creadora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructora de la clase Usuari. Inicialitza un usuari amb un nom i contrasenya,
     * i prepara una llista per als IDs dels alfabetes associats.
     */
    public Usuari(String nomUsuari, String contrasenya) throws Exception {

        if (nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        if (contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        if (nomUsuari == null || nomUsuari.isEmpty()) throw new Exception("El nom d'usuari no pot ser buit");
        if (contrasenya == null || contrasenya.isEmpty()) throw new Exception("La contrasenya no pot ser buida");
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }

    /**
     * Obté la contrasenya de l'usuari.
     *
     * @return Cadena de text que representa la contrasenya de l'usuari.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Obté el nom de l'usuari.
     *
     * @return text que representa el nom de l'usuari.
     */
    public String getNom() {
        return nomUsuari;
    }


    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //

    /**
     * Estableix o modifica el nom d'usuari.
     *
     * @param nomUsuari Nou nom d'usuari a establir.
     * @throws Exception Si el nom d'usuari excedeix la longitud màxima permesa.
     */

    public void modificarNomUsuari(String nomUsuari) throws Exception {
        if (nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
    }

    /**
     * Estableix o modifica la contrasenya
     *
     * @param contrasenya Nova contrasenya a establir.
     * @throws Exception Si la contrasenya excedeix la longitud màxima permesa.
     */
    public void modificarContrasenya(String contrasenya) throws Exception {
        if (contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.contrasenya = contrasenya;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //


    /**
     * Crear un usuari i mirar si el nom d'usuari ja existeix
     *
     * @param nomUsuari   Nom d'usuari a crear.
     * @param contrasenya Contrasenya a crear.
     * @throws Exception Si el nom d'usuari ja existeix.
     */
    public void crearUsuari(String nomUsuari, String contrasenya, ArrayList<String> nomUsuarisExistents) throws Exception {
        if (nomUsuarisExistents.contains(nomUsuari)) throw new Exception("El nom d'usuari ja existeix");
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }

    /**
     * Crear un usuari i mirar si el nom d'usuari ja existeix
     *
     * @param nomUsuari Nom d'usuari a crear.
     * @param contrasenya Contrasenya a crear.
     * @param usuarisContrasenyes HashMap amb els usuaris i les seves contrasenyes.
     * @return True si el nom d'usuari ja existeix, false en cas contrari.
     */

    public boolean verificarIniciSessio(String nomUsuari, String contrasenya, HashMap<String, String> usuarisContrasenyes) {
        String contrasenyaGuardada = usuarisContrasenyes.get(nomUsuari);
        return contrasenyaGuardada != null && contrasenyaGuardada.equals(contrasenya);
    }

}
