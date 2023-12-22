package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ElementTeclatLlista extends JPanel {
    private int id;
    private String nom;
    private String nomEntrada;
    private int files;
    private int columnes;
    private String nomAlfabet;

    public ElementTeclatLlista(int id, String nom, String nomEntrada, int files, int columnes, String nomAlfabet) {
        this.id = id;
        this.nom = nom;
        this.nomEntrada = nomEntrada;
        this.files = files;
        this.columnes = columnes;
        this.nomAlfabet = nomAlfabet;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNomEntrada() {
        return nomEntrada;
    }

    public int getFiles() {
        return files;
    }

    public int getColumnes() {
        return columnes;
    }

    public String getNomAlfabet() {
        return nomAlfabet;
    }
}
