package domini.controladors;

import java.util.ArrayList;
import java.util.HashMap;

import domini.classes.Teclat;


 /**
 * Classe ControladorTeclat. Gestiona un conjunt de teclats, i s'encarrega
 * de crear, modificar i eliminar els teclats. Coneix a ControladorAlgoritme,
 * i parla amb ell per tal de crear les distribucions optimes per a cada
 * entrada.
 */ 
public class ControladorTeclat {
    
    private HashMap<Integer, Teclat> conjuntTeclats;
    private Integer contador = 0;

    private static ControladorTeclat ctrlTeclats;

    //-------------------------------Contructora------------------------------//
    /** 
     *
     * Constructora de la clase. 
     *
     */
    public ControladorTeclat() { 

        conjuntTeclats = new HashMap<>();
    }

    /** 
     *
     * It is a constructor. 
     *
     */
    public static ControladorTeclat obtenirInstancia() { 

        if (ctrlTeclats == null) {
            ctrlTeclats = new ControladorTeclat();
        }
        return ctrlTeclats;
    }

    //---------------------------Metodes publics---------------------------//
    /** 
     *
     * Crear teclat dues mans
     *
     * @param lpf  Llista de paraules amb frequencies per a calcular la distribucio optima 
     * @param alfabet  Conjunt de lletres a colocar en el teclat 
     * @param uidEntrada  Identificador de l'entrada d'on prove la lpf
     * @param files  Nombre de files al layout del teclat 
     * @param columnes  Nombre de columnes del layout del teclat
     * @return Enter que representa el identificador del teclat creat
     */
    public Integer crearTeclatDuesMans(String nom, HashMap<String, Integer> lpf, ArrayList<Character> alfabet, 
     Integer uidEntrada, Integer files, Integer columnes) {

        
        // creem un identificador per al nou teclat
        Integer idTeclat = contador;   // de moment estaran ordenats
        contador++;

        // necessitem calcular un ArrayList<Character>, la distribucio del teclat 
        ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
        ArrayList<Character> teclesAmbLletres = ctrlAlgoritme.calcularDistribucioDuesMans(lpf, alfabet, files, columnes);

        // creem un teclat nou
        Teclat nouTeclat = new Teclat(nom, teclesAmbLletres, uidEntrada, files, columnes, idTeclat);

        // afegim el teclat al conjunt
        conjuntTeclats.put(idTeclat, nouTeclat);

        return idTeclat;
    }

    /** 
     *
     * Crear teclat polzes
     *
     * @param lpf  Llista de paraules amb frequencies per a calcular la distribucio optima 
     * @param alfabet  Conjunt de lletres a colocar en el teclat 
     * @param uidEntrada  Identificador de l'entrada d'on prove la lpf
     * @param files  Nombre de files al layout del teclat 
     * @param columnes  Nombre de columnes del layout del teclat
     * @return Enter que representa el identificador del teclat creat
     */
    public Integer crearTeclatPolzes(String nom, HashMap<String, Integer> lpf, ArrayList<Character> alfabet, 
     Integer uidEntrada, Integer files, Integer columnes) {


        // creem un identificador per al nou teclat
        Integer idTeclat = contador;   // de moment estaran ordenats
        contador++;

        // necessitem calcular un ArrayList<Character>, la distribucio del teclat
        ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
        ArrayList<Character> teclesAmbLletres = ctrlAlgoritme.calcularDistribucioPolzes(lpf, alfabet, files, columnes);

        // creem un teclat nou
        Teclat nouTeclat = new Teclat(nom, teclesAmbLletres, uidEntrada, files, columnes, idTeclat);

        // afegim el teclat al conjunt
        conjuntTeclats.put(idTeclat, nouTeclat);

        return idTeclat;
    }

    /** 
     *
     * Eliminar teclat
     *
     * @param idTeclat  Identificador del teclat a borrar. 
     * @throws   Exception No existeix un teclat amb aquell identificador. 
     */
    public void eliminarTeclat(Integer idTeclat) throws Exception { 

        if (conjuntTeclats.containsKey(idTeclat)) {
            conjuntTeclats.remove(idTeclat);
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

    public void resetTeclats() {
        conjuntTeclats = new HashMap<>();
    }
    //------------------------------Getters------------------------------//
    /** 
     *
     * Obte la distribucio de les lletres en les tecles de un teclat
     *
     * @param idTeclat  Identificador del teclat que volem consultar
     * @return Llista de caracters que representen les lletres al teclat per files.
     * @throws   Exception No existeix un teclat amb aquell identificador.
     */
    public ArrayList<Character> getDistribucioTeclat(Integer idTeclat) throws Exception { 
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getDistribucio();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

    public Integer getIdEntradaVinculadaTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getIdEntradaVinculada();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }
    /** 
     *
     * Obte la llista de identificadors dels teclats creats
     *
     * @return Llista de ids de teclat
     */
    public ArrayList<Integer> getIdTeclats() { 
        ArrayList<Integer> llistaIdTeclats = new ArrayList<>(conjuntTeclats.keySet());
        return llistaIdTeclats;
    }

    public String getNomTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getNom();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }
    public Integer getFilesTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getFiles();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

    public Integer getColumnesTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getColumnes();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }
}
