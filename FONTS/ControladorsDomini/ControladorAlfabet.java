package ControladorsDomini;

import Domini.Alfabet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe ControladorAlfabet. Gestiona un conjunt d'alfabets, i s'encarrega
 * de crear, modificar i eliminar els alfabets.
 */

public class ControladorAlfabet {
    private HashMap<Integer, Alfabet> conjuntAlfabets;
    private Integer comptador;

    //Instancia singleton del Controlador d'Alfabets
    private static ControladorAlfabet ctrlAlfabet;

    // ---------------------------------------------------------------------------- //
    //                              Constructora                                    //
    // ---------------------------------------------------------------------------- //
    public ControladorAlfabet() {
        conjuntAlfabets = new HashMap<>();
        comptador = 0;
    }

    // Metode per obtenir l'instancia singleton
    public static ControladorAlfabet obtenirInstancia() {
        if (ctrlAlfabet == null) {
            ctrlAlfabet = new ControladorAlfabet();
        }
        return ctrlAlfabet;
    }

    // ---------------------------------------------------------------------------- //
    //                Funcions de gestio del conjunt d'alfabets                     //
    // ---------------------------------------------------------------------------- //
    /**
     * Crea un nou alfabet a partir d'un conjunt de lletres separades per comes.
     * @param idioma Nom de l'idioma de l'alfabet
     * @param lletres_separades_comes Conjunt de lletres d'un sol caracter i no repetides de l'alfabet separades per comes
     * @return Retorna l'identificador de l'alfabet creat
     * @throws Exception Si s'intenta crear un alfabet amb lletres de multiples caracters
     *      * @throws Exception Si s'intenta crear un alfabet amb lletres repetides
     *      * @throws Exception Si s'intenta crear un alfabet sense cap lletra
     */
    public Integer crearAlfabet(String idioma, String lletres_separades_comes) throws Exception {

        // creem un identificador per al nou alfabet
        Integer idAlfabet = comptador;   // de moment estaran ordenats
        comptador++;

        // creem un alfabet nou
        Alfabet nouAlfabet = new Alfabet(idioma, idAlfabet, lletres_separades_comes);

        // afegim el teclat al conjunt
        conjuntAlfabets.put(idAlfabet, nouAlfabet);

        return idAlfabet;
    }

    /**
     * Elimina un alfabet del conjunt d'alfabets
     * @param idAlfabet Identificador de l'alfabet a eliminar
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void eliminarAlfabet(Integer idAlfabet) throws Exception {
        if (!conjuntAlfabets.containsKey(idAlfabet)) throw new Exception("No existeix cap alfabet amb aquest identificador");
        conjuntAlfabets.remove(idAlfabet);
    }

    // ---------------------------------------------------------------------------- //
    //                                 Getters                                      //
    // ---------------------------------------------------------------------------- //

    /**
     * Obte les lletres de l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @return ArrayList amb les lletres de l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public ArrayList<Character> getLletres(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getLletres();
    }

    /**
     * Obte l'idioma de l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @return String amb l'idioma de l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public String getIdioma(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getIdioma();
    }

    /**
     * Obte les entrades associades a l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @return ArrayList amb els identificadors de les entrades associades a l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public ArrayList<Integer> getEntrades(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getEntrades();
    }

    // ---------------------------------------------------------------------------- //
    //                                 Setters                                      //
    // ---------------------------------------------------------------------------- //

    /**
     * Modifica el nom de l'idioma de l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @param idioma Nou nom de l'idioma de l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void setIdioma(Integer idAlfabet, String idioma) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.setIdioma(idioma);
    }

    // ---------------------------------------------------------------------------- //
    //                           Metodes publics                                    //
    // ---------------------------------------------------------------------------- //

    /**
     * Associa una entrada a l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @param idEntrada Identificador de l'entrada a associar
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void associarEntrada(Integer idAlfabet, Integer idEntrada) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        alfabet.associarEntrada(idEntrada);
    }

    /**
     * Desvincula una entrada de l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @param idEntrada Identificador de l'entrada a desvincular
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void desvincularEntrada(Integer idAlfabet, Integer idEntrada) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        alfabet.desvincularEntrada(idEntrada);
    }

    /**
     * Comprova que les lletres d'una lpf estiguin contingudes a l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @param lpf_map HashMap amb les paraules de la lpf i el seu nombre d'aparicions
     * @return Retorna true si totes les lletres de totes les lps estan contingudes a l'alfabet, fals en cas contrari
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public boolean lletres_no_contingudes(Integer idAlfabet, HashMap<String, Integer> lpf_map) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        ArrayList<Character> lletres = alfabet.getLletres();
        for (String s : lpf_map.keySet()) {
            for (int i = 0; i < s.length(); i++) {
                if (!lletres.contains(s.charAt(i))) return false;
            }
        }
        return true;
    }

    /**
     * Importa un alfabet a partir d'un fitxer de text que conte lletres d'un sol caracter i no repetides separades per comes
     * @param idioma Nom de l'idioma de l'alfabet
     * @param localitzacioFitxer String amb el path del fitxer de text
     * @throws Exception Si s'intenta crear un alfabet amb lletres de multiples caracters
     * @throws Exception Si s'intenta crear un alfabet amb lletres repetides
     * @throws Exception Si s'intenta crear un alfabet sense cap lletra
     */
    public void importarAlfabet(String idioma, String localitzacioFitxer) throws Exception {
        // llegir fitxer i guardar contingut a contingutFitxer
        Path path = Paths.get(localitzacioFitxer);
        String contingutFitxer = new String(Files.readAllBytes(path));

        crearAlfabet(idioma, contingutFitxer);
    }

    /**
     * Afegeix una nova lletra a l'alfabet
     * @param idAlfabet Identificador de l'alfabet
     * @param lletra Lletra a afegir
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     * @throws Exception Si la lletra ja pertany a l'alfabet
     * @throws Exception Si la lletra es null
     */
    public void afegirLletraAlfabet(Integer idAlfabet, Character lletra) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.afegirLletra(lletra);
    }
}
