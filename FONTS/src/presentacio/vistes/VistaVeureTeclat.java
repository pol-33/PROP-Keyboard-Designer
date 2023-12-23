package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;
import presentacio.elements.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

/**
 * Classe VistaVeureTeclat. Vista per a la visualització d'un teclat.
 */
public class VistaVeureTeclat {

    private JFrame frame;

    /**
     * Constructora de la classe VistaVeureTeclat.
     * @param idTeclat Identificador del teclat.
     */
    public VistaVeureTeclat(Integer idTeclat) {
        initComponents(idTeclat);
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
     * @param idTeclado Identificador del teclat.
     */
    private void initComponents(Integer idTeclado) {
        frame = new JFrame("Veure Teclat");
        JPanel panel = new JPanel(new BorderLayout());

        JLabel nomLabel = new JLabel(ControladorPresentacio.getNomTeclat(idTeclado));
        nomLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nomLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(nomLabel, BorderLayout.NORTH);

        JLabel entradaLabel = new JLabel("Entrada associada: " + ControladorPresentacio.getNomEntrada(ControladorPresentacio.getIdEntradaTeclat(idTeclado)));
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
}
