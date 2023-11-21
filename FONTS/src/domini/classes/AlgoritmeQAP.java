package domini.classes;

public class AlgoritmeQAP {
    /**
     * Resol un problema QAP mitjançant Hill Climbing
     * @param fluxos Matriu de fluxos del problema QAP
     * @param costos Matriu de costos del problema QAP
     * @return int[] representant les asignacions
     */
    public int[] resoldreQAP(int[][] fluxos, int[][] costos) {
        // cridem a hill climbing
        HillClimbing hillClimbing = new HillClimbing(fluxos, costos);
        return hillClimbing.resoldreVarisIntentents(50);
    }
}
