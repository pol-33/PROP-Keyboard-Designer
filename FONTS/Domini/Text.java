package Domini;

public class Text extends Entrada {

    private String content;

    // Constructor
    public Text(String content) {
        this.content = content;
    }

    // Metode per obtindre el contingut del text
    public String getContent() {
        return content;
    }

    // Metode per modificar el contingut del text
    public void setContent(String newContent) {
        this.content = newContent;
    }

    // Metode per afegir més contingut al text
    public void appendContent(String additionalContent) {
        this.content += additionalContent;
    }

    // Metode per obtindre la longitud del text
    public int length() {
        return content.length();
    }

    // metode per fer print del contingut del text
    public void display() {
        System.out.println(content);
    }

}
