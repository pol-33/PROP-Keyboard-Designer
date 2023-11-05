package ControladorsDomini;

import ControladorsDomini.ControladorDomini;
import Domini.Alfabet;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPerfil {
    //Instancia singleton del Controlador de Perfil
    private static ControladorPerfil ctrl;

    //Hashmap que conté el nomUsuari junt als seus teclats
    private HashMap<String, Alfabet> Alfabets;

    //Metode per obtenir l'instància singleton
    public static ControladorPerfil obtenInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPerfil();
        }
        return ctrl;
    }

    public void crearAlfabet(String idioma, ArrayList<Character> lletres) {

    }
}
