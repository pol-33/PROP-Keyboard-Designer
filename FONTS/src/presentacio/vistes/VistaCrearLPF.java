package presentacio.vistes;

import presentacio.elements.*;

import javax.swing.*;
import presentacio.controladors.ControladorPresentacio;
import presentacio.elements.WordFrequency;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 * Classe VistaComCrearLPF. Vista per a la creació d'una LPF.
 */
public class VistaCrearLPF {

    private JFrame frame;
    private JTextField nomTextField;
    private JTextField wordTextField;
    private JTextField frequencyTextField;
    private DefaultListModel<WordFrequency> listModel;
    private JList<WordFrequency> list;
    private JComboBox<String> alfabetComboBox;

    /**
     * Constructora de la classe VistaComCrearLPF.
     */
    public VistaCrearLPF() {
        initComponents();
        initUI();
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

    /**
     * Inicialitza els components de la vista.
     */
    private void initComponents() {
        frame = new JFrame("Crear LPF");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel entryPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel nomLabel = new JLabel("Nom de la LPF:");
        nomTextField = new JTextField(20);
        entryPanel.add(nomLabel, gbc);
        entryPanel.add(nomTextField, gbc);

        JPanel alfabetPanel = new JPanel(new GridBagLayout());

        JLabel alfabetLabel = new JLabel("Seleccionar alfabet:");
        alfabetComboBox = new JComboBox<>();
        alfabetPanel.add(alfabetLabel, gbc);
        alfabetPanel.add(alfabetComboBox, gbc);

        JPanel entryAndAlphabetPanel = new JPanel(new GridLayout(2, 1));
        entryAndAlphabetPanel.add(entryPanel);
        entryAndAlphabetPanel.add(alfabetPanel);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(entryAndAlphabetPanel);

        JPanel addPanel = new JPanel(new GridBagLayout());

        JLabel wordLabel = new JLabel("Paraula:");
        wordTextField = new JTextField(20);
        addPanel.add(wordLabel, gbc);
        addPanel.add(wordTextField, gbc);

        JLabel frequencyLabel = new JLabel("Freqüència:");
        JSpinner frequencySpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        addPanel.add(frequencyLabel, gbc);
        addPanel.add(frequencySpinner, gbc);

        JButton addButton = new JButton("Afegir");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = wordTextField.getText();
                int frequency = (Integer) frequencySpinner.getValue();
                listModel.addElement(new WordFrequency(word, frequency));
                wordTextField.setText("");
                frequencySpinner.setValue(1);
            }
        });
        addPanel.add(addButton, gbc);

        JPanel listPanel = new JPanel(new BorderLayout());

        JLabel listLabel = new JLabel("Contingut de la LPF:");
        listPanel.add(listLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        JButton removeButton = new JButton("Eliminar");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });
        listPanel.add(removeButton, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(addPanel);
        bottomPanel.add(listPanel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);

        JButton crearButton = new JButton("Crear LPF");
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearLPF();
            }
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(crearButton, new GridBagConstraints());

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Inicialitza els elements de la interfície gràfica.
     */
    private void initUI() {
        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        for (Integer id : idAlfabets) {
            String nombreAlfabeto = ControladorPresentacio.getNomAlfabet(id);
            alfabetComboBox.addItem(nombreAlfabeto);
        }
    }

    /**
     * Crea una LPF.
     */
    private void crearLPF() {
        String nombreEntrada = nomTextField.getText();

        if (nombreEntrada.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No s'ha introduït cap nom.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert the list data into a HashMap<String, Integer>
        HashMap<String, Integer> contenidoEntradaMap = new HashMap<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            WordFrequency wordFrequency = listModel.getElementAt(i);
            contenidoEntradaMap.put(wordFrequency.getWord(), wordFrequency.getFrequency());
        }

        if (contenidoEntradaMap.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No s'ha introduït cap contingut.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int indiceSeleccionado = alfabetComboBox.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
            Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado);
            try {
                ControladorPresentacio.crearLPF(nombreEntrada, contenidoEntradaMap, idAlfabetSeleccionado);
                JOptionPane.showMessageDialog(frame, "LPF creat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error en crear la LPF.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No s'ha seleccionat cap alfabet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}