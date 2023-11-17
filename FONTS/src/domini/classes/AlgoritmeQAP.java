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
        HillClimbing hillClimbing = new HillClimbing(solucio, fluxos, costos);
        hillClimbing.resoldre();
        return hillClimbing.getSolucio();
    }
}
