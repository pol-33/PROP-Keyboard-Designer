package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblFiles = new JLabel("Noves files:");
        panel.add(lblFiles);

        tfFiles = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        panel.add(tfFiles);

        JLabel lblColumnes = new JLabel("Noves columnes:");
        panel.add(lblColumnes);

        tfColumnes = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        panel.add(tfColumnes);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarTeclat(idTeclat);
            }
        });
        panel.add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana si se presiona "Cancelar"
            }
        });
        panel.add(btnCancelar);

        add(panel);

        setSize(300, 150);
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
