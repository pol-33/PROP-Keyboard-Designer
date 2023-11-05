package Drivers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ControladorsDomini.ControladorAlgoritme;

public class DriverControladorAlgoritme {
    private ControladorAlgoritme ctrlAlgoritme = new ControladorAlgoritme();

    public static void main(String[] args) {
        DriverControladorAlgoritme driverCtrlAlgoritme = new DriverControladorAlgoritme();

        Scanner in = new Scanner(System.in);

        System.out.println("Benvingut al driver del Controlador d'Algorisme");
        boolean running = true;

        while (running) {
            System.out.println("\nEscull una opció:");
            System.out.println("1. Ordenar llista amb l'Algorisme QAP");
            System.out.println("2. Ordenar llista amb un Algorisme Personalitzat");
            System.out.println("3. Sortir");

            int opcio = in.nextInt();
            in.nextLine(); // Netegem el buffer d'entrada

            switch (opcio) {
                case 1:
                    driverCtrlAlgoritme.ordenarLlistaAlgoritmeQAP(in);
                    break;
                case 2:
                    driverCtrlAlgoritme.ordenarLlistaAlgoritmePersonalitzat(in);
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

    private void ordenarLlistaAlgoritmeQAP(Scanner in) {
        System.out.println("Introdueix la llista de paraules amb la seva freqüència (LPF):");
        HashMap<String, Integer> lpf = llegirLPF(in);

        // Implementem la lògica per provar la funció ordenarLlistaAlgoritmeQAP aquí
        ArrayList<Character> alfabet = obtenirAlfabet(in);
        ArrayList<Character> llistaOrdenada = ctrlAlgoritme.ordenarLlistaAlgoritmeQAP(lpf, alfabet);
        System.out.println("Llista ordenada amb l'Algorisme QAP: " + llistaOrdenada);
    }

    private void ordenarLlistaAlgoritmePersonalitzat(Scanner in) {
        System.out.println("Introdueix la llista de paraules amb la seva freqüència (LPF):");
        HashMap<String, Integer> lpf = llegirLPF(in);

        // Implementem la lògica per provar la funció ordenarLlistaAlgoritmePersonalitzat aquí
        ArrayList<Character> alfabet = obtenirAlfabet(in);
        ArrayList<Character> llistaOrdenada = ctrlAlgoritme.ordenarLlistaAlgoritmePersonalitzat(lpf, alfabet);
        System.out.println("Llista ordenada amb un Algorisme Personalitzat: " + llistaOrdenada);
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
