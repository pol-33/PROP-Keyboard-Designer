package persistencia.controladors;

import persistencia.classes.GestorAlfabets;
import persistencia.classes.GestorUsuaris;
import persistencia.classes.GestorTeclats;
import persistencia.classes.GestorEntrades;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Classe ControladorPersistencia. Controla les operacions de persistència entre els diferents gestors.
 */
public class ControladorPersistencia {

    // Instància única del controlador per aplicar el patró Singleton
    private static ControladorPersistencia ctrl;

    // Gestors per cada tipus de dada a persistir
    private GestorUsuaris gestorUsuaris = new GestorUsuaris();
    private GestorAlfabets gestorAlfabets = new GestorAlfabets();
    private GestorEntrades gestorEntrades = new GestorEntrades();
    private GestorTeclats gestorTeclats = new GestorTeclats();

    // Mètodes per gestionar la persistència

    /**
     * Mètode per obtenir la instància singleton del controlador.
     * @return La instància única del ControladorPersistencia.
     */
    public static ControladorPersistencia obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPersistencia();
        }
        return ctrl;
    }

    //--------------------------------Usuari---------------------------------//

    /**
     * Crea un nou usuari utilitzant el gestor d'usuaris.
     * @param username Nom d'usuari.
     * @param password Contrasenya de l'usuari.
     */
    public void crearUsuari(String username, String password) {
        gestorUsuaris.crearUsuari(username, password);
    }

    /**
     * Obté una llista de tots els usernames utilitzant el gestor d'usuaris.
     * @return ArrayList amb tots els usernames.
     */
    public ArrayList<String> obtenirUsernames() {
        return gestorUsuaris.obtenirUsernames();
    }

    /**
     * Obté la contrasenya d'un usuari específic.
     * @param username Nom d'usuari del qual es vol obtenir la contrasenya.
     * @return Contrasenya de l'usuari.
     */
    public String obtenirPasswordUsuari(String username) {
        return gestorUsuaris.obtenirPasswordUsuari(username);
    }

    /**
     * Modifica la contrasenya d'un usuari.
     * @param username Nom d'usuari al qual se li canviarà la contrasenya.
     * @param password Nova contrasenya per a l'usuari.
     */
    public void modificarPasswordUsuari(String username, String password) {
        gestorUsuaris.eliminarUsuari(username);
        gestorUsuaris.crearUsuari(username, password);
    }

    /**
     * Elimina un usuari utilitzant el gestor d'usuaris.
     * @param username Nom d'usuari a eliminar.
     */
    public void eliminarUsuari(String username) {
        gestorUsuaris.eliminarUsuari(username);
    }

    //--------------------------------Alfabet---------------------------------//

    /**
     * Crea un nou alfabet utilitzant el gestor d'alfabets.
     * @param username Nom d'usuari al qual pertany l'alfabet.
     * @param idAlfabet Identificador únic de l'alfabet.
     * @param nom Nom de l'alfabet.
     * @param lletres Llista de caràcters que formen l'alfabet.
     */
    public void crearAlfabet(String username, Integer idAlfabet, String nom, ArrayList<Character> lletres){
        gestorAlfabets.crearAlfabet(username, idAlfabet, nom, lletres);
    }

    /**
     * Carrega tots els alfabets associats a un usuari.
     * @param username Nom d'usuari del qual es volen carregar els alfabets.
     * @return ArrayList amb la informació de cada alfabet.
     */
    public ArrayList<String> carregarAlfabets(String username) {
        return gestorAlfabets.carregarAlfabets(username);
    }

    /**
     * Afegeix noves lletres a un alfabet existent.
     * @param idAlfabet Identificador de l'alfabet a modificar.
     * @param novesLletres Llista de noves lletres a afegir a l'alfabet.
     */
    public void afegirLletresAlfabet(Integer idAlfabet, ArrayList<Character> novesLletres) {
        String[] alfabet = gestorAlfabets.carregarAlfabet(idAlfabet);
        if (alfabet != null) {
            gestorAlfabets.eliminarAlfabet(idAlfabet);
            if (alfabet.length > 2) {
                alfabet[2] = alfabet[2] + "." + convertirArrayListToString(novesLletres);
            }
            gestorAlfabets.crearAlfabet(alfabet);
        }
    }

    /**
     * Elimina un alfabet utilitzant el gestor d'alfabets.
     * @param idAlfabet Identificador de l'alfabet a eliminar.
     */
    public void eliminarAlfabet(Integer idAlfabet) {
        gestorAlfabets.eliminarAlfabet(idAlfabet);
    }

    //--------------------------------Entrada---------------------------------//

    /**
     * Crea una nova entrada utilitzant el gestor d'entrades.
     * @param idAlfabet Identificador de l'alfabet associat a l'entrada.
     * @param idEntrada Identificador únic de l'entrada.
     * @param nom Nom de l'entrada.
     * @param lpf HashMap amb dades específiques (pot ser null).
     * @param text Text associat a l'entrada.
     */
    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        gestorEntrades.crearEntrada(idAlfabet, idEntrada, nom, lpf, text);
    }

    /**
     * Carrega totes les entrades associades a un alfabet.
     * @param idAlfabet Identificador de l'alfabet del qual es volen carregar les entrades.
     * @return ArrayList de arrays de Strings amb la informació de cada entrada.
     */
    public ArrayList<String[]> carregarEntrades(Integer idAlfabet) {
        return gestorEntrades.carregarEntrades(idAlfabet);
    }

    /**
     * Modifica el contingut d'una entrada existent.
     * @param idEntrada Identificador de l'entrada a modificar.
     * @param nom Nou nom per a l'entrada.
     * @param lpf Nou HashMap amb dades específiques (pot ser null).
     * @param text Nou text associat a l'entrada.
     */
    public void modificarContingutEntrada(Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        gestorEntrades.actualizarEntrada(idEntrada, nom, lpf, text);
    }

    /**
     * Elimina una entrada utilitzant el gestor d'entrades.
     * @param idEntrada Identificador de l'entrada a eliminar.
     */
    public void eliminarEntrada(Integer idEntrada) {
        gestorEntrades.eliminarEntrada(idEntrada);
    }

    //--------------------------------Teclat---------------------------------//

    /**
     * Crea un nou teclat utilitzant el gestor de teclats.
     * @param idEntrada Identificador de l'entrada associada al teclat.
     * @param idTeclat Identificador únic del teclat.
     * @param nom Nom del teclat.
     * @param numFiles Número de files del teclat.
     * @param numColumnes Número de columnes del teclat.
     * @param distribucio Llista amb la distribució de tecles.
     */
    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio) {
        gestorTeclats.crearTeclat(idEntrada, idTeclat, nom, numFiles, numColumnes, distribucio);
    }

    /**
     * Carrega tots els teclats associats a una entrada específica.
     * @param idEntrada Identificador de l'entrada de la qual es volen carregar els teclats.
     * @return ArrayList de arrays de Strings amb la informació de cada teclat.
     */
    public ArrayList<String[]> carregarTeclats(Integer idEntrada) {
        return gestorTeclats.carregarTeclats(idEntrada);
    }

    /**
     * Modifica el número de files d'un teclat existent.
     * @param idTeclat Identificador del teclat a modificar.
     * @param numFiles Nou número de files per al teclat.
     */
    public void modificarNumFilesTeclat(Integer idTeclat, Integer numFiles) {
        gestorTeclats.actualizarNumFilesTeclat(idTeclat, numFiles);
    }

    /**
     * Modifica el número de columnes d'un teclat existent.
     * @param idTeclat Identificador del teclat a modificar.
     * @param numColumnes Nou número de columnes per al teclat.
     */
    public void modificarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {
        gestorTeclats.actualizarNumColumnesTeclat(idTeclat, numColumnes);
    }

    /**
     * Modifica la distribució de les tecles d'un teclat existent.
     * @param idTeclat Identificador del teclat a modificar.
     * @param distribucio Nova distribució de tecles (llista de Strings representant la distribució).
     */
    public void modificarDistribucio(Integer idTeclat, ArrayList<String> distribucio) {
        gestorTeclats.actualizarDistribucioTeclat(idTeclat, distribucio);
    }

    /**
     * Elimina un teclat utilitzant el gestor de teclats.
     * @param idTeclat Identificador del teclat a eliminar.
     */
    public void eliminarTeclat(Integer idTeclat) {
        gestorTeclats.eliminarTeclat(idTeclat);
    }

    //--------------------------------Privat---------------------------------//

    /**
     * Converteix una llista d'objectes Character en una cadena de text.
     * Cada caràcter es separa per un punt.
     * @param llista ArrayList de Character a convertir.
     * @return String resultant de la conversió.
     */
    private static String convertirArrayListToString(ArrayList<Character> llista) {
        StringBuilder result = new StringBuilder();

        // Iterar sobre els elements de la llista i unir-los amb punts
        for (int i = 0; i < llista.size(); i++) {
            result.append(llista.get(i));
            if (i < llista.size() - 1) {
                result.append(".");
            }
        }

        return result.toString();
    }
}

