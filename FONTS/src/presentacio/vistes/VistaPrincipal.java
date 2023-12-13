package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;
import presentacio.elements.*;

import javax.swing.*;

import domini.controladors.ControladorDomini;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.ResourceBundle.Control;


public class VistaPrincipal extends JFrame {
    
    // Declaración de variables de la interfaz
    private JFrame frame;
    private JTabbedPane tabbedPane;

    private JPanel panellAlfabets;
    private JButton btCrearAlfabet, btModificarAlfabet, btEliminarAlfabet;
    private JList<ElementAlfabetLlista> jListAlfabets;

    private JPanel panellEntrades;
    private JButton btCrearEntrada, btModificarEntrada, btEliminarEntrada;
    private JList<ElementEntradaLlista> jListEntrades;

    private JPanel panellTeclats;
    private JButton btCrearTeclat, btModificarTeclat, btEliminarTeclat;
    private JList<ElementTeclatLlista> jListTeclats;

    // Constructor de la clase
    
    // Constructor de la classe
    public VistaPrincipal() {
        initComponents();
        initUI();
    }

    // Método para inicializar componentes de la interfaz
    private void initComponents() {
        // Inicialización de la ventana y el panel de pestañas
        frame = new JFrame("Vista principal");
        tabbedPane = new JTabbedPane();

        // Componentes relacionados con los Alfabetos
        panellAlfabets = new JPanel(); // GridLayout con 3 columnas y espaciado
        btCrearAlfabet = new JButton("Nou Alfabet");
        btModificarAlfabet = new JButton("Modificar Alfabet Seleccionat");
        btEliminarAlfabet = new JButton("Eliminar Alfabet Seleccionat");

        // Componentes relacionados con las Entradas
        panellEntrades = new JPanel(); // GridLayout con 3 columnas y espaciado
        btCrearEntrada = new JButton("Nova Entrada");
        btModificarEntrada = new JButton("Modificar Entrada Seleccionada");
        btEliminarEntrada = new JButton("Eliminar Entrada Seleccionada");

        // Componentes relacionados con los Teclados
        panellTeclats = new JPanel(); // GridLayout con 3 columnas y espaciado
        btCrearTeclat = new JButton("Nou Teclat");
        btModificarTeclat = new JButton("Modificar Teclat Seleccionat");
        btEliminarTeclat = new JButton("Eliminar Teclat Seleccionat");
    }

    // Método para inicializar la interfaz de usuario
    private void initUI() {
        initAlfabets();
        initEntrades();
        initTeclats();

        // Agregar el panel de pestañas al marco principal
        frame.add(tabbedPane);

        // Configuración de la ventana principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Centrar la ventana en la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    // Mostra la finestra
    public void mostrar() {
        frame.setVisible(true);
    }

    // Tanca la finestra
    public void tancar() {
        frame.setVisible(false);
    }

    // Método para inicializar la sección de Alfabets
    public void initAlfabets() {
        // Configuración del diseño del panel de Alfabets
        panellAlfabets.setLayout(new BoxLayout(panellAlfabets, BoxLayout.Y_AXIS));

        // Creación del panel de botones para los Alfabets
        JPanel panellBotonsAlfabet = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panellBotonsAlfabet.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Establece el ancho máximo
        panellBotonsAlfabet.add(btCrearAlfabet);
        panellBotonsAlfabet.add(btModificarAlfabet);
        panellBotonsAlfabet.add(btEliminarAlfabet);

        // Configuración de las acciones de los botones de Alfabets
        btCrearAlfabet.addActionListener(e -> crearAlfabet());
        btModificarAlfabet.addActionListener(e -> modificarAlfabet());
        btEliminarAlfabet.addActionListener(e -> eliminarAlfabet());

        // Obtención de datos de Alfabets y visualización en la lista
        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        DefaultListModel<ElementAlfabetLlista> listModel = new DefaultListModel<ElementAlfabetLlista>();

        // Iteración sobre los Alfabets y creación de elementos para la lista
        for (int id : idAlfabets) {
            String nom = ControladorPresentacio.getNomAlfabet(id);
            ArrayList<Character> lletres = ControladorPresentacio.getLletresAlfabet(id);
            ElementAlfabetLlista elm = new ElementAlfabetLlista(id, nom, lletres);
            listModel.addElement(elm);
        }

        // Configuración del renderizado de la lista de Alfabets
        jListAlfabets = new JList<>(listModel);
        jListAlfabets.setCellRenderer(new ElementAlfabetListRenderer());

        // Configuración del panel de Alfabets y su adición a las pestañas
        panellAlfabets.removeAll();
        panellAlfabets.add(new JScrollPane(jListAlfabets));
        panellAlfabets.add(panellBotonsAlfabet);

        tabbedPane.addTab("Alfabet", panellAlfabets);
    }

    // Método para inicializar la sección de Entradas
    public void initEntrades() {
        // Configuración del diseño del panel de Entradas
        panellEntrades.setLayout(new BoxLayout(panellEntrades, BoxLayout.Y_AXIS));

        // Creación del panel de botones para las Entradas
        JPanel panelBotonsEntrades = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonsEntrades.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Establece el ancho máximo
        panelBotonsEntrades.add(btCrearEntrada);
        panelBotonsEntrades.add(btModificarEntrada);
        panelBotonsEntrades.add(btEliminarEntrada);

        // Configuración de las acciones de los botones de Entradas
        btCrearEntrada.addActionListener(e -> crearEntrada());
        btModificarEntrada.addActionListener(e -> modificarEntrada());
        btEliminarEntrada.addActionListener(e -> eliminarEntrada());

        // Obtención de datos de Entradas y visualización en la lista
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntrades();
        DefaultListModel<ElementEntradaLlista> listModel = new DefaultListModel<ElementEntradaLlista>();

        // Iteración sobre las Entradas y creación de elementos para la lista
        for (int id : idEntrades) {
            String nom = ControladorPresentacio.getNomEntrada(id);
            String tipus = ControladorPresentacio.getTypeEntrada(id);
            String nomAlfabet = "cal implementar mes a domini";
            String contingutPreview = "cal implementar mes a domini";
            ElementEntradaLlista elm = new ElementEntradaLlista(id, nom, tipus, contingutPreview, nomAlfabet);
            listModel.addElement(elm);
        }

        // Configuración del renderizado de la lista de Entradas
        jListEntrades = new JList<>(listModel);
        jListEntrades.setCellRenderer(new ElementEntradaListRenderer());

        // Configuración del panel de Entradas y su adición a las pestañas
        panellEntrades.removeAll();
        panellEntrades.add(new JScrollPane(jListEntrades));
        panellEntrades.add(panelBotonsEntrades);

        tabbedPane.addTab("Entrades", panellEntrades);
    }
    
    // Método para inicializar la sección de Entradas
    public void initTeclats() {
        // Configuración del diseño del panel de Entradas
        panellTeclats.setLayout(new BoxLayout(panellTeclats, BoxLayout.Y_AXIS));

        // Creación del panel de botones para las Entradas
        JPanel panelBotonsTeclats = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonsTeclats.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Establece el ancho máximo
        panelBotonsTeclats.add(btCrearTeclat);
        panelBotonsTeclats.add(btModificarTeclat);
        panelBotonsTeclats.add(btEliminarTeclat);

        // Configuración de las acciones de los botones de Entradas
        btCrearTeclat.addActionListener(e -> crearTeclat());
        btModificarTeclat.addActionListener(e -> modificarTeclat());
        btEliminarTeclat.addActionListener(e -> eliminarTeclat());

        // Obtención de datos de Entradas y visualización en la lista
        ArrayList<Integer> idTeclats = ControladorPresentacio.getIdTeclats();
        DefaultListModel<ElementTeclatLlista> listModel = new DefaultListModel<ElementTeclatLlista>();

        // Iteración sobre las Entradas y creación de elementos para la lista
        for (int id : idTeclats) {
            String nom = ControladorPresentacio.getNomTeclat(id);
            int idEntrada = ControladorPresentacio.getIdEntradaTeclat(id);
            String nomEntrada = ControladorPresentacio.getNomEntrada(idEntrada);
            int files = ControladorPresentacio.getFilesTeclat(id);
            int columnes = ControladorPresentacio.getColumnesTeclat(id);

            ElementTeclatLlista elm = new ElementTeclatLlista(id, nom, nomEntrada, files, columnes);
            listModel.addElement(elm);
        }

        // Configuración del renderizado de la lista de Entradas
        jListTeclats = new JList<>(listModel);
        jListTeclats.setCellRenderer(new ElementTeclatListRenderer());

        // Configuración del panel de Entradas y su adición a las pestañas
        panellTeclats.removeAll();
        panellTeclats.add(new JScrollPane(jListTeclats));
        panellTeclats.add(panelBotonsTeclats);

        tabbedPane.addTab("Teclats", panellTeclats);
    }
    
    // metodo que llama el btCrearAlfabet
    private void crearAlfabet() {
        VistaCrearAlfabet vCrearAlfabet = new VistaCrearAlfabet();
    }

    // metodo que llama el btModificarAlfabet
    private void modificarAlfabet() {
        // Lógica para modificar un alfabeto seleccionado
        
    }

    // metodo que llama el btEliminarAlfabet
    private void eliminarAlfabet() {
        int selectedIndex = jListAlfabets.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap alfabet seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el alfabeto seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        model.remove(selectedIndex);
        ControladorPresentacio.eliminarAlfabet(model.get(selectedIndex).getId());
    }

    // metodo que llama el btCrearEntrada
    private void crearEntrada() {
        VistaCrearText vCrearAlfabet = new VistaCrearText();
        vCrearAlfabet.mostrar();
    }

    // metodo que llama el btModificarEntrada
    private void modificarEntrada() {
        // Lógica para modificar un alfabeto seleccionado
        
    }

    // metodo que llama el btEliminarEntrada
    private void eliminarEntrada() {
        int selectedIndex = jListEntrades.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap entrada seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar la entrada seleccionada?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        model.remove(selectedIndex);

        ControladorPresentacio.eliminarEntrada(model.get(selectedIndex).getId());
    }

    // metodo que llama el btCrearTeclat
    private void crearTeclat() {
    }

    // metodo que llama el btModificarTeclat
    private void modificarTeclat() {
    }

    // metodo que llama el btEliminarTeclat
    private void eliminarTeclat() {
        int selectedIndex = jListTeclats.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap entrada seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el seleccionat?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        model.remove(selectedIndex);
        ControladorPresentacio.eliminarTeclat(model.get(selectedIndex).getId());
    }

}
