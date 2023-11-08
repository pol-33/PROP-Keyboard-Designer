package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import ControladorsDomini.ControladorAlgoritme;

public class Teclat {

    protected ArrayList<Character> tecles;
    protected Integer num_files;
    protected Integer num_columnes;
    protected Entrada entrada;

    // Creadora
    public Teclat(ArrayList<Character> lletres, Entrada entrada) {
        this.entrada = entrada;
        num_files = 3;
        num_columnes = lletres.size()/num_files;
        if (lletres.size()%num_files != 0) num_columnes++;
        // Obtenir controlador algoritme
        ControladorAlgoritme ctrlAlgoritme = ControladorAlgoritme.obtenirInstancia();
        this.tecles = ctrlAlgoritme.calcularDistribucioDuesMans(entrada.getLPF(), lletres, num_files, num_columnes);
    }

    // Getters
    public ArrayList<Character> getTecles() {
        return tecles;
    }

    // Setters

    // Defineix el nombre de files del teclat, reajustant el nombre de columnes i la distribució de tecles
    public void setNumFiles(Integer num_files) {
        this.num_files = num_files;
        num_columnes = tecles.size()/num_files;
        if (tecles.size()%num_files != 0) num_columnes++;
        regenerarTeclat(tecles, entrada.getLPF());
    }

    // Funcions publiques

    // Recalcula la distribució de tecles del teclat
    public void regenerarTeclat(ArrayList<Character> lletres, HashMap<String, Integer> lpf) {
        // Obtenir controlador algoritme
        ControladorAlgoritme ctrlAlgoritme = ControladorAlgoritme.obtenirInstancia();
        this.tecles = ctrlAlgoritme.calcularDistribucioDuesMans(lpf, lletres, num_files, num_columnes);
    }




}
