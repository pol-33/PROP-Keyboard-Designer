package persistencia.classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorEntrades {
    private String entradesPath = "../../DATA/Entrades.csv";
    private String relacioEntradaAlfabetPath = "../../DATA/relacioEntradaTeclat.csv";

    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(entradesPath, true))) {
            String tipus = text.isEmpty() ? "0" : "1"; // 0 per LPF, 1 per text
            String lpfString = lpf.toString(); // Convertir HashMap a String

            String[] entrada = { idEntrada.toString(), nom, tipus, lpfString, text };
            writer.writeNext(entrada);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioEntradaAlfabetPath, true))) {
            String[] relacio = { idAlfabet.toString(), idEntrada.toString() };
            writer.writeNext(relacio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> carregarEntrades(Integer idAlfabet) {
        ArrayList<String[]> entrades = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(relacioEntradaAlfabetPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idAlfabet.toString())) {
                    entrades.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return entrades;
    }

    public void eliminarEntrada(Integer idEntrada) {
        List<String[]> updatedRows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(entradesPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (!row[0].equals(idEntrada.toString())) {
                    updatedRows.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(entradesPath))) {
            writer.writeAll(updatedRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
