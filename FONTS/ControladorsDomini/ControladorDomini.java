package ControladorsDomini;

import java.util.ArrayList;
import java.util.HashMap;


public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;
    private ControladorTeclat ctrlTeclat;
    private ControladorUsuari ctrlUsuari;
    private ControladorEntrada ctrlEntrada;
    private ControladorAlfabet ctrlAlfabet;

    private String usuariActiu = null;

    private ControladorDomini() {
        ctrlTeclat = ControladorTeclat.obtenirInstancia();
        ctrlUsuari = ControladorUsuari.obtenirInstancia();
        ctrlEntrada = ControladorEntrada.obtenirInstancia();
        ctrlAlfabet = ControladorAlfabet.obtenirInstancia();
    }

    //Metode per obtenir l'instància singleton
    public static ControladorDomini obtenirInstancia() {
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

    public ArrayList<Integer> getAlfabets(String nomUsuari) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlUsuari.getAlfabets(nomUsuari);
    }

    public String getIdiomaAlfabet(String nomUsuari, Integer idAlfabet) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlAlfabet.getIdioma(idAlfabet);
    }

    public ArrayList<Character> getLletresAlfabet(String nomUsuari, Integer idAlfabet) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlAlfabet.getLletres(idAlfabet);
    }

    public ArrayList<Integer> getEntrades(String nomUsuari, Integer idAlfabet) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure les teves entrades si no has iniciat sessio");
        return ctrlAlfabet.getEntrades(idAlfabet);
    }

    public String getTypeEntrada(String nomUsuari, Integer idEntrada) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure les teves entrades si no has iniciat sessio");
        return ctrlEntrada.getTypeEntrada(idEntrada);
    }

    public String getNomEntrada(String nomUsuari, Integer idEntrada) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure les teves entrades si no has iniciat sessio");
        return ctrlEntrada.getNomEntrada(idEntrada);
    }

    public String getContingutEntrada(String nomUsuari, Integer idEntrada) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure les teves entrades si no has iniciat sessio");
        return ctrlEntrada.getContingutEntrada(idEntrada);
    }

    public Integer getTeclat(String nomUsuari, Integer idEntrada) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlEntrada.getTeclat(idEntrada);
    }

    public ArrayList<Character> getTecles(String nomUsuari, Integer idTeclat) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlTeclat.getTecles(idTeclat);
    }

    public int getFiles(String nomUsuari, Integer idTeclat) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlTeclat.getFiles(idTeclat);
    }

    public int getColumnes(String nomUsuari, Integer idTeclat) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        return ctrlTeclat.getColumnes(idTeclat);
    }

    public ArrayList<Integer> getTeclatsAlfabet(String nomUsuari, Integer idAlfabet) {
        if (!usuariActiu.equals(nomUsuari)) throw new Exception("No pots veure els teus teclats si no has iniciat sessio");
        ArrayList<Integer> idEntrades = ctrlAlfabet.getEntrades(idAlfabet);
        ArrayList<Integer> idTeclats = new ArrayList<>();
        for (Integer idEntrada : idEntrades) {
            idTeclats.add(ctrlEntrada.getTeclat(idEntrada));
        }
        return idTeclats;
    }

    public Boolean usuariIniciatSessio() {
        return (usuariActiu != null);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Usuaris                             
    // ---------------------------------------------------------------------------- //
    public void crearUsuari(String nomUsuari, String contrasenya) throws Exception{
        ctrlUsuari.crearUsuari(nomUsuari, contrasenya);
    }

    public void eliminarUsuari(String nomUsuari) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder eliminar un usuari");
        if (usuariActiu.equals(nomUsuari)) {
            ArrayList<Integer> idsAlfabet = ctrlUsuari.getAlfabets(nomUsuari);
            for (Integer idAlfabet : idsAlfabet) {
                ctrlAlfabet.eliminarAlfabet(idAlfabet);
            }
            ctrlUsuari.eliminarUsuari(nomUsuari);
            usuariActiu = null;
        }
        else throw new Exception("No pots eliminar un usuari que no sigui el teu");

    }

    public void iniciarSessio(String nomUsuari, String contrasenya) throws Exception {
        if (usuariActiu != null) throw new Exception("Tanca la sessió actual per a poder iniciar sessio");
        Boolean resultat = ctrlUsuari.comprovaContrasenya(nomUsuari, contrasenya);
        if (resultat) {
            usuariActiu = nomUsuari;
        }
        else throw new Exception("Contrasenya Incorrecta");
    }
    public void tancarSessio() throws Exception {
        if (usuariActiu == null) {
            throw new Exception("Has d'haver iniciat sessio per a poder tancar-la");
        }
        usuariActiu = null;
    }

    public void modificarUsuari(String nomUsuari, String contrasenya) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder modificar un usuari");
        if (usuariActiu.equals(nomUsuari)) {
            ctrlUsuari.modificarUsuari(nomUsuari, contrasenya);
        }
        else throw new Exception("No pots modificar un usuari que no sigui el teu");
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

    public void modificarTeclat(Integer idTeclat, Integer idEntrada, Integer idAlfabet, int files, int columnes) {
        HashMap<String, Integer> lfp = ctrlEntrada.getLPF(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletres(idAlfabet);

        ctrlTeclat.modificarTeclat(idTeclat, lfp, alfabet, idEntrada, files, columnes);
    }

    public ArrayList<Character> getLletresTeclat(Integer idTeclat) {
        return ctrlTeclat.getLletresTeclat(idTeclat);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Entrada                             
    // ---------------------------------------------------------------------------- //
    public void crearText(String nomEntrada, String contingutEntrada, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.crearText(nomEntrada, contingutEntrada, lletres);
    }

    public void importarText(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.importarText(nomEntrada, localitzacio_fitxer, lletres);
    }

    public void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.crearLPF(nomEntrada, contingutEntrada, lletres);
    }

    public void importarLPF(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.importarLPF(nomEntrada, localitzacio_fitxer, lletres);
    }

    public void eliminarEntrada(Integer id) throws Exception {
        int idAlfabet = ctrlEntrada.getAlfabet(id);
        ctrlEntrada.eliminarEntrada(id);
        ctrlAlfabet.desvincularEntrada(idAlfabet, id);
    }

    // modificar text i lpf?

    // ---------------------------------------------------------------------------- //
    //                                   Alfabet                             
    // ---------------------------------------------------------------------------- //
    public Integer crearAlfabet(String idioma, String lletres_separades_comes) throws Exception {
        return ctrlAlfabet.crearAlfabet(idioma, lletres_separades_comes);
    }

    public void importarAlfabet(String idioma, String localitzacio_fitxer) throws Exception {
        ctrlAlfabet.importarAlfabet(idioma, localitzacio_fitxer);
    }

    // Modifica l'alfabet afegint-hi una lletra nova
    public void afegirLletraAlfabet(Integer idAlfabet, Character lletra) throws Exception {
        ctrlAlfabet.afegirLletraAlfabet(idAlfabet, lletra);
    }

    public void eliminarAlfabet(Integer idAlfabet) throws Exception {
        ArrayList<Integer> entradesContingudes = ctrlAlfabet.getEntrades(idAlfabet);
        ctrlAlfabet.eliminarAlfabet(idAlfabet);
        for (Integer idEntrada : entradesContingudes) {
            ctrlEntrada.eliminarEntrada(idEntrada);
        }
    }

}
