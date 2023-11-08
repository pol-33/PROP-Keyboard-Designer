package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class LPF extends Entrada {

    // Constructora
    public LPF(String nom, ArrayList<Character> lletres, HashMap<String, Integer> lpf) throws Exception {
        for (Map.Entry<String, Integer> paraulaAmbFreq : lpf.entrySet()) {
            if (paraulaAmbFreq.getValue() < 0) throw new Exception("Una paraula no pot tenir frequencia negativa");
        }
        this.nom = nom;
        this.lpf = lpf;
        this.teclat = creaTeclat(lletres);
    }

    // Mètode per reassignar la frequencia d'una paraula
    public void reassignarFrequencia(String paraula, Integer frequencia) throws Exception {
        if (frequencia < 0) throw new Exception("Una paraula no pot tenir frequencia negativa");
        this.lpf.put(paraula, frequencia);
    }

    // Mètode per obtenir la frequencia d'una paraula
    public int  obtenirFrequencia(String paraula) throws Exception {
        if (!this.lpf.containsKey(paraula)) {
            throw new Exception("La paraula no existeix en la llista de paraules, per tant la frequencia és 0");
        }
        return this.lpf.get(paraula);
    }

    // Mètode per obtenir el numbre de paraules diferents
    public int obtenirNombreParaulasDiferents() {
        return this.lpf.size();
    }

    // Mètode per imprimir el contingut de l'LPF
    public void imprimirEntrada(){
        System.out.println("Nom: " + this.nom);
        for (Map.Entry<String, Integer> entry : this.lpf.entrySet()) {
            System.out.println("Paraula: " + entry.getKey() + ", Frequencia: " + entry.getValue());
        }
    }
}
