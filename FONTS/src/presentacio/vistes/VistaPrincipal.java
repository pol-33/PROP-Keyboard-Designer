package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;
import presentacio.elements.*;

import javax.swing.*;

import domini.controladors.ControladorDomini;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class VistaPrincipal extends JFrame {
    
    private JFrame frame;
    private JTabbedPane tabbedPane;

    private JPanel panellAlfabets;
    private JButton btCrearAlfabet, btModificarAlfabet, btEliminarAlfabet;
    private JList<ElementAlfabetLlista> jListAlfabets;

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
    }

    private void initUI() {
        initAlfabets();
        tabbedPane.addTab("Entrades", new JPanel());
        tabbedPane.addTab("Teclats", new JPanel());

        frame.add(tabbedPane);

        // Configuramos la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Centrar la ventana en la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void initAlfabets() {
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
    
        ArrayList<ElementAlfabetLlista> elementsAlfabets = new ArrayList<>();
        ArrayList<Character> lletresANG = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> lletresCAST = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> lletresCAT = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        elementsAlfabets.add(new ElementAlfabetLlista(1, "Angles", lletresANG));
        elementsAlfabets.add(new ElementAlfabetLlista(2, "Castella", lletresCAST));
        elementsAlfabets.add(new ElementAlfabetLlista(3, "Catala", lletresCAT));
    
        DefaultListModel<ElementAlfabetLlista> listModel = new DefaultListModel<>();
        for (ElementAlfabetLlista elemento : elementsAlfabets) {
            listModel.addElement(elemento);
        }
    
        jListAlfabets = new JList<>(listModel);
        jListAlfabets.setCellRenderer(new ElementAlfabetListRenderer());
    
        panellAlfabets.add(new JScrollPane(jListAlfabets));
        panellAlfabets.add(panellBotonsAlfabet);
    
        tabbedPane.addTab("Alfabet", panellAlfabets);
    }

    private void crearAlfabet() {
        // Lógica para crear un nuevo alfabeto
        // Por ejemplo:
        
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
            //ControladorPresentacio.eliminarAlfabet(model.get(selectedIndex).getId());
            }
            catch (Exception e) {

            }
    }
}
