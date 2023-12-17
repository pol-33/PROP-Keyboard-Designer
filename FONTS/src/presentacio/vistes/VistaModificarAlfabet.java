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
    frame.setSize(300, 200);
    frame.setLocationRelativeTo(null);

    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel listLabel = new JLabel("Lletres a afegir a l'alfabet:");
    panel.add(listLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setMinimumSize(new Dimension(100, 30));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(scrollPane);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel letterLabel = new JLabel("Nova lletra:");
        letterTextField = new JTextField(5);
        letterTextField.setToolTipText("Introdueix una sola lletra aquí");
        letterTextField.setMinimumSize(new Dimension(40, 20));
        bottomPanel.add(letterLabel, gbc);
        bottomPanel.add(letterTextField, gbc);

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
        bottomPanel.add(addButton, gbc);
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