package Domini;

import java.util.HashMap;
import java.util.Map;
public class LPF extends Entrada {

    private HashMap<String, Integer> contingut;

    // Constructora
    public LPF(HashMap<String, Integer> contingut) throws Exception {
        for (Map.Entry<String, Integer> paraulaAmbFreq : contingut.entrySet()) {
            if (paraulaAmbFreq.getValue() < 0) throw new Exception("Una paraula no pot tenir frequencia negativa");
        }
        this.contingut = contingut;
    }

    // Mètode per reassignar la frequencia d'una paraula
    public void reassignarFrequencia(String paraula, Integer frequencia) throws Exception {
        if (frequencia < 0) throw new Exception("Una paraula no pot tenir frequencia negativa");
        this.contingut.put(paraula, frequencia);
    }

    // Mètode per obtenir la frequencia d'una paraula
    public int  obtenirFrquencia(String paraula) throws Exception {
        if (!this.contingut.containsKey(paraula)) {
            throw new Exception("La paraula no existeix en la llista de paraules, per tant la frequencia és 0");
        }
        return this.contingut.get(paraula);
    }

    // Mètode per obtenir el numbre de paraules diferents
    public int obtenirNombreParaulasDiferents() {
        return this.contingut.size();
    }

    // Mètode per imprimir el contingut de l'LPF
    public void mostrarParaulesFrequencia(){
        for (Map.Entry<String, Integer> entry : this.contingut.entrySet()) {
            System.out.println("Paraula: " + entry.getKey() + ", Frequencia: " + entry.getValue());
        }
    }

    // Mètode per retornar la LPF
    public HashMap<String, Integer> getLPF() {
        return this.contingut;
    }
}
