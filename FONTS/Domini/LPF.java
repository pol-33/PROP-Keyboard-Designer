package Domini;

import java.util.HashMap;
import java.util.Map;
public class LPF extends Entrada {

    private HashMap <String, Integer> lpf;

    // Constructora
    public LPF() {
        this.lpf = new HashMap<String, Integer>();
    }

    // Mètode per afegir una paraula o actualitzar el seu valor
    public void afegirParaula(String paraula) {
        if (this.lpf.containsKey(paraula)) {
            this.lpf.put(paraula, this.lpf.get(paraula) + 1);
        }
        else {
            this.lpf.put(paraula, 1);
        }
    }

    // Mètode per obtindre la frequencia d'una paraula
    public int  obtindreFrequencia(String paraula) {
        if (this.lpf.containsKey(paraula)) {
            return this.lpf.get(paraula);
        }
        else {
            return 0;
        }
    }

    // Mètode per obtindre el numbre de paraules diferents
    public int obtindreNombreParaulasDiferents() {
        return this.lpf.size();
    }

    // Mètode per imprimir el contingut de l'LPF
    public void mostrarParaulesFrequencia(){
        for (Map.Entry<String, Integer> entry : this.lpf.entrySet()) {
            System.out.println("Palabra: " + entry.getKey() + ", Frequencia: " + entry.getValue());
        }
    }

    // Mètode per retornar la LPF
    public HashMap<String, Integer> getLPF() {
        return this.lpf;
    }
}
