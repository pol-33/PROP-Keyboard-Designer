package presentacio.controladors;

import java.awt.*;
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

/**
 * Classe ControladorPresentacio. Controlador de la capa de presentació.
 */
public class ControladorPresentacio {
    
    // Variables estáticas para las vistas y el controlador de dominio
    private static VistaLogin vLogin;
    private static VistaPrincipal vPrincipal;
    private static ControladorDomini ctrlDomini = new ControladorDomini();

    /**
     * Metode per a iniciar l'aplicació.
     */
    public static void startAplicacio() {
        vLogin = new VistaLogin();
        vLogin.mostrar();
    }

    /**
     * Metode per a tancar l'aplicació.
     * @param usuari Nom de l'usuari que ha tancat sessió.
     * @param contrasenya Contrasenya de l'usuari que ha tancat sessió.
     * @return 0 si s'ha tancat l'aplicació correctament, -1 altrament.
     */
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

    /**
     * Metode per a iniciar sessió.
     * @param usuari Nom de l'usuari que vol iniciar sessió.
     * @param contrasenya Contrasenya de l'usuari que vol iniciar sessió.
     */
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

    /**
     * Metode per a tancar sessió.
     */
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

    /**
     * Metode per a tancar l'aplicació.
     * @param nomAlfabet Nom de l'alfabet a crear.
     * @param lletres Lletres de l'alfabet a crear.
     * @return 0 si s'ha creat l'alfabet correctament, -1 altrament.
     */
    public static int crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) {
        try {
            int idAlfabet = ctrlDomini.crearAlfabet(nomAlfabet, lletres);
            vPrincipal.afegirAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "Alfabet creat amb èxit");
            vPrincipal.updateAlfabetComboBoxes();
            return 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Metode per a eliminar un alfabet.
     * @param idAlfabet Identificador de l'alfabet a eliminar.
     */
    public static void eliminarAlfabet(Integer idAlfabet) {
        try {
            ctrlDomini.eliminarAlfabet(idAlfabet);
            JOptionPane.showMessageDialog(null, "Alfabet eliminat amb èxit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    /**
     * Obte els identificadors de tots els alfabets.
     * @return ArrayList amb els identificadors de tots els alfabets.
     */
    public static ArrayList<Integer> getIdAlfabets () {
        try {
            return ctrlDomini.getIdAlfabets();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metode per a obtenir el nom d'un alfabet.
     * @param idAlfabet Identificador de l'alfabet.
     * @return String amb el nom de l'alfabet.
     */
    public static String getNomAlfabet(Integer idAlfabet) {
        try {
            return ctrlDomini.getNomAlfabet(idAlfabet);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metode per a obtenir les lletres d'un alfabet.
     * @param idAlfabet Identificador de l'alfabet.
     * @return ArrayList amb les lletres de l'alfabet.
     */
    public static ArrayList<Character> getLletresAlfabet(Integer idAlfabet) {
        try {
            return ctrlDomini.getLletresAlfabet(idAlfabet);
        } catch (Exception e) {
            return null;
        }
    }

    // Metodes per a la gestió d'Entradas

    /**
     * Obtenir el nom de d'una entrada.
     * @param idEntrada Identificador de l'entrada.
     * @return String amb el nom de l'entrada.
     */
    public static String getNomEntrada(int idEntrada) {
        try {
            return ctrlDomini.getNomEntrada(idEntrada);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte el tipus d'una entrada.
     * @param idEntrada Identificador de l'entrada.
     * @return Integer amb el tipus de l'entrada.
     */
    public static Integer getTipusEntrada (int idEntrada) {
        try {
            return ctrlDomini.getTipusEntrada(idEntrada);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte els identificadors de totes les entrades.
     * @return ArrayList amb els identificadors de totes les entrades.
     */
    public static ArrayList<Integer> getIdEntrades () {
        try {
            return ctrlDomini.getIdEntrades();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte els identificadors de totes les entrades d'un alfabet.
     * @param idAlfabetSeleccionado Identificador de l'alfabet.
     * @return ArrayList amb els identificadors de totes les entrades d'un alfabet.
     */
    public static ArrayList<Integer> getIdEntradesVinculadesAlfabet(Integer idAlfabetSeleccionado) {
        try {
            return ctrlDomini.getIdEntradesVinculadesAlfabet(idAlfabetSeleccionado);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte el contingut de text d'una entrada.
     * @param idText Identificador de l'entrada.
     * @return String amb el contingut de text de l'entrada.
     */
    public static String getContingutText(Integer idText) {
        try {
            return ctrlDomini.getTextEntrada(idText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el contingut del text: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obte el contingut de lpf d'una entrada.
     * @param idLPF Identificador de l'entrada.
     * @return HashMap amb el contingut de lpf de l'entrada.
     */
    public static HashMap<String, Integer> getContingutLPF(Integer idLPF) {
        try {
            return ctrlDomini.getLpfEntrada(idLPF);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el contingut de l'LPF: " + e.getMessage());
            return null;
        }
    }

    /**
     * Elimina una entrada.
     * @param idEntrada Identificador de l'entrada.
     */
    public static void eliminarEntrada(Integer idEntrada) {
        try {
            ctrlDomini.eliminarEntrada(idEntrada);
            JOptionPane.showMessageDialog(null, "Entrada eliminada amb exit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // metodes per a la gestió de Teclats

    /**
     * Obte el nom d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @return String amb el nom del teclat.
     */
    public static String getNomTeclat(int idTeclat) {
        try {
            return ctrlDomini.getNomTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte l'identificador de l'entrada vinculada a un teclat.
     * @param idTeclat Identificador del teclat.
     * @return Integer amb l'identificador de l'entrada vinculada al teclat.
     */
    public static Integer getIdEntradaTeclat (int idTeclat) {
        try {
            return ctrlDomini.getIdEntradaVinculadaTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte el nombre de files d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @return Integer amb el nombre de files del teclat.
     */
    public static Integer getFilesTeclat (int idTeclat) {
        try {
            return ctrlDomini.getFilesTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte el nombre de columnes d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @return Integer amb el nombre de columnes del teclat.
     */
    public static Integer getColumnesTeclat (int idTeclat) {
        try {
            return ctrlDomini.getColumnesTeclat(idTeclat);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obte la distribucio de tecles d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @return ArrayList amb la distribucio de tecles del teclat.
     */
    public static ArrayList<Character> getDistribucioTeclat (int idTeclat) {
        try {
            return ctrlDomini.getDistribucioTeclat(idTeclat);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al veure el teclat: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obte els identificadors de tots els teclats.
     * @return ArrayList amb els identificadors de tots els teclats.
     */
    public static ArrayList<Integer> getIdTeclats () {
        try {
            return ctrlDomini.getIdTeclats();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Elimina un teclat.
     * @param idTeclat Identificador del teclat.
     */
    public static void eliminarTeclat(Integer idTeclat) {
        try {
            ctrlDomini.eliminarTeclat(idTeclat);
            JOptionPane.showMessageDialog(null, "Teclat eliminat amb exit!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    /**
     * Crear un teclat optimitzat per a dues mans.
     * @param nom Nom del teclat.
     * @param idEntrada Identificador de l'entrada vinculada al teclat.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     * @return 0 si s'ha creat el teclat correctament, -1 altrament.
     */
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

    /**
     * Crear un teclat optimitzat per a dos dits.
     * @param nom Nom del teclat.
     * @param idEntrada Identificador de l'entrada vinculada al teclat.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     * @return 0 si s'ha creat el teclat correctament, -1 altrament.
     */
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

    /**
     * Modifica les files i columnes d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     */
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

    /**
     * Obte el nombre de files òptimes d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @param numCols Nombre de columnes del teclat.
     * @return Integer amb el nombre de files òptimes del teclat.
     */
    public static Integer getFilesOptimesTeclat(Integer idTeclat, Integer numCols) {
        try {
            return ctrlDomini.getFilesOptimesTeclat(idTeclat, numCols);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir les files òptimes del teclat: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obte el nombre de columnes òptimes d'un teclat.
     * @param idTeclat Identificador del teclat.
     * @param numFiles Nombre de files del teclat.
     * @return Integer amb el nombre de columnes òptimes del teclat.
     */
    public static Integer getColumnesOptimesTeclat(Integer idTeclat, Integer numFiles) {
        try {
            return ctrlDomini.getColumnesOptimesTeclat(idTeclat, numFiles);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir les columnes òptimes del teclat: " + e.getMessage());
            return null;
        }
    }

    /**
     * Importa un teclat.
     * @param selectedFile Arxiu amb el teclat a importar.
     * @throws FileNotFoundException Si no s'ha trobat l'arxiu.
     */
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
            JComboBox<String> tipusTeclatComboBox = new JComboBox<>(new String[]{"Teclat d'ordinador (dues mans)", "Teclat tàctil (dos dits)"});

            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new JLabel("Selecciona la entrada a la que vols associar el teclat"));
            panel.add(entradaComboBox);
            panel.add(new JLabel("Selecciona el tipus de teclat"));
            panel.add(tipusTeclatComboBox);

            int resultat = JOptionPane.showConfirmDialog(null, panel, "Selecciona les opcions", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultat == JOptionPane.OK_OPTION) {
                int indiceSeleccionado = entradaComboBox.getSelectedIndex();
                Integer idEntradaSeleccionada = idEntrades.get(indiceSeleccionado);
                Integer idAlfabetEntradaSeleccionada = getIdAlfabetEntrada(idEntradaSeleccionada);
                ArrayList<Character> lletresAlfabet = getLletresAlfabet(idAlfabetEntradaSeleccionada);
                if (lletresAlfabet.size() != distribucio.size() || !lletresAlfabet.containsAll(distribucio) || !distribucio.containsAll(lletresAlfabet)) {
                    JOptionPane.showMessageDialog(null, "La distribució de lletres no coincideix amb l'alfabet de l'entrada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (files * columnes < distribucio.size()) {
                    JOptionPane.showMessageDialog(null, "El nombre de files i columnes no pot ser inferior al nombre de lletres.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if (files * columnes > distribucio.size()) {
                    // Calculate the total size of the keyboard and the number of blank spaces needed
                    int totalSize = files * columnes;
                    int blankSpacesNeeded = totalSize - distribucio.size();

                    // Create a new list for the new distribution
                    ArrayList<Character> newDistribucio = new ArrayList<>();

                    // If there are still blank spaces needed, add them to the end of the new distribution
                    for (int i = 0; i < blankSpacesNeeded; i++) {
                        distribucio.add(' '); // Add a blank space
                    }
                }

                String tipusTeclat = (String) tipusTeclatComboBox.getSelectedItem();
                int tipus = tipusTeclat.equals("Teclat d'ordinador (dues mans)") ? 0 : 1;
                Integer idTeclat = ctrlDomini.importarTeclat(nomTeclat, idEntradaSeleccionada, files, columnes, distribucio, tipus);
                vPrincipal.afegirTeclat(idTeclat);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en importar el Teclat.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en importar el Teclat: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Obte el tipus d'un teclat.
     * @param idTeclado Identificador del teclat.
     * @return Integer amb el tipus del teclat.
     */
    public static int getTipusTeclat(int idTeclado) {
        try {
            return ctrlDomini.getTipusTeclat(idTeclado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el tipus del teclat: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Obte l'identificador de l'alfabet d'una entrada.
     * @param idEntradaSeleccionada Identificador de l'entrada.
     * @return Integer amb l'identificador de l'alfabet de l'entrada.
     */
    private static Integer getIdAlfabetEntrada(Integer idEntradaSeleccionada) {
        try {
            return ctrlDomini.getIdAlfabetEntrada(idEntradaSeleccionada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir l'alfabet de l'entrada: " + e.getMessage());
            return null;
        }
    }

    // Metodes per a la gestio de Texts

    /**
     * Crea un text.
     * @param nomEntrada Nom de l'entrada.
     * @param contingutEntrada Contingut del text.
     * @param idAlfabet Identificador de l'alfabet.
     */
    public static void crearText(String nomEntrada, String contingutEntrada, Integer idAlfabet) {
        try {
            int idEntrada = ctrlDomini.crearText(nomEntrada, contingutEntrada, idAlfabet);
            vPrincipal.afegirEntrada(idEntrada);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el text: " + e.getMessage());
        }
    }

    /**
     * Crea un LPF.
     * @param nomEntrada Nom de l'entrada.
     * @param contingutEntrada Contingut del LPF.
     * @param idAlfabet Identificador de l'alfabet.
     */
    public static void crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, Integer idAlfabet) {
        try {
            int idEntrada = ctrlDomini.crearLPF(nomEntrada, contingutEntrada, idAlfabet);
            vPrincipal.afegirEntrada(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el LPF: " + e.getMessage());
        }
    }

    /**
     * Importa una LPF
     * @param file Arxiu amb la LPF a importar.
     * @throws FileNotFoundException Si no s'ha trobat l'arxiu.
     */
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

    /**
     * Importa un text.
     * @param file Arxiu amb el text a importar.
     * @throws FileNotFoundException Si no s'ha trobat l'arxiu.
     */
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

    /**
     * Modifica un alfabet afegint una lletra.
     * @param idAlfabet Identificador de l'alfabet.
     * @param letter Lletra a afegir.
     */
    public static void modificarAlfabetAfegirLletra(Integer idAlfabet, Character letter) {
        try {
            ctrlDomini.afegirLletraAlfabet(idAlfabet, letter);
            vPrincipal.actualitzarAlfabetLlista(idAlfabet);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en modificar l'alfabet: " + e.getMessage());
        }
    }

    /**
     * Moodifica el contingut d'un text.
     * @param idEntrada Identificador de l'entrada.
     * @param newContent Nou contingut del text.
     */
    public static void modificarContingutText(Integer idEntrada, String newContent) {
        try {
            ctrlDomini.modificarContingutText(idEntrada, newContent);
            vPrincipal.actualitzarEntradaLlista(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en modificar l'entrada text: " + e.getMessage());
        }
    }

    /**
     * Modifica el contingut d'un LPF.
     * @param idEntrada Identificador de l'entrada.
     * @param newContent Nou contingut del LPF.
     */
    public static void modificarContingutLPF(Integer idEntrada, HashMap<String, Integer> newContent) {
        try {
            ctrlDomini.modificarContingutLPF(idEntrada, newContent);
            vPrincipal.actualitzarEntradaLlista(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar l'entrada LPF: " + e.getMessage());
        }
    }

    /**
     * Obte els identificadors dels teclats vinculats a una entrada.
     * @param id Identificador de l'entrada.
     * @return ArrayList amb els identificadors dels teclats vinculats a una entrada.
     */
    public static ArrayList<Integer> getIdTeclatsVinculatsAEntrada(int id) {
        try {
            return ctrlDomini.getIdTeclatsVinculatsAEntrada(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir els teclats vinculats a l'entrada: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obte el nom de l'alfabet d'una entrada.
     * @param idEntrada Identificador de l'entrada.
     * @return String amb el nom de l'alfabet de l'entrada.
     */
    public static String getNomAlfabetEntrada(int idEntrada) {
        try {
            return ctrlDomini.getNomAlfabetEntrada(idEntrada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el nom de l'alfabet de l'entrada: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obte la preview del contingut d'una entrada.
     * @param idEntrada Identificador de l'entrada.
     * @return String amb la preview del contingut de l'entrada.
     */
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

    /**
     * Obte el nom de l'usuari actiu
     * @return String amb el nom de l'usuari actiu
     */
    public static String getNomUsuariActiu() {
        try {
            return ctrlDomini.getNomUsuariActiu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtenir el nom de l'usuari actiu: " + e.getMessage());
            return null;
        }
    }
}
