package domini.classes;

import java.util.PriorityQueue;

public class HillClimbing {
    private class Swap {
        int i;
        int j;
        int cost;

        public Swap(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }

    private int[] solucioActual;
    private int[][] fluxos;
    private int[][] costos;
    private int costActual;

    public HillClimbing(int[] solucioActual, int[][] fluxos, int[][] costos) {
        this.solucioActual = solucioActual;
        this.fluxos = fluxos;
        this.costos = costos;
        this.costActual = calcularCostSolucio();
    }

    public void resoldre() {
        boolean millora = true;
        PriorityQueue<Swap> swapsQueue = new PriorityQueue<>((s1, s2) -> s1.cost - s2.cost);

        while (millora) {
            millora = false;
            int millorCost = costActual;

            // Omplir la cua de prioritat amb tots els possibles swaps i els seus costos
            for (int i = 0; i < solucioActual.length; i++) {
                for (int j = i + 1; j < solucioActual.length; j++) {
                    // Intercanviem els dos elements per generar una nova solucio
                    int auxSwap = solucioActual[i];
                    solucioActual[i] = solucioActual[j];
                    solucioActual[j] = auxSwap;

                    // Calcular el cost de la nova solucio
                    int nouCost = calcularCostSolucio();

                    // Afegir el swap a la PriorityQueue amb el seu cost
                    swapsQueue.offer(new Swap(i, j, nouCost));

                    // Desfer el swap per tornar a l'estat original
                    auxSwap = solucioActual[i];
                    solucioActual[i] = solucioActual[j];
                    solucioActual[j] = auxSwap;
                }
            }

            // Aplicar els swaps de la PriorityQueue fins a trobar el millor
            while (!swapsQueue.isEmpty()) {
                Swap swap = swapsQueue.poll();
                int i = swap.i;
                int j = swap.j;
                int swapCost = swap.cost;

                // Aplicar el swap amb el millor cost
                int auxSwap = solucioActual[i];
                solucioActual[i] = solucioActual[j];
                solucioActual[j] = auxSwap;

                // Actualitzar el cost actual
                costActual = swapCost;

                if (costActual < millorCost) {
                    millorCost = costActual;
                    millora = true;
                } else {
                    // Desfer el swap si no millora el cost
                    auxSwap = solucioActual[i];
                    solucioActual[i] = solucioActual[j];
                    solucioActual[j] = auxSwap;
                }
            }
        }
    }
    public int[] getSolucio() {
        return solucioActual;
    }
    private int calcularCostSolucio() {
        int costTotal = 0;
        int n = solucioActual.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costTotal += fluxos[i][j] * costos[solucioActual[i]][solucioActual[j]];
            }
        }

        return costTotal;
    }
}
