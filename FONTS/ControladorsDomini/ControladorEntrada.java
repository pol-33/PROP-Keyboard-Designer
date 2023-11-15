package ControladorsDomini;

import Domini.Entrada;
import Domini.LPF;
import Domini.Text;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorEntrada {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private HashMap<Integer, Entrada> conjuntEntrades;

    private Integer contador = 0;

    private static ControladorEntrada ctrlEntrada;

    // ---------------------------------------------------------------------------- //
    //                                   Creadora
    // ---------------------------------------------------------------------------- //
    public ControladorEntrada() {
        this.conjuntEntrades = new HashMap<>();
    }

    public static ControladorEntrada obtenirInstancia() {
        if (ctrlEntrada == null) {
            ctrlEntrada = new ControladorEntrada();
        }
        return ctrlEntrada;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //
    public String getNomEntrada(Integer id) throws Exception {
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(id);
        return entrada.getNom();
    }

    public String getTypeEntrada(Integer id) throws Exception {
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix una entrada amb aquest identificador");
        Entrada entrada = conjuntEntrades.get(id);
        if (entrada instanceof Text) {
            return "text";
        } else {
            return "lpf";
        }
    }

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //
    public void crearText(String nomEntrada, String contingutEntrada, ArrayList<Character> lletres) throws Exception {
        Text nouText = new Text(nomEntrada, contador, lletres, contingutEntrada);
        conjuntEntrades.put(contador, nouText);
        contador++;
    }

    public void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, ArrayList<Character> lletres) throws Exception {
        LPF nouLPF = new LPF(nomEntrada, contador, lletres, contingutEntrada);
        conjuntEntrades.put(contador, nouLPF);
        contador++;
    }

    public void eliminarEntrada(Integer id) throws Exception {
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix una entrada amb aquest identificador");

        conjuntEntrades.remove(id);
    }

    public void asociarTeclat(Integer idEntrada, Integer idTeclat) throws Exception {
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix una entrada amb aquest identificador");

        Entrada entrada = conjuntEntrades.get(idEntrada);
        entrada.asociarTeclat(idTeclat);
    }

    public void importarText(String nomEntrada,
                             String localitzacio_fitxer,
                             ArrayList<Character> lletres) throws Exception
    {
        Path path = Paths.get(localitzacio_fitxer);
        String contingutFitxer = new String(Files.readAllBytes(path));
        this.crearText(nomEntrada, contingutFitxer, lletres);
    }

    public void importarLPF(String nomEntrada,
                            String localitzacio_fitxer,
                            ArrayList<Character> lletres) throws Exception
    {
        //FALTA CREAR FUNCIO PER LLEGIR LA LPF
    }

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
