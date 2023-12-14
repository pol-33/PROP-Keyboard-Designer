package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaCrearAlfabet extends JFrame {
    private JTextField symbolField;
    private JList<Character> symbolList;
    private DefaultListModel<Character> symbolListModel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton createAlphabetButton;
    private JTextField alphabetNameField;

    public VistaCrearAlfabet() {
        setTitle("Vista Alfabet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        alphabetNameField = new JTextField();
        alphabetNameField.setBounds(20, 20, 150, 30);
        add(alphabetNameField);

        symbolField = new JTextField();
        symbolField.setBounds(20, 60, 150, 30);
        add(symbolField);

        symbolListModel = new DefaultListModel<>();
        symbolList = new JList<>(symbolListModel);
        JScrollPane scrollPane = new JScrollPane(symbolList);
        scrollPane.setBounds(20, 100, 150, 150);
        add(scrollPane);

        addButton = new JButton("Afegir");
        addButton.setBounds(200, 60, 100, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String symbol = symbolField.getText();
                if (!symbol.isEmpty()) {
                    symbolListModel.addElement(symbol.charAt(0));
                    symbolField.setText("");
                }
            }
        });
        add(addButton);

        deleteButton = new JButton("Esborrar");
        deleteButton.setBounds(200, 100, 100, 30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = symbolList.getSelectedIndex();
                if (selectedIndex != -1) {
                    symbolListModel.remove(selectedIndex);
                }
            }
        });
        add(deleteButton);

        moveUpButton = new JButton("Amunt");
        moveUpButton.setBounds(320, 100, 100, 30);
        moveUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = symbolList.getSelectedIndex();
                if (selectedIndex > 0) {
                    Character temp = symbolListModel.get(selectedIndex);
                    symbolListModel.set(selectedIndex, symbolListModel.get(selectedIndex - 1));
                    symbolListModel.set(selectedIndex - 1, temp);
                    symbolList.setSelectedIndex(selectedIndex - 1);
                }
            }
        });
        add(moveUpButton);

        moveDownButton = new JButton("Avall");
        moveDownButton.setBounds(320, 140, 100, 30);
        moveDownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = symbolList.getSelectedIndex();
                if (selectedIndex != -1 && selectedIndex < symbolListModel.size() - 1) {
                    Character temp = symbolListModel.get(selectedIndex);
                    symbolListModel.set(selectedIndex, symbolListModel.get(selectedIndex + 1));
                    symbolListModel.set(selectedIndex + 1, temp);
                    symbolList.setSelectedIndex(selectedIndex + 1);
                }
            }
        });
        add(moveDownButton);

        createAlphabetButton = new JButton("Crear Alfabet");
        createAlphabetButton.setBounds(200, 220, 150, 30);
        createAlphabetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alphabetName = alphabetNameField.getText();
                ArrayList<Character> alphabetSymbols = new ArrayList<>();
                for (int i = 0; i < symbolListModel.getSize(); i++) {
                    alphabetSymbols.add(symbolListModel.getElementAt(i));
                }
                ControladorPresentacio.crearAlfabet(alphabetName, alphabetSymbols);
                setVisible(false);
            }
        });
        add(createAlphabetButton);

        setVisible(true);
    }
}
