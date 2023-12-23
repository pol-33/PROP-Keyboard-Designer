package presentacio.elements;

import javax.swing.*;
import java.awt.*;

/**
 * Classe ElementTeclatListRenderer. Renderitza els elements de la llista de teclats.
 */
public class ElementTeclatListRenderer extends JPanel implements ListCellRenderer<ElementTeclatLlista> {
    private JLabel nomLabel;
    private JLabel nomEntradaLabel;
    private JLabel tipusLabel;
    private JLabel filesColumnesLabel;

    /**
     * Constructora de la classe ElementTeclatListRenderer.
     */
    public ElementTeclatListRenderer() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel textPanel = new JPanel(new BorderLayout());

        nomLabel = new JLabel();
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textPanel.add(nomLabel, BorderLayout.WEST); // Align nomLabel to the left

        filesColumnesLabel = new JLabel();
        filesColumnesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        filesColumnesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        tipusLabel = new JLabel();
        tipusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tipusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a new JPanel for filesColumnesLabel and tipusLabel and add it to the center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(Box.createHorizontalGlue()); // Add vertical glue before the labels
        centerPanel.add(tipusLabel);
        centerPanel.add(Box.createHorizontalGlue()); // Add horizontal glue between the labels
        centerPanel.add(filesColumnesLabel);
        centerPanel.add(Box.createHorizontalGlue()); // Add vertical glue after the labels
        textPanel.add(centerPanel, BorderLayout.CENTER);

        nomEntradaLabel = new JLabel();
        nomEntradaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        nomEntradaLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Align infoLabel to the left
        textPanel.add(nomEntradaLabel, BorderLayout.EAST); // Place infoLabel to the right

        add(textPanel, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ElementTeclatLlista> list,
                                                  ElementTeclatLlista value,
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
        nomEntradaLabel.setText(value.getNomEntrada() + " | " + value.getNomAlfabet());
        filesColumnesLabel.setText("Files x Columnes: " + value.getFiles() + "x" + value.getColumnes());
        tipusLabel.setText("Tipus: " + value.getTipus());

        return this;
    }
}
