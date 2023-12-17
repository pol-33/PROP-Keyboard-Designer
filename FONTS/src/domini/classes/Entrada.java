package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Entrada. Representa un element abstracte del sistema que gestiona informació relacionada amb teclats i alfabets.
 */

public abstract class Entrada {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    protected String nom;
    protected Integer id;
    protected HashMap<String, Integer> lpf;
    protected ArrayList<Integer> idTeclats;
    protected Integer idAlfabet;
    protected Integer tipus;

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //


    /**
     * Retorna els idTeclats
     * @return ArrayList<Integer> amb els ids 
     */
    public ArrayList<Integer> getIdTeclats() {
        return new ArrayList<>(idTeclats);
    }


    /**
     * Retorna el nom de l'entrada.
     * @return String amb el nom de l'entrada.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retorna la LPF de l'entrada.
     * @return HashMap de String i Integer.
     */
    public HashMap<String, Integer> getLPF() {
        return this.lpf;
    }

    /**
     * Retorna els teclats vinculats a l'entrada.
     * @return ArrayList d'Integer amb els identificadors dels teclats vinculats a l'entrada.
     */
    public ArrayList<Integer> getTeclatsVinculats() {
        return idTeclats;
    }

    /**
     * Retorna l'identificador de l'alfabet vinculat a l'entrada.
     * @return Integer amb l'identificador de l'alfabet vinculat a l'entrada.
     */
    public Integer getIdAlfabetVinculat() {
        return idAlfabet;
    }

    public Integer getTipus() {
        return tipus;
    }



    public String getText() {
        return null;
    }
    
    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //

    /**
     * Vincula un teclat a l'entrada.
     * @param idTeclat Identificador del teclat a vincular.
     * @throws Exception Si l'entrada ja té aquest teclat vinculat.
     */
    public void vincularTeclat(Integer idTeclat) throws Exception {
        if (idTeclats.contains(idTeclat)) {
            throw new Exception("L'entrada ja té aquest teclat vinculat");
        }
        idTeclats.add(idTeclat);
    }

    /**
     * Desvincula un teclat de l'entrada.
     * @param idTeclat Identificador del teclat a desvincular.
     * @throws Exception Si l'enrada no tenia aquest teclat vinculat.
     */
    public void desvincularTeclat(Integer idTeclat) throws Exception {
        if (idTeclats.contains(idTeclat)) {
            idTeclats.remove(idTeclat);
        }
        else throw new Exception("L'entrda no tenia aquest teclat vinculat");
    }
}
