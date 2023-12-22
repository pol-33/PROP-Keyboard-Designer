package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ElementEntradaLlista extends JPanel {
    private int id;
    private String nom;
    private String tipus;
    private String contingutPreview;
    private String nomAlfabet;

    public ElementEntradaLlista(int id, String nom, String tipus, String contingutPreview, String nomAlfabet) {
        this.id = id;
        this.nom = nom;
        this.tipus = tipus;
        this.contingutPreview = contingutPreview;
        this.nomAlfabet = nomAlfabet;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getTipus() {
        return tipus;
    }

    public String getContingutPreview() {
        return contingutPreview;
    }

    public String getNomAlfabet() {
        return nomAlfabet;
    }

    public void setContingutPreview(String contingutPreview) {
        this.contingutPreview = contingutPreview;
    }
}

