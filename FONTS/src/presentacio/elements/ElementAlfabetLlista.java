package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ElementAlfabetLlista extends JPanel {
    private int id;
    private String nom;
    private ArrayList<Character> lletres;

    public ElementAlfabetLlista(int id, String nom, ArrayList<Character> lletres) {
        this.id = id;
        this.nom = nom;
        this.lletres = lletres;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLetras() {
        // Lógica para obtener las letras como String
        StringBuilder letrasSeparadas = new StringBuilder();
        for (char letra : lletres) {
            letrasSeparadas.append(letra).append(", ");
        }
        if (letrasSeparadas.length() > 2) {
            letrasSeparadas.delete(letrasSeparadas.length() - 2, letrasSeparadas.length());
        }
        return letrasSeparadas.toString();
    }
}

