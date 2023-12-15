package presentacio.vistes;

import javax.swing.*;
import presentacio.controladors.ControladorPresentacio;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VistaCrearText {

    private JFrame frame;
    private JTextField nomTextField;
    private JTextArea contingutTextArea;
    private JComboBox<String> alfabetComboBox;

    public VistaCrearText() {
        initComponents();
        initUI();
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void tancar() {
        frame.setVisible(false);
    }

    private void initComponents() {
        frame = new JFrame("Crear Text");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel nomLabel = new JLabel("Nom de la entrada:");
        nomTextField = new JTextField(20);

        JLabel contingutLabel = new JLabel("Contingut:");
        contingutTextArea = new JTextArea(15, 20);
        JScrollPane scrollPane = new JScrollPane(contingutTextArea);

        JLabel alfabetLabel = new JLabel("Seleccionar alfabet:");
        alfabetComboBox = new JComboBox<>();

        panel.add(nomLabel, gbc);
        panel.add(nomTextField, gbc);
        panel.add(contingutLabel, gbc);
        panel.add(scrollPane, gbc);
        panel.add(alfabetLabel, gbc);
        panel.add(alfabetComboBox, gbc);

        JButton crearButton = new JButton("Crear Text");
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearTexto();
            }
        });

        panel.add(crearButton, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
    }

    private void initUI() {
        ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
        for (Integer id : idAlfabets) {
            String nombreAlfabeto = ControladorPresentacio.getNomAlfabet(id);
            alfabetComboBox.addItem(nombreAlfabeto);
        }
    }

    private void crearTexto() {
        String nombreEntrada = nomTextField.getText();
        String contenidoEntrada = contingutTextArea.getText();

        int indiceSeleccionado = alfabetComboBox.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            ArrayList<Integer> idAlfabets = ControladorPresentacio.getIdAlfabets();
            Integer idAlfabetSeleccionado = idAlfabets.get(indiceSeleccionado);
            try {
                ControladorPresentacio.crearText(nombreEntrada, contenidoEntrada, idAlfabetSeleccionado);
                JOptionPane.showMessageDialog(frame, "Text creat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error en crear el text.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No s'ha seleccionat cap alfabet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
