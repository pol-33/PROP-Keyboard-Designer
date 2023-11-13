package ControladorsDomini;

import Domini.AlgoritmeQAP;
import Domini.AlgoritmeLAP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControladorAlgoritme {
    private static ControladorAlgoritme ctrl;

    public static ControladorAlgoritme obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorAlgoritme();
        }
        return ctrl;
    }

    // Retorna assignacions de lletres a tecles en funcio de teclat de dues mans
    public ArrayList<Character> calcularDistribucioDuesMans(HashMap<String, Integer> lpf, ArrayList<Character> alfabet, int files, int columnes) {
        // Si sobren tecles afegim caracters en blanc a l'alfabet
        int tamanyAlfabet = alfabet.size();
        while (tamanyAlfabet < files * columnes) {
            tamanyAlfabet++;
            alfabet.add(' ');
        }

        // Crear les matrius de flux i costos del problema QAP que resoldrem
        int[][] flux = calcularMatriuFlux(lpf, alfabet);
        int[][] distancia = calcularMatriuCostos(files, columnes);

        // Resoldre el problema QAP per obtenir les assignacions de lletres a tecles optimes
        AlgoritmeQAP algoritmeQAP = new AlgoritmeQAP();
        int[] distribucioOptima = algoritmeQAP.resoldreQAP(flux, distancia);

        // Convertir l'index de la distribucio optima a caracters segons l'alfabet
        ArrayList<Character> distribucioTeclat = new ArrayList<>();
        for (int i = 0; i < distribucioOptima.length; i++) {
            distribucioTeclat.add(alfabet.get(distribucioOptima[i]));
        }

        return distribucioTeclat;
    }

    // Retorna assignacions de lletres a tecles en funcio d'un teclat de polzes
    public ArrayList<Character> calcularDistribucioPolzes(HashMap<String, Integer> lpf, ArrayList<Character> alfabet, int files, int columnes) {
        
        // Si sobren tecles afegim espais
        int tamanyAlfabet = alfabet.size();
        while (tamanyAlfabet < files * columnes) {
            tamanyAlfabet++;
            alfabet.add(' ');
        }

        // Crear els vectors de frequencies i costos per a LAP
        int[] frequencia = calcularVectorFrequencies(lpf, alfabet);
        int[] costos = calcularVectorCostos(files, columnes);

        // Resoldre el QAP per obtenir la distribució òptima
        AlgoritmeLAP algoritmeLAP = new AlgoritmeLAP();
        int[] distribucioOptima = algoritmeLAP.resoldreCostosConstants(frequencia, costos);

        // Convertir l'índex de la distribució òptima a caràcters segons l'alfabet
        ArrayList<Character> distribucioTeclat = new ArrayList<>();
        for (int i = 0; i < distribucioOptima.length; i++) {
            distribucioTeclat.add(alfabet.get(distribucioOptima[i]));
        }
        return distribucioTeclat;
    }

    // Funcions auxiliars per a preparar problema QAP
    private int[][] calcularMatriuFlux(HashMap<String, Integer> lpf, ArrayList<Character> alfabet) {
        int n = alfabet.size();
        int[][] flux = new int[n][n];
    
        for (String paraula : lpf.keySet()) {
            int freq = lpf.get(paraula);
    
            for (int i = 0; i < paraula.length() - 1; i++) {
                char c1 = paraula.charAt(i);
                char c2 = paraula.charAt(i + 1);
                
                // sabem que els simbols del alfabet son no repetits
                int idx1 = alfabet.indexOf(c1);
                int idx2 = alfabet.indexOf(c2);
    
                // Comprovar si els caracters es troben a l'alfabet abans d'utilitzar els indexs
                if (idx1 != -1 && idx2 != -1) {
                    flux[idx1][idx2] += freq;
                    flux[idx2][idx1] += freq;
                }
            }
        }
    
        return flux;
    }
    private int[][] calcularMatriuCostos(int files, int columnes) {
        int n = files * columnes;
        int[][] costos = new int[n][n];
    
        int migColumnes = columnes / 2; // Mitad de las columnas
    
        // Cálculo de la matriz de distancias con las condiciones específicas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int fila1 = i / columnes;
                int columna1 = i % columnes;
                int fila2 = j / columnes;
                int columna2 = j % columnes;
    
                if (columna1 == columna2) {
                    costos[i][j] = 2; // Si utilitzem el mateix dit el cost es 2
                } else {
                    if ((columna1 < migColumnes && columna2 < migColumnes) || (columna1 >= migColumnes && columna2 >= migColumnes)) {
                        costos[i][j] = 1; // Si utilitzem la mateixa ma el cost es 1
                    } else {
                        costos[i][j] = 0; // per les altres el cost es 0
                    }
                }
            }
        }
    
        return costos;
    }
    
    // Funcions auxiliars per a preparar problema LAP
    public int[] calcularVectorFrequencies(HashMap<String, Integer> lpf, ArrayList<Character> alfabet) {
        int[] frequencia = new int[alfabet.size()];

        for (Map.Entry<String, Integer> entry : lpf.entrySet()) {
            String palabra = entry.getKey();
            int freq = entry.getValue();

            for (int i = 0; i < palabra.length(); i++) {
                char letra = palabra.charAt(i);
                int index = alfabet.indexOf(letra);
                if (index != -1) {
                    frequencia[index] += freq; // Incrementa la frecuencia de la letra según las veces que aparece en la palabra
                }
            }
        }

        return frequencia;
    }
    public int[] calcularVectorCostos(int files, int columnes) {
        int[] costos = new int[files * columnes]; // Tamaño del array según el número de teclas

        int index = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                double cost = obtenirCostMinim(i, j, files, columnes);
                costos[index] = (int) (10.0*cost); // Asigna directamente el costo al array en el índice correspondiente
                index++;
            }
        }

        return costos;
    } 
    private double obtenirCostMinim(int i, int j, int files, int columnes) {
        double cantInferiorEsq = calcularDistanciaEuclidiana(i, j, files - 1, 0);
        double cantInferiorDreta = calcularDistanciaEuclidiana(i, j, files - 1, columnes - 1);

        return Math.min(cantInferiorEsq, cantInferiorDreta);
    }
    private double calcularDistanciaEuclidiana(int i1, int j1, int i2, int j2) {
        double distancia = Math.sqrt(Math.pow(i1 - i2, 2) + Math.pow(j1 - j2, 2));
        return distancia;
    }
}