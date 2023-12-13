package presentacio.elements;

import javax.swing.*;
import java.awt.*;

public class ElementTeclatListRenderer extends JPanel implements ListCellRenderer<ElementTeclatLlista> {
    private JLabel nomLabel;
    private JLabel nomEntradaLabel;
    private JLabel filesColumnesLabel;

    public ElementTeclatListRenderer() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        nomLabel = new JLabel();
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        textPanel.add(nomLabel);

        nomEntradaLabel = new JLabel();
        nomEntradaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(nomEntradaLabel);

        filesColumnesLabel = new JLabel();
        filesColumnesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(filesColumnesLabel);

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

        nomLabel.setText("Nom: " + value.getNom());
        nomEntradaLabel.setText("Nom Entrada: " + value.getNomEntrada());
        filesColumnesLabel.setText("Files x Columnes: " + value.getFiles() + "x" + value.getColumnes());

        return this;
    }
}
