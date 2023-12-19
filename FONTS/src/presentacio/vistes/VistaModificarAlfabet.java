package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import presentacio.controladors.ControladorPresentacio;

public class VistaModificarAlfabet {

    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JTextField letterTextField;
    private Integer idAlfabet;

    public VistaModificarAlfabet(Integer idAlfabet) {
        this.idAlfabet = idAlfabet;
        initComponents();
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void tancar() {
        frame.setVisible(false);
    }

    private void initComponents() {
        frame = new JFrame("Modificar Alfabet");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create new labels
        JLabel alfabetNameLabel = new JLabel("Nom de l'alfabet: " + ControladorPresentacio.getNomAlfabet(idAlfabet));
        String actualLettersString = ControladorPresentacio.getLletresAlfabet(idAlfabet).toString();
        actualLettersString = actualLettersString.replace("[", "");  //remove the right bracket
        actualLettersString = actualLettersString.replace("]", "");  //remove the left bracket
        JLabel actualLettersLabel = new JLabel("Lletres actuals: " + actualLettersString);

        // Create a new JPanel for the info labels and add them to it
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(alfabetNameLabel);
        infoPanel.add(actualLettersLabel);

        // Add infoPanel to the panel at BorderLayout.NORTH
        panel.add(infoPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setMinimumSize(new Dimension(60, 100));
        scrollPane.setPreferredSize(new Dimension(200, 100)); // Set a smaller preferred size for the JScrollPane

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH; // Change fill to BOTH to allow vertical resizing

        JLabel letterLabel = new JLabel("Nova lletra:");
        letterTextField = new JTextField(5);
        letterTextField.setToolTipText("Introdueix una sola lletra aquí");
        letterTextField.setMinimumSize(new Dimension(40, 20));
        letterTextField.setPreferredSize(new Dimension(100, 20)); // Set a preferred size for the JTextField

        JButton addButton = new JButton("Afegir lletra");
        addButton.setToolTipText("Afegeix la lletra introduïda a la llista");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String letter = letterTextField.getText();
                if (letter.length() != 1) {
                    JOptionPane.showMessageDialog(frame, "Introdueix una sola lletra.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                listModel.addElement(letter);
                letterTextField.setText("");
            }
        });

        JPanel inputPanel = new JPanel(new GridBagLayout()); // Use a GridBagLayout for the inputPanel
        GridBagConstraints gbcInput = new GridBagConstraints();
        gbcInput.insets = new Insets(5, 10, 5, 10);
        gbcInput.fill = GridBagConstraints.HORIZONTAL;
        gbcInput.weightx = 1.0; // Set the weightx value for the components in the inputPanel
        gbcInput.weighty = 0.0; // Set the weighty value for the components in the inputPanel

        gbcInput.gridy = 0;
        inputPanel.add(letterLabel, gbcInput);

        gbcInput.gridy = 1;
        inputPanel.add(letterTextField, gbcInput);

        gbcInput.gridy = 2;
        inputPanel.add(addButton, gbcInput);

        // Place the inputPanel in the first column of the grid
        gbc.gridx = 1;
        gbc.weightx = 0.5; // Set the weightx value for the inputPanel
        gbc.weighty = 1.0; // Set the weighty value for the inputPanel
        bottomPanel.add(inputPanel, gbc);

        // Create the "Eliminar" button
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected indices from the JList
                int[] selectedIndices = list.getSelectedIndices();
                // Remove the selected elements from the listModel
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.remove(selectedIndices[i]);
                }
            }
        });

        // Create a new JPanel for the JScrollPane and the "Eliminar" button
        JPanel listPanel = new JPanel(new BorderLayout());
        JLabel listLabel = new JLabel("Lletres a afegir:");
        listPanel.add(listLabel, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        listPanel.add(eliminarButton, BorderLayout.SOUTH);

        // Place the listPanel in the second column of the grid
        gbc.gridx = 2;
        gbc.gridy = 0;
        bottomPanel.add(listPanel, gbc);

        centerPanel.add(bottomPanel);
        panel.add(centerPanel, BorderLayout.CENTER);

        JButton acceptarButton = new JButton("Acceptar");
        acceptarButton.setToolTipText("Afegeix les lletres de la llista a l'alfabet");
        acceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Character> lettersToAdd = new ArrayList<>();
                for (int i = 0; i < listModel.getSize(); i++) {
                    String letterString = listModel.getElementAt(i);
                    char letterChar = letterString.charAt(0);
                    lettersToAdd.add(letterChar);
                }
                if (!lettersToAdd.isEmpty()) {
                    for (char letter : lettersToAdd) {
                        ControladorPresentacio.modificarAlfabetAfegirLletra(idAlfabet, letter);
                    }
                    frame.dispose();
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(acceptarButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH); // Add the buttonPanel to the BorderLayout.SOUTH of the frame
    }
}