package Domini;

public class QAPBranchAndBound {

    public int[] resoldreQAP(int[][] flux, int[][] distancia) {
        int n = flux.length;
        boolean[] asignado = new boolean[n];
        int[] mejorSolucion = new int[n];
        int[] solucionInicial = generarSolucionInicial(n);

        branchAndBound(solucionInicial, asignado, 0, 0, mejorSolucion, Integer.MAX_VALUE, flux, distancia);

        return mejorSolucion;
    }

    private int[] generarSolucionInicial(int n) {
        int[] solucion = new int[n];
        for (int i = 0; i < n; i++) {
            solucion[i] = i;
        }
        return solucion;
    }

    private void branchAndBound(int[] solParcial, boolean[] asignado, int costoParcial, int paso, int[] mejorSolucion, int mejorCoste, int[][] flux, int[][] distancia) {
        int n = solParcial.length;

        if (paso == n) {
            if (costoParcial < mejorCoste) {
                mejorCoste = costoParcial;
                System.arraycopy(solParcial, 0, mejorSolucion, 0, n);
            }
            return;
        }

        for (int i = paso; i < n; i++) {
            swap(solParcial, i, paso);
            asignado[paso] = true;

            int cota = calcularCotaGilmoreLawler(solParcial, asignado, costoParcial, flux, distancia);

            if (cota < mejorCoste) {
                branchAndBound(solParcial, asignado, costoParcial + cota, paso + 1, mejorSolucion, mejorCoste, flux, distancia);
            }

            asignado[paso] = false;
            swap(solParcial, i, paso);
        }
    }

    private int calcularCotaGilmoreLawler(int[] solParcial, boolean[] asignado, int costoParcial, int[][] flux, int[][] distancia) {
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

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
