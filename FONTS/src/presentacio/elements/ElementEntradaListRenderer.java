package presentacio.elements;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Classe ElementEntradaListRenderer. Renderitza els elements de la llista d'entrades.
 */
public class ElementEntradaListRenderer extends JPanel implements ListCellRenderer<ElementEntradaLlista> {
    JPanel panell;
    private JLabel nomLabel;
    private JLabel infoLabel;
    private JLabel contingutPreviewLabel;

    /**
     * Constructora de la classe ElementEntradaListRenderer.
     */
    public ElementEntradaListRenderer() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panell = new JPanel();
        panell.setLayout(new GridLayout(0, 1));
        panell.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panell.setBackground(Color.gray);
        
        JPanel primerPanell = new JPanel(new BorderLayout());
        primerPanell.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        nomLabel = new JLabel();
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        primerPanell.add(nomLabel, BorderLayout.WEST); // Alinea nomLabel a la izquierda

        infoLabel = new JLabel();
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Alinea infoLabel a la izquierda
        primerPanell.add(infoLabel, BorderLayout.EAST); // Coloca infoLabel a la derecha

        panell.add(primerPanell);

        contingutPreviewLabel = new JLabel();
        contingutPreviewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contingutPreviewLabel.setMinimumSize(new Dimension(100, 20)); // Set the minimum size

        panell.add(contingutPreviewLabel);

        add(panell, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ElementEntradaLlista> list,
                                                  ElementEntradaLlista value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if (isSelected) {
            //setBackground(list.getSelectionBackground());
            //setForeground(list.getSelectionForeground());
            panell.setBackground(list.getSelectionBackground());
            panell.setForeground(list.getSelectionForeground());
        } else {
            //setBackground(list.getBackground());
            //setForeground(list.getForeground());
            panell.setBackground(Color.lightGray);
            panell.setForeground(list.getForeground());
        }

        nomLabel.setText(value.getNom());
        infoLabel.setText(value.getTipus() + " | " + value.getNomAlfabet());
        //contingutPreviewLabel.setText(value.getContingutPreview());

        String previewText = value.getContingutPreview();
        contingutPreviewLabel.setText(previewText);
        FontMetrics fm = contingutPreviewLabel.getFontMetrics(contingutPreviewLabel.getFont());
        int textWidth = fm.stringWidth(previewText);
        int labelWidth = contingutPreviewLabel.getWidth();

        if (labelWidth < 0) {
            labelWidth = 735;
        }

        if (textWidth > labelWidth) {
            int approxChars = labelWidth / fm.charWidth('n'); // Estimate the number of characters that can fit
            if (approxChars > 0) {
                previewText = previewText.substring(0, Math.min(approxChars, previewText.length())) + "...";
            } else {
                previewText = "...";
            }
        }

        // Select the first 20 characters
        //previewText = previewText.substring(0, Math.min(100, previewText.length())) + "...";

        contingutPreviewLabel.setText(previewText);

        return this;
    }
}
