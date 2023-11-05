package ControladorsDomini;

import Domini.Algoritme;
import Domini.Entrada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControladorAlgoritme {
    //Instancia singleton del Controlador de Algoritme
    private static ControladorAlgoritme ctrl;

    private Algoritme algoritme = new Algoritme();

    //Metode per obtenir l'instància singleton
    public static ControladorAlgoritme obtenInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorAlgoritme();
        }
        return ctrl;
    }

    public ArrayList<Character> ordenarLlistaAlgoritmeQAP (HashMap<String, Integer> lpf, ArrayList<Character> albafet) {
        // convertir a matriu QAP
        // cridar a algoritme
        // desconvertir de solucio QAP a ArrayList<Character>
        return albafet;
    }
    public ArrayList<Character> ordenarLlistaAlgoritmePersonalitzat (HashMap<String, Integer> lpf, ArrayList<Character> alfabet){
        ArrayList<Character> alfabetOrdenat = algoritme.ordenarPerFrequencia(lpf, alfabet);
        return alfabetOrdenat;
    }

}