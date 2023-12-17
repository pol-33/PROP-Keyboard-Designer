package persistencia.classes;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GestorUsuaris {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String usuarisPath = "../../DATA/usuaris.csv";
    private String relacioUsuariAlfabetPath = "../../DATA/relacioUsuariAlfabet.csv";

    public void crearUsuari(String username, String contrasenya) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(usuarisPath, true)))  {
            String[] usuari = { username, contrasenya};
            writer.writeNext(usuari);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public ArrayList<String> obtenirUsernames() {
        ArrayList<String> usernames = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(usuarisPath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                if (row.length > 0) {
                    usernames.add(row[0]);
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

}
