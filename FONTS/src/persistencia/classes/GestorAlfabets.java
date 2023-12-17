package persistencia.classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorAlfabets {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String alfabetsPath = "../../DATA/alfabet.csv";
    private String relacioUsuariAlfabetPath = "../../DATA/relacioUsuariAlfabet.csv";

    public void crearAlfabet(String username, Integer idAlfabet, String nom, ArrayList<Character> lletres) {
        //Creem l'alfabet al fitxer alfabet.csv
        try (CSVWriter writer = new CSVWriter(new FileWriter(alfabetsPath, true)))  {
            String lletresString = convertirArrayListToString(lletres);
            System.out.println(lletresString);
            String[] alfabet = { String.valueOf(idAlfabet), nom, lletresString };

            writer.writeNext(alfabet);
        } catch (IOException e) {
            e.getMessage();
        }

        //creem la relació usuari alfabet
        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioUsuariAlfabetPath, true))) {
            String[] relacio = { username, String.valueOf(idAlfabet) };

            writer.writeNext(relacio);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void crearAlfabet(String[] alfabet) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(alfabetsPath, true))) {
            writer.writeNext(alfabet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] carregarAlfabet(Integer idAlfabet) {
        try (CSVReader reader = new CSVReader(new FileReader(alfabetsPath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                if (row.length > 0 && Objects.equals(row[0], String.valueOf(idAlfabet))) {
                    return row;
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<String> carregarAlfabets(String username) {
        //Obtenim els id's dels alfabets de l'usuari
        ArrayList<Integer> idAlfabets = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(relacioUsuariAlfabetPath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                if (row.length > 0 && Objects.equals(row[0], username)) {
                    idAlfabets.add(Integer.valueOf(row[1]));
                }
            }
        } catch (IOException | CsvException e) {
            e.getMessage();
        }

        //Generem l'array amb tota la informació de cada alfabet
        ArrayList<String> alfabets = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(alfabetsPath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                //Si la fila conté un alfabet de l'usuari, l'afegim
                if (row.length > 0 && idAlfabets.contains(Integer.valueOf(row[0]))) {
                    alfabets.add(convertirArrayAString(row));
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        return alfabets;
    }

    public void eliminarAlfabet(Integer idAlfabet) {
        List<String[]> updatedRows = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(alfabetsPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row.length > 0 && !row[0].equals(String.valueOf(idAlfabet))) {
                    updatedRows.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(alfabetsPath))) {
            writer.writeAll(updatedRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertirArrayListToString(ArrayList<Character> llista) {
        StringBuilder result = new StringBuilder();

        // Iterar sobre los elementos del ArrayList
        for (int i = 0; i < llista.size(); i++) {
            result.append(llista.get(i));

            // Agregar una coma si no es el último elemento
            if (i < llista.size() - 1) {
                result.append(".");
            }
        }

        return result.toString();
    }

    private static String convertirArrayAString(String[] array) {
        return String.join(", ", array);
    }

}
