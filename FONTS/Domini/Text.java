package Domini;

import java.util.*;

public class Text extends Entrada{

    // Nom text
    private String content;

    // Constructor
    public Text(String content) {
        this.content = content;
    }

    // Mètode per obtindre el contingut del text
    public String getContent() {
        return content;
    }

    // Mètode per modificar el contingut del text
    public void setContent(String newContent) {
        this.content = newContent;
    }

    // Mètode per afegir més contingut al text
    public void appendContent(String additionalContent) {
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

}
