package Drivers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ControladorsDomini.ControladorAlgoritme;

public class DriverControladorAlgoritme {
    private final ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();

    public static void main(String[] args) {
        DriverControladorAlgoritme driverCtrlAlgoritme = new DriverControladorAlgoritme();

        Scanner in = new Scanner(System.in);

        System.out.println("Benvingut al driver del Controlador d'Algorisme");
        boolean running = true;

        while (running) {
            System.out.println("\nEscull una opció:");
            System.out.println("1. Ordenar llista amb l'Algorisme QAP, dues mans");
            System.out.println("2. Ordenar llista amb un Algoritme per dos polzes");
            System.out.println("3. Sortir");

            int opcio = in.nextInt();
            in.nextLine(); // Netegem el buffer d'entrada

            switch (opcio) {
                case 1:
                    driverCtrlAlgoritme.ordenarLlistaAlgoritmeDuesMans(in);
                    break;
                case 2:
                    driverCtrlAlgoritme.ordenarLlistaAlgoritmePolzes(in);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opció invàlida. Torna a intentar.");
            }
        }

        System.out.println("Driver finalitzat.");
        in.close();
    }

    private void ordenarLlistaAlgoritmePolzes(Scanner in) {
        HashMap<String, Integer> lpf = obtenerTextoEProcesar(in); // Obtener el texto y procesarlo

        System.out.println("Introduce el número de filas:");
        int filas = in.nextInt();
        in.nextLine(); // Limpiar el buffer del scanner

        System.out.println("Introduce el número de columnas:");
        int columnas = in.nextInt();
        in.nextLine(); // Limpiar el buffer del scanner

        ArrayList<Character> alfabeto = obtenirAlfabet(in);
        ArrayList<Character> listaOrdenada = ctrlAlgoritme.calcularDistribucioPolzes(lpf, alfabeto, filas, columnas);

        imprimirTeclado(filas, columnas, listaOrdenada);
    }
    
    private HashMap<String, Integer> obtenerTextoEProcesar(Scanner in) {
        System.out.println("Introduce un texto en inglés:");
        String texto = in.nextLine();
        return crearListaPalabrasConRepeticiones(texto);
    }

    private void ordenarLlistaAlgoritmeDuesMans(Scanner in) {
        HashMap<String, Integer> lpf = obtenerTextoEProcesar(in); // Obtener el texto y procesarlo

        System.out.println("Introduce el número de filas:");
        int filas = in.nextInt();
        in.nextLine(); // Limpiar el buffer del scanner

        System.out.println("Introduce el número de columnas:");
        int columnas = in.nextInt();
        in.nextLine(); // Limpiar el buffer del scanner

        ArrayList<Character> alfabeto = obtenirAlfabet(in);
        ArrayList<Character> listaOrdenada = ctrlAlgoritme.calcularDistribucioDuesMans(lpf, alfabeto, filas, columnas);

        imprimirTeclado(filas, columnas, listaOrdenada);
    }

    private HashMap<String, Integer> crearListaPalabrasConRepeticiones(String texto) {
        HashMap<String, Integer> palabrasConRepeticiones = new HashMap<>();
        String[] palabras = texto.toLowerCase().split("\\s+");

        for (String palabra : palabras) {
            if (palabrasConRepeticiones.containsKey(palabra)) {
                palabrasConRepeticiones.put(palabra, palabrasConRepeticiones.get(palabra) + 1);
            } else {
                palabrasConRepeticiones.put(palabra, 1);
            }
        }
        return palabrasConRepeticiones;
    }

    private void imprimirTeclado(int files, int columnes, ArrayList<Character> llistaOrdenada) {
        System.out.println("Teclado generado:");

        // Representación visual del teclado con teclas generadas
        System.out.println("\nRepresentación visual del teclado:");
        int index = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                System.out.print("[" + llistaOrdenada.get(index) + "] ");
                index++;
            }
            System.out.println();
        }
    }

    private HashMap<String, Integer> llegirLPF(Scanner in) {
        HashMap<String, Integer> lpf = new HashMap<>();
        int numParaules;

        System.out.print("Introdueix el nombre de paraules a la llista: ");
        numParaules = in.nextInt();
        in.nextLine(); // Netegem el buffer d'entrada

        for (int i = 0; i < numParaules; i++) {
            System.out.print("Introdueix la paraula " + (i + 1) + ": ");
            String paraula = in.nextLine();

            System.out.print("Introdueix la seva freqüència: ");
            int freq = in.nextInt();
            in.nextLine(); // Netegem el buffer d'entrada

            lpf.put(paraula, freq);
        }
        return lpf;
    }

    private ArrayList<Character> obtenirAlfabet(Scanner in) {
        System.out.println("Introdueix els símbols de l'alfabet (sense espais): ");
        String simbols = in.nextLine();
    
        ArrayList<Character> alfabet = new ArrayList<>();
        for (char c : simbols.toCharArray()) {
            alfabet.add(c);
        }
    
        return alfabet;
    }
}
