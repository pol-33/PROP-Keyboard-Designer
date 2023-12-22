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
    }

    // Métodos para la gestión de usuarios
    public static int crearUsuari(String usuari, String contrasenya) {
        try {
            ctrlDomini.crearUsuari(usuari, contrasenya);
            vPrincipal = new VistaPrincipal();
            vPrincipal.mostrar();
            vLogin.tancar();
            return 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return -1;
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
    
    // Métodos para la gestión de Alfabets
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
            return ctrlDomini.getTextEntrada(idText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el contingut del text: " + e.getMessage());
            return null;
        }
    }

    public static HashMap<String, Integer> getContingutLPF(Integer idLPF) {
        try {
            return ctrlDomini.getLpfEntrada(idLPF);
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

    public static Integer getFilesOptimesTeclat(Integer idTeclat, Integer numCols) {
        try {
            return ctrlDomini.getFilesOptimesTeclat(idTeclat, numCols);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir les files òptimes del teclat: " + e.getMessage());
            return null;
        }
    }

    public static Integer getColumnesOptimesTeclat(Integer idTeclat, Integer numFiles) {
        try {
            return ctrlDomini.getColumnesOptimesTeclat(idTeclat, numFiles);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir les columnes òptimes del teclat: " + e.getMessage());
            return null;
        }
    }

    public static void importarTeclat(File selectedFile) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(selectedFile);
            String nomTeclat = scanner.nextLine();
            if (nomTeclat == null || nomTeclat.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nom del teclat no pot estar buit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int files = Integer.parseInt(scanner.nextLine());
            int columnes = Integer.parseInt(scanner.nextLine());
            ArrayList<Character> distribucio = new ArrayList<>();
            for (char c : scanner.nextLine().toCharArray()) {
                distribucio.add(c);
            }
            scanner.close();

            ArrayList<Integer> idEntrades = getIdEntrades();
            ArrayList<String> nombresEntrades = new ArrayList<>();
            for (Integer id : idEntrades) {
                nombresEntrades.add(getNomEntrada(id));
            }
            JComboBox<String> entradaComboBox = new JComboBox<>(nombresEntrades.toArray(new String[0]));
            int resultat = JOptionPane.showConfirmDialog(null, entradaComboBox, "Selecciona la entrada a la que vols associar el teclat", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultat == JOptionPane.OK_OPTION) {
                int indiceSeleccionado = entradaComboBox.getSelectedIndex();
                Integer idEntradaSeleccionada = idEntrades.get(indiceSeleccionado);
                Integer idAlfabetEntradaSeleccionada = getIdAlfabetEntrada(idEntradaSeleccionada);
                ArrayList<Character> lletresAlfabet = getLletresAlfabet(idAlfabetEntradaSeleccionada);
                if (lletresAlfabet.size() != distribucio.size() || !lletresAlfabet.containsAll(distribucio) || !distribucio.containsAll(lletresAlfabet)) {
                    JOptionPane.showMessageDialog(null, "La distribució de lletres no coincideix amb l'alfabet de l'entrada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Integer idTeclat = ctrlDomini.importarTeclat(nomTeclat, idEntradaSeleccionada, files, columnes, distribucio, 0);
                vPrincipal.afegirTeclat(idTeclat);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en importar el Teclat.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en importar el Teclat: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Integer getIdAlfabetEntrada(Integer idEntradaSeleccionada) {
        try {
            return ctrlDomini.getIdAlfabetEntrada(idEntradaSeleccionada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir l'alfabet de l'entrada: " + e.getMessage());
            return null;
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
            String line = scanner.nextLine();
            int lastSpaceIndex = line.lastIndexOf(" ");
            String word = line.substring(0, lastSpaceIndex);
            int frequency = Integer.parseInt(line.substring(lastSpaceIndex + 1));
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
            ctrlDomini.modificarContingutText(idEntrada, newContent);
            vPrincipal.actualitzarEntradaLlista(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en modificar l'entrada text: " + e.getMessage());
        }
    }

    public static void modificarContingutLPF(Integer idEntrada, HashMap<String, Integer> newContent) {
        try {
            ctrlDomini.modificarContingutLPF(idEntrada, newContent);
            vPrincipal.actualitzarEntradaLlista(idEntrada);
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

    public static String getNomAlfabetEntrada(int idEntrada) {
        try {
            return ctrlDomini.getNomAlfabetEntrada(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el nom de l'alfabet de l'entrada: " + e.getMessage());
            return null;
        }
    }

    public static String getPreviewEntrada(int idEntrada) {
        try {
            if (getTipusEntrada(idEntrada) == 0) {
                return getContingutText(idEntrada);
            } else {
                return getContingutLPF(idEntrada).toString();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir la preview de l'entrada: " + e.getMessage());
            return null;
        }
    }

}
