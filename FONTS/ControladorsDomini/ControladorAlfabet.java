package ControladorsDomini;

import Domini.Alfabet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorAlfabet {
    private HashMap<Integer, Alfabet> conjuntAlfabets;
    private Integer comptador = 0;

    //Instancia singleton del Controlador d'Alfabets
    private static ControladorAlfabet ctrlAlfabet;

    // ---------------------------------------------------------------------------- //
    //                              Constructora                                    //
    // ---------------------------------------------------------------------------- //
    public ControladorAlfabet() {
        conjuntAlfabets = new HashMap<>();
    }

    //Metode per obtenir l'instància singleton
    public static ControladorAlfabet obtenirInstancia() {
        if (ctrlAlfabet == null) {
            ctrlAlfabet = new ControladorAlfabet();
        }
        return ctrlAlfabet;
    }

    // ---------------------------------------------------------------------------- //
    //                Funcions de gestió del conjunt d'alfabets                     //
    // ---------------------------------------------------------------------------- //
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

    public ArrayList<Integer> eliminarAlfabet(Integer idAlfabet) throws Exception {
        if (!conjuntAlfabets.containsKey(idAlfabet)) throw new Exception("No existeix cap alfabet amb aquest identificador");
        ArrayList<Integer> idEntrades = conjuntAlfabets.get(idAlfabet).getEntrades();
        conjuntAlfabets.remove(idAlfabet);
        return idEntrades;
    }

    // ---------------------------------------------------------------------------- //
    //                                 Getters                                      //
    // ---------------------------------------------------------------------------- //
    public ArrayList<Character> getLletres(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getLletres();
    }

    public String getIdioma(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getIdioma();
    }

    public Integer getIdAlfabet(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getIdAlfabet();
    }

    public ArrayList<Integer> getEntrades(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getEntrades();
    }

    // ---------------------------------------------------------------------------- //
    //                                 Setters                                      //
    // ---------------------------------------------------------------------------- //
    public void setLletres(Integer idAlfabet, ArrayList<Character> lletres) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.setLletres(lletres);
    }

    public void setIdioma(Integer idAlfabet, String idioma) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.setIdioma(idioma);
    }

    // ---------------------------------------------------------------------------- //
    //                           Mètodes públics                                    //
    // ---------------------------------------------------------------------------- //
    public void associarEntrada(Integer idAlfabet, Integer idEntrada) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        alfabet.associarEntrada(idEntrada);
    }

    public void desvincularEntrada(Integer idAlfabet, Integer idEntrada) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");

        alfabet.desvincularEntrada(idEntrada);
    }

    // Comprova que les lletres d'una lps estiguin contingudes a l'alfabet. Retorna true si totes les lletres de totes les lps estan contingudes a l'alfabet.
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

    public void importarAlfabet(String idioma, String localitzacioFitxer) throws Exception {
        // llegir fitxer i guardar contingut a contingutFitxer
        Path path = Paths.get(localitzacioFitxer);
        String contingutFitxer = new String(Files.readAllBytes(path));

        // crear alfabet
        crearAlfabet(idioma, contingutFitxer);
    }

    public void afegirLletraAlfabet(Integer idAlfabet, Character lletra) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.afegirLletra(lletra);
    }
}
