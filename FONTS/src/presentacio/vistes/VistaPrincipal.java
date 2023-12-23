package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;
import presentacio.elements.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Classe VistaPrincipal. Vista principal de l'aplicació que permet gestionar els alfabets, les entrades i els teclats.
 */
public class VistaPrincipal extends JFrame {
    
    // Declaracio de variables de la interficie
    private JFrame frame;
    
    private JPanel panellMenu = new JPanel();
    JButton btTancarSesio;
    JComboBox<String> alfabetComboBoxEntrades = new JComboBox<String>();
    JComboBox<String> alfabetComboBoxTeclats = new JComboBox<String>();

    private JTabbedPane pestanyes;

    private JPanel panellAlfabets;
    private JButton btCrearAlfabet, btModificarAlfabet, btEliminarAlfabet;
    private JList<ElementAlfabetLlista> jListAlfabets;

    private JPanel panellEntrades;
    private JButton btCrearText, btCrearLPF, btModificarEntrada, btEliminarEntrada;
    private JList<ElementEntradaLlista> jListEntrades;

    private JPanel panellTeclats;
    private JButton btCrearTeclat, btModificarTeclat, btEliminarTeclat, btVeureTeclat;
    private JList<ElementTeclatLlista> jListTeclats;

    /**
     * Constructora de la classe VistaPrincipal.
     */
    public VistaPrincipal() {
        initUI();
        carregaAlfabets();
        carregaTotesEntrades();
        carregaTotsTeclats();
    }

    /**
     * Mostra la vista.
     */
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Tanca la vista.
     */
    public void tancar() {
        frame.setVisible(false);
    }

    // * ------------------ INICIALITZADORS DEL UI -----------

    /**
     * Inicialitza els components de la vista i la interficie d'usuari.
     */
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

    /**
     * Inicialitza el panell de menu superior.
     */
    private void initUIMenu() {
        panellMenu.setLayout(new BorderLayout());

        btTancarSesio = new JButton("Tancar Sessió");
        btTancarSesio.addActionListener(e -> tancarSessio());

        // Add a label with the name of the active user
        JLabel labelNomUsuari = new JLabel("Benvingut al teu compte, " + ControladorPresentacio.getNomUsuariActiu() + "!");
        labelNomUsuari.setFont(new Font("Arial", Font.BOLD, 18));
        panellMenu.add(labelNomUsuari, BorderLayout.WEST);

        panellMenu.add(btTancarSesio, BorderLayout.EAST);
    }

    /**
     * Inicialitza el panell d'Alfabets.
     */
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

    /**
     * Inicialitza el panell d'Entrades.
     */
    private void initUIEntrades() {

        // Configuracio del disseny del panell d'Entrades
        panellEntrades = new JPanel(); // GridLayout amb 3 columnes i espaiat
        panellEntrades.setLayout(new BoxLayout(panellEntrades, BoxLayout.Y_AXIS));

        // Botons
        btCrearText = new JButton("Nova Entrada");
        btCrearLPF = new JButton("Nova LPF");
        btModificarEntrada = new JButton("Modificar Entrada Seleccionada");
        btEliminarEntrada = new JButton("Eliminar");

        // Configuracio de les accions dels botons d'Entrades
        btCrearText.addActionListener(e -> crearEntrada());
        btModificarEntrada.addActionListener(e -> modificarEntrada());
        btEliminarEntrada.addActionListener(e -> eliminarEntrada());


        updateAlfabetComboBoxes();

        alfabetComboBoxEntrades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = alfabetComboBoxEntrades.getSelectedIndex();
                if (indiceSeleccionado == 0) {
                    carregaTotesEntrades();
                }
                else if (indiceSeleccionado > 0) {
                    ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
                    Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado-1);
                    carregaEntrades(idAlfabetSeleccionado);
                }
            }
        });

        // Create a JPanel for the JComboBox
        JPanel alfabetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filtrar entrades per idioma: ");
        alfabetPanel.add(filterLabel);
        alfabetPanel.add(alfabetComboBoxEntrades);

        // Set the maximum height of the alfabetPanel
        alfabetPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, alfabetPanel.getPreferredSize().height));

        // Add the JPanel to the BorderLayout.NORTH of panellEntrades
        panellEntrades.add(alfabetPanel, BorderLayout.NORTH);


        // Creacio del panell de botons per a les Entrades
        JPanel panelBotonsEntrades = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonsEntrades.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Estableix l'ample maxim
        panelBotonsEntrades.add(btCrearText);
        panelBotonsEntrades.add(btModificarEntrada);
        panelBotonsEntrades.add(btEliminarEntrada);

        // afegim la llista al panell, tot i que encara no l'hem carregat
        DefaultListModel<ElementEntradaLlista> listModel = new DefaultListModel<ElementEntradaLlista>();
        jListEntrades = new JList<>(listModel);
        jListEntrades.setCellRenderer(new ElementEntradaListRenderer());
        panellEntrades.add(new JScrollPane(jListEntrades), BorderLayout.CENTER);

        // Afegim el panell dels botons a la pestanya
        panellEntrades.add(panelBotonsEntrades, BorderLayout.NORTH);
    }

    /**
     * Inicialitza el panell de Teclats.
     */
    private void initUITeclats() {
        // Configuracio del disseny del panell de Teclats
        panellTeclats = new JPanel();
        panellTeclats.setLayout(new BoxLayout(panellTeclats, BoxLayout.Y_AXIS));

        // Botons
        btCrearTeclat = new JButton("Crear Nou Teclat");
        btModificarTeclat = new JButton("Modificar Files i Columnes");
        btEliminarTeclat = new JButton("Eliminar");
        btVeureTeclat = new JButton("Veure Teclat Seleccionat");

        // Configuracio de les accions dels botons dels Teclats
        btCrearTeclat.addActionListener(e -> crearTeclat());
        btModificarTeclat.addActionListener(e -> modificarTeclat());
        btEliminarTeclat.addActionListener(e -> eliminarTeclat());
        btVeureTeclat.addActionListener(e -> veureTeclat());


        updateAlfabetComboBoxes();

        alfabetComboBoxTeclats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = alfabetComboBoxTeclats.getSelectedIndex();
                if (indiceSeleccionado == 0) {
                    carregaTotsTeclats();
                }
                else if (indiceSeleccionado > 0) {
                    ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
                    Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado-1);
                    carregaTeclats(idAlfabetSeleccionado);
                }
            }
        });

        // Create a JPanel for the JComboBox
        JPanel alfabetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filtrar teclats per idioma: ");
        alfabetPanel.add(filterLabel);
        alfabetPanel.add(alfabetComboBoxTeclats);

        // Set the maximum height of the alfabetPanel
        alfabetPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, alfabetPanel.getPreferredSize().height));

        // Add the JPanel to the BorderLayout.NORTH of panellEntrades
        panellTeclats.add(alfabetPanel, BorderLayout.NORTH);


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

    /**
     * Carrega els alfabets a la llista.
     */
    public void carregaAlfabets() {

        // Obtencio de dades d'Alfabets i visualitzacio a la llista
        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        
        // Iteracio sobre els Alfabets i creacio d'elements per a la llista
        for (int id : idAlfabets) {
            afegirAlfabet(id);
        }

    }

    /**
     * Carrega totes les entrades a la llista.
     */
    public void carregaTotesEntrades() {
        // Get the DefaultListModel associated with jListEntrades
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();

        // Clear the model
        model.clear();

        // Obtencio de dades d'Entrades i visualitzacio a la llista
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntrades();

        // Iteracio sobre les Entrades i creacio d'elements per a la llista
        for (int id : idEntrades) {
            afegirEntrada(id);
        }

        // Selecciona el primer element de lamllista, si n'hi ha
        if (model.size() > 0) jListEntrades.setSelectedIndex(0);
    }

    /**
     * Carrega les entrades vinculades a un alfabet a la llista.
     */
    public void carregaEntrades(Integer idAlfabet) {
        // Get the DefaultListModel associated with jListEntrades
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();

        // Clear the model
        model.clear();

        // Obtencio de dades d'Entrades i visualitzacio a la llista
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntradesVinculadesAlfabet(idAlfabet);

        // Iteracio sobre les Entrades i creacio d'elements per a la llista
        for (int id : idEntrades) {
            afegirEntrada(id);
        }
    }
    
    /**
     * Carrega tots els teclats a la llista.
     */
    public void carregaTotsTeclats() {
        // Get the DefaultListModel associated with jListEntrades
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();

        // Clear the model
        model.clear();

        // Obtencio de dades dels Teclats i visualitzacio a la llista
        ArrayList<Integer> idTeclats = ControladorPresentacio.getIdTeclats();

        // Iteracio sobre els Teclats i creacio d'elements per a la llista
        for (int id : idTeclats) {
            afegirTeclat(id);
        }
    }

    /**
     * Carrega els teclats vinculats a un alfabet a la llista.
     */
    public void carregaTeclats(Integer idAlfabet) {
        // Get the DefaultListModel associated with jListEntrades
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();

        // Clear the model
        model.clear();

        // Obtencio de dades d'Entrades i visualitzacio a la llista
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntradesVinculadesAlfabet(idAlfabet);
        ArrayList<Integer> idTeclats = new ArrayList<>();

        // Iteracio sobre les Entrades i creacio d'elements per a la llista
        for (int id : idEntrades) {
            ArrayList<Integer> idTeclatsEntrada = ControladorPresentacio.getIdTeclatsVinculatsAEntrada(id);
            for (int idTeclat : idTeclatsEntrada) {
                if (!idTeclats.contains(idTeclat)) idTeclats.add(idTeclat);
            }
        }

        // Iteracio sobre els Teclats i creacio d'elements per a la llista
        for (int id : idTeclats) {
            afegirTeclat(id);
        }
    }

    /**
     * Alfegeix un alfabet al panell d'Alfabets.
     */
    public void afegirAlfabet(int idAlfabeto) {
        String nombreAlfabeto = ControladorPresentacio.getNomAlfabet(idAlfabeto);
        ArrayList<Character> letrasAlfabeto = ControladorPresentacio.getLletresAlfabet(idAlfabeto);
        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        ElementAlfabetLlista nuevoAlfabeto = new ElementAlfabetLlista(idAlfabeto, nombreAlfabeto, letrasAlfabeto);
        model.addElement(nuevoAlfabeto);
    }

    /**
     * Actualitza la informacio d'un alfabet de la llista del panell.
     * @param idAlfabet Identificador de l'alfabet a actualitzar
     */
    public void actualitzarAlfabetLlista(int idAlfabet) {
        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();

        for (int i = 0; i < model.size(); i++) {
            if (model.get(i).getId() == idAlfabet) {
                model.remove(i);
                break;
            }
        }

        afegirAlfabet(idAlfabet);
    }

    /**
     * Actualitza la informacio d'un teclat de la llista del panell.
     * @param idTeclat Identificador del teclat a actualitzar
     */
    public void actualitzarTeclatLlista(int idTeclat) {
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();

        for (int i = 0; i < model.size(); i++) {
            if (model.get(i).getId() == idTeclat) {
                model.remove(i);
                break;
            }
        }

        afegirTeclat(idTeclat);
    }

    /**
     * Afegeix un teclat al panell de Teclats.
     * @param idTeclado Identificador del teclat a afegir
     */
    public void afegirTeclat(int idTeclado) {
        String nomTeclado = ControladorPresentacio.getNomTeclat(idTeclado);
        int idEntrada = ControladorPresentacio.getIdEntradaTeclat(idTeclado);
        String nomEntrada = ControladorPresentacio.getNomEntrada(idEntrada);
        int files = ControladorPresentacio.getFilesTeclat(idTeclado);
        int columnas = ControladorPresentacio.getColumnesTeclat(idTeclado);
        String nomAlfabet = ControladorPresentacio.getNomAlfabetEntrada(idEntrada);
        int numTipusTeclat = ControladorPresentacio.getTipusTeclat(idTeclado);
        String tipusTeclat;
        if (numTipusTeclat == 0) tipusTeclat = "Teclat d'ordinador (dues mans)";
        else tipusTeclat = "Teclat tàctil (dos dits)";
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        ElementTeclatLlista nuevoTeclado = new ElementTeclatLlista(idTeclado, nomTeclado, nomEntrada, files, columnas, nomAlfabet, tipusTeclat);
        model.addElement(nuevoTeclado);
    }

    /**
     * Afegeix una entrada a la llista del panell d'Entrades.
     * @param idEntrada Identificador de l'entrada a afegir
     */
    public void afegirEntrada(int idEntrada) {
        String nombreEntrada = ControladorPresentacio.getNomEntrada(idEntrada);
        Integer tipus = ControladorPresentacio.getTipusEntrada(idEntrada);
        String nomTipus = "";
        if (tipus == 0) nomTipus = "Text";
        else nomTipus = "LPF";

        String nombreAlfabeto = ControladorPresentacio.getNomAlfabetEntrada(idEntrada);
        String contingutPreview = ControladorPresentacio.getPreviewEntrada(idEntrada);
        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        ElementEntradaLlista nuevaEntrada = new ElementEntradaLlista(idEntrada, nombreEntrada, nomTipus, contingutPreview, nombreAlfabeto);
        model.addElement(nuevaEntrada);
    }

    // ! ------------------- METODES DELS BOTONS ------------------------------

    /**
     * Obre la vista per a crear un nou alfabet.
     */
    private void crearAlfabet() {
        VistaCrearAlfabet vCrearAlfabet = new VistaCrearAlfabet();
    }

    /**
     * Obre la vista per modificar un alfabet.
     */
    private void modificarAlfabet() {
        int indexSeleccionat = jListAlfabets.getSelectedIndex();
        if (indexSeleccionat == -1) {
            JOptionPane.showMessageDialog(this, "Cap alfabet seleccionat!");
            return;
        }

        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        int idAlfabetSeleccionat = model.get(indexSeleccionat).getId();

        VistaModificarAlfabet vModificarAlfabet = new VistaModificarAlfabet(idAlfabetSeleccionat);
        vModificarAlfabet.mostrar();
    }

    /**
     * Elimina un alfabet de la llista.
     */
    private void eliminarAlfabet() {
        int indexSeleccionat = jListAlfabets.getSelectedIndex();
        if (indexSeleccionat == -1) {
            JOptionPane.showMessageDialog(this, "Cap alfabet seleccionat!");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "Estas segur d'eliminar l'alfabet seleccionat?", "Confirmar eliminació", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION || confirmacion == JOptionPane.CLOSED_OPTION) {
            return;
        }

        DefaultListModel<ElementAlfabetLlista> model = (DefaultListModel<ElementAlfabetLlista>) jListAlfabets.getModel();
        int idAlfabetSeleccionat = model.get(indexSeleccionat).getId();

        // Check if the selected alphabet has any associated teclats
        ArrayList<Integer> idEntrades = ControladorPresentacio.getIdEntradesVinculadesAlfabet(idAlfabetSeleccionat);
        if (!idEntrades.isEmpty()) {
            // If it does, display a new confirmation dialog
            int confirmacionTeclats = JOptionPane.showConfirmDialog(this,
                    "L'alfabet seleccionat té entrades associades que també s'eliminaran. \n" +
                            "Si aquestes entrades tenen teclats associats aquests també s'eliminaran. \n\nVols continuar?",
                    "Confirmar eliminació",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (confirmacionTeclats == JOptionPane.NO_OPTION || confirmacionTeclats == JOptionPane.CLOSED_OPTION) {
                return;
            }
        }

        // Remember the selected alphabet in alfabetComboBoxes
        String selectedAlphabetEntrades = (String) alfabetComboBoxEntrades.getSelectedItem();
        String selectedAlphabetTeclats = (String) alfabetComboBoxTeclats.getSelectedItem();

        // Proceed with the deletion
        ControladorPresentacio.eliminarAlfabet(idAlfabetSeleccionat);
        model.remove(indexSeleccionat);

        updateAlfabetComboBoxes();

        // Restore the selected alphabet in alfabetComboBoxes
        alfabetComboBoxEntrades.setSelectedItem(selectedAlphabetEntrades);
        alfabetComboBoxTeclats.setSelectedItem(selectedAlphabetTeclats);

        // If the selected alphabet was deleted, select the first item
        if (alfabetComboBoxEntrades.getSelectedItem() == null) {
            alfabetComboBoxEntrades.setSelectedIndex(0);
        }
        if (alfabetComboBoxTeclats.getSelectedItem() == null) {
            alfabetComboBoxTeclats.setSelectedIndex(0);
        }
    }

    /**
     * Obra la vista per a crear una nova entrada.
     */
    private void crearEntrada() {
        VistaCrearEntrada vCrearText = new VistaCrearEntrada();
        vCrearText.mostrar();
    }

    /**
     * Obre la vista per a modificar una entrada.
     */
    private void modificarEntrada() {
        int indexSeleccionat = jListEntrades.getSelectedIndex();
        if (indexSeleccionat == -1) {
            JOptionPane.showMessageDialog(this, "Cap entrada seleccionada!");
            return;
        }

        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        ElementEntradaLlista entradaSeleccionada = model.get(indexSeleccionat);
        int idEntradaSeleccionada = entradaSeleccionada.getId();
        String tipusEntrada = entradaSeleccionada.getTipus();

        if (tipusEntrada.equals("Text")) {
            VistaModificarText vModificarEntrada = new VistaModificarText(idEntradaSeleccionada);
            vModificarEntrada.mostrar();
        } else if (tipusEntrada.equals("LPF")) {
            VistaModificarLPF vModificarLPF = new VistaModificarLPF(idEntradaSeleccionada);
            vModificarLPF.mostrar();
        }
    }

    /**
     * Actualitza la informacio d'una entrada de la llista del panell.
     * @param idEntrada
     */
    public void actualitzarEntradaLlista(Integer idEntrada) {
        for (int i = 0; i < jListEntrades.getModel().getSize(); i++) {
            ElementEntradaLlista element = jListEntrades.getModel().getElementAt(i);
            if (element.getId() == idEntrada) {
                String contingutPreview = ControladorPresentacio.getPreviewEntrada(idEntrada);
                element.setContingutPreview(contingutPreview);
                break;
            }
        }
    }

    /**
     * Elimina una entrada de la llista.
     */
    private void eliminarEntrada() {
        int indexSeleccionat = jListEntrades.getSelectedIndex();
        if (indexSeleccionat == -1) {
            JOptionPane.showMessageDialog(this, "Cap entrada seleccionat!");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Estàs segur d'eliminar la entrada seleccionada?",
                "Confirmar eliminació",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION || confirmacion == JOptionPane.CLOSED_OPTION) {
            return;
        }

        DefaultListModel<ElementEntradaLlista> model = (DefaultListModel<ElementEntradaLlista>) jListEntrades.getModel();
        int idEntradaSeleccionada = model.get(indexSeleccionat).getId();

        // Check if the selected entry has any associated teclats
        ArrayList<Integer> idTeclats = ControladorPresentacio.getIdTeclatsVinculatsAEntrada(idEntradaSeleccionada);
        if (!idTeclats.isEmpty()) {
            // If it does, display a new confirmation dialog
            int confirmacionTeclats = JOptionPane.showConfirmDialog(this,
                    "L'entrada seleccionada té teclats associats que també s'eliminaran. \n\nVols continuar?",
                    "Confirmar eliminació",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (confirmacionTeclats == JOptionPane.NO_OPTION || confirmacionTeclats == JOptionPane.CLOSED_OPTION) {
                return;
            }
        }

        // Proceed with the deletion
        ControladorPresentacio.eliminarEntrada(idEntradaSeleccionada);
        model.remove(indexSeleccionat);

        // Update the teclats panel according to the alfabetComboBox status
        int indiceSeleccionado = alfabetComboBoxTeclats.getSelectedIndex();
        if (indiceSeleccionado == 0) {
            carregaTotsTeclats();
        }
        else if (indiceSeleccionado > 0) {
            ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
            Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado-1);
            carregaTeclats(idAlfabetSeleccionado);
        }
    }

    /**
     * Obre la vista per a crear un nou teclat.
     */
    private void crearTeclat() {
        VistaComCrearTeclat vComCrearTeclat = new VistaComCrearTeclat();
        vComCrearTeclat.mostrar();
    }

    /**
     * Obre la vista per a modificar un teclat.
     */
    private void modificarTeclat() {
        int indexSeleccionat = jListTeclats.getSelectedIndex();
        if (indexSeleccionat == -1) JOptionPane.showMessageDialog(this, "Cap teclat seleccionat!");

        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        int idTeclatSeleccionat = model.get(indexSeleccionat).getId();

        VistaModificarTeclat vModificarTeclat = new VistaModificarTeclat(idTeclatSeleccionat);
        vModificarTeclat.mostrar();
    }

    /**
     * Elimina un teclat de la llista.
     */
    private void eliminarTeclat() {
        int selectedIndex = jListTeclats.getSelectedIndex();
        if (selectedIndex == -1) JOptionPane.showMessageDialog(this, "Cap teclat seleccionat!");

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Estàs segur que vols eliminar el teclat seleccionat?",
                "Confirmar eliminació",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION || confirmacion == JOptionPane.CLOSED_OPTION) return;
         
        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        ControladorPresentacio.eliminarTeclat(model.get(selectedIndex).getId());
        model.remove(selectedIndex);
    }

    /**
     * Obre la vista per a veure un teclat.
     */
    private void veureTeclat() {
        int indexSeleccionat = jListTeclats.getSelectedIndex();
        if (indexSeleccionat == -1) JOptionPane.showMessageDialog(this, "Cap teclat seleccionat!");

        DefaultListModel<ElementTeclatLlista> model = (DefaultListModel<ElementTeclatLlista>) jListTeclats.getModel();
        int idTeclatSeleccionat = model.get(indexSeleccionat).getId();

        VistaVeureTeclat vVeureTeclat = new VistaVeureTeclat(idTeclatSeleccionat);
        vVeureTeclat.mostrar();
    }

    /**
     * Tanca la sessio de l'usuari.
     */
    private void tancarSessio() {
        ControladorPresentacio.tancarSessio();
    }

    /**
     * Actualitza els comboboxes de filtre d'alfabets de les pestanyes d'Entrades i Teclats.
     */
    public void updateAlfabetComboBoxes() {
        // Update alfabetComboBoxEntrades
        alfabetComboBoxEntrades.removeAllItems();
        alfabetComboBoxEntrades.addItem("Tots");

        // Update alfabetComboBoxTeclats
        alfabetComboBoxTeclats.removeAllItems();
        alfabetComboBoxTeclats.addItem("Tots");

        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        for (Integer id : idAlfabets) {
            String nombreAlfabeto = ControladorPresentacio.getNomAlfabet(id);

            // Add the alphabet to both combo boxes
            alfabetComboBoxEntrades.addItem(nombreAlfabeto);
            alfabetComboBoxTeclats.addItem(nombreAlfabeto);
        }
    }
}
