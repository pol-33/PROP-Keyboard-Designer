package Domini;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Usuari {

    //Nombre d'usuari

    private String nomUsuari;

    //contrasenya d'usuari

    private String contrasenya;

    //Vector punter a les partides
    private ArrayList<Teclat> teclats;

    //Classe creadora d'un usuari
    public Usuari(String nomUsuari, String contrasenya) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.teclats = new ArrayList<Teclat>();
    }

    //Funcio per obtindre el nom d'un usuari
    public String getNomUsuari() { return nomUsuari; }

    //Funcio per modificar el nom d'un usuari
    public void setNomUsuari(String nomUsuari) { this.nomUsuari = nomUsuari; }

    //Funcio per obtindre la contrasenya d'un usuari
    public String getContrasenya() {return contrasenya;}

    //Funcio modificar contraseña
    public void setContrasenya(String contrasenya) { this.contrasenya = contrasenya;}

    // Funcio mirar si la contransenya pasada com a parametre coincideix amb la del usuari
    public boolean contrasenyaCorrecta(String contrasenya) { return this.contrasenya.equals(contrasenya);}

    //Funcio per obtindre el numero de teclats d'un usuari
    public Integer getTeclats() { return this.teclats.size();}

    //Funcio per obtindre els teclats d'un usuari

    public Teclat[] obtindreTeclats() { return this.teclats.Array(new Teclat[this.teclats.size()]);}





}
