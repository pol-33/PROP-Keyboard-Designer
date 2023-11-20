package domini.controladors;

import domini.classes.Alfabet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Classe ControladorAlfabet. Gestiona un conjunt d'alfabets, i s'encarrega
 * de crear, modificar i eliminar els alfabets.
 */
public class ControladorAlfabet {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    private HashMap<Integer, Alfabet> conjuntAlfabets;
    private Integer comptador;
    private static ControladorAlfabet ctrlAlfabet;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructora de la clase. Inicialitza el conjunt d'alfabets del sistema.
     */
    public ControladorAlfabet() {
        conjuntAlfabets = new HashMap<>();
        comptador = 0;
    }

    /**
     * Obtenir la instància singleton de ControladorAlfabet.
     * @return La instància única de ControladorAlfabet.
     */
    public static ControladorAlfabet obtenirInstancia() {
        if (ctrlAlfabet == null) {
            ctrlAlfabet = new ControladorAlfabet();
        }
        return ctrlAlfabet;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Crear i eliminar
    // ---------------------------------------------------------------------------- //

    /**
     * Crea un nou alfabet a partir d'un conjunt de lletres separades per comes.
     * @param nomAlfabet Nom de l'alfabet
     * @param lletres Conjunt de lletres d'un sol caracter i no repetides de l'alfabet separades per comes
     * @return Retorna l'identificador de l'alfabet creat
     * @throws Exception Si s'intenta crear un alfabet amb lletres de multiples caracters
     *      * @throws Exception Si s'intenta crear un alfabet amb lletres repetides
     *      * @throws Exception Si s'intenta crear un alfabet sense cap lletra
     */
    public Integer crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) throws Exception {

        Integer idAlfabet = comptador;   // de moment estaran ordenats
        comptador++;

        Alfabet nouAlfabet = new Alfabet(nomAlfabet, idAlfabet, lletres);

        conjuntAlfabets.put(idAlfabet, nouAlfabet);

        return idAlfabet;
    }

    /**
     * Elimina un alfabet del conjunt d'alfabets.
     * @param idAlfabet Identificador de l'alfabet a eliminar
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void eliminarAlfabet(Integer idAlfabet) throws Exception {
        if (!conjuntAlfabets.containsKey(idAlfabet)) throw new Exception("No existeix cap alfabet amb aquest identificador");
        conjuntAlfabets.remove(idAlfabet);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     * Obte les lletres de l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @return ArrayList amb les lletres de l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public ArrayList<Character> getLletresAlfabet(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getLletres();
    }

    /**
     * Obte l'idioma de l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @return String amb l'idioma de l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public String getNomAlfabet(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getNom();
    }

    /**
     * Obte les entrades associades a l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @return ArrayList amb els identificadors de les entrades associades a l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public ArrayList<Integer> getEntradesVinculadesAlfabet(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getEntrades();
    }

    /**
     * Retorna una llista amb els identificadors de tots els alfabets.
     * @return ArrayList amb els identificadors de tots els alfabets.
     */
    public ArrayList<Integer> getIdAlfabets() {
        ArrayList<Integer> llistaIds = new ArrayList<>(conjuntAlfabets.keySet());
        return llistaIds; 
    }

    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //

    /**
     * Modifica el nom de l'idioma de l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @param idioma Nou nom de l'idioma de l'alfabet
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void setNomAlfabet(Integer idAlfabet, String idioma) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.setNom(idioma);
    }

    // ---------------------------------------------------------------------------- //
    //                                   Metodes publics
    // ---------------------------------------------------------------------------- //
    /**
     * Associa una entrada a l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @param idEntrada Identificador de l'entrada a associar
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void vincularEntradaAlfabet(Integer idAlfabet, Integer idEntrada) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        alfabet.vincularEntrada(idEntrada);
    }

    /**
     * Desvincula una entrada de l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @param idEntrada Identificador de l'entrada a desvincular
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    public void desvincularEntradaAlfabet(Integer idAlfabet, Integer idEntrada) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        alfabet.desvincularEntrada(idEntrada);
    }

    /**
     * Importa un alfabet a partir d'un fitxer de text que conte lletres d'un sol caracter i no repetides separades per comes.
     * @param idioma Nom de l'idioma de l'alfabet
     * @param localitzacioFitxer String amb el path del fitxer de text
     * @throws Exception Si s'intenta crear un alfabet amb lletres de multiples caracters
     * @throws Exception Si s'intenta crear un alfabet amb lletres repetides
     * @throws Exception Si s'intenta crear un alfabet sense cap lletra
     */
    public void importarAlfabet(String idioma, String localitzacioFitxer) throws Exception {
        Path path = Paths.get(localitzacioFitxer);
        String contingutFitxer = new String(Files.readAllBytes(path));
        crearAlfabet(idioma, csvACharArray(contingutFitxer));
    }

    /**
     * Afegeix una nova lletra a l'alfabet.
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


    /**
     * Reinicialitzar el conjunt d'alfabets gestionats per aquest controlador.
     */
    public void resetAlfabets(){
        conjuntAlfabets = new HashMap<>();
    }

    // ---------------------------------------------------------------------------- //
    //                                   Metodes privats
    // ---------------------------------------------------------------------------- //
    /**
     * Comprova que les lletres d'una lpf estiguin contingudes a l'alfabet.
     * @param idAlfabet Identificador de l'alfabet
     * @param lpf_map HashMap amb les paraules de la lpf i el seu nombre d'aparicions
     * @return Retorna true si totes les lletres de totes les lps estan contingudes a l'alfabet, fals en cas contrari
     * @throws Exception Si no existeix cap alfabet amb l'identificador donat
     */
    private boolean lletres_no_contingudes(Integer idAlfabet, HashMap<String, Integer> lpf_map) throws Exception {
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
     * Comprova si hi ha lletres repetides en un conjunt de caracters.
     * @param lletres ArrayList de lletres a comprovar.
     * @return True si hi ha lletres repetides en un conjunt de caracters, false altrament.
     */
    private boolean lletres_repetides(ArrayList<Character> lletres) {
        HashSet<Character> set = new HashSet<>(lletres);
        return set.size() != lletres.size();
    }

     /**
     * Obte un ArrayList de lletres a partir d'un String no buit de lletres no repetides separades per comes.
     * @param lletresSeparadesPerComes String no buit de lletres no repetides separades per comes.
     * @return ArrayList de lletres a partir d'un String no buit de lletres no repetides separades per comes.
     * @throws Exception Si les lletres son de multiples caracters
     * @throws Exception Si l'alfabet no ta cap lletra
     * @throws Exception Si s'han introduit lletres repetides
     */
    private ArrayList<Character> csvACharArray (String lletresSeparadesPerComes) throws Exception {
        String[] lletres_separades = lletresSeparadesPerComes.split(",");
        ArrayList<Character> lletres = new ArrayList<Character>();
        for (String s : lletres_separades) {
            if (s.length() > 1) throw new Exception("ERROR: les lletres nomas poden tenir un caracter");
            if (s.length() != 0) lletres.add(s.charAt(0));
        }
        if (lletres.isEmpty()) throw new Exception("ERROR: l'alfabet ha de tenir almenys una lletra");
        // Verifica si hi ha lletres repetides.
        if(lletres_repetides(lletres)) throw new Exception("ERROR: s'han introduit lletres repetides");
        return lletres;
    }
}
