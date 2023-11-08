package Domini;

import java.util.Arrays;

public class QAPBranchAndBound {

    // Función principal para resolver el QAP usando Branch & Bound con cota de Gilmore-Lawler
    public int[] resoldreQAP(int[][] flux, int[][] distancia) {
        int n = flux.length;
        boolean[] asignado = new boolean[n];
        int[] mejorSolucion = new int[n];
        int mejorCoste = Integer.MAX_VALUE;

        int[] solucionInicial = generarSolucionInicial(n); // Genera la solución inicial en el orden de entrada

        branchAndBound(solucionInicial, asignado, 0, 0, mejorSolucion, mejorCoste, flux, distancia);

        return mejorSolucion;
    }

    // Genera una solución inicial en el orden de entrada
    private int[] generarSolucionInicial(int n) {
        int[] solucion = new int[n];
        for (int i = 0; i < n; i++) {
            solucion[i] = i;
        }
        return solucion;
    }

    // Método principal de Branch & Bound
    private void branchAndBound(int[] solParcial, boolean[] asignado, int costoParcial, int paso, int[] mejorSolucion, int mejorCoste, int[][] flux, int[][] distancia) {
        int n = solParcial.length;

        if (paso == n) {
            // Verificar si es una solución completa y actualizar mejorSolucion y mejorCoste si es mejor
            if (costoParcial < mejorCoste) {
                mejorCoste = costoParcial;
                System.arraycopy(solParcial, 0, mejorSolucion, 0, n);
            }
            return;
        }

        for (int i = paso; i < n; i++) {
            // Hacer el branching y calcular la cota de Gilmore-Lawler para las soluciones candidatas
            swap(solParcial, i, paso);
            asignado[paso] = true;

            int cota = calcularCotaGilmoreLawler(solParcial, asignado, flux, distancia);

            if (cota < mejorCoste) {
                branchAndBound(solParcial, asignado, costoParcial, paso + 1, mejorSolucion, mejorCoste, flux, distancia);
            }

            asignado[paso] = false;
            swap(solParcial, i, paso); // Revertir la solución parcial
        }
    }

    // Cálculo de la cota de Gilmore-Lawler
    private int calcularCotaGilmoreLawler(int[] solParcial, boolean[] asignado, int[][] flux, int[][] distancia) {
        int n = solParcial.length;
        int costoTotal = 0;

        for (int i = 0; i < n; i++) {
            if (asignado[i]) {
                for (int j = 0; j < n; j++) {
                    if (asignado[j]) {
                        costoTotal += flux[i][j] * distancia[solParcial[i]][solParcial[j]];
                    }
                }
            }
        }

        return costoTotal;
    }

    // Intercambia dos elementos en un array
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
