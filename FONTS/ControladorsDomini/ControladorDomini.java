package ControladorsDomini;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe ControladorDomini. Gestiona la capa de domini del sistema.
 */

public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;
    private ControladorTeclat ctrlTeclat;
    private ControladorUsuari ctrlUsuari;
    private ControladorEntrada ctrlEntrada;
    private ControladorAlfabet ctrlAlfabet;

    private String usuariActiu = null;

    // ---------------------------------------------------------------------------- //
    //                              Constructora                                    //
    // ---------------------------------------------------------------------------- //
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

    /**
     * Retorna una llista de noms dels usuaris existents.
     * @return ArrayList<String> que representa la llista de noms dels usuaris.
     */
    public ArrayList<String> getLlistaUsuaris() {
        return ctrlUsuari.getLlistaUsuaris();
    }

    /**
     * Retorna una llista de noms dels alfabets existents de l'usuari loggejat.
     * @return ArrayList<Integer> que representa la llista de noms dels alfabets.
     * @throws Exception Si l'usuari no ha inciat sessió
     */
    public ArrayList<Integer> getAlfabets() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus teclats");
        return ctrlUsuari.getAlfabets(usuariActiu);
    }

    /**
     * Retorna el nom de l'idioma de l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public String getIdiomaAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus alfabets");
        if (!ctrlUsuari.getAlfabets(usuariActiu).contains(idAlfabet)) throw new Exception("L'usuari no te un alfabet amb aquest identificador");
        return ctrlAlfabet.getIdioma(idAlfabet);
    }

    /**
     * Retorna les lletres de l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Character> getLletresAlfabet(Integer idAlfabet) {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus alfabets");
        if (!ctrlUsuari.getAlfabets(usuariActiu).contains(idAlfabet)) throw new Exception("L'usuari no te un alfabet amb aquest identificador");
        return ctrlAlfabet.getLletres(idAlfabet);
    }

    /**
     * Retorna les entrades associades a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Integer> getEntrades(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!ctrlUsuari.getAlfabets(usuariActiu).contains(idAlfabet)) throw new Exception("L'usuari no te un alfabet amb aquest identificador");
        return ctrlAlfabet.getEntrades(idAlfabet);
    }

    /**
     * Retorna el tipus de l'entrada demanada, "text" o "lpf" que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return String que representa el tipus de l'entrada. Pot ser "text" o "lpf"
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public String getTypeEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te una entrada amb aquest identificador");
        return ctrlEntrada.getTypeEntrada(idEntrada);
    }

    /**
     * Retorna el nom de l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return String que representa el nom de l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public String getNomEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te una entrada amb aquest identificador");
        return ctrlEntrada.getNomEntrada(idEntrada);
    }

    /**
     * Retorna el contingut de l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return String que representa el contingut de l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public String getContingutEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te una entrada amb aquest identificador");
        return ctrlEntrada.getContingutEntrada(idEntrada);
    }

    /**
     * Retorna el teclat associat a l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return Integer que representa l'identificador del teclat associat a l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public Integer getTeclat(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te una entrada amb aquest identificador");
        return ctrlEntrada.getTeclat(idEntrada);
    }

    /**
     * Retorna les tecles associat a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idTeclat
     * @return
     * @throws Exception
     */
    public ArrayList<Character> getTecles(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        Integer idEntrada = ctrlTeclat.getEntrada(idTeclat);
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te un teclat amb aquest identificador");
        return ctrlTeclat.getTecles(idTeclat);
    }

    public int getFiles(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        Integer idEntrada = ctrlTeclat.getEntrada(idTeclat);
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te un teclat amb aquest identificador");
        return ctrlTeclat.getFiles(idTeclat);
    }

    public int getColumnes(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        Integer idEntrada = ctrlTeclat.getEntrada(idTeclat);
        if (!entradaEsdUsuari(usuariActiu, idEntrada)) throw new Exception("L'usuari no te un teclat amb aquest identificador");
        return ctrlTeclat.getColumnes(idTeclat);
    }

    public ArrayList<Integer> getTeclatsAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!ctrlUsuari.getAlfabets(usuariActiu).contains(idAlfabet)) throw new Exception("L'usuari no te un alfabet amb aquest identificador");
        ArrayList<Integer> idEntrades = ctrlAlfabet.getEntrades(idAlfabet);
        ArrayList<Integer> idsTeclats = new ArrayList<>();
        for (Integer idEntrada : idEntrades) {
            idsTeclats.add(ctrlEntrada.getTeclat(idEntrada));
        }
        return idsTeclats;
    }

    public Boolean usuariIniciatSessio() {
        return (usuariActiu != null);
    }

    // ---------------------------------------------------------------------------- //
    //                       Private class methods                                  //
    // ---------------------------------------------------------------------------- //

    private boolean entradaEsdUsuari(String idUsuari, Integer idEntrada) {
        ArrayList<Integer> idsAlfabets = ctrlUsuari.getAlfabets(idUsuari);
        boolean esdUsuari = false;

        int i = 0;
        while (!esdUsuari && idsAlfabets.size() > i) {
            Integer idAlfabet = idsAlfabets.get(i);
            if (ctrlAlfabet.getEntrades(idAlfabet).contains(idEntrada)) esdUsuari = true;
        }
        return esdUsuari;
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
    //                                   Teclats                                    //
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
