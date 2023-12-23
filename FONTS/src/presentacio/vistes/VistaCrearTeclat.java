package presentacio.vistes;

import javax.swing.*;

import presentacio.controladors.ControladorPresentacio;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Classe VistaCrearTeclat. Vista per a la creació d'un teclat.
 */
public class VistaCrearTeclat {

    private JFrame frame;
    private JTextField nomTextField;
    private JComboBox<String> entradaComboBox;
    private JSpinner filesSpinner;
    private JSpinner columnesSpinner;
    private JComboBox<String> tipusComboBox;

    /**
     * Constructora de la classe VistaCrearTeclat.
     */
    public VistaCrearTeclat() {
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
        frame = new JFrame("Crear Teclat");
        JPanel panel = new JPanel(new GridBagLayout()); // Change to GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nomLabel = new JLabel("Nom del teclat:");
        nomTextField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomLabel, gbc);
        gbc.gridx = 1;
        panel.add(nomTextField, gbc);

        JLabel entradaLabel = new JLabel("Seleccionar entrada:");
        entradaComboBox = new JComboBox<>();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(entradaLabel, gbc);
        gbc.gridx = 1;
        panel.add(entradaComboBox, gbc);

        JLabel filesLabel = new JLabel("Número de files:");
        filesSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 500, 1));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(filesLabel, gbc);
        gbc.gridx = 1;
        panel.add(filesSpinner, gbc);

        JLabel columnesLabel = new JLabel("Número de columnes:");
        columnesSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 500, 1));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(columnesLabel, gbc);
        gbc.gridx = 1;
        panel.add(columnesSpinner, gbc);

        JLabel tipusLabel = new JLabel("Tipus de teclat:");
        tipusComboBox = new JComboBox<>();
        tipusComboBox.addItem("Teclat d'ordinador (dues mans)");
        tipusComboBox.addItem("Teclat tàctil (dos dits)");
        tipusComboBox.setSelectedIndex(0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(tipusLabel, gbc);
        gbc.gridx = 1;
        panel.add(tipusComboBox, gbc);

        JButton crearButton = new JButton("Crear Teclat");
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearTeclado();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(crearButton, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(490, 340);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Inicialitza els elements de la interfície gràfica.
     */
    private void initUI() {
        ArrayList<Integer> idEntradas = ControladorPresentacio.getIdEntrades();
        for (Integer id : idEntradas) {
            String nombreEntrada = ControladorPresentacio.getNomEntrada(id);
            entradaComboBox.addItem(nombreEntrada);
        }
    }

    /**
     * Crea el teclat amb els paràmetres especificats.
     */
    private void crearTeclado() {
        String nombreTeclado = nomTextField.getText();
        if (nombreTeclado.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El nom del teclat no pot estar buit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int indiceSeleccionado = entradaComboBox.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            ArrayList<Integer> idEntradas = ControladorPresentacio.getIdEntrades();
            Integer idEntradaSeleccionada = idEntradas.get(indiceSeleccionado);
            int files = (int) filesSpinner.getValue();
            int columnes = (int) columnesSpinner.getValue();
            int tipusTeclat = tipusComboBox.getSelectedIndex();

            int ret;
            if (tipusTeclat == 0)
                ret = ControladorPresentacio.crearTeclatDuesMans(nombreTeclado, idEntradaSeleccionada, files, columnes);
            else
                ret = ControladorPresentacio.crearTeclatPolzes(nombreTeclado, idEntradaSeleccionada, files, columnes);

            if (ret == 0) frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "No s'ha seleccionat cap entrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
