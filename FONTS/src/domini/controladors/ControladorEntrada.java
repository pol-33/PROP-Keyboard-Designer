package domini.controladors;

import domini.classes.Entrada;
import domini.classes.LPF;
import domini.classes.Text;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe ControladorEntrada. Gestiona les entrades de text i LPF (Llista de Paraules Freqüents)
 * en el sistema.
 */
public class ControladorEntrada {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    private HashMap<Integer, Entrada> conjuntEntrades;
    private Integer contador = 0;
    private static ControladorEntrada ctrlEntrada;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructor privat. Inicialitza el conjunt d'entrades del sistema.
     */
    public ControladorEntrada() {
        this.conjuntEntrades = new HashMap<>();
    }

    /**
     * Obtenir la instància singleton de ControladorEntrada.
     * @return La instància única de ControladorEntrada.
     */
    public static ControladorEntrada obtenirInstancia() {
        if (ctrlEntrada == null) {
            ctrlEntrada = new ControladorEntrada();
        }
        return ctrlEntrada;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    /**
     * Retorna el nom de l'entrada donada.
     * @param idEntrada Identificador de l'entrada.
     * @return El nom de l'entrada.
     * @throws Exception Si no existeix una entrada amb aquest identificador.
     */
    public String getNomEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getNom();
    }

    /**
     * Retorna el tipus de l'entrada donada, sigui "text" o "lpf".
     * @param idEntrada Identificador de l'entrada.
     * @return El tipus de l'entrada.
     * @throws Exception Si no existeix una entrada amb aquest identificador.
     */
    public String getTypeEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        if (entrada instanceof Text) {
            return "text";
        } else {
            return "lpf";
        }
    }

    /**
     * Retorna la llista de paraules freqüents de l'entrada donada.
     * @param idEntrada Identificador de l'entrada.
     * @return HashMap amb les paraules i les seves freqüències.
     * @throws Exception Si no existeix una entrada amb aquest identificador o l'entrada no és de tipus LPF.
     */
    public HashMap<String, Integer> getLpfEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getLPF();
    }

    /**
     * Retorna els identificadors dels teclats vinculats a l'entrada donada.
     * @param idEntrada Identificador de l'entrada.
     * @return ArrayList amb els identificadors dels teclats vinculats.
     * @throws Exception Si no existeix una entrada amb aquest identificador.
     */
    public ArrayList<Integer> getIdTeclatsVinculatsAEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getTeclatsVinculats();
    }

    /**
     * Retorna l'identificador de l'alfabet vinculat a l'entrada donada.
     * @param idEntrada Identificador de l'entrada.
     * @return Identificador de l'alfabet vinculat.
     * @throws Exception Si no existeix una entrada amb aquest identificador.
     */
    public Integer getIdAlfabetVinculatAEntrada(int idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getIdAlfabetVinculat();
    }

    /**
     * Retorna una llista amb els identificadors de totes les entrades.
     * @return ArrayList amb els identificadors de totes les entrades.
     */
    public ArrayList<Integer> getIdEntrades() {
        ArrayList<Integer> llistaIds = new ArrayList<>(conjuntEntrades.keySet());
        return llistaIds;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Metodes publics
    // ---------------------------------------------------------------------------- //
    /**
     * Crea una nova entrada de tipus Text.
     * @param nomEntrada Nom de l'entrada.
     * @param contingutEntrada Contingut del text.
     * @param lletres Lletres de l'alfabet associat.
     * @param idAlfabet Identificador de l'alfabet associat.
     * @throws Exception Si hi ha errors durant la creació de l'entrada.
     */
    public void crearText(String nomEntrada, String contingutEntrada, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        Text nouText = new Text(nomEntrada, contador, contingutEntrada, idAlfabet);
        conjuntEntrades.put(contador, nouText);
        contador++;
    }

    public void carregarText(Integer id, String nomEntrada, String contingutEntrada, Integer idAlfabet, HashMap<String, Integer> lpf) throws Exception {
        Text nouText = new Text(nomEntrada, id, contingutEntrada, idAlfabet, lpf);
        conjuntEntrades.put(id, nouText);
        if (id > contador) {
            contador = id+1;
        } else {
            ++contador;
        }
    }

    /**
     * Crea una nova entrada de tipus LPF.
     * @param nomEntrada Nom de l'entrada.
     * @param contingutEntrada Contingut de l'LPF.
     * @param lletres Lletres de l'alfabet associat.
     * @param idAlfabet Identificador de l'alfabet associat.
     * @throws Exception Si hi ha errors durant la creació de l'entrada.
     */
    public void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        LPF nouLPF = new LPF(nomEntrada, contador, contingutEntrada, idAlfabet);
        conjuntEntrades.put(contador, nouLPF);
        contador++;
    }

    public void carregarLPF(Integer id, String nomEntrada, HashMap<String, Integer> contingutEntrada, Integer idAlfabet) throws Exception {
        LPF nouLPF = new LPF(nomEntrada, id, contingutEntrada, idAlfabet);
        conjuntEntrades.put(id, nouLPF);
        if (id > contador) {
            contador = id+1;
        } else {
            ++contador;
        }
    }

    /**
     * Elimina una entrada del sistema.
     * @param id Identificador de l'entrada a eliminar.
     * @throws Exception Si no existeix una entrada amb aquest identificador.
     */
    public void eliminarEntrada(Integer id) throws Exception {
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix una entrada amb aquest identificador");

        conjuntEntrades.remove(id);
    }

    /**
     * Vincula un teclat a una entrada.
     * @param idEntrada Identificador de l'entrada.
     * @param idTeclat Identificador del teclat a vincular.
     * @throws Exception Si no existeix una entrada amb aquest identificador.
     */
    public void vincularTeclatAEntrada(Integer idEntrada, Integer idTeclat) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");

        Entrada entrada = conjuntEntrades.get(idEntrada);
        entrada.vincularTeclat(idTeclat);
    }

    /**
     * Importa un text des d'un fitxer i crea una nova entrada amb ell.
     * @param nomEntrada Nom de la nova entrada.
     * @param localitzacio_fitxer Camí del fitxer de text.
     * @param lletres Lletres de l'alfabet associat.
     * @param idAlfabet Identificador de l'alfabet associat.
     * @throws Exception Si hi ha errors durant la importació o la creació de l'entrada.
     */
    public void importarText(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        Path path = Paths.get(localitzacio_fitxer);
        String contingutFitxer = new String(Files.readAllBytes(path));
        this.crearText(nomEntrada, contingutFitxer, lletres, idAlfabet);
    }

    /**
     * Importa una LPF des d'un fitxer i crea una nova entrada amb ella.
     * @param nomEntrada Nom de la nova entrada.
     * @param localitzacio_fitxer Camí del fitxer LPF.
     * @param lletres Lletres de l'alfabet associat.
     * @param idAlfabet Identificador de l'alfabet associat.
     * @throws Exception Si hi ha errors durant la importació o la creació de l'entrada.
     */
    public void importarLPF(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        // Implementació pendent
    }

    /**
     * Reestableix el conjunt d'entrades, eliminant totes les existents.
     */
    public void resetEntrades() {
        conjuntEntrades = new HashMap<>();
    }

    // ---------------------------------------------------------------------------- //
    //                                   Metodes privats
    // ---------------------------------------------------------------------------- //

    /**
     * Formateja una LPF a partir d'un string d'entrada.
     * @param input String d'entrada amb les dades de l'LPF.
     * @return HashMap amb les paraules i les seves freqüències.
     */
    private HashMap<String, Integer> formatejarALPF(String input) {
        HashMap<String, Integer> lpf = new HashMap<>();

        String[] paraulesAmbFrequencia = input.split("\n");

        for (String paraulaAmbFrequencia : paraulesAmbFrequencia) {
            String paraula = paraulaAmbFrequencia.split(" ")[0];
            String stringFrequencia = paraulaAmbFrequencia.split(" ")[1];
            Integer frequencia = Integer.parseInt(stringFrequencia);

            if (lpf.containsKey(paraula)) {
                Integer frequenciaActual = lpf.get(paraula);
                frequencia += frequenciaActual;
            }
            lpf.put(paraula, frequencia);
        }

        return lpf;
    }

}
