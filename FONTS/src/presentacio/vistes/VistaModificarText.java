package presentacio.vistes;

import javax.swing.*;
import presentacio.controladors.ControladorPresentacio;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe VistaModificarText. Vista per a la modificació d'un text.
 */
public class VistaModificarText {

    private JFrame frame;
    private JTextArea contingutTextArea;
    private Integer idText;

    /**
     * Constructora de la classe VistaModificarText.
     * @param idText Identificador del text.
     */
    public VistaModificarText(Integer idText) {
        this.idText = idText;
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
        frame = new JFrame("Crear Text");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        String nombreEntrada = ControladorPresentacio.getNomEntrada(idText);
        JLabel nomLabel = new JLabel("Nom del text: " + nombreEntrada);
        panel.add(nomLabel, gbc);

        String nomAlfabet = ControladorPresentacio.getNomAlfabetEntrada(idText);
        JLabel alfabetLabel = new JLabel("Alfabet seleccionat: " + nomAlfabet);
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

    /**
     * Inicialitza el contingut de la vista.
     */
    private void initUI() {
        String contingutText = ControladorPresentacio.getContingutText(idText);
        contingutTextArea.setText(contingutText);
    }

    /**
     * Modifica el text.
     */
    private void modificarText() {
        String contenidoEntrada = contingutTextArea.getText();

        if (contenidoEntrada.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El contingut del text no pot ser buit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ControladorPresentacio.modificarContingutText(idText, contenidoEntrada);
            JOptionPane.showMessageDialog(frame, "Text modificat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error en modificar el text.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
