package presentacio.vistes;
import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe VistaCrearAlfabet. Vista per a la creació d'un alfabet.
 */
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

    /**
     * Constructora de la classe VistaCrearAlfabet.
     */
    public VistaCrearAlfabet() {
        setTitle("Vista Alfabet");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(null);

        JLabel alphabetNameLabel = new JLabel("Nom de l'alfabet: ");
        alphabetNameLabel.setBounds(20, 20, 150, 30);
        add(alphabetNameLabel);

        alphabetNameField = new JTextField();
        alphabetNameField.setBounds(180, 20, 150, 30);
        add(alphabetNameField);

        JLabel symbolFieldLabel = new JLabel("Afegir símbol: ");
        symbolFieldLabel.setBounds(20, 60, 150, 30);
        add(symbolFieldLabel);

        symbolField = new JTextField();
        // Only allow one character
        ((AbstractDocument) symbolField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 1) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        symbolField.setBounds(180, 60, 150, 30);
        add(symbolField);

        symbolListModel = new DefaultListModel<>();
        symbolList = new JList<>(symbolListModel);
        JScrollPane scrollPane = new JScrollPane(symbolList);
        scrollPane.setBounds(20, 100, 150, 200);
        add(scrollPane);

        addButton = new JButton("Afegir");
        addButton.setBounds(340, 60, 100, 30);
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

        deleteButton = new JButton("Eliminar");
        deleteButton.setBounds(340, 100, 100, 30);
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
        moveUpButton.setBounds(340, 140, 100, 30);
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
        moveDownButton.setBounds(340, 180, 100, 30);
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
        createAlphabetButton.setBounds(180, 320, 150, 30);
        createAlphabetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alphabetName = alphabetNameField.getText();
                ArrayList<Character> alphabetSymbols = new ArrayList<>();
                for (int i = 0; i < symbolListModel.getSize(); i++) {
                    alphabetSymbols.add(symbolListModel.getElementAt(i));
                }
                int ret = ControladorPresentacio.crearAlfabet(alphabetName, alphabetSymbols);
                if (ret == 0) setVisible(false);
            }
        });
        add(createAlphabetButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
