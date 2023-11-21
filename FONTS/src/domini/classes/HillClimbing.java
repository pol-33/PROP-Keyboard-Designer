package domini.classes;

import java.util.Random;

/**
 * Classe HillClimbing. Implementa l'algorisme d'optimització local "Hill Climbing" per a la resolucio d'un problema.
 */
public class HillClimbing {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    private int[][] fluxos;
    private int[][] costos;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructor de la classe HillClimbing.
     * @param fluxos Matriu de fluxos.
     * @param costos Matriu de costos.
     */
    public HillClimbing(int[][] fluxos, int[][] costos) {
        this.fluxos = fluxos;
        this.costos = costos;
    }

    // ---------------------------------------------------------------------------- //
    //                                Metodes publics
    // ---------------------------------------------------------------------------- //

    /**
     * Intenta resoldre el problema diverses vegades amb solucions inicials aleatories i retorna la millor trobada.
     * @param numIntents Número de intents per a trobar la millor solucio.
     * @return La millor solucio trobada.
     */
    public int[] resoldreVarisIntentents(int numIntents) {
        Random random = new Random();
        int n = fluxos.length;
        int[] millorSolucio = null;
        int millorCost = Integer.MAX_VALUE;

        for (int intento = 0; intento < numIntents; intento++) {
            int[] solucioAleatoria = generarSolucioAleatoria(n, random);

            int costoActual = resoldre(solucioAleatoria);

            if (costoActual < millorCost) {
                millorSolucio = solucioAleatoria.clone();
                millorCost = costoActual;
            }
        }
        return millorSolucio;
    }

    // ---------------------------------------------------------------------------- //
    //                                Metodes privats
    // ---------------------------------------------------------------------------- //

    /**
     * Executa l'algorisme Hill Climbing per trobar una millora iterativament a partir d'una solucio donada.
     * @param solucioActual La solucio actual a avaluar i millorar.
     * @return El cost final de la millor solució trobada.
     */
    private int resoldre(int[] solucioActual) {
        boolean millora = true;

        while (millora) {
            int costActual = calcularCostSolucio(solucioActual);

            millora = false;

            for (int i = 0; i < solucioActual.length; i++) {
                for (int j = i + 1; j < solucioActual.length; j++) {
                    intercanviar(solucioActual, i, j);

                    int nouCost = calcularCostSolucio(solucioActual);
                    if (nouCost < costActual) {
                        millora = true;
                        costActual = nouCost;
                    } else {
                        intercanviar(solucioActual, i, j);
                    }
                }
            }
        }

        return calcularCostSolucio(solucioActual);
    }

    /**
     * Calcula el cost de la solucio actual en funcio de les matrius de fluxos i costos.
     * @param solucioActual La solucio per a la qual es calcula el cost.
     * @return El cost de la solucio actual.
     */
    private int calcularCostSolucio(int[] solucioActual) {
        int costTotal = 0;
        int n = solucioActual.length;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costTotal += fluxos[i][j] * costos[solucioActual[i]][solucioActual[j]];
            }
        }

        return costTotal;
    }

    /**
     * Genera una solucio aleatoria a partir d'una mida n.
     * @param n Mida de la solucio a generar.
     * @param random Objecte Random per a generacio aleatoria.
     * @return Una solucio aleatoria generada.
     */
    private int[] generarSolucioAleatoria(int n, Random random) {
        int[] solucioAleatoria = new int[n];

        for (int i = 0; i < n; i++) {
            solucioAleatoria[i] = i;
        }

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = solucioAleatoria[i];
            solucioAleatoria[i] = solucioAleatoria[j];
            solucioAleatoria[j] = temp;
        }

        return solucioAleatoria;
    }

    /**
     * Intercanvia dos elements d'una solucio.
     * @param solucioActual La solucio en la qual es realitza l'intercanvi.
     * @param i Index del primer element a intercanviar.
     * @param j Index del segon element a intercanviar.
     */
    private void intercanviar(int[] solucioActual, int i, int j) {
        int temp = solucioActual[i];
        solucioActual[i] = solucioActual[j];
        solucioActual[j] = temp;
    }
}
