package presentacio.elements;

import javax.swing.*;
import java.awt.*;

public class ElementEntradaListRenderer extends JPanel implements ListCellRenderer<ElementEntradaLlista> {
    private JLabel nomLabel;
    private JLabel tipus;
    private JLabel nomAlfabet;
    private JLabel contingutPreview;

    public ElementEntradaListRenderer() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(true);

        nomLabel = new JLabel();
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(nomLabel);

        tipus = new JLabel();
        tipus.setHorizontalAlignment(SwingConstants.LEFT);
        add(tipus);

        nomAlfabet = new JLabel();
        nomAlfabet.setHorizontalAlignment(SwingConstants.LEFT);
        add(nomAlfabet);

        contingutPreview = new JLabel();
        contingutPreview.setHorizontalAlignment(SwingConstants.LEFT);
        add(contingutPreview);
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
        tipus.setText(value.getTipus());
        nomAlfabet.setText(value.getNomAlfabet());
        contingutPreview.setText(value.getContingutPreview());

        return this;
    }
}

