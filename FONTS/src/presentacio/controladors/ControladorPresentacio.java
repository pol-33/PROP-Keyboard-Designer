package presentacio.controladors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domini.controladors.ControladorDomini;
import presentacio.elements.ElementAlfabetLlista;
import presentacio.vistes.*;

public class ControladorPresentacio {
    
    private static VistaLogin vLogin;
    private static VistaPrincipal vPrincipal;

    /** Instància del controlador de domini */
    private static ControladorDomini ctrlDomini = new ControladorDomini();

    public static void startAplicacio() {
        vLogin = new VistaLogin();
        vLogin.mostrar();
        crearProves();
    }

    private static void crearProves() {
        try {
            // crear 3 alfabets
            ArrayList<Character> lletresANG = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> lletresCAST = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> lletresCAT = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            int idAngles = ctrlDomini.crearAlfabet("Angles", lletresANG);
            int idCastella = ctrlDomini.crearAlfabet("Castella", lletresCAST);
            int idCatala = ctrlDomini.crearAlfabet("Catala", lletresCAT);

            // crear 2 texts i una entrada
            int idTxt1 = ctrlDomini.crearText("Hello world!", "Hello world in english!", idAngles);
            int idTxt2 = ctrlDomini.crearText("Hola mundo!", "Hola mundo en español!", idCastella);
            HashMap<String, Integer> lpf = new HashMap<>();
            lpf.put("hola", 3);
            lpf.put("mon", 4);
            int idLPF1 = ctrlDomini.crearLPF("Hola mon", lpf, idCatala);

            // crear 3 teclats
            ctrlDomini.crearTeclatDuesMans("Teclat 1", idTxt1, 3, 10);
            ctrlDomini.crearTeclatDuesMans("Teclat 2", idTxt2, 3, 10);
            ctrlDomini.crearTeclatDuesMans("Teclat 2", idTxt2, 3, 10);

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al carregar les proves: " + e.getMessage());
        }

    }

    public static void crearUsuari(String usuari, String contrasenya) {
        try {
            ctrlDomini.crearUsuari(usuari, contrasenya);
            vPrincipal = new VistaPrincipal();
            vPrincipal.mostrar();
            vLogin.tancar();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void iniciarSessio(String usuari, String contrasenya) {
        try {
            ctrlDomini.iniciarSessio(usuari, contrasenya);
            vPrincipal = new VistaPrincipal();
            vPrincipal.mostrar();
            vLogin.tancar();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) {
        try {
            ctrlDomini.crearAlfabet(nomAlfabet, lletres);
            vPrincipal.initAlfabets();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void eliminarAlfabet(Integer idAlfabet) {
        try {
            ctrlDomini.eliminarAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "Alfabet eliminat amb exit!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static ArrayList<Integer> getIdAlfabets () {
        try {
            return ctrlDomini.getIdAlfabets();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String getNomAlfabet(Integer idAlfabet) {
        try {
            return ctrlDomini.getNomAlfabet(idAlfabet);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Character> getLletresAlfabet(Integer idAlfabet) {
        try {
            return ctrlDomini.getLletresAlfabet(idAlfabet);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String getNomEntrada(int idEntrada) {
        try {
            return ctrlDomini.getNomEntrada(idEntrada);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String getTypeEntrada (int idEntrada) {
        try {
            return ctrlDomini.getTypeEntrada(idEntrada);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Integer> getIdEntrades () {
        try {
            return ctrlDomini.getIdEntrades();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void eliminarEntrada(Integer idEntrada) {
        try {
            ctrlDomini.eliminarAlfabet(idEntrada);
            JOptionPane.showMessageDialog(null, "Entrada eliminada amb exit!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
