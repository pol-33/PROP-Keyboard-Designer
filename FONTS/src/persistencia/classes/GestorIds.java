package persistencia.classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorIds {
    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String idsMaximsPath = "../../DATA/idsMaxims.csv";

    // ---------------------------------------------------------------------------- //
    //                                   Mètodes
    // ---------------------------------------------------------------------------- //
    public Integer carregarIdMaxAlfabet() {
        Integer idMax = 0;
        // Llegeix relacioEntradaTeclatPath per trobar el teclat associat a idEntrada
        try (CSVReader reader = new CSVReader(new FileReader(idsMaximsPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                idMax = Integer.valueOf(row[0]);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return idMax;
    }

    public Integer carregarIdMaxEntrada() {
        Integer idMax = 0;
        // Llegeix relacioEntradaTeclatPath per trobar el teclat associat a idEntrada
        try (CSVReader reader = new CSVReader(new FileReader(idsMaximsPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                idMax = Integer.valueOf(row[1]);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return idMax;
    }

    public Integer carregarIdMaxTeclat() {
        Integer idMax = 0;
        // Llegeix relacioEntradaTeclatPath per trobar el teclat associat a idEntrada
        try (CSVReader reader = new CSVReader(new FileReader(idsMaximsPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                idMax = Integer.valueOf(row[2]);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return idMax;
    }

    public void augmentarIdAlfabet() {
        String[] row;
        List<String[]> rows;

        try (CSVReader reader = new CSVReader(new FileReader(idsMaximsPath))) {
            rows = reader.readAll();
            row = rows.get(0);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        row[0] = String.valueOf(Integer.valueOf(row[0]) + 1);
        rows.set(0, row);
        try (CSVWriter writer = new CSVWriter(new FileWriter(idsMaximsPath))) {
            writer.writeAll(rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void augmentarIdEntrada() {
        String[] row;
        List<String[]> rows;

        try (CSVReader reader = new CSVReader(new FileReader(idsMaximsPath))) {
            rows = reader.readAll();
            row = rows.get(0);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        row[1] = String.valueOf(Integer.valueOf(row[1]) + 1);
        rows.set(0, row);
        try (CSVWriter writer = new CSVWriter(new FileWriter(idsMaximsPath))) {
            writer.writeAll(rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void augmentarIdTeclat() {
        String[] row;
        List<String[]> rows;

        try (CSVReader reader = new CSVReader(new FileReader(idsMaximsPath))) {
            rows = reader.readAll();
            row = rows.get(0);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        row[2] = String.valueOf(Integer.valueOf(row[2]) + 1);
        rows.set(0, row);
        try (CSVWriter writer = new CSVWriter(new FileWriter(idsMaximsPath))) {
            writer.writeAll(rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
