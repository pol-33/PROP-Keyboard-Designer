package ControladorsDomini;

import Domini.Usuari;

import java.util.HashMap;
import java.util.Map;

public class ControladorUsuari {

    // Mapa per guardar els usuaris
    private Map<String, Usuari> usuaris;

    // Constructora de la classe
    public ControladorUsuari() {
        usuaris = new HashMap<>();
    }

    // Mètode per afegir un usuari
    public void afegirUsuari(Usuari usuari) throws Exception{
        if(usuari == null) throw new Exception ("L'usuari no pot ser null");
        if(usuaris.containsKey(usuari.getNomUsuari())) throw new Exception("Ja existeix un usuari amb aquest nom");
        usuaris.put(usuari.getNomUsuari(), usuari);
    }

    // Mètode per eliminar un usuari
    public void eliminarUsuari(String nomUsuari) throws Exception{
        if(nomUsuari == null) throw new Exception("El nom d'usuari no pot ser null");
        if(!usuaris.containsKey(nomUsuari)) throw new Exception("No existeix cap usuari amb aquest nom");
        usuaris.remove(nomUsuari);
    }

    // Mètode per obtenir un usuari
    public Usuari obtenirUsuari(String nomUsuari) throws Exception {
        if(nomUsuari == null) throw new Exception("El nom d'usuari no pot ser null");
        Usuari usuari = usuaris.get(nomUsuari);
        if(usuari == null) throw new Exception("No existeix cap usuari amb aquest nom");
        return usuari;
    }

    // Verificar les dades d'un usuari
    public boolean checkUsuari(String nomUsuari, String contrasenya){
        Usuari usuari = usuaris.get(nomUsuari);
        return usuari != null && usuari.contrasenyaCorrecta(contrasenya);
    }
}
