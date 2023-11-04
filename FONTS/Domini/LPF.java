package Domini;

import java.util.HashMap;
import java.util.Map;

public class LPF extends Entrada {

    private HashMAp <String, Integer> lpf;

    //Constructora
    public LPF() {
        this.lpf = new HashMap<String, Integer>();
    }

    //Metode per afegir una paraula o actualitzar el seu valor
    public void afegirParaula(String paraula) {
        if (this.lpf.containsKey(paraula)) {
            this.lpf.put(paraula, this.lpf.get(paraula) + 1);
        }
        else {
            this.lpf.put(paraula, 1);
        }
    }

    //Metode per obtindre la frequencia d'una paraula
    public int  obtindreFrequencia(String paraula) {
        if (this.lpf.containsKey(paraula)) {
            return this.lpf.get(paraula);
        }
        else {
            return 0;
        }
    }



    
}
