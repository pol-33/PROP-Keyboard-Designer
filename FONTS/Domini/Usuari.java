package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalDouble;

public class Usuari {

    // Longitud maxima del nom d'usuari i contrasenya
    private static final int MAX_LENGTH = 15;

    // Nom d'usuari
    private String nomUsuari;

    // Contrasenya d'usuari
    private String contrasenya;

    // Alfabet de l'usuari
   // private ArrayList<Alfabet> alfabets;

    //string = nom del alfabet
    private HashMap<String, Alfabet> alfabets;


    // Classe creadora d'un usuari
    public Usuari(String nomUsuari, String contrasenya) throws Exception{
        if(nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        if(contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.alfabets = new HashMap<>();
    }

    //
    public void crearAlfabet(String nomAlfabet, String textAlfabet) throws Exception{
        Alfabet nouAlfabet = new Alfabet(nomAlfabet, textAlfabet);
        this.alfabets.put(nomAlfabet, nouAlfabet);
    }

    //
    public void crearText (String nomAlfabet, String nomEntrada, String contingutEntrada) throws Exception {
        Alfabet alfabet = alfabets.get(nomAlfabet);
        alfabet.crearText(nomAlfabet, contingutEntrada);//contingut del text
    }

    //
    public void cearLPF (String nomAlfabet, String nomEntrada, HashMap<String, Integer> contingutEntrada) throws Exception {
        Alfabet alfabet = alfabets.get(nomAlfabet);
        alfabet.crearLPF(nomAlfabet, contingutEntrada); //hashmap string integer fet
    }

    /*
   // public void crearTeclat (String nomTeclat, String nomAlfabet) {
        Alfabet alfabet = alfabets.find(nomAlfabet);
        alfabet.crearTeclat(nomTeclat);
    }
*/
    /*
    public void eliminarAlfabet(Alfabet nomAlfabet) throws Exception{
        OptionalDouble index = this.alfabets.stream().filter(alfabet -> alfabet.getNomAlfabet().equals(nomAlfabet)).findFirst();
        if(index.isPresent()) this.alfabets.remove(index.getAsInt());
        else throw new Exception("No existeix cap alfabet amb aquest nom");
    }
    */
    // Funcio per obtindre el nom d'un usuari
    public String getNomUsuari() {
        return nomUsuari;
    }

    // Funció per modificar el nom d'un usuari
    public void setNomUsuari(String nomUsuari) throws Exception{
        if(nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
    }

    // Funció per obtindre la contrasenya d'un usuari
    public String getContrasenya() {
        return contrasenya;
    }

    // Funció modificar contraseña
    public void setContrasenya(String contrasenya) throws Exception {
        if(contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.contrasenya = contrasenya;
    }

    // Funció mirar si la contransenya pasada com a parametre coincideix amb la de l'usuari
    public boolean contrasenyaCorrecta(String contrasenya) {
        return this.contrasenya.equals(contrasenya);
    }
}
