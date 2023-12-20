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
            ArrayList<Character> lletresANG = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> lletresCAST = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> lletresCAT = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
            ArrayList<Character> vocals = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

            int idAngles = ctrlDomini.crearAlfabet("Angles", lletresANG);
            int idCastella = ctrlDomini.crearAlfabet("Castella", lletresCAST);
            int idCatala = ctrlDomini.crearAlfabet("Catala", lletresCAT);
            int idVocals = ctrlDomini.crearAlfabet("Vocals", vocals);

            int idTxt1 = ctrlDomini.crearText("Hello world!", "Hello world in english!", idAngles);
            int idTxt2 = ctrlDomini.crearText("Hola mundo!", "Hola mundo en español!", idCastella);
            HashMap<String, Integer> lpf = new HashMap<>();
            lpf.put("hola", 3);
            lpf.put("mon", 4);
            int idLPF1 = ctrlDomini.crearLPF("Hola mon", lpf, idCatala);
            int idLPF2 = ctrlDomini.crearLPF("Hola mon 2", lpf, idVocals);

            ctrlDomini.crearTeclatDuesMans("Teclat 1", idTxt1, 3, 10);
            ctrlDomini.crearTeclatDuesMans("Teclat 2", idTxt2, 3, 10);
            ctrlDomini.crearTeclatDuesMans("Teclat 3", idTxt2, 3, 10);
            ctrlDomini.crearTeclatPolzes("Teclat 4", idLPF1, 3, 10);
            ctrlDomini.crearTeclatPolzes("Teclat 5", idLPF2, 3, 10);


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
    public static int crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) {
        try {
            int idAlfabet = ctrlDomini.crearAlfabet(nomAlfabet, lletres);
            vPrincipal.afegirAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "Alfabet creat amb èxit");
            return 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return -1;
        }
    }

    public static void eliminarAlfabet(Integer idAlfabet) {
        try {
            ctrlDomini.eliminarAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "Alfabet eliminat amb èxit!");
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

    public static Integer getIdAlfabetDeLPF(Integer idLPF) {
        try {
            //return ctrlDomini.getIdAlfabetDeLPF(idLPF);
            return 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir l'id de l'alfabet de l'LPF: " + e.getMessage());
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

    public static ArrayList<Integer> getIdEntradesVinculadesAlfabet(Integer idAlfabetSeleccionado) {
        try {
            return ctrlDomini.getIdEntradesVinculadesAlfabet(idAlfabetSeleccionado);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getContingutText(Integer idText) {
        try {
            //return ctrlDomini.getContingutText(idText);
            return "aqui es mostra el contingut del text quan s'implementi la funcio a domini";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el contingut del text: " + e.getMessage());
            return null;
        }
    }

    public static HashMap<String, Integer> getContingutLPF(Integer idLPF) {
        try {
            //return ctrlDomini.getContingutLPF(idLPF);
            HashMap<String, Integer> contingutProva = new HashMap<>();
            contingutProva.put("funcio", 3);
            contingutProva.put("obtenir", 4);
            contingutProva.put("contingut", 1);
            contingutProva.put("falta", 2);
            contingutProva.put("implementar", 5);
            contingutProva.put("domini", 6);
            return contingutProva;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el contingut de l'LPF: " + e.getMessage());
            return null;
        }
    }

    public static void eliminarEntrada(Integer idEntrada) {
        try {
            ctrlDomini.eliminarEntrada(idEntrada);
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
            ctrlDomini.eliminarTeclat(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat eliminat amb exit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static int crearTeclatDuesMans(String nom, Integer idEntrada, int files, int columnes) {
        try {
            int idTeclat = ctrlDomini.crearTeclatDuesMans(nom, idEntrada, files, columnes);
            vPrincipal.afegirTeclat(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat creat amb èxit");
            return 0;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el teclat: " + e.getMessage());
            return -1;
        }
    }

    public static int crearTeclatPolzes(String nom, Integer idEntrada, int files, int columnes) {
        try {
            int idTeclat = ctrlDomini.crearTeclatPolzes(nom, idEntrada, files, columnes);
            vPrincipal.afegirTeclat(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat creat amb exit");
            return 0;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el teclat: " + e.getMessage());
            return -1;
        }
    }

    public static void modificarFilesColumnesTeclat(Integer idTeclat, int files, int columnes) {
        try {
            ctrlDomini.modificarFilesColumnesTeclat(idTeclat, files, columnes);
            vPrincipal.actualitzarTeclatLlista(idTeclat);

            JOptionPane.showMessageDialog(null, "Teclat modificat amb èxit");
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
            vPrincipal.actualitzarAlfabetLlista(idAlfabet);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en modificar l'alfabet: " + e.getMessage());
        }
    }

    public static void modificarContingutText(Integer idEntrada, String newContent) {
        try {
            //ctrlDomini.modificarContingutText(idEntrada, newContent);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en modificar l'entrada text: " + e.getMessage());
        }
    }

    public static void modificarContingutLPF(Integer idEntrada, HashMap<String, Integer> newContent) {
        try {
            //ctrlDomini.modificarContingutLPF(idEntrada, newContent);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar l'entrada LPF: " + e.getMessage());
        }
    }

    public static ArrayList<Integer> getIdTeclatsVinculatsAEntrada(int id) {
        try {
            return ctrlDomini.getIdTeclatsVinculatsAEntrada(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir els teclats vinculats a l'entrada: " + e.getMessage());
            return null;
        }
    }
}
