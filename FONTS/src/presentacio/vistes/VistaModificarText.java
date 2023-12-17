package presentacio.vistes;

import javax.swing.*;
import presentacio.controladors.ControladorPresentacio;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VistaModificarText {

    private JFrame frame;
    private JTextArea contingutTextArea;
    private Integer idText;

    public VistaModificarText(Integer idText) {
        this.idText = idText;
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

        String nombreEntrada = ControladorPresentacio.getNomEntrada(idText);
        JLabel nomLabel = new JLabel("Nom del text: " + nombreEntrada);
        panel.add(nomLabel, gbc);

        //Integer idAlfabet = ControladorPresentacio.getIdAlfabetDeLPF(idLPF);
        //String nombreAlfabet = ControladorPresentacio.getNomAlfabet(idAlfabet);
        String nombreAlfabet = "getIdAlfabetDeLPF(idLPF) no implementat a domini";
        JLabel alfabetLabel = new JLabel("Alfabet seleccionat: " + nombreAlfabet);
        panel.add(alfabetLabel, gbc);

        JLabel contingutLabel = new JLabel("Contingut:");
        contingutTextArea = new JTextArea(15, 30);
        contingutTextArea.setLineWrap(true); // Enable line wrapping
        contingutTextArea.setWrapStyleWord(true); // Wrap lines at word boundaries
        JScrollPane scrollPane = new JScrollPane(contingutTextArea);
        panel.add(contingutLabel, gbc);
        panel.add(scrollPane, gbc);

        JButton modificarButton = new JButton("Guardar Text");
        modificarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarText();
            }
        });

        panel.add(modificarButton, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
    }

    private void initUI() {
        String contingutText = ControladorPresentacio.getContingutText(idText);
        contingutTextArea.setText(contingutText);
    }

    private void modificarText() {
        String contenidoEntrada = contingutTextArea.getText();

        try {
            ControladorPresentacio.modificarContingutText(idText, contenidoEntrada);
            JOptionPane.showMessageDialog(frame, "Text modificat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error en modificar el text.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
