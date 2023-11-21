package domini.classes;

import java.util.Arrays;
import java.util.Comparator;

public class AlgoritmeLAP {

    /**
     * Resol un problema LAP, donats els vectors de frequencies i costos.
     * Cal tenir en compte que els costos han de ser constants per a cada
     * possible assignacio del problema.
     * @param frecuencies Vector de frequencies
     * @param costos Vector de costos
     * @return int[] representant les asignacions
     */
    public int[] resoldreCostosConstants(int[] frecuencies, int[] costos) {
        int tamanyProblema = frecuencies.length;

        Integer[][] frequenciesAmbIndex = new Integer[tamanyProblema][2];
        Integer[][] costosAmbIndex = new Integer[tamanyProblema][2];
        for (int i = 0; i < tamanyProblema; i++) {
            frequenciesAmbIndex[i][0] = frecuencies[i];
            frequenciesAmbIndex[i][1] = i;
            costosAmbIndex[i][0] = costos[i];
            costosAmbIndex[i][1] = i;
        }

        Arrays.sort(frequenciesAmbIndex, Comparator.comparing((Integer[] arr) -> arr[0]).reversed());

        Arrays.sort(costosAmbIndex, Comparator.comparing((Integer[] arr) -> arr[0]));

        int[] resultatAssignacions = new int[tamanyProblema];
        for (int i = 0; i < tamanyProblema; i++) {
            resultatAssignacions[costosAmbIndex[i][1]] = frequenciesAmbIndex[i][1];
        }

        return resultatAssignacions;
    }
}
