package presentacio.vistes;

import javax.swing.*;

import presentacio.controladors.ControladorPresentacio;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VistaCrearTeclat {

    private JFrame frame;
    private JTextField nomTextField;
    private JComboBox<String> entradaComboBox;
    private JSpinner filesSpinner;
    private JSpinner columnesSpinner;
    private JComboBox<String> tipusComboBox;

    public VistaCrearTeclat() {
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
        frame = new JFrame("Crear Teclado");
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel nomLabel = new JLabel("Nombre del teclado:");
        nomTextField = new JTextField();

        JLabel entradaLabel = new JLabel("Seleccionar entrada:");
        entradaComboBox = new JComboBox<>();

        JLabel filesLabel = new JLabel("Número de filas:");
        filesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        JLabel columnesLabel = new JLabel("Número de columnas:");
        columnesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        JLabel tipusLabel = new JLabel("Algoritme a utilizar:");
        tipusComboBox = new JComboBox<>();
        tipusComboBox.addItem("Algoritme per a teclats de portatil");
        tipusComboBox.addItem("Algoritme per a teclats tactils");
        tipusComboBox.setSelectedIndex(0);

        panel.add(nomLabel);
        panel.add(nomTextField);
        panel.add(entradaLabel);
        panel.add(entradaComboBox);
        panel.add(filesLabel);
        panel.add(filesSpinner);
        panel.add(columnesLabel);
        panel.add(columnesSpinner);
        panel.add(tipusLabel);
        panel.add(tipusComboBox);

        JButton crearButton = new JButton("Crear Teclado");
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearTeclado();
            }
        });

        panel.add(new JLabel());
        panel.add(crearButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
    }

    private void initUI() {
        ArrayList<Integer> idEntradas = ControladorPresentacio.getIdEntrades();
        for (Integer id : idEntradas) {
            String nombreEntrada = ControladorPresentacio.getNomEntrada(id);
            entradaComboBox.addItem(nombreEntrada);
        }
    }

    private void crearTeclado() {
        String nombreTeclado = nomTextField.getText();
        int indiceSeleccionado = entradaComboBox.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            ArrayList<Integer> idEntradas = ControladorPresentacio.getIdEntrades();
            Integer idEntradaSeleccionada = idEntradas.get(indiceSeleccionado);
            try {
                int files = (int) filesSpinner.getValue();
                int columnes = (int) columnesSpinner.getValue();
                int tipusTeclat = tipusComboBox.getSelectedIndex();

                if (tipusTeclat == 0)
                    ControladorPresentacio.crearTeclatDuesMans(nombreTeclado, idEntradaSeleccionada, files, columnes);
                else
                    ControladorPresentacio.crearTeclatPolzes(nombreTeclado, idEntradaSeleccionada, files, columnes);

                frame.dispose();
                JOptionPane.showMessageDialog(frame, "Teclado creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al crear el teclado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No se ha seleccionado ninguna entrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
