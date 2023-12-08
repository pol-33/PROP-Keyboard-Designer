package persistencia.controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    
    private ControladorPersistencia() {
    }

    //Metode per obtenir l'instància singleton
    public static ControladorPersistencia obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPersistencia();
        }
        return ctrl;
    }

    //--------------------------------Usuari---------------------------------//
    public ArrayList<String> getUsuarisExistents() {
        return new ArrayList<String>();
    }


    public HashMap<String, String> getUsuarisContrasenyes() {
        return new HashMap<String, String>();
    }

    public void guardarUsuari(String nomUsuari, String contrasenya) {

    }

    public void eliminarUsuari(String nomUsuari) {

    }

    //--------------------------------Alfabet---------------------------------//
    public ArrayList<String> getAlfabetsUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getAlfabet(Integer id) {
        return new String();
    }

    public void guardarAlfabet(Integer id, String nomAlfabet, ArrayList<Character> lletres, ArrayList<Integer> idEntrades) {
    }

    public void eliminarAlfabet(Integer id) {
    }

    //--------------------------------Entrada---------------------------------//
    public ArrayList<String> getEntradesUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getEntrada(Integer id) {
        return new String();
    }

    public void guardarText(Integer id, String nomEntrada, String text, ArrayList<Integer> idTeclats) {
    }

    public void guardarLPF(Integer id, String nomEntrada, HashMap<String, Integer> lpf, ArrayList<Integer> idTeclats) {
    }

    public void eliminarEntrada(Integer id) {
    }

    //--------------------------------Teclat---------------------------------//
    public ArrayList<String> getTeclatsUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getTeclat(Integer id) {
        return new String();
    }

    public void guardarTeclat(Integer id, String nom, Integer numFiles, Integer numColumnes, ArrayList<Character> distribucio, Integer idEntrada) {
    }

    public void eliminarTeclat(Integer id) {
    }

    //--------------------------------Obrir/Tancar---------------------------------//

    //Mètode per obrir un fitxer amb mode lectura
    private void obrirFitxerLectura(String nomFitxer) throws IOException {
        fileOutputStream = new FileOutputStream("../../DATA/"+nomFitxer);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
    }

    // Mètode per obrir un fitxer amb mode escriptura
    private void obrirFitxerEscriptura(String nomFitxer) throws IOException {
        fileOutputStream = new FileOutputStream("../../DATA/"+nomFitxer);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
    }

    // Mètode per tancar InputStream
    private void tancarInputStream() throws IOException {
        objectInputStream.close();
    }

    // Mètode per tancar OutputStream
    private void tancarOutputStream() throws IOException {
        objectOutputStream.flush();
        objectOutputStream.close();
    }
    // Mètode per escriure objecte
    private void escriureObjecte(Object objecte) throws IOException {
        objectOutputStream.writeObject(objecte);
    }

}
