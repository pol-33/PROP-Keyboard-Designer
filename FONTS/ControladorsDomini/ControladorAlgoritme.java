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