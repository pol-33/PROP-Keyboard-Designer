package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe ElementAlfabetLlista. Representa un element de la llista d'alfabets.
 */
public class ElementAlfabetLlista extends JPanel {
    private int id;
    private String nom;
    private ArrayList<Character> lletres;

    /**
     * Constructora de la classe ElementAlfabetLlista.
     * @param id Identificador de l'alfabet.
     * @param nom Nom de l'alfabet.
     * @param lletres Lletres de l'alfabet.
     */
    public ElementAlfabetLlista(int id, String nom, ArrayList<Character> lletres) {
        this.id = id;
        this.nom = nom;
        this.lletres = lletres;
    }

    /**
     * Obté l'identificador de l'alfabet.
     * @return L'identificador de l'alfabet.
     */
    public int getId() {
        return id;
    }

    /**
     * Obté el nom de l'alfabet.
     * @return El nom de l'alfabet.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obté les lletres de l'alfabet.
     * @return Les lletres de l'alfabet.
     */
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

