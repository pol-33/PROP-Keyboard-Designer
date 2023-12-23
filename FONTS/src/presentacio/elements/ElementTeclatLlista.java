package presentacio.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe ElementTeclatLlista. Representa un element de la llista de teclats.
 */
public class ElementTeclatLlista extends JPanel {
    private int id;
    private String nom;
    private String nomEntrada;
    private int files;
    private int columnes;
    private String nomAlfabet;
    private String tipus;

    /**
     * Constructora de la classe ElementTeclatLlista.
     * @param id Identificador del teclat.
     * @param nom Nom del teclat.
     * @param nomEntrada Nom de l'entrada del teclat.
     * @param files Nombre de files del teclat.
     * @param columnes Nombre de columnes del teclat.
     * @param nomAlfabet Nom de l'alfabet del teclat.
     * @param tipus Tipus del teclat.
     */
    public ElementTeclatLlista(int id, String nom, String nomEntrada, int files, int columnes, String nomAlfabet, String tipus) {
        this.id = id;
        this.nom = nom;
        this.nomEntrada = nomEntrada;
        this.files = files;
        this.columnes = columnes;
        this.nomAlfabet = nomAlfabet;
        this.tipus = tipus;
    }

    /**
     * Obté l'identificador del teclat.
     * @return L'identificador del teclat.
     */
    public int getId() {
        return id;
    }

    /**
     * Obté el nom del teclat.
     * @return El nom del teclat.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obté el nom de l'entrada del teclat.
     * @return El nom de l'entrada del teclat.
     */
    public String getNomEntrada() {
        return nomEntrada;
    }

    /**
     * Obté el nombre de files del teclat.
     * @return El nombre de files del teclat.
     */
    public int getFiles() {
        return files;
    }

    /**
     * Obté el nombre de columnes del teclat.
     * @return El nombre de columnes del teclat.
     */
    public int getColumnes() {
        return columnes;
    }

    /**
     * Obté el nom de l'alfabet del teclat.
     * @return El nom de l'alfabet del teclat.
     */
    public String getNomAlfabet() {
        return nomAlfabet;
    }

    /**
     * Obté el tipus del teclat.
     * @return El tipus del teclat.
     */
    public String getTipus() {
        return tipus;
    }
}
