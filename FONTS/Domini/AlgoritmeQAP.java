package Domini;

import java.util.Arrays;

public class AlgoritmeQAP {

    // funcio principal
    public int[] resoldreQAP(int[][] flux, int[][] distancia) {
        int n = flux.length;
        int[] mejorSolucion = new int[n];
        boolean[] asignado = new boolean[n];
        Arrays.fill(asignado, false);

        for (int i = 0; i < n; i++) {
            int mejorIndice = -1;
            int mejorCoste = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!asignado[j]) {
                    int costoTotal = calcularCostoTotal(j, asignado, flux, distancia);
                    if (costoTotal < mejorCoste) {
                        mejorCoste = costoTotal;
                        mejorIndice = j;
                    }
                }
            }

            mejorSolucion[i] = mejorIndice;
            asignado[mejorIndice] = true;
        }

        return mejorSolucion;
    }
     // calculra cost de asignacio parcial
    private int calcularCostoTotal(int indice, boolean[] asignado, int[][] flux, int[][] distancia) {
        int costoTotal = 0;
        int n = asignado.length;

        for (int i = 0; i < n; i++) {
            if (asignado[i]) {
                costoTotal += flux[indice][i] * distancia[i][indice];
            }
        }

        return costoTotal;
    }
}

