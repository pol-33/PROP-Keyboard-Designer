package domini.classes;

public class AlgoritmeQAP {
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
                        millora = true;
                    }
                    else {
                        // deixem la solucio com estava, desfem el swap
                        auxSwap = solucioActual[i];
                        solucioActual[i] = solucioActual[j];
                        solucioActual[j] = auxSwap;
                    }
                }
            }
            
            costActual = millorCost; // Actualizar el costo actual al mejor costo encontrado
        }
    }
    
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
