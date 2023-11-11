package ControladorsDomini;

import java.util.ArrayList;
import java.util.HashMap;


public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;
    private ControladorTeclat ctrlTeclat = new ControladorTeclat();
    private ControladorUsuari ctrlUsuari = new ControladorUsuari();

    private ControladorEntrada ctrlEntrada = new ControladorEntrada();

    // Mapa con todos los usuarios registrados en el sistema
    private String usuariActiu = null;

    private ControladorDomini() {
    }

    //Metode per obtenir l'instància singleton
    public static ControladorDomini obtenInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorDomini();
        }
        return ctrl;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters                             
    // ---------------------------------------------------------------------------- //
    
    public ArrayList<String> getLlistaUsuaris() {
        return ctrlUsuari.getLlistaUsuaris();
    }
    public ArrayList<Integer> getLlistaTeclat() {
        return ctrlTeclat.getLlistaTeclats();
    }
    public ArrayList<Integer> getLlistaEntrades() {
        return ctrlEntrada.getLlistaEntrades();
    }
    public ArrayList<Integer> getLlistaAlfabets() {
        return ctrlAlfabet.getLlistaAlfabets();
    }
    public Boolean usuariIniciatSessio() {
        return (usuariActiu != null);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Usuaris                             
    // ---------------------------------------------------------------------------- //
    public void iniciarSessio(String nomUsuari, String contrasenya) throws Exception {
        if (usuariActiu != null) throw new Exception("Tanca la sessió actual per a poder iniciar sessio");
        Boolean resultat = ctrlUsuari.comprovaContrasenya(nomUsuari, contrasenya);
        if (resultat) {
            usuariActiu = nomUsuari;
        }
        else throw new Exception("Contrasenya Incorrecta");
    }
    public void crearUsuari(String nomUsuari, String contrasenya) throws Exception{
        ctrlUsuari.crearUsuari(nomUsuari, contrasenya);
    }
    public void tancarSessio() throws Exception {
        if (usuariActiu == null) {
            throw new Exception("Has d'haver iniciat sessio per a poder tancar-la");
        }
        usuariActiu = null;
        System.out.println("S'ha tancat sessio correctament");
    }

    // ---------------------------------------------------------------------------- //
    //                                   Teclats                             
    // ---------------------------------------------------------------------------- //
    public Integer crearTeclatDuesMans(Integer idEntrada, Integer idAlfabet, int files, int columnes) {
        HashMap<String, Integer> lfp = ctrlEntrada.getLPF(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletres(idAlfabet);

        Integer idTeclat = ctrlTeclat.crearTeclatDuesMans(lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.asociarTeclat(idEntrada, idTeclat);
        return idTeclat;
    }
    public Integer crearTeclatPolzes(Integer idEntrada, Integer idAlfabet, int files, int columnes) {
        HashMap<String, Integer> lfp = ctrlEntrada.getLPF(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletres(idAlfabet);

        Integer idTeclat = ctrlTeclat.crearTeclatPolzes(lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.asociarTeclat(idEntrada, idTeclat);
        return idTeclat;
    }
    public void eliminarTeclat(Integer idTeclat) {
        ctrlTeclat.eliminarTeclat(idTeclat);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Entrada                             
    // ---------------------------------------------------------------------------- //
    public void crearText(String nomEntrada, String contingutEntrada, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.crearText(nomEntrada, contingutEntrada, lletres);
    }

    public void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.crearLPF(nomEntrada, contingutEntrada, lletres);
    }
    public void eliminarEntrada(Integer id) throws Exception {
        ctrlEntrada.eliminarEntrada(id);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Alfabet                             
    // ---------------------------------------------------------------------------- //
    public Integer crearAlfabet()
    public Integer eliminarAlfabet()

}
