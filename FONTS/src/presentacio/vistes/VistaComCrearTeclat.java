package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe VistaComCrearTeclat. Vista per a la creació d'un teclat.
 */
public class VistaComCrearTeclat {

    private JFrame frame;

    /**
     * Constructora de la classe VistaComCrearTeclat.
     */
    public VistaComCrearTeclat() {
        initComponents();
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JButton crearTeclatButton = new JButton("Crear Teclat");
        crearTeclatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaCrearTeclat().mostrar();
                tancar();
            }
        });

        JButton importarTeclatButton = new JButton("Importar Teclat");
        importarTeclatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        ControladorPresentacio.importarTeclat(selectedFile);
                        tancar();
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, "Error en importar el Teclat.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        panel.add(crearTeclatButton, gbc);
        panel.add(importarTeclatButton, gbc);

        frame.add(panel);
    }
}