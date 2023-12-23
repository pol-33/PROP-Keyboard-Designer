package presentacio.vistes;

import domini.controladors.ControladorDomini;
import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe VistaCrearEntrada. Vista per a la creació d'una entrada.
 */
public class VistaCrearEntrada {
    private JFrame frame;

    /**
     * Constructora de la classe VistaCrearEntrada.
     */
    public VistaCrearEntrada() {
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
        frame = new JFrame("Crear Entrada");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JButton crearTextButton = new JButton("Crear Text");
        crearTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaCrearText().mostrar();
                tancar();
            }
        });

        JButton importarTextButton = new JButton("Importar Text");
        importarTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        ControladorPresentacio.importarText(selectedFile);
                        tancar();
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, "Error en importar el Text.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        textPanel.add(crearTextButton, gbc);
        textPanel.add(importarTextButton, gbc);
        tabbedPane.addTab("Text", textPanel);

        JPanel lpfPanel = new JPanel(new GridBagLayout());

        JButton crearLPFButton = new JButton("Crear LPF");
        crearLPFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaCrearLPF().mostrar();
                tancar();
            }
        });

        JButton importarLPFButton = new JButton("Importar LPF");
        importarLPFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        ControladorPresentacio.importarLPF(selectedFile);
                        tancar();
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, "Error en importar la LPF.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        lpfPanel.add(crearLPFButton, gbc);
        lpfPanel.add(importarLPFButton, gbc);
        tabbedPane.addTab("LPF", lpfPanel);

        frame.add(tabbedPane);
    }
}