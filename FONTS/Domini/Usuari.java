package Domini;

import Domini.Alfabet;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuari {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    // Longitud maxima del nom d'usuari i contrasenya
    private static final int MAX_LENGTH = 15;

    // Nom d'usuari
    private String nomUsuari;

    // Contrasenya d'usuari
    private String contrasenya;

    // Punter cap a alfabets
    private ArrayList<String> idsAlfabets;

    // ---------------------------------------------------------------------------- //
    //                                   Creadora
    // ---------------------------------------------------------------------------- //

    // Classe creadora d'un usuari
    public Usuari(String nomUsuari, String contrasenya) throws Exception{
        if(nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        if(contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.idsAlfabets = new ArrayList<>();
    }

    // ---------------------------------------------------------------------------- //
    //                                   Getters
    // ---------------------------------------------------------------------------- //

    // Funcio per obtindre el nom d'un usuari
    public String getNomUsuari() {
        return nomUsuari;
    }

    // Funció per obtindre la contrasenya d'un usuari
    public String getContrasenya() {
        return contrasenya;
    }

    //Retorna el Array que conte tots els ids dels alfabets de l'usuari
    public ArrayList<String> getIDsAlfabets() {
        return idsAlfabets;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //


    // Funció per modificar el nom d'un usuari
    public void setNomUsuari(String nomUsuari) throws Exception{
        if(nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
    }


    // Funció modificar contraseña
    public void setContrasenya(String contrasenya) throws Exception {
        if(contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.contrasenya = contrasenya;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //

    // Funció mirar si la contransenya pasada com a parametre coincideix amb la de l'usuari
    public boolean contrasenyaCorrecta(String contrasenya) {
        return this.contrasenya.equals(contrasenya);
    }


    // Afegeix un alfabet en el cas que aquest no estigui ja afegit
    public void afegirAlfabet(String idAlfabet){
        if(!idsAlfabets.contains(idAlfabet)) idsAlfabets.add(idAlfabet);
    }

    // Elimina un alfabet en el cas que aquest estigui afegit
    public void eliminarAlfabet(String idioma) throws Exception {
        if (!idsAlfabets.contains(idioma)) {
            throw new Exception("No existeix un alfabet d'aquest idioma");
        }
        idsAlfabets.remove(idioma);
    }

    // Retorna true si l'usuari te l'alfabet amb id idAlfabets
    public boolean teAlfabets(String idAlfabets) throws Exception {
        if(idsAlfabets.isEmpty()) {
            throw new Exception("El usuari no te cap alfabet");
        }
        return idsAlfabets.contains(idAlfabets);
    }

    // Funció per imprimir un usuari amb totes les seves dades
    public String print(){
        return "Usuari{" +
                "nomUsuari='" + nomUsuari + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", idsAlfabets=" + idsAlfabets + '}';
    }

}
