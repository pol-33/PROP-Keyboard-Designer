package presentacio.elements;

import javax.swing.*;
import java.awt.*;

public class ElementAlfabetListRenderer extends JPanel implements ListCellRenderer<ElementAlfabetLlista> {
    private JLabel nomLabel;
    private JLabel letrasLabel;

    public ElementAlfabetListRenderer() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(true);

        nomLabel = new JLabel();
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(nomLabel);

        letrasLabel = new JLabel();
        letrasLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(letrasLabel);
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

