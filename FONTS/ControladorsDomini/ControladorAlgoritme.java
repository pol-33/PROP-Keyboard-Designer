package ControladorsDomini;

import Domini.Algoritme;
import Domini.AlgoritmeQAP;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorAlgoritme {
    private static ControladorAlgoritme ctrl;

    private Algoritme algoritme = new Algoritme();
    private AlgoritmeQAP algoritmeQAP = new AlgoritmeQAP();

    public static ControladorAlgoritme obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorAlgoritme();
        }
        return ctrl;
    }

    // Funcions principals
    public ArrayList<Character> calcularDistribucioDuesMans(HashMap<String, Integer> lpf, ArrayList<Character> alfabet, int files, int columnes) {
        // Crear les matrius de flux i distància basades en les especificacions del problema
        int[][] flux = calcularMatriuFlux(lpf, alfabet);
        int[][] distancia = calcularMatriuDistancia(files, columnes);

        // Resoldre el QAP per obtenir la distribució òptima
        int[] distribucioOptima = algoritmeQAP.resoldreQAP(flux, distancia);

        // Convertir l'índex de la distribució òptima a caràcters segons l'alfabet
        ArrayList<Character> distribucioTeclat = new ArrayList<>();
        for (int i = 0; i < distribucioOptima.length; i++) {
            distribucioTeclat.add(alfabet.get(distribucioOptima[i]));
        }

        return distribucioTeclat;
    }

    // Mètode per calcular la matriu de flux a partir del HashMap i l'alfabet
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
    
                // Comprovar si els caràcters es troben a l'alfabet abans d'utilitzar els índexs
                if (idx1 != -1 && idx2 != -1) {
                    flux[idx1][idx2] += freq;
                    flux[idx2][idx1] += freq;
                }
            }
        }
    
        return flux;
    }

    // Mètode per calcular la matriu de distància basada en les files i columnes del teclat
    private int[][] calcularMatriuDistancia(int files, int columnes) {
        int n = files * columnes;
        int[][] distancia = new int[n][n];

        // Càlcul de la distància euclidiana entre les tecles
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int fila1 = i / columnes;
                int columna1 = i % columnes;
                int fila2 = j / columnes;
                int columna2 = j % columnes;

                // Fórmula de distància euclidiana entre dues tecles
                distancia[i][j] = 5 - (int) (Math.pow(fila2 - fila1, 2) + Math.pow(columna2 - columna1, 2));
            }
        }

        return distancia;
    }

    public ArrayList<Character> calcularDistribucioPolzes(HashMap<String, Integer> lpf, ArrayList<Character> alfabet, int files, int columnes) {
        
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (char lletra : alfabet) {
            frequencies.put(lletra, 0);
        }

        for (String paraula : lpf.keySet()) {
            for (char lletra : paraula.toCharArray()) {
                if (alfabet.contains(lletra)) {
                    int frequencia = lpf.get(paraula);
                    frequencies.put(lletra, frequencies.get(lletra) + frequencia);
                }
            }
        }

        ArrayList<Character> lletresOrdenades = new ArrayList<>(alfabet);
        lletresOrdenades.sort((a, b) -> frequencies.get(b) - frequencies.get(a));

        ArrayList<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < files * columnes; i++) {
            indexs.add(i);
        }
        indexs.sort((i, j) -> {
            double costMinimI = obtenirCostMinim(i, files, columnes);
            double costMinimJ = obtenirCostMinim(j, files, columnes);
            return Double.compare(costMinimI, costMinimJ);
        });

        ArrayList<Character> teclat = new ArrayList<>(files * columnes);
        for (int i = 0; i < files * columnes; i++) {
            teclat.add(' ');
        }

        for (int i = 0; i < indexs.size(); i++) {
            int index = indexs.get(i);
            if (i < lletresOrdenades.size()) {
                teclat.set(index, lletresOrdenades.get(i));
            }
        }

        return teclat;
    }

    private double obtenirCostMinim(int index, int files, int columnes) {
        int i = index / columnes;
        int j = index % columnes;

        double cantInferiorEsq = calcularDistanciaEuclidiana(i, j, files - 1, 0);
        double cantInferiorDreta = calcularDistanciaEuclidiana(i, j, files - 1, columnes - 1);

        return Math.min(cantInferiorEsq, cantInferiorDreta);
    }

    private double calcularDistanciaEuclidiana(int i1, int j1, int i2, int j2) {
        double distancia = Math.sqrt(Math.pow(i1 - i2, 2) + Math.pow(j1 - j2, 2));
        return distancia;
    }
}


/*
// pre: files * columnes = alfabet.size()
// post: [files*columnes] assigancions de lletres a tecles tal que es minimitzi el cost d'escriure
public ArrayList<Character> calcularDistribucioDuesMans (HashMap<String, Integer> lpf, ArrayList<Character> alfabet,
    int files, int columnes) {
    // Supongamos que se tiene la matriz QAP y el algoritmo de resolución
    // Se requiere la implementación de la conversión de datos a una matriz QAP y el llamado al algoritmo para resolverlo

    int n = alfabet.size();  // Tamaño del alfabeto
    // TODO: Convertir los datos en una matriz QAP
    //int[][] matrizQAP = convertirAMatrizQAP(lpf, alfabet);

    // TODO: Llamar al algoritmo QAP para resolver el problema
    // Aquí se necesitaría la llamada al algoritmo apropiado para resolver la matriz QAP
    //int[] solucionQAP = algoritmeQAP.resoldre(matrizQAP);

    // TODO: Desconvertir la solución QAP a un ArrayList de caracteres
    //ArrayList<Character> alfabetOrdenado = desconvertirSolucionQAP(solucionQAP, alfabet);

    return alfabetOrdenado;
    }
// Funcions auxiliars

// Función para calcular la distancia en el teclado entre dos letras
private int[][] calcularCostosTeclesDuesMans(int files, int columnes) {
    int numTecles = files * columnes;
    int[][] matriuCostos = new int[numTecles][numTecles];

    for (int i = 0; i < numTecles; i++) {
        for (int j = 0; j < numTecles; j++) {
            int distanciaEuclidiana = calcularDistanciaEuclidiana(i, j, files, columnes);
            matriuCostos[i][j] = 5 - distanciaEuclidiana;
        }
    }
    return matriuCostos;
}


private int[][] calcularCostesCeldas(int filas, int columnas) {
    int[][] costes = new int[filas][columnas];
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            costes[i][j] = Math.min(i, filas - 1 - i) + Math.min(j, columnas - 1 - j);
        }
    }
    return costes;
}*/