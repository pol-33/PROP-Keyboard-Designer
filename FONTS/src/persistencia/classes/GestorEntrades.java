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

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String entradesPath = "../../DATA/entrada.csv";
    private String relacioAlfabetEntradaPath = "../../DATA/relacioAlfabetEntrada.csv";

    private String relacioEntradaTeclatPath = "../../DATA/relacioEntradaTeclat.csv";

    String nom, text;
    HashMap<String, Integer> lpf;


    // ---------------------------------------------------------------------------- //
    //                                   Mètodes
    // ---------------------------------------------------------------------------- //

    /**
     * Crea una nova entrada i l'emmagatzema en un arxiu CSV.
     * @param idAlfabet Identificador de l'alfabet associat.
     * @param idEntrada Identificador de l'entrada.
     * @param nom       Nom de l'entrada.
     * @param lpf       HashMap amb dades específiques (pot ser null).
     * @param text      Text associat a l'entrada.
     */
    public void crearEntrada(Integer idAlfabet, Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        // Escriure l'entrada en Entrades.csv
        try (CSVWriter writer = new CSVWriter(new FileWriter(entradesPath, true))) {
            String tipus = lpf != null ? "0" : "1"; // 0 per a LPF, 1 per a text
            String lpfString = lpf != null ? lpf.toString() : "";
            String[] entrada = {idEntrada.toString(), nom, tipus, lpfString, text};

            writer.writeNext(entrada);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escriure la relació entre l'entrada i l'alfabet en relacioEntradaTeclat.csv
        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioAlfabetEntradaPath, true))) {
            String[] relacio = {idAlfabet.toString(), idEntrada.toString()};

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
        ArrayList<String> idsEntradesAssociades = new ArrayList<>();
        ArrayList<String[]> entradesCompletes = new ArrayList<>();

        // Leer relacioAlfabetEntradaPath para encontrar entradas asociadas a idAlfabet
        try (CSVReader reader = new CSVReader(new FileReader(relacioAlfabetEntradaPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idAlfabet.toString())) {
                    idsEntradesAssociades.add(row[1]); // Suponiendo que el id de la entrada está en la segunda columna
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        // Leer entradesPath para recopilar la información completa de cada entrada asociada
        try (CSVReader reader = new CSVReader(new FileReader(entradesPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (idsEntradesAssociades.contains(row[0])) { // Suponiendo que el id de la entrada está en la primera columna de Entrada.csv
                    entradesCompletes.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return entradesCompletes;
    }

    /**
     * Elimina una entrada específica dels arxius CSV corresponents.
     * Això inclou l'arxiu d'entrades general i els arxius de relació.
     * @param idEntrada Identificador de l'entrada a eliminar.
     */
    public void eliminarEntrada(Integer idEntrada) {
        // Actualizar Entrades.csv eliminant la entrada especificada
        actualitzarArchivoCSV(entradesPath, idEntrada, 0);

        // Actualizar relacioEntradaTeclat.csv eliminant la relació de la entrada especificada
        actualitzarArchivoCSV(relacioEntradaTeclatPath, idEntrada, 0);

        // Actualizar relacioAlfabetEntradaPath eliminant la relació de la entrada especificada
        actualitzarArchivoCSV(relacioAlfabetEntradaPath, idEntrada, 1);
    }

    /**
     * Actualitza un arxiu CSV eliminant una fila específica.
     * Aquest mètode és genèric i pot ser utilitzat per qualsevol arxiu CSV.
     * @param filePath Ruta de l'arxiu CSV a actualitzar.
     * @param idEntrada Identificador de l'entrada a eliminar de l'arxiu.
     * @param idColumnIndex Índex de la columna que conté l'identificador a comparar.
     */
    private void actualitzarArchivoCSV(String filePath, Integer idEntrada, int idColumnIndex) {
        List<String[]> updatedRows = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (!row[idColumnIndex].equals(idEntrada.toString())) {
                    updatedRows.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return;
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(updatedRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Actualitza una entrada específica en els arxius CSV.
     * @param idEntrada Identificador de l'entrada a actualitzar.
     * @param nom       Nou nom per a l'entrada.
     * @param lpf       Nou HashMap amb dades específiques (pot ser null).
     * @param text      Nou text associat a l'entrada.
     */
    public void actualizarEntrada(Integer idEntrada, String nom, HashMap<String, Integer> lpf, String text) {
        List<String[]> entradasActualizadas = new ArrayList<>();
        boolean entradaEncontrada = false;

        try (CSVReader reader = new CSVReader(new FileReader(entradesPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idEntrada.toString())) {
                    String tipus = determinarTipo(row); // Determina si es text o LPF basat en la fila existent
                    String lpfString = "";
                    String texto = "";

                    if ("0".equals(tipus) && lpf != null) { // Actualizar com LPF
                        lpfString = lpf.toString();
                        texto = ""; // Asumim que el text esta buit quan es LPF
                    } else if ("1".equals(tipus)) { // Actualizar com text
                        lpfString = ""; // Asumiendo que lpf  esta buit quan tenim un texto
                        texto = text;
                    }

                    String[] entradaActualizada = {idEntrada.toString(), nom, tipus, lpfString, texto};
                    entradasActualizadas.add(entradaActualizada);
                    entradaEncontrada = true;
                } else {
                    entradasActualizadas.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        if (entradaEncontrada) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(entradesPath))) {
                writer.writeAll(entradasActualizadas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Determina el tipus d'una entrada basant-se en la informació de la fila.
     * @param row Fila de l'arxiu CSV que representa una entrada.
     * @return El tipus d'entrada ('0' per a LPF, '1' per a text).
     */
    private String determinarTipo(String[] row) {
        return row[2]; // Suposant que la columna 3 emmagatzema el tipus ('0' per a LPF, '1' per a text)
    }
}

