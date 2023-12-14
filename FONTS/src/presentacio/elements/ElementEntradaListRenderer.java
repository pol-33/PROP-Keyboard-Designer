package presentacio.elements;

import javax.swing.*;
import java.awt.*;

public class ElementEntradaListRenderer extends JPanel implements ListCellRenderer<ElementEntradaLlista> {
    private JLabel nomLabel;
    private JLabel tipusLabel;
    private JLabel nomAlfabetLabel;
    private JLabel contingutPreviewLabel;

    public ElementEntradaListRenderer() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));

        nomLabel = new JLabel();
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        textPanel.add(nomLabel);

        tipusLabel = new JLabel();
        tipusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(tipusLabel);

        nomAlfabetLabel = new JLabel();
        nomAlfabetLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(nomAlfabetLabel);

        contingutPreviewLabel = new JLabel();
        contingutPreviewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(contingutPreviewLabel);

        add(textPanel, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ElementEntradaLlista> list,
                                                  ElementEntradaLlista value,
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
        tipusLabel.setText("Tipus: " + value.getTipus());
        nomAlfabetLabel.setText("Alfabet: " + value.getNomAlfabet());
        contingutPreviewLabel.setText(value.getContingutPreview());

        return this;
    }
}
