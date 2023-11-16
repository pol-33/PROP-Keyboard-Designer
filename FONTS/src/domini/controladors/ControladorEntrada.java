package domini.controladors;

import domini.classes.Entrada;
import domini.classes.LPF;
import domini.classes.Text;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorEntrada {

    private HashMap<Integer, Entrada> conjuntEntrades;

    private Integer contador = 0;

    private static ControladorEntrada ctrlEntrada;

    //-------------------------------Contructora------------------------------//
    public ControladorEntrada() {
        this.conjuntEntrades = new HashMap<>();
    }

    public static ControladorEntrada obtenirInstancia() {
        if (ctrlEntrada == null) {
            ctrlEntrada = new ControladorEntrada();
        }
        return ctrlEntrada;
    }

    //------------------------------Getters------------------------------//
    public String getNomEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getNom();
    }

    public String getTypeEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        if (entrada instanceof Text) {
            return "text";
        } else {
            return "lpf";
        }
    }

    public HashMap<String, Integer> getLpfEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getLPF();
    }

    public ArrayList<Integer> getIdTeclatsVinculatsAEntrada(Integer idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getTeclatsVinculats();
    }

    public Integer getIdAlfabetVinculatAEntrada(int idEntrada) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(idEntrada);
        return entrada.getIdAlfabetVinculat();
    }
    //---------------------------Metodes publics---------------------------//
    public void crearText(String nomEntrada, String contingutEntrada, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        Text nouText = new Text(nomEntrada, contador, lletres, contingutEntrada, idAlfabet);
        conjuntEntrades.put(contador, nouText);
        contador++;
    }

    public void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        LPF nouLPF = new LPF(nomEntrada, contador, lletres, contingutEntrada, idAlfabet);
        conjuntEntrades.put(contador, nouLPF);
        contador++;
    }

    public void eliminarEntrada(Integer id) throws Exception {
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix una entrada amb aquest identificador");

        conjuntEntrades.remove(id);
    }

    public void vincularTeclatAEntrada(Integer idEntrada, Integer idTeclat) throws Exception {
        if (!conjuntEntrades.containsKey(idEntrada)) throw new Exception("No existeix una entrada amb aquest identificador");

        Entrada entrada = conjuntEntrades.get(idEntrada);
        entrada.vincularTeclat(idTeclat);
    }

    public void importarText(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        Path path = Paths.get(localitzacio_fitxer);
        String contingutFitxer = new String(Files.readAllBytes(path));
        this.crearText(nomEntrada, contingutFitxer, lletres, idAlfabet);
    }

    public void importarLPF(String nomEntrada, String localitzacio_fitxer, ArrayList<Character> lletres, Integer idAlfabet) throws Exception {
        //FALTA CREAR FUNCIO PER LLEGIR LA LPF
    }

    //---------------------------Metodes provats---------------------------//
    //El format de la lpf en string ha de ser de Paraula␣Frequencia\n
    private HashMap<String, Integer> formatejarALPF(String input) {
        HashMap<String, Integer> lpf = new HashMap<>();

        String[] paraulesAmbFrequencia = input.split("\n");

        for (String paraulaAmbFrequencia : paraulesAmbFrequencia) {
            String paraula = paraulaAmbFrequencia.split(" ")[0];
            String stringFrequencia = paraulaAmbFrequencia.split(" ")[1];
            Integer frequencia = Integer.parseInt(stringFrequencia);

            //Si la paraula ja ha aparegut abans a la lpf sumem la frequencia que portava + la que volem afegir
            if (lpf.containsKey(paraula)) {
                Integer frequenciaActual = lpf.get(paraula);
                frequencia += frequenciaActual;
            }
            lpf.put(paraula, frequencia);
        }

        return lpf;
    }

}
