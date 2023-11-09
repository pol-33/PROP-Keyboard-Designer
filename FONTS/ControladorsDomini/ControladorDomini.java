package ControladorsDomini;

import Domini.Usuari;

import java.util.HashMap;

public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;

    // Mapa con todos los usuarios registrados en el sistema
    private HashMap<String,Usuari> Usuaris;

    //Instancia de l'usuari actiu
    private Usuari usuariActiu;

    private ControladorDomini() {
        Usuaris = new HashMap<>();
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
                usuariActiu = usuariSessio;
                System.out.println("S'ha iniciat sessio correctament");
            } else {
                throw new Exception("La contrasenya no es correcte");
            }
        } else {
            throw new Exception("No existeix un usuari amb aquest nom");
        }
    }

    public void registrarUsuari(String nomUsuari, String contrasenya) throws Exception{
        if (!Usuaris.containsKey(nomUsuari)) {
            Usuari nouUsuari = new Usuari(nomUsuari, contrasenya);
            Usuaris.put(nomUsuari, nouUsuari);
            System.out.println("S'ha registrat l'usuari " + nomUsuari + " correctament");
        } else {
            throw new Exception("Ja existeix un usuari amb aquesta contrasenya");
        }
    }

    //Tancar sessio usuari actiu
    public void tancarSessio() throws Exception {
        if (usuariActiu == null) {
            throw new Exception("Has d'haver iniciat sessio per a poder tancar-la");
        }
        usuariActiu = null;
        System.out.println("S'ha tancat sessio correctament");
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

    //Retorna true si l'usuari està autenticat o fals altrament
    public boolean usuariAutenticat() {
        return (usuariActiu != null);
    }

    //----------------------------------------------------------------------------------------------------------------------------
    //Crea un alfabet
    public void crearAlfabet(String idioma, String textAlfabet) throws Exception {
        usuariActiu.crearAlfabet(idioma, textAlfabet);
        System.out.println("S'ha creat correctament l'alfabet: " + idioma);
    }

    //Llista els alfabets
    public void llistarAlfabets() throws Exception {
        usuariActiu.llistarAlfabets();
    }

    public void eliminarAlfabet(String idioma) throws Exception {
        usuariActiu.eliminarAlfabet(idioma);
        System.out.println("S'ha eliminat correctament l'idioma " + idioma);
    }

    //----------------------------------------------------------------------------------------------------------------------------
    public void crearText(String idioma, String nomEntrada, String contingutEntrada) throws Exception {
        usuariActiu.crearText(idioma, nomEntrada, contingutEntrada);
        System.out.println("S'ha creat correctament el text " + nomEntrada);
    }

    public void crearLPF(String idioma, String nomEntrada, HashMap<String, Integer> contingutEntrada) throws Exception {
        usuariActiu.crearLPF(idioma, nomEntrada, contingutEntrada);
        System.out.println("S'ha creat correctament la llista de paraules amb frequencia " + nomEntrada);
    }

    public void llistarTexts() throws Exception {
        usuariActiu.llistarTexts();
    }

    public void llistarLPFs() throws Exception {
        usuariActiu.llistarLPFs();
    }
}
