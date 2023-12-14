package domini.controladors;

import java.util.ArrayList;
import java.util.HashMap;

import domini.classes.Teclat;


 /**
 * Classe ControladorTeclat. Gestiona un conjunt de teclats, i s'encarrega
 * de crear, modificar i eliminar els teclats. Coneix a ControladorAlgoritme,
 * i parla amb ell per tal de crear les distribucions óptimes per a cada
 * entrada.
 */ 
public class ControladorTeclat {

     // ---------------------------------------------------------------------------- //
     //                                   Atributs
     // ---------------------------------------------------------------------------- //
    
    private HashMap<Integer, Teclat> conjuntTeclats;
    private Integer contador = 0;
    private static ControladorTeclat ctrlTeclats;

     // ---------------------------------------------------------------------------- //
     //                                   Constructora
     // ---------------------------------------------------------------------------- //

     /**
      * Constructora de la clase. Inicialitza el conjunt de teclats.
      */
    public ControladorTeclat() { 

        conjuntTeclats = new HashMap<>();
    }

     /**
      * Obtenir la instància singleton de ControladorTeclat.
      * @return La instància única de ControladorTeclat.
      */
    public static ControladorTeclat obtenirInstancia() { 

        if (ctrlTeclats == null) {
            ctrlTeclats = new ControladorTeclat();
        }
        return ctrlTeclats;
    }

     // ---------------------------------------------------------------------------- //
     //                                   Metodes publics
     // ---------------------------------------------------------------------------- //

     /**
     * Crear teclat dues mans
     * @param lpf  Llista de paraules amb frequencies per a calcular la distribucio optima 
     * @param alfabet  Conjunt de lletres a colocar en el teclat 
     * @param uidEntrada  Identificador de l'entrada d'on prove la lpf
     * @param files  Nombre de files al layout del teclat 
     * @param columnes  Nombre de columnes del layout del teclat
     * @return Enter que representa l'identificador del teclat creat
     */
    public Integer crearTeclatDuesMans(String nom, HashMap<String, Integer> lpf, ArrayList<Character> alfabet, 
     Integer uidEntrada, Integer files, Integer columnes) throws Exception {

        Integer idTeclat = contador;
        contador++;

        ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
        ArrayList<Character> teclesAmbLletres = ctrlAlgoritme.calcularDistribucioDuesMans(lpf, alfabet, files, columnes);

        Teclat nouTeclat = new Teclat(nom, teclesAmbLletres, uidEntrada, files, columnes, idTeclat, 0);
        conjuntTeclats.put(idTeclat, nouTeclat);
        return idTeclat;
    }

    /**
     * Crear teclat polzes
     * @param lpf  Llista de paraules amb frequencies per a calcular la distribucio optima 
     * @param alfabet  Conjunt de lletres a colocar en el teclat 
     * @param uidEntrada  Identificador de l'entrada d'on prove la lpf
     * @param files  Nombre de files al layout del teclat 
     * @param columnes  Nombre de columnes del layout del teclat
     * @return Enter que representa l'identificador del teclat creat
     */
    public Integer crearTeclatPolzes(String nom, HashMap<String, Integer> lpf, ArrayList<Character> alfabet, 
     Integer uidEntrada, Integer files, Integer columnes) throws Exception {


        Integer idTeclat = contador;
        contador++;

        ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
        ArrayList<Character> teclesAmbLletres = ctrlAlgoritme.calcularDistribucioPolzes(lpf, alfabet, files, columnes);

        Teclat nouTeclat = new Teclat(nom, teclesAmbLletres, uidEntrada, files, columnes, idTeclat, 1);
        conjuntTeclats.put(idTeclat, nouTeclat);
        return idTeclat;
    }
    
    /**
     * Crea un teclat donada la seva distribucio, sense pasar per algoritme
     * @param nom Nom del teclat
     * @param distribucio Distribucio de les lletres en el teclat
     * @param idTeclat Identificador del teclat
     * @param idEntrada Identificador de l'entrada associada
     * @param files Nombre de files al layout del teclat
     * @param columnes Nombre de columnes al layout del teclat
     * @return
     * @throws Exception
     */
    public void carregarTeclat(String nom, ArrayList<Character> distribucio,
        int id, int idEntrada, int files, int columnes) {
        
        Teclat nouTeclat = new Teclat(nom, distribucio, idEntrada, files, columnes, id);
        conjuntTeclats.put(id, nouTeclat);

        if (id > contador) {
            contador = id+1;
        } else {
            ++contador;
        }
    }
    
    /**
     * Eliminar teclat
     * @param idTeclat  Identificador del teclat a borrar. 
     * @throws Exception No existeix un teclat amb aquell identificador.
     */
    public void eliminarTeclat(Integer idTeclat) throws Exception { 

        if (conjuntTeclats.containsKey(idTeclat)) {
            conjuntTeclats.remove(idTeclat);
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

    public void modificarFilesColumnesTeclat(Integer idTeclat, HashMap<String, Integer> lpf, ArrayList<Character> alfabet, int files, int columnes) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {

            // recalcular la distribucio del teclat
            int tipus = conjuntTeclats.get(idTeclat).getTipus();
            if (tipus == 0) {
                ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
                ArrayList<Character> distribucio = ctrlAlgoritme.calcularDistribucioDuesMans(lpf, alfabet, files, columnes);
                conjuntTeclats.get(idTeclat).setDistribucio(distribucio);
            }
            else {
                ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();
                ArrayList<Character> distribucio = ctrlAlgoritme.calcularDistribucioPolzes(lpf, alfabet, files, columnes);
                conjuntTeclats.get(idTeclat).setDistribucio(distribucio);
            }

            // modificar files i columnes
            conjuntTeclats.get(idTeclat).modificarFilesColumnes(files, columnes);
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

     /**
      * Reinicialitza el conjunt de teclats, eliminant tots els existents.
      */
    public void resetTeclats() {
        conjuntTeclats = new HashMap<>();
    }

     // ---------------------------------------------------------------------------- //
     //                                   Getters
     // ---------------------------------------------------------------------------- //

    /**
     * Obte la distribucio de les lletres en les tecles de un teclat
     * @param idTeclat  Identificador del teclat que volem consultar
     * @return Llista de caracters que representen les lletres al teclat per files.
     * @throws Exception No existeix un teclat amb aquell identificador.
     */
    public ArrayList<Character> getDistribucioTeclat(Integer idTeclat) throws Exception { 
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getDistribucio();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

     /**
      * Obté l'identificador de l'entrada vinculada a un teclat.
      * @param idTeclat Identificador del teclat a consultar.
      * @return Identificador de l'entrada vinculada.
      * @throws Exception Si no existeix un teclat amb aquest identificador.
      */
    public Integer getIdEntradaVinculadaTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getIdEntradaVinculada();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }
    /**
     * Obte la llista d'identificadors dels teclats creats
     * @return Llista d'ids de teclat
     */
    public ArrayList<Integer> getIdTeclats() { 
        ArrayList<Integer> llistaIdTeclats = new ArrayList<>(conjuntTeclats.keySet());
        return llistaIdTeclats;
    }

     /**
      * Obté el nom d'un teclat.
      * @param idTeclat Identificador del teclat a consultar.
      * @return Nom del teclat.
      * @throws Exception Si no existeix un teclat amb aquest identificador.
      */
    public String getNomTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getNom();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

     /**
      * Obté el nombre de files d'un teclat.
      * @param idTeclat Identificador del teclat a consultar.
      * @return Nombre de files del teclat.
      * @throws Exception Si no existeix un teclat amb aquest identificador.
      */
    public Integer getFilesTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getFiles();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }

     /**
      * Obté el nombre de columnes d'un teclat.
      * @param idTeclat Identificador del teclat a consultar.
      * @return Nombre de columnes del teclat.
      * @throws Exception Si no existeix un teclat amb aquest identificador.
      */
    public Integer getColumnesTeclat(Integer idTeclat) throws Exception {
        if (conjuntTeclats.containsKey(idTeclat)) {
            return conjuntTeclats.get(idTeclat).getColumnes();
        } 
        else {
            throw new Exception("No existeix el teclat amb aquest id");
        }
    }
}
