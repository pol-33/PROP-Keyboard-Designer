package Domini;

/**
 * S'encarrega de resoldre el QAP, utilitzant hill climbing
 */
public class AlgoritmeQAP {
    /**
     * Resol un problema QAP en general
     * @param fluxos Matriu que representa el nombre de vegades que dues lletres estan juntes
     * @param costos Matriu que representa el cost entre dues tecles
     * @return
     */
    public int[] resoldreQAP(int[][] fluxos, int[][] costos) {
        int tamanyProblema = fluxos.length;
        int[] solucio = new int[tamanyProblema];

        // Generem una solucio inicial per ordre on les lletres s'asignen
        // a tecles per ordre
        for (int i = 0; i < tamanyProblema; i++) {
            solucio[i] = i;
        }

        // cridem a hill climbing
        hillClimbing(solucio, fluxos, costos, calcularCostSolucio(solucio, fluxos, costos));
        
        return solucio;
    }

    /**
     * Resol un problema de hill climbing, on l'estat es el vector de assignacions,
     * els operadors son swaps d'elements, i l'heuristica es calcula en funcio del 
     * problema QAP (amb matrius de fluxos i costos)
     * @param solucioActual
     * @param fluxos
     * @param costos
     * @param costActual
     */
    private void hillClimbing(int[] solucioActual, int[][] fluxos, int[][] costos, int costActual) {
        boolean millora = true;
    
        while (millora) {
            millora = false;
            int millorCost = costActual;
    
            for (int i = 0; i < solucioActual.length && !millora; i++) {
                for (int j = i + 1; j < solucioActual.length && !millora; j++) {
                    // Intercanviem els dos elements per generar una nova solucio
                    int auxSwap = solucioActual[i];
                    solucioActual[i] = solucioActual[j];
                    solucioActual[j] = auxSwap;
                    
                    // Calcular el cost de la nova solucio
                    int nouCost = calcularCostSolucio(solucioActual, fluxos, costos);
    
                    // Comprovar si el swap millora el cost
                    if (nouCost < millorCost) {
                        // Actualitzem cost
                        millorCost = nouCost;
                        millora = true; // amb aixo sortirem dels fors i comencem una nova iteracio
                    }
                    else {
                        // deixem la solucio com estava, desfem el swap
                        auxSwap = solucioActual[i];
                        solucioActual[i] = solucioActual[j];
                        solucioActual[j] = auxSwap;
                    }
                }
            }
        }
    }
    
    /**
     * Calcula el cost de una solucio concreta tenint en compte els fluxos i costos
     * @param solucio
     * @param fluxos
     * @param costos
     * @return
     */
    private int calcularCostSolucio(int[] solucio, int[][] fluxos, int[][] costos) {
        int costTotal = 0;
        int n = solucio.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costTotal += fluxos[i][j] * costos[solucio[i]][solucio[j]];
            }
        }

        return costTotal;
    }
}
