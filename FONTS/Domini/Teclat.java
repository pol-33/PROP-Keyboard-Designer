package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Teclat {

    //Identificador de teclat
    private int id;
    private ArrayList<Character> tecles;

    // Creadora
    public Teclat(ArrayList<Character> tecles, HashMap<String, Integer> lpf) {
        this.tecles = tecles;
    }

    // Getters
    public int getId() {
        return id;
    }


}
