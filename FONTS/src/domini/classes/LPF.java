package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class LPF extends Entrada {

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //
    public LPF(String nomEntrada, Integer id, ArrayList<Character> lletres, HashMap<String, Integer> contingutEntrada, Integer idAlfabet) throws Exception {
        this.nom = nomEntrada;
        this.id = id;
        this.lpf = contingutEntrada;
        this.idAlfabet = idAlfabet;
        this.idTeclats = new ArrayList<>();
        for (Map.Entry<String, Integer> paraulaAmbFreq : lpf.entrySet()) {
            if (paraulaAmbFreq.getValue() < 0) throw new Exception("Una paraula no pot tenir frequencia negativa");
        }
    }
}
