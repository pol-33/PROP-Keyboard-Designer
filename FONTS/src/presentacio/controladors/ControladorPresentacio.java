package presentacio.controladors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;

import domini.controladors.ControladorDomini;
import presentacio.elements.ElementAlfabetLlista;
import presentacio.vistes.*;

public class ControladorPresentacio {
    
    // Variables estáticas para las vistas y el controlador de dominio
    private static VistaLogin vLogin;
    private static VistaPrincipal vPrincipal;
    private static ControladorDomini ctrlDomini = new ControladorDomini();

    // Método para iniciar la aplicación
    public static void startAplicacio() {
        vLogin = new VistaLogin();
        vLogin.mostrar();
        crearProves();
    }

    // Método para crear datos de prueba
    private static void crearProves() {
        try {
            ArrayList<Character> lletresANG = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> lletresCAST = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> lletresCAT = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

            int idAngles = ctrlDomini.crearAlfabet("Angles", lletresANG);
            int idCastella = ctrlDomini.crearAlfabet("Castella", lletresCAST);
            int idCatala = ctrlDomini.crearAlfabet("Catala", lletresCAT);

            int idTxt1 = ctrlDomini.crearText("Hello world!", "Hello world in english!", idAngles);
            int idTxt2 = ctrlDomini.crearText("Hola mundo!", "Hola mundo en español!", idCastella);
            HashMap<String, Integer> lpf = new HashMap<>();
            lpf.put("hola", 3);
            lpf.put("mon", 4);
            int idLPF1 = ctrlDomini.crearLPF("Hola mon", lpf, idCatala);

            ctrlDomini.crearTeclatDuesMans("Teclat 1", idTxt1, 3, 10);
            ctrlDomini.crearTeclatDuesMans("Teclat 2", idTxt2, 3, 10);
            ctrlDomini.crearTeclatDuesMans("Teclat 2", idTxt2, 3, 10);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al carregar les proves: " + e.getMessage());
        }

    }

    // Métodos para la gestión de usuarios
    public static void crearUsuari(String usuari, String contrasenya) {
        try {
            ctrlDomini.crearUsuari(usuari, contrasenya);
            vPrincipal = new VistaPrincipal();
            vPrincipal.mostrar();
            vLogin.tancar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void iniciarSessio(String usuari, String contrasenya) {
        try {
            ctrlDomini.iniciarSessio(usuari, contrasenya);
            vPrincipal = new VistaPrincipal();
            vPrincipal.mostrar();
            vLogin.tancar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void tancarSessio() {
        try {
            ctrlDomini.tancarSessio();
            vPrincipal.tancar();
            vLogin = new VistaLogin();
            vLogin.mostrar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al tancar sessio: " + e.getMessage());
        }
    }
    
    // Métodos para la gestión de Alfabetos
    public static void crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) {
        try {
            int idAlfabet = ctrlDomini.crearAlfabet(nomAlfabet, lletres);
            vPrincipal.afegirAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "A;lfabet creat amb exit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void eliminarAlfabet(Integer idAlfabet) {
        try {
            ctrlDomini.eliminarAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "Alfabet eliminat amb exit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static ArrayList<Integer> getIdAlfabets () {
        try {
            return ctrlDomini.getIdAlfabets();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getNomAlfabet(Integer idAlfabet) {
        try {
            return ctrlDomini.getNomAlfabet(idAlfabet);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Character> getLletresAlfabet(Integer idAlfabet) {
        try {
            return ctrlDomini.getLletresAlfabet(idAlfabet);
        } catch (Exception e) {
            return null;
        }
    }

    // Métodos para la gestión de Entradas
    public static String getNomEntrada(int idEntrada) {
        try {
            return ctrlDomini.getNomEntrada(idEntrada);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getTipusEntrada (int idEntrada) {
        try {
            return ctrlDomini.getTipusEntrada(idEntrada);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Integer> getIdEntrades () {
        try {
            return ctrlDomini.getIdEntrades();
        } catch (Exception e) {
            return null;
        }
    }

    public static void eliminarEntrada(Integer idEntrada) {
        try {
            ctrlDomini.eliminarAlfabet(idEntrada);
            JOptionPane.showMessageDialog(null, "Entrada eliminada amb exit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // Métodos para la gestión de Teclats
    public static String getNomTeclat(int idTeclat) {
        try {
            return ctrlDomini.getNomTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getIdEntradaTeclat (int idTeclat) {
        try {
            return ctrlDomini.getIdEntradaVinculadaTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getFilesTeclat (int idTeclat) {
        try {
            return ctrlDomini.getFilesTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getColumnesTeclat (int idTeclat) {
        try {
            return ctrlDomini.getColumnesTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Character> getDistribucioTeclat (int idTeclat) {
        try {
            return ctrlDomini.getDistribucioTeclat(idTeclat);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al veure el teclat: " + e.getMessage());
            return null;
        }
    }
    
    public static ArrayList<Integer> getIdTeclats () {
        try {
            return ctrlDomini.getIdTeclats();
        } catch (Exception e) {
            return null;
        }
    }

    public static void eliminarTeclat(Integer idTeclat) {
        try {
            ctrlDomini.eliminarAlfabet(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat eliminat amb exit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void crearTeclatDuesMans(String nom, Integer idEntrada, int files, int columnes) {
        try {
            int idTeclat = ctrlDomini.crearTeclatDuesMans(nom, idEntrada, files, columnes);
            vPrincipal.afegirTeclat(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat modificat amb exit");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el teclat: " + e.getMessage());
        }
    }

    public static void crearTeclatPolzes(String nom, Integer idEntrada, int files, int columnes) {
        try {
            int idTeclat = ctrlDomini.crearTeclatPolzes(nom, idEntrada, files, columnes);
            vPrincipal.afegirTeclat(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat creat amb exit");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el teclat: " + e.getMessage());
        }
    }

    public static void modificarFilesColumnesTeclat(Integer idTeclat, int files, int columnes) {
        try {
            ctrlDomini.modificarFilesColumnesTeclat(idTeclat, files, columnes);
            vPrincipal.actualitzarTeclatLlista(idTeclat);

            JOptionPane.showMessageDialog(null, "Teclat modificat amb exit");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el teclat: " + e.getMessage());
        }
    }

    // Metodes per a la gestio de Texts
    public static void crearText(String nomEntrada, String contingutEntrada, Integer idAlfabet) {
        try {
            int idEntrada = ctrlDomini.crearText(nomEntrada, contingutEntrada, idAlfabet);
            vPrincipal.afegirEntrada(idEntrada);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el text: " + e.getMessage());
        }
    }

    public static void crearLPF(String nombreEntrada, HashMap<String, Integer> contenidoEntrada, Integer idAlfabetSeleccionado) {
        try {
            int idEntrada = ctrlDomini.crearLPF(nombreEntrada, contenidoEntrada, idAlfabetSeleccionado);
            vPrincipal.afegirEntrada(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el LPF: " + e.getMessage());
        }
    }

    public static void importarLPF(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String nombreEntrada = scanner.nextLine();
        HashMap<String, Integer> contenidoEntrada = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            String word = parts[0];
            int frequency = Integer.parseInt(parts[1]);
            contenidoEntrada.put(word, frequency);
        }
        scanner.close();

        // Get the ids of the existing alphabets
        ArrayList<Integer> idAlfabets = getIdAlfabets();
        // Get the names of the existing alphabets
        ArrayList<String> nombresAlfabets = new ArrayList<>();
        for (Integer id : idAlfabets) {
            nombresAlfabets.add(getNomAlfabet(id));
        }

        // Create a JComboBox with the names of the alphabets
        JComboBox<String> alfabetComboBox = new JComboBox<>(nombresAlfabets.toArray(new String[0]));

        // Show a dialog to select an alphabet
        int result = JOptionPane.showConfirmDialog(null, alfabetComboBox, "Seleccionar alfabeto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // If the user clicked "OK", create the LPF with the selected alphabet
        if (result == JOptionPane.OK_OPTION) {
            int indiceSeleccionado = alfabetComboBox.getSelectedIndex();
            Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado);
            crearLPF(nombreEntrada, contenidoEntrada, idAlfabetSeleccionado);
        }
    }

    public static void importarText(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String nombreEntrada = scanner.nextLine();
        StringBuilder contenidoEntrada = new StringBuilder();
        while (scanner.hasNextLine()) {
            contenidoEntrada.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        // Get the ids of the existing alphabets
        ArrayList<Integer> idAlfabets = getIdAlfabets();
        // Get the names of the existing alphabets
        ArrayList<String> nombresAlfabets = new ArrayList<>();
        for (Integer id : idAlfabets) {
            nombresAlfabets.add(getNomAlfabet(id));
        }

        // Create a JComboBox with the names of the alphabets
        JComboBox<String> alfabetComboBox = new JComboBox<>(nombresAlfabets.toArray(new String[0]));

        // Show a dialog to select an alphabet
        int result = JOptionPane.showConfirmDialog(null, alfabetComboBox, "Seleccionar alfabeto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // If the user clicked "OK", create the Text with the selected alphabet
        if (result == JOptionPane.OK_OPTION) {
            int indiceSeleccionado = alfabetComboBox.getSelectedIndex();
            Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado);
            crearText(nombreEntrada, contenidoEntrada.toString(), idAlfabetSeleccionado);
        }
    }

    public static void modificarAlfabetAfegirLletra(Integer idAlfabet, Character letter) {
        try {
            ctrlDomini.afegirLletraAlfabet(idAlfabet, letter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar l'alfabet: " + e.getMessage());
        }
    }
}
