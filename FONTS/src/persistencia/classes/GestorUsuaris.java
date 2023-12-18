package persistencia.classes;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Classe GestorUsuaris. Gestiona els usuaris emmagatzemats en arxius CSV.
 */
public class GestorUsuaris {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String usuarisPath = "../../DATA/usuari.csv";
    private String relacioUsuariAlfabetPath = "../../DATA/relacioUsuariAlfabet.csv";

    // ---------------------------------------------------------------------------- //
    //                                   Mètodes
    // ---------------------------------------------------------------------------- //


    /**
     * Crea un nou usuari i l'emmagatzema en un arxiu CSV.
     * @param username Nom d'usuari.
     * @param contrasenya Contrasenya de l'usuari.
     */
    public void crearUsuari(String username, String contrasenya) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(usuarisPath, true)))  {
            String[] usuari = { username, contrasenya};
            writer.writeNext(usuari);
        } catch (IOException e) {
            e.getMessage();
        }
    }


    /**
     * Obté els usernames de tots els usuaris.
     * @return ArrayList de Strings amb els usernames.
     */
    public ArrayList<String> obtenirUsernames() {
        ArrayList<String> usernames = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(usuarisPath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                if (row.length > 0) {
                    if (!row[0].isEmpty()) {
                        usernames.add(row[0]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de E/S al leer el archivo CSV");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado al leer el archivo CSV");
        }

        return usernames;
    }

    /**
     * Obté la contrasenya d'un usuari específic.
     * @param username Nom d'usuari.
     * @return String amb la contrasenya de l'usuari, null si no es troba.
     */
    public String obtenirPasswordUsuari(String username) {
        try (CSVReader reader = new CSVReader(new FileReader(usuarisPath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                if (Objects.equals(row[0], username)) { //quan trobem l'usuari
                    return row[1]; //retornem la contrasenya
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Elimina un usuari específic dels arxius CSV.
     * @param username Nom d'usuari a eliminar.
     */
    public void eliminarUsuari(String username) {
        List<String[]> updatedRows = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(usuarisPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row.length > 0 && !row[0].equals(username)) {
                    updatedRows.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(usuarisPath))) {
            writer.writeAll(updatedRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
