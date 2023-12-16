package persistencia.classes;

import com.opencsv.*;

import java.io.FileWriter;
import java.io.IOException;

public class GestorUsuaris {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String usuarisPath = "../../DATA/usuaris.csv";
    private String relacioUsuariAlfabetPath = "../../DATA/relacioUsuariAlfabet.csv";

    public void crearUsuari(String username, String contrasenya) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(usuarisPath)))  {
            String[] usuari = { username, contrasenya};
            writer.writeNext(usuari);
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
