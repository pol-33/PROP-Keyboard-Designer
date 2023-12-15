package persistencia.classes;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestorUsuaris {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String filePath = "../../DATA/usuaris.csv";
    private Path path = Paths.get(filePath);

    public ArrayList<String> obtenirUsernames(String username) {
        ArrayList<String> usernames = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                usernames.add(values[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usernames;
    }
}
