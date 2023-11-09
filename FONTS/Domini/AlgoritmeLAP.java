package Domini;

import java.util.Arrays;
import java.util.Comparator;

public class AlgoritmeLAP {
    public int[] resoldreCostosConstants(int[] frecuencies, int[] costos) {
        int tamanyProblema = frecuencies.length;

        // Creem una matriu per a ordenar recordant el seu index a la matriu original
        Integer[][] frequenciesAmbIndex = new Integer[tamanyProblema][2];
        Integer[][] costosAmbIndex = new Integer[tamanyProblema][2];
        for (int i = 0; i < tamanyProblema; i++) {
            frequenciesAmbIndex[i][0] = frecuencies[i];
            frequenciesAmbIndex[i][1] = i;
            costosAmbIndex[i][0] = costos[i];
            costosAmbIndex[i][1] = i;
        }

        // Ordenar frequencies de grans a petites
        Arrays.sort(frequenciesAmbIndex, Comparator.comparing((Integer[] arr) -> arr[0]).reversed());

        // Ordenar costos de petits a grans
        Arrays.sort(costosAmbIndex, Comparator.comparing((Integer[] arr) -> arr[0]));

        // Asignar las majors frequencies als costos mes petits
        int[] resultatAssignacions = new int[tamanyProblema];
        for (int i = 0; i < tamanyProblema; i++) {
            resultatAssignacions[costosAmbIndex[i][1]] = frequenciesAmbIndex[i][1];
        }

        return resultatAssignacions;
    }
}
