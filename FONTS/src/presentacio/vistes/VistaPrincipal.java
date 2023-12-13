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
    
    private JFrame frame;
    private JTabbedPane tabbedPane;

    private JPanel panellAlfabets;
    private JButton btCrearAlfabet, btModificarAlfabet, btEliminarAlfabet;
    private JList<ElementAlfabetLlista> jListAlfabets;

    private JPanel panellEntrades;
    private JButton btCrearEntrada, btModificarEntrada, btEliminarEntrada;
    private JList<ElementEntradaLlista> jListEntrades;

    public void tancar() {
        frame.setVisible(false);
    }
    public void mostrar() {
        frame.setVisible(true);
    } 

    public VistaPrincipal() {
        initComponents();
        initUI();
    }

    private void initComponents() {
        frame = new JFrame("Vista principal");
        tabbedPane = new JTabbedPane();

        panellAlfabets = new JPanel(); // GridLayout con 3 columnas y espaciado
        btCrearAlfabet = new JButton("Nou Alfabet");
        btModificarAlfabet = new JButton("Modificar Alfabet Seleccionat");
        btEliminarAlfabet = new JButton("Eliminar Alfabet Seleccionat");

        panellEntrades = new JPanel(); // GridLayout con 3 columnas y espaciado
        btCrearEntrada = new JButton("Nova Entrada");
        btModificarEntrada = new JButton("Modificar Entrada Seleccionada");
        btEliminarEntrada = new JButton("Eliminar Entrada Seleccionada");
    }

    private void initUI() {
        initAlfabets();
        initEntrades();
        
        tabbedPane.addTab("Teclats", new JPanel());

        frame.add(tabbedPane);

        // Configuramos la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Centrar la ventana en la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    public void initAlfabets() {
        panellAlfabets.setLayout(new BoxLayout(panellAlfabets, BoxLayout.Y_AXIS));
    
        JPanel panellBotonsAlfabet = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panellBotonsAlfabet.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Establece el ancho máximo
        panellBotonsAlfabet.add(btCrearAlfabet);
        panellBotonsAlfabet.add(btModificarAlfabet);
        panellBotonsAlfabet.add(btEliminarAlfabet);

        // Configuración de las acciones de los botones
        btCrearAlfabet.addActionListener(e -> crearAlfabet());
        btModificarAlfabet.addActionListener(e -> modificarAlfabet());
        btEliminarAlfabet.addActionListener(e -> eliminarAlfabet());
    
        // carregar els alfabets
        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        DefaultListModel<ElementAlfabetLlista> listModel = new DefaultListModel<ElementAlfabetLlista>();

        for (int id : idAlfabets) {
            String nom = ControladorPresentacio.getNomAlfabet(id);
            ArrayList<Character> lletres = ControladorPresentacio.getLletresAlfabet(id);
            ElementAlfabetLlista elm = new ElementAlfabetLlista(id, nom, lletres);
            listModel.addElement(elm);
        }
    
        jListAlfabets = new JList<>(listModel);
        jListAlfabets.setCellRenderer(new ElementAlfabetListRenderer());
        
        panellAlfabets.removeAll();
        panellAlfabets.add(new JScrollPane(jListAlfabets));
        panellAlfabets.add(panellBotonsAlfabet);
    
        tabbedPane.addTab("Alfabet", panellAlfabets);
    }

    public void initEntrades() {
        panellEntrades.setLayout(new BoxLayout(panellEntrades, BoxLayout.Y_AXIS));
    
        JPanel panelBotonsEntrades = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonsEntrades.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Establece el ancho máximo
        panelBotonsEntrades.add(btCrearEntrada);
        panelBotonsEntrades.add(btModificarEntrada);
        panelBotonsEntrades.add(btEliminarEntrada);

        // Configuración de las acciones de los botones
        btCrearEntrada.addActionListener(e -> crearEntrada());
        btModificarEntrada.addActionListener(e -> modificarEntrada());
        btEliminarEntrada.addActionListener(e -> eliminarEntrada());
    
        // carregar els alfabets
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntrades();
        DefaultListModel<ElementEntradaLlista> listModel = new DefaultListModel<ElementEntradaLlista>();

        for (int id : idEntrades) {
            String nom = ControladorPresentacio.getNomEntrada(id);
            String tipus = ControladorPresentacio.getTypeEntrada(id);
            String nomAlfabet = "cal implementar mes a domini";
            String contingutPreview = "cal implementar mes a domini";
            ElementEntradaLlista elm = new ElementEntradaLlista(id, nom, tipus, contingutPreview, nomAlfabet);
            listModel.addElement(elm);
        }
    
        jListEntrades = new JList<>(listModel);
        jListEntrades.setCellRenderer(new ElementEntradaListRenderer());
        
        panellEntrades.removeAll();
        panellEntrades.add(new JScrollPane(jListEntrades));
        panellEntrades.add(panelBotonsEntrades);
    
        tabbedPane.addTab("Entrades", panellEntrades);
    }

    private void crearAlfabet() {
        VistaCrearAlfabet vCrearAlfabet = new VistaCrearAlfabet();
    }

    private void modificarAlfabet() {
        // Lógica para modificar un alfabeto seleccionado
        
    }

    private void eliminarAlfabet() {
        int selectedIndex = jListAlfabets.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap alfabet seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el alfabeto seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        model.remove(selectedIndex);
        try {
            ControladorPresentacio.eliminarAlfabet(model.get(selectedIndex).getId());
        }
        catch (Exception e) {

        }
    }

    private void crearEntrada() {
        VistaCrearAlfabet vCrearAlfabet = new VistaCrearAlfabet();
    }

    private void modificarEntrada() {
        // Lógica para modificar un alfabeto seleccionado
        
    }

    private void eliminarEntrada() {
        int selectedIndex = jListEntrades.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap entrada seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar la entrada seleccionada?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        model.remove(selectedIndex);
        try {
            ControladorPresentacio.eliminarEntrada(model.get(selectedIndex).getId());
        }
        catch (Exception e) {

        }
    }
}
