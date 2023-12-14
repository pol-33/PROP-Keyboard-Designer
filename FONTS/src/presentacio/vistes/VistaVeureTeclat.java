package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;
import presentacio.elements.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

public class VistaVeureTeclat {

    private JFrame frame;

    public VistaVeureTeclat(Integer idTeclat) {
        initComponents(idTeclat);
        initUI();
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void tancar() {
        frame.setVisible(false);
    }

    private void initComponents(Integer idTeclado) {
        frame = new JFrame("Ver Teclado");
        JPanel panel = new JPanel(new BorderLayout());

        JLabel nomLabel = new JLabel(ControladorPresentacio.getNomTeclat(idTeclado));
        panel.add(nomLabel, BorderLayout.NORTH);

        JLabel entradaLabel = new JLabel("Entrada asociada: " + ControladorPresentacio.getNomEntrada(ControladorPresentacio.getIdEntradaTeclat(idTeclado)));
        panel.add(entradaLabel, BorderLayout.CENTER);

        LayoutTeclat layout = new LayoutTeclat(ControladorPresentacio.getDistribucioTeclat(idTeclado),
            ControladorPresentacio.getFilesTeclat(idTeclado),
            ControladorPresentacio.getColumnesTeclat(idTeclado));

        panel.add(layout);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
    }

    private void initUI() {
        
    }
}
