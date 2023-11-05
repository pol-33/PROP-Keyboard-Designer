package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Algoritme {
    
    // Mètode per ordenar els símbols de l'alfabet
    public ArrayList<Character> ordenarPerFrequencia(HashMap<String, Integer> paraulesFrequencia, ArrayList<Character> simbolsAlfabet) {
        // Farem servir un TreeMap per ordenar els símbols per freqüència
        TreeMap<Integer, List<Character>> frequenciaSimbols = new TreeMap<>();

        // Inicialitzem la freqüència dels símbols
        for (char simbol : simbolsAlfabet) {
            frequenciaSimbols.put(0, new ArrayList<>());
        }

        // Iterem sobre el HashMap per calcular la freqüència de cada símbol a partir de les paraules
        for (String paraula : paraulesFrequencia.keySet()) {
            int frequencia = paraulesFrequencia.get(paraula);
            for (char c : paraula.toCharArray()) {
                if (frequenciaSimbols.containsKey(frequencia)) {
                    frequenciaSimbols.get(frequencia).add(c);
                } else {
                    // En cas de no trobar la freqüència exacta, s'afegeix al següent valor més gran
                    Integer higherFrequency = frequenciaSimbols.higherKey(frequencia);
                    if (higherFrequency != null) {
                        frequenciaSimbols.get(higherFrequency).add(c);
                    } else {
                        // Si no hi ha una freqüència més gran, s'afegeix a la freqüència 0 (no es troba a la llista)
                        frequenciaSimbols.get(0).add(c);
                    }
                }
            }
        }

        // Creem una llista ordenada de símbols basada en la seva freqüència
        ArrayList<Character> simbolsOrdenats = new ArrayList<>();
        for (List<Character> simbols : frequenciaSimbols.descendingMap().values()) {
            simbolsOrdenats.addAll(simbols);
        }

        return simbolsOrdenats;
    }
}
