package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class VistaModificarTeclat extends JFrame {
    private JSpinner tfFiles;
    private JSpinner tfColumnes;

    public VistaModificarTeclat(Integer idTeclat) {
        initUI(idTeclat);
    }

    // Mostra la finestra
    public void mostrar() {
        setVisible(true);
    }

    // Tanca la finestra
    public void tancar() {
        setVisible(false);
    }


    private void initUI(Integer idTeclat) {
        setTitle("Modificar Teclat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JPanel panelFiles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFiles = new JLabel("Noves files:");
        panelFiles.add(lblFiles);

        int currentFiles = ControladorPresentacio.getFilesTeclat(idTeclat);
        tfFiles = new JSpinner(new SpinnerNumberModel(currentFiles, 1, Integer.MAX_VALUE, 1));
        tfFiles.setPreferredSize(new Dimension(160, 20)); // Set the preferred size
        panelFiles.add(tfFiles);

        panel.add(panelFiles, gbc);

        JPanel panelColumnes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblColumnes = new JLabel("Noves columnes:");
        panelColumnes.add(lblColumnes);

        int currentColumnes = ControladorPresentacio.getColumnesTeclat(idTeclat);
        tfColumnes = new JSpinner(new SpinnerNumberModel(currentColumnes, 1, Integer.MAX_VALUE, 1));
        tfColumnes.setPreferredSize(new Dimension(130, 20)); // Set the preferred size
        panelColumnes.add(tfColumnes);

        panel.add(panelColumnes, gbc);

        ChangeListener filesChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int files = (int) tfFiles.getValue();
                Integer columnesOptimes = ControladorPresentacio.getColumnesOptimesTeclat(idTeclat, files);
                if (columnesOptimes != null) {
                    tfColumnes.setValue(columnesOptimes);
                }
            }
        };

        ChangeListener columnesChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int columnes = (int) tfColumnes.getValue();
                Integer filesOptimes = ControladorPresentacio.getFilesOptimesTeclat(idTeclat, columnes);
                if (filesOptimes != null) {
                    tfFiles.setValue(filesOptimes);
                }
            }
        };

        JPanel panelCheckBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox cbOptimization = new JCheckBox("Activar optimització de files i columnes", true);
        cbOptimization.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Checkbox has been selected
                    tfFiles.addChangeListener(filesChangeListener);
                    tfColumnes.addChangeListener(columnesChangeListener);
                } else {
                    // Checkbox has been deselected
                    tfFiles.removeChangeListener(filesChangeListener);
                    tfColumnes.removeChangeListener(columnesChangeListener);
                }
            }
        });
        panelCheckBox.add(cbOptimization);

        panel.add(panelCheckBox, gbc);

        // Manually trigger the stateChanged event
        if (cbOptimization.isSelected()) {
            tfFiles.addChangeListener(filesChangeListener);
            tfColumnes.addChangeListener(columnesChangeListener);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Add some vertical space

        JPanel panelAceptar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Acceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarTeclat(idTeclat);
            }
        });
        panelAceptar.add(btnAceptar);

        panel.add(panelAceptar, gbc);

        add(panel);

        setSize(325, 200);
        setLocationRelativeTo(null);
    }

    private void modificarTeclat(Integer idTeclat) {
        int files = (Integer) tfFiles.getValue();
        int columnes = (Integer) tfColumnes.getValue();

        // Llamar al controlador para modificar el teclado con las nuevas filas y columnas
        ControladorPresentacio.modificarFilesColumnesTeclat(idTeclat, files, columnes);

        dispose(); // Cerrar la ventana después de la modificación
    }
}
