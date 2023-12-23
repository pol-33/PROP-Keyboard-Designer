package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe ElementEntradaLlista. Representa un element de la llista d'entrades.
 */

public class ElementEntradaLlista extends JPanel {
    private int id;
    private String nom;
    private String tipus;
    private String contingutPreview;
    private String nomAlfabet;

    /**
     * Constructora de la classe ElementEntradaLlista.
     * @param id Identificador de l'entrada.
     * @param nom Nom de l'entrada.
     * @param tipus Tipus de l'entrada.
     * @param contingutPreview Contingut de l'entrada.
     * @param nomAlfabet Nom de l'alfabet de l'entrada.
     */
    public ElementEntradaLlista(int id, String nom, String tipus, String contingutPreview, String nomAlfabet) {
        this.id = id;
        this.nom = nom;
        this.tipus = tipus;
        this.contingutPreview = contingutPreview;
        this.nomAlfabet = nomAlfabet;
    }

    /**
     * Obté l'identificador de l'entrada.
     * @return L'identificador de l'entrada.
     */
    public int getId() {
        return id;
    }

    /**
     * Obté el nom de l'entrada.
     * @return El nom de l'entrada.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obté el tipus de l'entrada.
     * @return El tipus de l'entrada.
     */
    public String getTipus() {
        return tipus;
    }

    /**
     * Obté el contingut de l'entrada.
     * @return El contingut de l'entrada.
     */
    public String getContingutPreview() {
        return contingutPreview;
    }

    /**
     * Obté el nom de l'alfabet de l'entrada.
     * @return El nom de l'alfabet de l'entrada.
     */
    public String getNomAlfabet() {
        return nomAlfabet;
    }

    /**
     * Estableix el contingut de l'entrada.
     * @param contingutPreview El contingut de l'entrada.
     */
    public void setContingutPreview(String contingutPreview) {
        this.contingutPreview = contingutPreview;
    }
}

