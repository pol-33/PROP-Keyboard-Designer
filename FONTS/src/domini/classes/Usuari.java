package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Usuari. Representa un usuari en el sistema, incloent detalls com
 * el nom, la contrasenya i funcionalitats per a la creació i autenticació.
 */
public class Usuari {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    private static final int MAX_LENGTH = 15;
    private String nomUsuari;
    private String contrasenya;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructor de la classe Usuari. Inicialitza un usuari amb un nom i contrasenya.
     * @param nomUsuari   Nom d'usuari.
     * @param contrasenya Contrasenya de l'usuari.
     * @throws Exception Si el nom o la contrasenya no compleixen amb els requisits.
     */
    public Usuari(String nomUsuari, String contrasenya) throws Exception {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     * Obté la contrasenya de l'usuari.
     * @return String amb la contrasenya de l'usuari.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Obté el nom de l'usuari.
     * @return String amb el nom de l'usuari.
     */
    public String getNom() {
        return nomUsuari;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //

    /**
     * Estableix o modifica el nom d'usuari.
     * @param nomUsuari Nou nom d'usuari a establir.
     * @throws Exception Si el nom d'usuari excedeix la longitud màxima permesa.
     */
    public void modificarNomUsuari(String nomUsuari) throws Exception {
        if (nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
    }

    /**
     * Estableix o modifica la contrasenya.
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
     * Inicia sessió per a un usuari existent en el sistema.
     * @param nomUsuari Nom d'usuari per a iniciar sessió.
     * @param contrasenya Contrasenya per a iniciar sessió.
     * @param nomUsuarisContrasenyes HashMap amb noms d'usuari i les seves contrasenyes.
     * @return Usuari objecte de l'usuari que ha iniciat sessió.
     * @throws Exception Si el nom d'usuari no existeix o la contrasenya és incorrecta.
     */
    public static Usuari iniciarSessio(String nomUsuari, String contrasenya, String contrasenyaUsuari) throws Exception {
        if (!contrasenya.equals(contrasenyaUsuari)) throw new Exception("La contrasenya no es correcte");

        return new Usuari(nomUsuari, contrasenya);
    }

    /**
     * Crea un nou usuari en el sistema.
     * @param nomUsuari Nom d'usuari a crear.
     * @param contrasenya Contrasenya per al nou usuari.
     * @param nomUsuarisExistents Llista amb noms d'usuari existents.
     * @return Usuari objecte del nou usuari creat.
     * @throws Exception Si el nom d'usuari ja existeix o no compleix amb els requisits.
     */
    public static Usuari crearUsuari(String nomUsuari, String contrasenya, ArrayList<String> nomUsuarisExistents) throws Exception {
        if (nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        if (contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        if (nomUsuari.isEmpty()) throw new Exception("El nom d'usuari no pot ser buit");
        if (contrasenya.isEmpty()) throw new Exception("La contrasenya no pot ser buida");
        if (nomUsuarisExistents.contains(nomUsuari)) throw new Exception("El nom d'usuari ja existeix");
        return new Usuari(nomUsuari, contrasenya);
    }

    /**
     * Verifica l'inici de sessió d'un usuari donat un nom d'usuari i contrasenya.
     * @param nomUsuari Nom d'usuari a verificar.
     * @param contrasenya Contrasenya a verificar.
     * @param usuarisContrasenyes HashMap amb els usuaris i les seves contrasenyes.
     * @return True si l'inici de sessió és correcte, false en cas contrari
     */
    public boolean verificarIniciSessio(String nomUsuari, String contrasenya, HashMap<String, String> usuarisContrasenyes) {
        String contrasenyaGuardada = usuarisContrasenyes.get(nomUsuari);
        return contrasenyaGuardada != null && contrasenyaGuardada.equals(contrasenya);
    }
}
