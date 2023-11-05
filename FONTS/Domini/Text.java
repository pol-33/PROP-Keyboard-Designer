package Domini;

import java.util.*;

public class Text extends Entrada{

    // Nom text
    private String content;

    // Constructor
    public Text(String content) throws Exception {
        if(content == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.content = content;
    }

    // Mètode per obtindre el contingut del text
    public String getContent() {
        return content;
    }

    // Mètode per modificar el contingut del text
    public void setContent(String newContent) throws Exception {
        if(newContent == null) throw new IllegalArgumentException("ERROR: El contingut del text no pot ser null");
        this.content = newContent;
    }

    // Mètode per afegir més contingut al text
    public void appendContent(String additionalContent) throws Exception {
        if(additionalContent == null) throw new IllegalArgumentException("ERROR: El contingut adicioanl del text no pot ser null");
        this.content += additionalContent;
    }

    // Mètode per obtindre la longitud del text
    public int length() {
        return content.length();
    }

    // Mètode per fer print del contingut del text
    public void display() {
        System.out.println(content);
    }

    // Transformar Text a LPF
    public LPF getLPF() {
        LPF lpf = new LPF();
        String[] paraules = content.split(" ");
        for (String paraula : paraules) {
            lpf.afegirParaula(paraula);
        }
        return lpf;
    }

}
