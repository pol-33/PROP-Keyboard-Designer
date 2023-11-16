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
    private ControladorEntrada ctrlEntrada;
    private ControladorAlfabet ctrlAlfabet;

    private String usuariActiu = null;

    //-------------------------------Contructora------------------------------//
    private ControladorDomini() {
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
     * Retorna les tecles associat a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idTeclat
     * @return
     * @throws Exception
     */
    public ArrayList<Character> getDistribucioTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getDistribucioTeclat(idTeclat);
    }

    public int getFilesTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
         return ctrlTeclat.getFilesTeclat(idTeclat);
    }

    public int getColumnesTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getColumnesTeclat(idTeclat);
    }

    public Boolean sessioIniciada() {
        return (usuariActiu != null);
    }

    //--------------------------------Usuari---------------------------------//
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


    //--------------------------------Teclats---------------------------------//
    public Integer crearTeclatDuesMans(Integer idEntrada, Integer idAlfabet, int files, int columnes) throws Exception {
        HashMap<String, Integer> lfp = ctrlEntrada.getLpfEntrada(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletresAlfabet(idAlfabet);

        Integer idTeclat = ctrlTeclat.crearTeclatDuesMans(lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.vincularTeclatAEntrada(idEntrada, idTeclat);
        return idTeclat;
    }
    
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
