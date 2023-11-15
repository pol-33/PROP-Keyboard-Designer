package Domini;

import Domini.Alfabet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Usuari. Representa un usuari en el sistema, amb els seus detalls com
 * el nom, la contrasenya i els alfabetes associats. Permet la creació, modificació
 * i gestió de les dades personals de l'usuari.
 */
public class Usuari {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //

    // Longitud màxima del nom d'usuari i contrasenya
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

    /**
     *
     * Constructora de la clase Usuari. Inicialitza un usuari amb un nom i contrasenya,
     *  i prepara una llista per als IDs dels alfabetes associats.
     *
     */
    public Usuari(String nomUsuari, String contrasenya) throws Exception{
        if(nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        if(contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.idsAlfabets = new ArrayList<>();
    }

    //
    public void crearAlfabet(String nomAlfabet, String textAlfabet) throws Exception{
        if (alfabets.containsKey(nomAlfabet)) throw new Exception("Ja existeix un alfabet d'aquest idioma");
        Alfabet nouAlfabet = new Alfabet(nomAlfabet, textAlfabet);
        this.alfabets.put(nomAlfabet, nouAlfabet);
    }

    //Funcio per a llistar alfabets
    public void llistarAlfabets() throws Exception {
        if (alfabets.isEmpty()) throw new Exception("L'usuari no té alfabets");
        System.out.println("Aquests son els alfabets:");
        alfabets.forEach((idioma, Alfabet) -> {
            System.out.println("Idioma: " + idioma);
            System.out.println("Lletres de l'idioma: ");
            Alfabet.imprimirLletres();
        });
    }

    public void eliminarAlfabet(String idioma) throws Exception {
        if (!alfabets.containsKey(idioma)) {
            throw new Exception("No existeix un alfabet d'aquest idioma");
        }
        alfabets.remove(idioma);
    }

    //
    public void crearText (String idioma, String nomEntrada, String contingutEntrada) throws Exception {
        if (!alfabets.containsKey(idioma)) {
            throw new Exception("No existeix un alfabet d'aquest idioma");
        }
        Alfabet alfabet = alfabets.get(idioma);
        alfabet.crearText(nomEntrada, contingutEntrada);//contingut del text
    }

    //
    public void crearLPF (String idioma, String nomEntrada, HashMap<String, Integer> contingutEntrada) throws Exception {
        if (!alfabets.containsKey(idioma)) {
            throw new Exception("No existeix un alfabet d'aquest idioma");
        }
        Alfabet alfabet = alfabets.get(idioma);
        alfabet.crearLPF(nomEntrada, contingutEntrada);
    }

    public void llistarTexts() throws Exception {
        if (alfabets.isEmpty()) throw new Exception("No existeixen textos ni alfabets");
        alfabets.forEach((idioma, alfabet) -> {
            System.out.println(idioma);
            alfabet.llistarTexts();
        });
    }

    public void llistarLPFs() throws Exception {
        if (alfabets.isEmpty()) throw new Exception("No existeixen llistes de paraules frequents ni alfabets");
        alfabets.forEach((idioma, alfabet) -> {
            System.out.println(idioma);
            alfabet.llistarLPFs();
        });
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

    /**
     *
     * Obté la contrasenya de l'usuari.
     *
     * @return Cadena de text que representa la contrasenya de l'usuari.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     *
     * Obté la llista d'IDs dels alfabets associats a l'usuari.
     *
     * @return ArrayList que conté els IDs dels alfabetes de l'usuari.
     */
    public ArrayList<String> getIDsAlfabets() {
        return idsAlfabets;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Setters
    // ---------------------------------------------------------------------------- //

    /**
     *
     * Estableix o modifica el nom d'usuari.
     *
     * @param nomUsuari Nou nom d'usuari a establir.
     * @throws Exception Si el nom d'usuari excedeix la longitud màxima permesa.
     */

    public void setNomUsuari(String nomUsuari) throws Exception{
        if(nomUsuari.length() > MAX_LENGTH) throw new Exception("El nom d'usuari no pot superar els 15 caracters");
        this.nomUsuari = nomUsuari;
    }

    /**
     *
     * Estableix o modifica la contrasenya
     *
     * @param contrasenya Nova contrasenya a establir.
     * @throws Exception Si la contrasenya excedeix la longitud màxima permesa.
     */
    public void setContrasenya(String contrasenya) throws Exception {
        if(contrasenya.length() > MAX_LENGTH) throw new Exception("La contrasenya no pot superar els 15 caracters");
        this.contrasenya = contrasenya;
    }

    // ---------------------------------------------------------------------------- //
    //                                   Funcions
    // ---------------------------------------------------------------------------- //

    /**
     *
     * Comprova si la contrasenya introduïda coincideix amb la contrasenya de l'usuari.
     *
     * @param contrasenya La contrasenya a comprovar.
     * @return True si les contrasenyes coincideixen, false en cas contrari.
     */
    public boolean contrasenyaCorrecta(String contrasenya) {
        return this.contrasenya.equals(contrasenya);
    }

    /**
     *
     * Afegeix un identificador d'alfabet a la llista de l'usuari si aquest no
     * està ja present.
     *
     * @param idAlfabet El identificador de l'alfabet a afegir.
     */
    public void afegirAlfabet(String idAlfabet){
        if(!idsAlfabets.contains(idAlfabet)) idsAlfabets.add(idAlfabet);
    }

    /**
     *
     * Elimina un identificador d'alfabet de la llista de l'usuari si aquest està present.
     *
     * @param idioma El idioma de l'alfabet a eliminar.
     * @throws Exception Si l'alfabet no es troba a la llista de l'usuari.
     */
    public void eliminarAlfabet(String idioma) throws Exception {
        if (!idsAlfabets.contains(idioma)) {
            throw new Exception("No existeix un alfabet d'aquest idioma");
        }
        idsAlfabets.remove(idioma);
    }

    /**
     *
     * Comprova si l'usuari té un alfabet específic.
     *
     * @param idAlfabets El identificador de l'alfabet a comprovar.
     * @return True si l'usuari té l'alfabet, false en cas contrari.
     * @throws Exception Si l'usuari no té cap alfabet.
     */
    public boolean teAlfabets(String idAlfabets) throws Exception {
        if(idsAlfabets.isEmpty()) {
            throw new Exception("El usuari no te cap alfabet");
        }
        return idsAlfabets.contains(idAlfabets);
    }

    /**
     *
     * Impressió de l'usuari, amb totes les seves dades
     *
     */
    public String print(){
        return "Usuari{" +
                "nomUsuari='" + nomUsuari + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", idsAlfabets=" + idsAlfabets + '}';
    }
}
