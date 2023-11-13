package ControladorsDomini;

import Domini.Entrada;
import Domini.LPF;
import Domini.Text;

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
        if (!conjuntEntrades.containsKey(id)) throw new Exception("No existeix ninguna entrada amb aquest identificador");

        conjuntEntrades.remove(id);
    }

}
