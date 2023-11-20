package domini.controladors;

import domini.classes.AlgoritmeQAP;
import domini.classes.AlgoritmeLAP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe ControladorAlgoritme. Encarregat de la gestió dels dos algorismes de distribució de lletres a tecles.
 */
public class ControladorAlgoritme {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private static ControladorAlgoritme ctrl;

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Obté una instància del ControladorAlgoritme (patró Singleton).
     * @return La instància del ControladorAlgoritme.
     */
    public static ControladorAlgoritme obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorAlgoritme();
        }
        return ctrl;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Metodes publics
    // ---------------------------------------------------------------------------- //

    /**
    * Calcula la distribució de lletres a tecles per a un teclat de dues mans.
    * @param lpf Mapa de freqüència de paraules.
    * @param alfabet Llista d'alfabet.
    * @param files Nombre de files del teclat.
    * @param columnes Nombre de columnes del teclat.
    * @return Una llista amb la distribució de lletres a tecles optimitzada.
    */
    public ArrayList<Character> calcularDistribucioDuesMans(HashMap<String, Integer> lpf,
     ArrayList<Character> alfabet, int files, int columnes) throws Exception{
        if (files * columnes < alfabet.size())
            throw new Exception("No caben totes les lletres dins del layout proposat per files i columnes.");

        int[][] flux = calcularMatriuFlux(lpf, alfabet);
        int[][] distancia = calcularMatriuCostos(files, columnes);

        AlgoritmeQAP algoritmeQAP = new AlgoritmeQAP();
        int[] distribucioOptima = algoritmeQAP.resoldreQAP(flux, distancia);

        ArrayList<Character> distribucioTeclat = new ArrayList<>();
        for (int i = 0; i < distribucioOptima.length; i++) {
            distribucioTeclat.add(alfabet.get(distribucioOptima[i]));
        }

        int tamanyTeclat = distribucioTeclat.size();
        while (tamanyTeclat < files * columnes) {
            tamanyTeclat++;
            distribucioTeclat.add(' ');
        }
        return distribucioTeclat;
    }

    /**
     * Calcula la distribució de lletres a tecles per a un teclat de polzes.
     * @param lpf Mapa de freqüència de paraules.
     * @param alfabet Llista d'alfabet.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     * @return Una llista amb la distribució de lletres a tecles optimitzada.
     */
    public ArrayList<Character> calcularDistribucioPolzes(HashMap<String, Integer> lpf,
     ArrayList<Character> alfabet, int files, int columnes) throws Exception {
        if (files * columnes < alfabet.size())
            throw new Exception("No caben totes les lletres dins del layout proposat per files i columnes.");

        int tamanyAlfabet = alfabet.size();
        while (tamanyAlfabet < files * columnes) {
            tamanyAlfabet++;
            alfabet.add(' ');
        }

        int[] frequencia = calcularVectorFrequencies(lpf, alfabet);
        int[] costos = calcularVectorCostos(files, columnes);

        AlgoritmeLAP algoritmeLAP = new AlgoritmeLAP();
        int[] distribucioOptima = algoritmeLAP.resoldreCostosConstants(frequencia, costos);

        ArrayList<Character> distribucioTeclat = new ArrayList<>();
        for (int i = 0; i < distribucioOptima.length; i++) {
            distribucioTeclat.add(alfabet.get(distribucioOptima[i]));
        }
        return distribucioTeclat;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Metodes privats
    // ---------------------------------------------------------------------------- //

    /**
     * Calcula la matriu de flux per al problema QAP.
     * @param lpf Mapa de freqüència de paraules.
     * @param alfabet Llista d'alfabet.
     * @return La matriu de flux generada per al QAP.
     */
    private int[][] calcularMatriuFlux(HashMap<String, Integer> lpf, ArrayList<Character> alfabet) {
        int n = alfabet.size();
        int[][] flux = new int[n][n];

        for (String paraula : lpf.keySet()) {
            int freq = lpf.get(paraula);

            for (int i = 0; i < paraula.length() - 1; i++) {
                char c1 = paraula.charAt(i);
                char c2 = paraula.charAt(i + 1);

                int idx1 = alfabet.indexOf(c1);
                int idx2 = alfabet.indexOf(c2);

                if (idx1 != -1 && idx2 != -1) {
                    flux[idx1][idx2] += freq;
                    flux[idx2][idx1] += freq;
                }
            }
        }
        return flux;
    }

    /**
     * Calcula la matriu de costos per al problema QAP.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     * @return La matriu de costos generada per al QAP.
     */
    private int[][] calcularMatriuCostos(int files, int columnes) {
        int n = files * columnes;
        int[][] costos = new int[n][n];

        int migColumnes = columnes / 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int fila1 = i / columnes;
                int columna1 = i % columnes;
                int fila2 = j / columnes;
                int columna2 = j % columnes;

                if (columna1 == columna2) {
                    costos[i][j] = 2;
                } else {
                    if ((columna1 < migColumnes && columna2 < migColumnes) || (columna1 >= migColumnes && columna2 >= migColumnes)) {
                        costos[i][j] = 1;
                    } else {
                        costos[i][j] = 0;
                    }
                }
            }
        }
        return costos;
    }

    /**
     * Calcula el vector de freqüències per al LAP.
     * @param lpf Mapa de freqüència de paraules.
     * @param alfabet Llista d'alfabet.
     * @return El vector de freqüències generat per al LAP.
     */
    private int[] calcularVectorFrequencies(HashMap<String, Integer> lpf, ArrayList<Character> alfabet) {
        int[] frequencia = new int[alfabet.size()];

        for (Map.Entry<String, Integer> entry : lpf.entrySet()) {
            String palabra = entry.getKey();
            int freq = entry.getValue();

            for (int i = 0; i < palabra.length(); i++) {
                char letra = palabra.charAt(i);
                int index = alfabet.indexOf(letra);
                if (index != -1) {
                    frequencia[index] += freq;
                }
            }
        }
        return frequencia;
    }

    /**
     * Calcula el vector de costos per al LAP.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     * @return El vector de costos generat per al LAP.
     */
    private int[] calcularVectorCostos(int files, int columnes) {
        int[] costos = new int[files * columnes];

        int index = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                double cost = obtenirCostMinim(i, j, files, columnes);
                costos[index] = (int) (10.0*cost);
                index++;
            }
        }
        return costos;
    }

    /**
     * Obte el cost minim de la tecla (i, j) en funcio a la distancia.
     * minima a una de les dues cantonades inferiors.
     * @param i index fila
     * @param j index columna
     * @param files numero de files
     * @param columnes numero de columnes
     * @return double
     */
    private double obtenirCostMinim(int i, int j, int files, int columnes) {
        double cantInferiorEsq = calcularDistanciaEuclidiana(i, j, files - 1, 0);
        double cantInferiorDreta = calcularDistanciaEuclidiana(i, j, files - 1, columnes - 1);
        return Math.min(cantInferiorEsq, cantInferiorDreta);
    }

    /**
     * Calcula la distància euclidiana entre dos punts (i1, j1) i (i2, j2). Utilitzat per calcular distàncies al teclat.
     * @param i1 Índex de fila del primer punt.
     * @param j1 Índex de columna del primer punt.
     * @param i2 Índex de fila del segon
     * @param j2 Índex de colimna del segon punt.
     * @return Double amb la distància Euclidiana entre els dos punts.
     */
    private double calcularDistanciaEuclidiana(int i1, int j1, int i2, int j2) {
        double distancia = Math.sqrt(Math.pow(i1 - i2, 2) + Math.pow(j1 - j2, 2));
        return distancia;
    }
}