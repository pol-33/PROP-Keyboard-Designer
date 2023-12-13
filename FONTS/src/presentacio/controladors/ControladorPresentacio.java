package presentacio.controladors;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domini.controladors.ControladorDomini;
import presentacio.vistes.*;

public class ControladorPresentacio {
    
    private static VistaLogin vLogin;

    /** Instància del controlador de domini */
    private static ControladorDomini ctrlDomini = new ControladorDomini();


    public static void startAplicacio() {
        vLogin = new VistaLogin();
        vLogin.mostrar();
    }

    public static void crearUsuari(String usuari, String contrasenya) {
        try {
            ctrlDomini.crearUsuari(usuari, contrasenya);
            VistaPrincipal vPrincipal = new VistaPrincipal();
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
            VistaPrincipal vPrincipal = new VistaPrincipal();
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
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
