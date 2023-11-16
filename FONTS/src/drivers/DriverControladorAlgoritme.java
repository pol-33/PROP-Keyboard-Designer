package drivers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import domini.controladors.ControladorAlgoritme;

public class DriverControladorAlgoritme {
    private final ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();

    public static void main(String[] args) {
        DriverControladorAlgoritme driverCtrlAlgoritme = new DriverControladorAlgoritme();

        Scanner in = new Scanner(System.in);

        System.out.println("Driver del Controlador d'Algoritme");
        boolean running = true;

        while (running) {
            System.out.println("\nEscull una opció:");
            System.out.println("1. Ordenar amb algoritme per a dues mans (QAP)");
            System.out.println("2. Ordenar amb algortime per dos polzes (LAP)");
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
                    System.out.println("Opció invàlida.");
            }
        }

        System.out.println("Driver finalitzat.");
        in.close();
    }

    private void ordenarLlistaAlgoritmePolzes(Scanner in) {
        System.out.println("Escull una opció:");
        System.out.println("1. Introduir un text");
        System.out.println("2. Introduir una llista de paraules amb freqüències");
        int opcio = in.nextInt();
        in.nextLine(); // Netegem el buffer d'entrada

        HashMap<String, Integer> llistaFreq;
        if (opcio == 1) {
            llistaFreq = obtenirTextIProcessar(in);
        } else if (opcio == 2) {
            llistaFreq = llegirLlistaParaulesFreq(in);
        } else {
            System.out.println("Opció invàlida.");
            return;
        }

        System.out.println("Introdueix el nombre de files:");
        int files = in.nextInt();
        in.nextLine(); // Netegem el buffer del scanner

        System.out.println("Introdueix el nombre de columnes:");
        int columnes = in.nextInt();
        in.nextLine(); // Netegem el buffer del scanner

        ArrayList<Character> alfabet = obtenirAlfabet(in);
        ArrayList<Character> llistaOrdenada = ctrlAlgoritme.calcularDistribucioPolzes(llistaFreq, alfabet, files, columnes);

        imprimirTeclat(files, columnes, llistaOrdenada);
    }
    
    private void ordenarLlistaAlgoritmeDuesMans(Scanner in) {
        System.out.println("Escull una opció:");
        System.out.println("1. Introduir un text");
        System.out.println("2. Introduir una llista de paraules amb freqüències");
        int opcio = in.nextInt();
        in.nextLine(); // Netegem el buffer d'entrada

        HashMap<String, Integer> llistaFreq;
        if (opcio == 1) {
            llistaFreq = obtenirTextIProcessar(in);
        } else if (opcio == 2) {
            llistaFreq = llegirLlistaParaulesFreq(in);
        } else {
            System.out.println("Opció invàlida.");
            return;
        }

        System.out.println("Introdueix el nombre de files:");
        int files = in.nextInt();
        in.nextLine(); // Netegem el buffer del scanner

        System.out.println("Introdueix el nombre de columnes:");
        int columnes = in.nextInt();
        in.nextLine(); // Netegem el buffer del scanner

        ArrayList<Character> alfabet = obtenirAlfabet(in);
        ArrayList<Character> llistaOrdenada = ctrlAlgoritme.calcularDistribucioDuesMans(llistaFreq, alfabet, files, columnes);

        imprimirTeclat(files, columnes, llistaOrdenada);
    }

    private HashMap<String, Integer> obtenirTextIProcessar(Scanner in) {
        System.out.println("Introdueix un text:");
        String text = in.nextLine();
        return crearLlistaParaulesAmbRepeticions(text);
    }

    private HashMap<String, Integer> crearLlistaParaulesAmbRepeticions(String text) {
        HashMap<String, Integer> paraulesAmbRepeticions = new HashMap<>();
        String[] paraules = text.toLowerCase().split("\\s+");

        for (String paraula : paraules) {
            if (paraulesAmbRepeticions.containsKey(paraula)) {
                paraulesAmbRepeticions.put(paraula, paraulesAmbRepeticions.get(paraula) + 1);
            } else {
                paraulesAmbRepeticions.put(paraula, 1);
            }
        }
        return paraulesAmbRepeticions;
    }

    private void imprimirTeclat(int files, int columnes, ArrayList<Character> llistaOrdenada) {
        System.out.println("Teclat generat:");

        // Representació visual del teclat amb tecles generades
        System.out.println("\nRepresentació visual del teclat:");
        int index = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                System.out.print("[" + llistaOrdenada.get(index) + "] ");
                index++;
            }
            System.out.println();
        }
    }

    private HashMap<String, Integer> llegirLlistaParaulesFreq(Scanner in) {
        HashMap<String, Integer> llistaFreq = new HashMap<>();
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

            llistaFreq.put(paraula, freq);
        }
        return llistaFreq;
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
