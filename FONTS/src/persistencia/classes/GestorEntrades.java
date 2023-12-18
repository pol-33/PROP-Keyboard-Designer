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

/**
 * Classe GestorEntrades. Gestiona les entrades emmagatzemades en arxius CSV.
 */
public class GestorEntrades {
    // Rutes als arxius CSV per a entrades i les seves relacions amb l'alfabet.
    private String entradesPath = "../../DATA/Entrades.csv";
    private String relacioEntradaAlfabetPath = "../../DATA/relacioEntradaTeclat.csv";

    /**
     * Crea una nova entrada i l'emmagatzema en un arxiu CSV.
     * @param idAlfabet Identificador de l'alfabet associat.
     * @param idEntrada Identificador de l'entrada.
     * @param nom Nom de l'entrada.
     * @param lpf HashMap amb dades específiques (pot ser null).
     * @param text Text associat a l'entrada.
     */
    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        // Escriure l'entrada en Entrades.csv
        try (CSVWriter writer = new CSVWriter(new FileWriter(entradesPath, true))) {
            String tipus = lpf != null ? "0" : "1"; // 0 per a LPF, 1 per a text
            String lpfString = lpf != null ? lpf.toString() : "";
            String[] entrada = { idEntrada.toString(), nom, tipus, lpfString, text };

            writer.writeNext(entrada);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escriure la relació entre l'entrada i l'alfabet en relacioEntradaTeclat.csv
        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioEntradaAlfabetPath, true))) {
            String[] relacio = { idAlfabet.toString(), idEntrada.toString() };

            writer.writeNext(relacio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega les entrades associades a un alfabet específic.
     * @param idAlfabet Identificador de l'alfabet.
     * @return ArrayList de arrays de Strings representant les entrades.
     */
    public ArrayList<String[]> carregarEntrades(Integer idAlfabet) {
        ArrayList<String[]> entrades = new ArrayList<>();
        // Llegir relacioEntradaTeclat.csv per trobar entrades associades a idAlfabet
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

    /**
     * Elimina una entrada específica dels arxius CSV.
     * @param idEntrada Identificador de l'entrada a eliminar.
     */
    public void eliminarEntrada(Integer idEntrada) {
        List<String[]> updatedEntrades = new ArrayList<>();
        List<String[]> updatedRelacions = new ArrayList<>();

        // Actualitzar Entrades.csv eliminant l'entrada especificada
        try (CSVReader reader = new CSVReader(new FileReader(entradesPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (!row[0].equals(idEntrada.toString())) {
                    updatedEntrades.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(entradesPath))) {
            writer.writeAll(updatedEntrades);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Actualitzar relacioEntradaTeclat.csv eliminant la relació de l'entrada especificada
        try (CSVReader reader = new CSVReader(new FileReader(relacioEntradaAlfabetPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (!row[1].equals(idEntrada.toString())) {
                    updatedRelacions.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioEntradaAlfabetPath))) {
            writer.writeAll(updatedRelacions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
