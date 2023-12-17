package persistencia.classes;

import java.io.*;
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

    // ---------------------------------------------------------------------------- //

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


    public ArrayList<String> obtenirPasswordUsuari(String username) {
        ArrayList<String> contrasenyas = new ArrayList<>();
        File file = path.toFile();

        if (!file.exists()) {
            return contrasenyas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // asumim que el format de cada linia sera "usuario,contraseña"
                String[] userData = line.split(",");
                if (userData.length > 1 && userData[0].equals(username)) {
                    contrasenyas.add(userData[1]); // fiquem el password a la llista si coincideix el username
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contrasenyas;
    }

    public void eliminarUsuari(String nomUsuari) throws IOException {
        File inputFile = path.toFile();
        ArrayList<String> users = new ArrayList<>();

        // llegeix l'arxiu i guarda els usuarios en una llista, excepte el que volem eliminar
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // asumim que cada linia tindra el forma "usuario,contraseña"
                String[] userData = currentLine.split(",");
                if (!userData[0].equals(nomUsuari)) {
                    users.add(currentLine);
                }
            }
        }

        // tornem a escriu l'arxiu com una llista actualutzada
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            for (String userLine : users) {
                writer.write(userLine);
                writer.newLine();
            }
        }
    }
}

