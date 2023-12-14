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
        
        JPanel textPanel = new JPanel(new BorderLayout());

        nomLabel = new JLabel();
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textPanel.add(nomLabel, BorderLayout.WEST); // Alinea nomLabel a la izquierda



        filesColumnesLabel = new JLabel();
        filesColumnesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        filesColumnesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(filesColumnesLabel, BorderLayout.CENTER);

        nomEntradaLabel = new JLabel();
        nomEntradaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        nomEntradaLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Alinea infoLabel a la izquierda
        textPanel.add(nomEntradaLabel, BorderLayout.EAST); // Coloca infoLabel a la derecha


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
        nomEntradaLabel.setText(value.getNomEntrada());
        filesColumnesLabel.setText("Files x Columnes: " + value.getFiles() + "x" + value.getColumnes());

        return this;
    }
}
