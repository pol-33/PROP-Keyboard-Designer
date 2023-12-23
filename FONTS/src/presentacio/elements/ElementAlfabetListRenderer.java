package presentacio.elements;

import javax.swing.*;
import java.awt.*;

/**
 * Classe ElementAlfabetListRenderer. Renderitza els elements de la llista d'alfabets.
 */
public class ElementAlfabetListRenderer extends JPanel implements ListCellRenderer<ElementAlfabetLlista> {
    private JLabel nomLabel;
    private JLabel letrasLabel;

    /**
     * Constructora de la classe ElementAlfabetListRenderer.
     */
    public ElementAlfabetListRenderer() {
        setLayout(new BorderLayout());
        setOpaque(true);

        nomLabel = new JLabel();
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nomLabel.setPreferredSize(new Dimension(100, 20)); // Establece un tamaño para el nombre
        nomLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Añade un pequeño margen

        letrasLabel = new JLabel();
        letrasLabel.setHorizontalAlignment(SwingConstants.LEFT);
        letrasLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Añade un pequeño margen

        add(nomLabel, BorderLayout.WEST);
        add(letrasLabel, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ElementAlfabetLlista> list,
                                                  ElementAlfabetLlista value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        nomLabel.setText(value.getNom());
        letrasLabel.setText(value.getLetras());

        return this;
    }
}
