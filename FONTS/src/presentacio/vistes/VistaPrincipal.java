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
    
    // Declaracio de variables de la interficie
    private JFrame frame;
    
    private JPanel panellMenu = new JPanel();
    JButton btTancarSesio;

    private JTabbedPane pestanyes;

    private JPanel panellAlfabets;
    private JButton btCrearAlfabet, btModificarAlfabet, btEliminarAlfabet;
    private JList<ElementAlfabetLlista> jListAlfabets;

    private JPanel panellEntrades;
    private JButton btCrearEntrada, btModificarEntrada, btEliminarEntrada;
    private JList<ElementEntradaLlista> jListEntrades;

    private JPanel panellTeclats;
    private JButton btCrearTeclat, btModificarTeclat, btEliminarTeclat, btVeureTeclat;
    private JList<ElementTeclatLlista> jListTeclats;

    // ? Constructor de la classe
    public VistaPrincipal() {
        initUI();
        carregaAlfabets();
        carregaEntrades();
        carregarTeclats();
    }

    // Mostra la finestra
    public void mostrar() {
        frame.setVisible(true);
    }

    // Tanca la finestra
    public void tancar() {
        frame.setVisible(false);
    }

    // * ------------------ INICIALITZADORS DEL UI -----------

    // Metode per inicialitzar la interficie d'usuari
    private void initUI() {
        // Inicialitzacio de la finestra i el panell de pestanyes
        frame = new JFrame("Vista principal");
        
        JPanel panellFrame = new JPanel(new BorderLayout());

        panellMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panellMenu.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Estableix l'ample maxim
        
        pestanyes = new JTabbedPane();

        initUIMenu();
        panellFrame.add(panellMenu, BorderLayout.NORTH);

        initUIAlfabets();
        pestanyes.addTab("Alfabet", panellAlfabets);

        initUIEntrades();
        pestanyes.addTab("Entrades", panellEntrades);

        initUITeclats();
        pestanyes.addTab("Teclats", panellTeclats);

        panellFrame.add(pestanyes, BorderLayout.CENTER);

        frame.add(panellFrame);

        // Configuracio de la finestra principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Centrar la finestra a la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void initUIMenu() {
        btTancarSesio = new JButton("Tancar Sessio");
        btTancarSesio.addActionListener(e -> tancarSessio());

        panellMenu.add(btTancarSesio);
    }

    private void initUIAlfabets() {

        // Configuracio del disseny del panell d'Alfabets
        panellAlfabets = new JPanel();
        panellAlfabets.setLayout(new BoxLayout(panellAlfabets, BoxLayout.Y_AXIS));

        // Botons d'Alfabets
        btCrearAlfabet = new JButton("Nou Alfabet");
        btModificarAlfabet = new JButton("Modificar Alfabet Seleccionat");
        btEliminarAlfabet = new JButton("Eliminar Alfabet Seleccionat");

        // Configuracio de les accions dels botons d'Alfabets
        btCrearAlfabet.addActionListener(e -> crearAlfabet());
        btModificarAlfabet.addActionListener(e -> modificarAlfabet());
        btEliminarAlfabet.addActionListener(e -> eliminarAlfabet());

        // Creacio del panell de botons per als Alfabets
        JPanel panellBotonsAlfabet = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panellBotonsAlfabet.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Estableix l'ample maxim
        panellBotonsAlfabet.add(btCrearAlfabet);
        panellBotonsAlfabet.add(btModificarAlfabet);
        panellBotonsAlfabet.add(btEliminarAlfabet);

        // afegim la llista al panell, tot i que encara no l'hem carregat
        DefaultListModel<ElementAlfabetLlista> listModel = new DefaultListModel<ElementAlfabetLlista>();
        jListAlfabets = new JList<>(listModel);
        jListAlfabets.setCellRenderer(new ElementAlfabetListRenderer());
        panellAlfabets.add(new JScrollPane(jListAlfabets));

        // afegim el panell dels botons al panell
        panellAlfabets.add(panellBotonsAlfabet);
        
    }

    private void initUIEntrades() {

        // Configuracio del disseny del panell d'Entrades
        panellEntrades = new JPanel(); // GridLayout amb 3 columnes i espaiat
        panellEntrades.setLayout(new BoxLayout(panellEntrades, BoxLayout.Y_AXIS));

        // Botons
        btCrearEntrada = new JButton("Nova Entrada");
        btModificarEntrada = new JButton("Modificar Entrada Seleccionada");
        btEliminarEntrada = new JButton("Eliminar Entrada Seleccionada");

        // Configuracio de les accions dels botons d'Entrades
        btCrearEntrada.addActionListener(e -> crearEntrada());
        btModificarEntrada.addActionListener(e -> modificarEntrada());
        btEliminarEntrada.addActionListener(e -> eliminarEntrada());

        // Creacio del panell de botons per a les Entrades
        JPanel panelBotonsEntrades = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonsEntrades.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Estableix l'ample maxim
        panelBotonsEntrades.add(btCrearEntrada);
        panelBotonsEntrades.add(btModificarEntrada);
        panelBotonsEntrades.add(btEliminarEntrada);

        // afegim la llista al panell, tot i que encara no l'hem carregat
        DefaultListModel<ElementEntradaLlista> listModel = new DefaultListModel<ElementEntradaLlista>();
        jListEntrades = new JList<>(listModel);
        jListEntrades.setCellRenderer(new ElementEntradaListRenderer());
        panellEntrades.add(new JScrollPane(jListEntrades));

        // Afegim el panell dels botons a la pestanya
        panellEntrades.add(panelBotonsEntrades);

    }

    private void initUITeclats() {
        // Configuracio del disseny del panell de Teclats
        panellTeclats = new JPanel();
        panellTeclats.setLayout(new BoxLayout(panellTeclats, BoxLayout.Y_AXIS));

        // Botons
        btCrearTeclat = new JButton("Nou Teclat");
        btModificarTeclat = new JButton("Modificar Teclat Seleccionat");
        btEliminarTeclat = new JButton("Eliminar Teclat Seleccionat");
        btVeureTeclat = new JButton("Veure Teclat Seleccionat");

        // Configuracio de les accions dels botons dels Teclats
        btCrearTeclat.addActionListener(e -> crearTeclat());
        btModificarTeclat.addActionListener(e -> modificarTeclat());
        btEliminarTeclat.addActionListener(e -> eliminarTeclat());
        btVeureTeclat.addActionListener(e -> veureTeclat());

        // Creacio del panell de botons per als Teclats
        JPanel panelBotonsTeclats = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonsTeclats.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Estableix l'ample maxim
        panelBotonsTeclats.add(btVeureTeclat);
        panelBotonsTeclats.add(btCrearTeclat);
        panelBotonsTeclats.add(btModificarTeclat);
        panelBotonsTeclats.add(btEliminarTeclat);

        // afegim la llista al panell, tot i que encara no l'hem carregat
        DefaultListModel<ElementTeclatLlista> listModel = new DefaultListModel<ElementTeclatLlista>();
        jListTeclats = new JList<>(listModel);
        jListTeclats.setCellRenderer(new ElementTeclatListRenderer());
        panellTeclats.add(new JScrollPane(jListTeclats));

        // Afegim el panell dels botons a la pestanya
        panellTeclats.add(panelBotonsTeclats);

    }

    // ! ------------------ INICIALITZADORS DE LLISTES -----------

    // Metode per inicialitzar la seccio dels Alfabets
    public void carregaAlfabets() {

        // Obtencio de dades d'Alfabets i visualitzacio a la llista
        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        
        // Iteracio sobre els Alfabets i creacio d'elements per a la llista
        for (int id : idAlfabets) {
            afegirAlfabet(id);
        }

    }

    // Metode per inicialitzar la seccio d'Entrades
    public void carregaEntrades() {
        
        // Obtencio de dades d'Entrades i visualitzacio a la llista
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntrades();

        // Iteracio sobre les Entrades i creacio d'elements per a la llista
        for (int id : idEntrades) {
            afegirEntrada(id);
        }


    }
    
    // Metode per inicialitzar la seccio dels Teclats
    public void carregarTeclats() {
        
        // Obtencio de dades dels Teclats i visualitzacio a la llista
        ArrayList<Integer> idTeclats = ControladorPresentacio.getIdTeclats();

        // Iteracio sobre els Teclats i creacio d'elements per a la llista
        for (int id : idTeclats) {
            afegirTeclat(id);
        }
    }

    // * metodo que llama el btEliminarTeclat
    private void eliminarTeclat() {
        int selectedIndex = jListTeclats.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap entrada seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el seleccionat?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        model.remove(selectedIndex);
        ControladorPresentacio.eliminarTeclat(model.get(selectedIndex).getId());
    }
    
    // * Metode per afegir un alfabet a la llista
    public void afegirAlfabet(int idAlfabeto) {
        String nombreAlfabeto = ControladorPresentacio.getNomAlfabet(idAlfabeto);
        ArrayList<Character> letrasAlfabeto = ControladorPresentacio.getLletresAlfabet(idAlfabeto);
        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        ElementAlfabetLlista nuevoAlfabeto = new ElementAlfabetLlista(idAlfabeto, nombreAlfabeto, letrasAlfabeto);
        model.addElement(nuevoAlfabeto);
    }

    // * Metode per afegir un teclat a la llista
    public void afegirTeclat(int idTeclado) {
        String nombreTeclado = ControladorPresentacio.getNomTeclat(idTeclado);
        int idEntrada = ControladorPresentacio.getIdEntradaTeclat(idTeclado);
        String nombreEntrada = ControladorPresentacio.getNomEntrada(idEntrada);
        int files = ControladorPresentacio.getFilesTeclat(idTeclado);
        int columnas = ControladorPresentacio.getColumnesTeclat(idTeclado);
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        ElementTeclatLlista nuevoTeclado = new ElementTeclatLlista(idTeclado, nombreTeclado, nombreEntrada, files, columnas);
        model.addElement(nuevoTeclado);
    }

    // ! ------------------- METODES DELS BOTONS ------------------------------

    // * Metode per afegir una entrada a la llista
    public void afegirEntrada(int idEntrada) {
        String nombreEntrada = ControladorPresentacio.getNomEntrada(idEntrada);
        String tipoEntrada = ControladorPresentacio.getTypeEntrada(idEntrada);
        String nombreAlfabeto = "cal implementar mes a domini"; // Obtener el nombre del alfabeto según tu lógica
        String contingutPreview = "cal implementar mes a domini"; // Obtener una vista previa según tu lógica
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        ElementEntradaLlista nuevaEntrada = new ElementEntradaLlista(idEntrada, nombreEntrada, tipoEntrada, contingutPreview, nombreAlfabeto);
        model.addElement(nuevaEntrada);
    }

    // * Metode que crida el btCrearAlfabet
    private void crearAlfabet() {
        VistaCrearAlfabet vCrearAlfabet = new VistaCrearAlfabet();
    }

    // TODO metode que crida el btModificarAlfabet
    private void modificarAlfabet() {
        // Logica per modificar un alfabet seleccionat
    }

    // * metode que crida el btEliminarAlfabet
    private void eliminarAlfabet() {
        int indexSeleccionat = jListAlfabets.getSelectedIndex();
        if (indexSeleccionat == -1) JOptionPane.showMessageDialog(this, "Cap alfabet seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "Estas segur d'eliminar l'alfabet seleccionat?", "Confirmar eliminació", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        model.remove(indexSeleccionat);
        ControladorPresentacio.eliminarAlfabet(model.get(indexSeleccionat).getId());
    }
    
    // TODO metode que crida el btCrearEntrada
    // ! Falta moder crear una LPF
    private void crearEntrada() {
        VistaCrearText vCrearText = new VistaCrearText();
        vCrearText.mostrar();
    }

    // TODO metode que crida el btModificarEntrada
    private void modificarEntrada() {
    }

    // * metode que crida el btEliminarEntrada
    private void eliminarEntrada() {
        int indexSeleccionat = jListEntrades.getSelectedIndex();
        if (indexSeleccionat == -1) JOptionPane.showMessageDialog(this, "Cap entrada seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estas segur d'eliminar la entrada seleccionada?", "Confirmar eliminació", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) return;
         
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        model.remove(indexSeleccionat);
        
        ControladorPresentacio.eliminarEntrada(model.get(indexSeleccionat).getId());
    }

    // * metode que crida el btCrearTeclat
    private void crearTeclat() {
        VistaCrearTeclat vCrearTeclat = new VistaCrearTeclat();
        vCrearTeclat.mostrar();
    }

    // TODO metode que crida el btModificarTeclat
    private void modificarTeclat() {
    }

    // * metode que crida el btVeureTeclat
    private void veureTeclat() {
        int indexSeleccionat = jListTeclats.getSelectedIndex();
        if (indexSeleccionat == -1) JOptionPane.showMessageDialog(this, "Cap teclat seleccionat!");

        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        int idTeclatSeleccionat = model.get(indexSeleccionat).getId();

        VistaVeureTeclat vVeureTeclat = new VistaVeureTeclat(idTeclatSeleccionat);
        vVeureTeclat.mostrar();
    }

    // * metode que crida el btTancarSessio
    private void tancarSessio() {
        ControladorPresentacio.tancarSessio();
    }
}
