package ControladorsDomini;

import Domini.Alfabet;

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
        Alfabet nouAlfabet = new Alfabet(idioma, lletres_separades_comes);

        // afegim el teclat al conjunt
        conjuntAlfabets.put(idAlfabet, nouAlfabet);

        return idAlfabet;
    }

    public int eliminarAlfabet(Integer idAlfabet) throws Exception {
        if (!conjuntAlfabets.containsKey(idAlfabet)) return 1;
        conjuntAlfabets.remove(idAlfabet);
        return 0;
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

    public int getNumLletres(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getNumLletres();
    }

    public int getNumTextos(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getNumTextos();
    }

    public int getNumLPFs(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getNumLPFs();
    }

    public ArrayList<Integer> getTextos(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getTextos();
    }

    public ArrayList<Integer> getLPFs(Integer idAlfabet) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        return alfabet.getLPFs();
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
    public void afegirText(Integer idAlfabet, Integer idText) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.afegirText(idText);
    }

    public void afegirLPF(Integer idAlfabet, Integer idLPF) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.afegirLPF(idLPF);
    }

    public void eliminarText(Integer idAlfabet, Integer idText) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.eliminarText(idText);
    }

    public void eliminarLPF(Integer idAlfabet, Integer idLPF) throws Exception {
        Alfabet alfabet = conjuntAlfabets.get(idAlfabet);
        if (alfabet == null) throw new Exception("ERROR: no existeix cap alfabet amb aquest id");
        alfabet.eliminarLPF(idLPF);
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


}
