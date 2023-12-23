package presentacio.elements;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe LayoutTeclat. Representa el layout d'un teclat.
 */
public class LayoutTeclat extends JPanel {
    /**
     * Constructora de la classe LayoutTeclat.
     * @param lletres Lletres del teclat.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     */
    public LayoutTeclat(ArrayList<Character> lletres, int files, int columnes) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panellTecles = new JPanel();
        panellTecles.setLayout(new GridLayout(files, columnes, 5, 5));
        panellTecles.setBorder(new EmptyBorder(10, 10, 10, 10));

        Dimension preferredSize = new Dimension(300, 300); // Tamaño estimado para las teclas
        panellTecles.setPreferredSize(preferredSize);

        for (char c : lletres) {
            JLabel tecla = new JLabel(String.valueOf(c));
            tecla.setFont(new Font("Arial", Font.PLAIN, 14));
            tecla.setHorizontalAlignment(SwingConstants.CENTER);
            tecla.setVerticalAlignment(SwingConstants.CENTER);
            tecla.setOpaque(true);
            tecla.setBackground(Color.LIGHT_GRAY);
            tecla.setBorder(new LineBorder(Color.BLACK, 1));

            panellTecles.add(tecla);
        }
        add(panellTecles, BorderLayout.CENTER);

        JLabel info = new JLabel("Files: " + files + "   Columnes: " + columnes);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        add(info, BorderLayout.NORTH);
    }

    /**
     * Mètode main de la classe LayoutTeclat.
     * @param args Arguments input.
     */
    public static void main(String[] args) {
        ArrayList<Character> letras = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            letras.add(c);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new LayoutTeclat(letras, 4, 7)); // Ajusta las filas y columnas según tu diseño
        frame.setVisible(true);
    }
}
