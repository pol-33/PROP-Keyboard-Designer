package ControladorsDomini;

import Domini.Usuari;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;

    // Mapa con todos los usuarios registrados en el sistema
    private HashMap<String,Usuari> Usuaris;

    //Instancia de l'usuari actiu
    private Usuari usuariActiu;

    private ControladorDomini() {
        Usuaris = new HashMap<String, Usuari>();
    }

    //Metode per obtenir l'instància singleton
    public static ControladorDomini obtenInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorDomini();
        }
        return ctrl;
    }

    public void iniciarSessio(String nomUsuari, String contrasenya) throws Exception {
        if (usuariActiu != null) throw new Exception("Tanca la sessió actual per a poder iniciar sessio");
        if (Usuaris.containsKey(nomUsuari)) {
            Usuari usuariSessio = Usuaris.get(nomUsuari);
            if (usuariSessio.contrasenyaCorrecta(contrasenya)) {
                System.out.println("S'ha iniciat sessio correctament");
                usuariActiu = usuariSessio;
            } else {
                throw new Exception("La contrasenya no es correcte");
            };
        } else {
            throw new Exception("No existeix un usuari amb aquest nom");
        }
    }

    public void registrarUsuari(String nomUsuari, String contrasenya) throws Exception{
        if (!Usuaris.containsKey(nomUsuari)) {
            Usuari nouUsuari = new Usuari(nomUsuari, contrasenya);
            System.out.println("S'ha registrat l'usuari correctament");
            Usuaris.put(nomUsuari, nouUsuari);
        } else {
            throw new Exception("Ja existeix un usuari amb aquesta contrasenya");
        }
    }

    //Tancar sessio usuari actiu
    public void tancarSessio() throws Exception {
        if (usuariActiu == null) {
            throw new Exception("Has d'haver iniciat sessio per a poder tancar-la");
        }
        System.out.println("S'ha tancat sessio correctament");
        usuariActiu = null;
    }

    //Llista tots els usuaris
    public void llistarUsuaris() throws Exception {
        if (!Usuaris.isEmpty()) {
            System.out.println("Aquests son els ususaris registrats al sistema");
            for (String nomUsuari : Usuaris.keySet()) {
                System.out.println(nomUsuari);
            }
        } else {
            throw new Exception("No hi ha cap usuari registrat al sistema");
        }
    }

    //Comprova que hi ha un usuari autenticat
    public void usuariAutenticat() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessió per a poder fer aquesta funcionalitat");
    }

    //----------------------------------------------------------------------------------------------------------------------------
    //Crea un alfabet
    public void crearAlfabet(String idioma, String textAlfabet) throws Exception {
        usuariAutenticat();
        usuariActiu.crearAlfabet(idioma, textAlfabet);
    }

    public void llistarAlfabets() throws Exception {
        usuariAutenticat();
        usuariActiu.llistarAlfabets();
    }

}
