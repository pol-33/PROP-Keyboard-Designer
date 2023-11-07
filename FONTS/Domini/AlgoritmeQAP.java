package Domini;

public class AlgoritmeQAP {
    // si flux = n * n
    // int[4 5 2 6 5 3 .. n]
    //    t1 t2 t3 t4
    // t1
    // t2
    // t3
    // t4
    public int[] resoldreQAP(int[][] flux, int[][] distancia) {
        int n = flux.length;
        int[] solucioOptima = new int[n];
        int[] ubicacions = new int[n];

        for (int i = 0; i < n; i++) {
            ubicacions[i] = i;
        }

        int menorCost = Integer.MAX_VALUE;

        do {
            int costTotal = calcularCostTotal(ubicacions, flux, distancia);
            if (costTotal < menorCost) {
                menorCost = costTotal;
                solucioOptima = ubicacions.clone();
            }
        } while (seguentPermutacio(ubicacions));

        return solucioOptima;
    }

    public int calcularCostTotal(int[] ubicacions, int[][] flux, int[][] distancia) {
        int costTotal = 0;
        int n = ubicacions.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costTotal += flux[i][j] * distancia[ubicacions[i]][ubicacions[j]];
            }
        }

        return costTotal;
    }

    public boolean seguentPermutacio(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}
