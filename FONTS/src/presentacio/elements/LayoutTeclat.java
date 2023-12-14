package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LayoutTeclat extends JPanel {

    public LayoutTeclat(ArrayList<Character> lletres, int files, int columnes) {
        setLayout(new GridLayout(files, columnes));

        for (char c : lletres) {
            add(new JLabel(String.valueOf(c), SwingConstants.CENTER));
        }
    }
}

