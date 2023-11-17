package domini.controladors;

import domini.classes.Usuari;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe ControladorDomini. Gestiona la capa de domini del sistema.
 */

public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;
    private ControladorPersistencia ctrlPersistencia;
    private ControladorTeclat ctrlTeclat;
    private ControladorEntrada ctrlEntrada;
    private ControladorAlfabet ctrlAlfabet;

    private Usuari usuariActiu = null;

    //-------------------------------Contructora------------------------------//
    private ControladorDomini() {
        ctrlPersistencia = ControladorPersistencia.obtenirInstancia();
        ctrlTeclat = ControladorTeclat.obtenirInstancia();
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

    //--------------------------------Getters---------------------------------//

    /**
     * Retorna una llista de noms dels alfabets existents de l'usuari loggejat.
     * @return ArrayList<Integer> que representa la llista de noms dels alfabets.
     * @throws Exception Si l'usuari no ha inciat sessió
     */
    public ArrayList<Integer> getIdAlfabets() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus teclats");
        return ctrlAlfabet.getIdAlfabets();
    }

    /**
     * Retorna el nom de l'idioma de l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public String getNomAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus alfabets");
        return ctrlAlfabet.getNomAlfabet(idAlfabet);
    }

    /**
     * Retorna les lletres de l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Character> getLletresAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus alfabets");
        return ctrlAlfabet.getLletresAlfabet(idAlfabet);
    }

    /**
     * Retorna les entrades associades a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Integer> getIdEntradesVinculadesAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
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
        return ctrlEntrada.getNomEntrada(idEntrada);
    }

    /**
     * Retorna el teclat associat a l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return Integer que representa l'identificador del teclat associat a l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public ArrayList<Integer> getIdTeclatsVinculatsAEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlEntrada.getIdTeclatsVinculatsAEntrada(idEntrada);
    }

    /**
     * Retorna les tecles del teclat demanat, que pertany a l'usuari loggejat.
     * @param idTeclat Identificador del teclat
     * @return ArrayList<Character> que representa les tecles del teclat
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un teclat amb aquest identificador
     */
    public ArrayList<Character> getDistribucioTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getDistribucioTeclat(idTeclat);
    }

    /**
     * Retorna el número de files del teclat demanat, que pertany a l'usuari loggejat.
     * @param idTeclat Identificador del teclat
     * @return int que representa el número de files del teclat
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un teclat amb aquest identificador
     */
    public int getFilesTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
         return ctrlTeclat.getFilesTeclat(idTeclat);
    }

    /**
     * Retorna el número de columnes del teclat demanat, que pertany a l'usuari loggejat.
     * @param idTeclat Identificador del teclat
     * @return int que representa el número de columnes del teclat
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un teclat amb aquest identificador
     */
    public int getColumnesTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getColumnesTeclat(idTeclat);
    }

    /**
     * Retorna els identificadors dels teclats associats a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @return ArrayList<Integer> que representa els identificadors dels teclats associats a l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Integer> getTeclatsAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!ctrlAlfabet.getIdAlfabets().contains(idAlfabet)) throw new Exception("L'usuari no te un alfabet amb aquest identificador");
        ArrayList<Integer> idEntrades = ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
        ArrayList<Integer> idsTeclats = new ArrayList<>();
        for (Integer idEntrada : idEntrades) {
            idsTeclats.addAll(ctrlEntrada.getIdTeclatsVinculatsAEntrada(idEntrada));
        }
        return idsTeclats;
    }

    /**
     * Comprova si hi ha un usuari amb la sessió iniciada.
     * @return boolean que representa si hi ha un usuari amb la sessió iniciada.
     */
    public boolean usuariIniciatSessio() {
        return (usuariActiu != null);
    }

    //-------------------------Private class methods-------------------------//

    /**
     * Comprova si les lletres d'un alfabet estan repetides.
     * @param idUsuari Identificador de l'usuari
     * @param idEntrada Identificador de l'entrada
     * @return boolean amb valor true si l'usuari especificat te l'entrada especificada, false en cas contrari
     */
    private boolean entradaEsdUsuari(String idUsuari, Integer idEntrada) {
        ArrayList<Integer> idsAlfabets = ctrlAlfabet.getIdAlfabets();
        boolean esdUsuari = false;

        int i = 0;
        while (!esdUsuari && idsAlfabets.size() > i) {
            Integer idAlfabet = idsAlfabets.get(i);
            if (ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet).contains(idEntrada)) esdUsuari = true;
        }
        return esdUsuari;
    }

    private void carregarInfoUsuari(String nomUsuari) {
        //xx info ctrlPersistencia.getInfo();
        // carregar els alfabets
        // carregar els textos
        // carregar les lpf
        // carregar els teclats
    }

    private void guardarInfoUsuari() {
        // generar xx amb la info
        ctrlPersistencia.guardarInfoUsuari(usuariActiu.getNom(), xx);
    }

    private void resetInfoPrograma() {
        ctrlAlfabet.resetAlfabets();
        ctrlEntrada.resetEntrades();
        ctrlTeclat.resetTeclats();
    }

    //--------------------------------Usuari---------------------------------//

    /**
     * Crea un usuari a partir d'un nom i contrasenya.
     * @param nomUsuari Nom de l'usuari a crear
     * @param contrasenya Contrasenya de l'usuari
     * @throws Exception Si ja existeix un usuari amb aquest nom.
     */
    public void crearUsuari(String nomUsuari, String contrasenya) throws Exception{
        ArrayList<String> nomUsuarisExistents = ctrlPersistencia.getUsuarisExistents();
        usuariActiu.crearUsuari(nomUsuari, contrasenya, nomUsuarisExistents);
    }

    /**
     * Elimina un usuari existent.
     * @throws Exception Si s'intenta eliminar un usuari que no sigui el propi
     */
    public void eliminarUsuari() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder eliminar un usuari");
        ctrlPersistencia.eliminarUsuari(usuariActiu.getNom());
        tancarSessio();
    }

    /**
     * Inicia la sessio d'un usuari.
     * @param nomUsuari Nom de l'usuari
     * @param contrasenya Contrasenya de l'usuari
     * @throws Exception Si ja hi ha un usuari amb la sessió iniciada
     * @throws Exception Si la combinació usuari-contraseya és incorrecta
     */
    public void iniciarSessio(String nomUsuari, String contrasenya) throws Exception {
        if (usuariActiu != null) throw new Exception("Tanca la sessió actual per a poder iniciar sessio");
        HashMap<String, String> usuarisContrasenyes = ctrlPersistencia.getUsuarisContrasenyes();
        boolean dadesCorrectes = usuariActiu.verificarIniciSessio(nomUsuari, contrasenya, usuarisContrasenyes);
        if (dadesCorrectes) usuariActiu = new Usuari(nomUsuari, contrasenya);
        else throw new Exception("Usuari o contrasenya incorrectes");
        carregarInfoUsuari(nomUsuari);
    }

    /**
     * Tanca la sessio de l'usuari actiu.
     * @throws Exception Si no hi ha cap usuari amb la sessió iniciada
     */
    public void tancarSessio() throws Exception {
        if (usuariActiu == null) {
            throw new Exception("Has d'haver iniciat sessio per a poder tancar-la");
        }
        guardarInfoUsuari();
        resetInfoPrograma();
        usuariActiu = null;
    }

    /**
     * Modifica la contrasenya de l'usuari actiu.
     * @param contrasenya Nova contrasenya
     * @throws Exception Si no hi ha cap usuari amb la sessió iniciada
     */
    public void modificarContrasenyaUsuari(String contrasenya) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder modificar un usuari");
        usuariActiu.modificarContrasenya(contrasenya);
    }


    /**
     * Modifica el nom de l'usuari actiu.
     * @param nomUsuari Nova contrasenya
     * @throws Exception Si no hi ha cap usuari amb la sessió iniciada
     */
    public void modificarNomUsuari(String nomUsuari) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder modificar un usuari");
        usuariActiu.modificarNomUsuari(nomUsuari);
    }

    //--------------------------------Teclats---------------------------------//

    /**
     * Crea un teclat optimitzat per a dues mans a partir d'un alfabet i el nombre de files i columnes que ha de tenir el teclat.
     * @param idEntrada Identificador de l'entrada
     * @param idAlfabet Identificador de l'alfabet
     * @param files Nombre de files del teclat
     * @param columnes Nombre de columnes del teclat
     * @return Integer que representa l'identificador del teclat creat
     */
    public Integer crearTeclatDuesMans(Integer idEntrada, Integer idAlfabet, int files, int columnes) throws Exception {
        HashMap<String, Integer> lfp = ctrlEntrada.getLpfEntrada(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletresAlfabet(idAlfabet);
 
        Integer idTeclat = ctrlTeclat.crearTeclatDuesMans(lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.vincularTeclatAEntrada(idEntrada, idTeclat);
        return idTeclat;
    }

    /**
     * Crea un teclat optiitzat per a polzes a partir d'un alfabet i el nombre de files i columnes que ha de tenir el teclat.
     * @param idEntrada Identificador de l'entrada
     * @param idAlfabet Identificador de l'alfabet
     * @param files Nombre de files del teclat
     * @param columnes Nombre de columnes del teclat
     * @return Integer que representa l'identificador del teclat creat
     */
    public Integer crearTeclatPolzes(Integer idEntrada, Integer idAlfabet, int files, int columnes) throws Exception {
        HashMap<String, Integer> lfp = ctrlEntrada.getLpfEntrada(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletresAlfabet(idAlfabet);

        Integer idTeclat = ctrlTeclat.crearTeclatPolzes(lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.vincularTeclatAEntrada(idEntrada, idTeclat);
        return idTeclat;
    }
   
    public void eliminarTeclat(Integer idTeclat) throws Exception {
        ctrlTeclat.eliminarTeclat(idTeclat);
    }

    //--------------------------------Entrades---------------------------------//
    public void crearText(String nomEntrada, String contingutEntrada, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        ctrlEntrada.crearText(nomEntrada, contingutEntrada, lletres, idAlfabet);
    }

    public void importarText(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        ctrlEntrada.importarText(nomEntrada, localitzacio_fitxer, lletres, idAlfabet);
    }

    public void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        ctrlEntrada.crearLPF(nomEntrada, contingutEntrada, lletres, idAlfabet);
    }

    public void importarLPF(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres) throws Exception {
        ctrlEntrada.importarLPF(nomEntrada, localitzacio_fitxer, lletres);
    }

    public void eliminarEntrada(Integer idEntrada) throws Exception {
        int idAlfabet = ctrlEntrada.getIdAlfabetVinculatAEntrada(idEntrada);
        ctrlEntrada.eliminarEntrada(idEntrada);
        ctrlAlfabet.desvincularEntradaAlfabet(idAlfabet, idEntrada);
    }

    //--------------------------------Alfabets---------------------------------//
    public Integer crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) throws Exception {
        return ctrlAlfabet.crearAlfabet(nomAlfabet, lletres);
    }

    public void importarAlfabet(String nomAlfabet, String localitzacio_fitxer) throws Exception {
        ctrlAlfabet.importarAlfabet(nomAlfabet, localitzacio_fitxer);
    }

    // Modifica l'alfabet afegint-hi una lletra nova
    public void afegirLletraAlfabet(Integer idAlfabet, Character lletra) throws Exception {
        ctrlAlfabet.afegirLletraAlfabet(idAlfabet, lletra);
    }

    public void eliminarAlfabet(Integer idAlfabet) throws Exception {
        // Si s'elimina un alfabet es borren totes les entrades i teclats que deriven
        // d'aquest
        ArrayList<Integer> entradesVinculades = ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
        ctrlAlfabet.eliminarAlfabet(idAlfabet);
        for (Integer idEntrada : entradesVinculades) {
            ArrayList<Integer> teclatsVinculats = ctrlEntrada.getIdTeclatsVinculatsAEntrada(idEntrada);
            for (Integer idTeclat : teclatsVinculats) {
                ctrlTeclat.eliminarTeclat(idTeclat);
            }
            ctrlEntrada.eliminarEntrada(idEntrada);
        }
    }
}
