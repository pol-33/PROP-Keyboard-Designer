package persistencia.classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe GestorTeclats. Gestiona els teclats emmagatzemats en arxius CSV.
 */
public class GestorTeclats {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private String teclatPath = "../../DATA/teclat.csv";
    private String relacioEntradaTeclatPath = "../../DATA/relacioEntradaTeclat.csv";

    // ---------------------------------------------------------------------------- //
    //                                   Mètodes
    // ---------------------------------------------------------------------------- //

    /**
     * Crea un nou teclat i l'emmagatzema en un arxiu CSV.
     * @param idEntrada Identificador de l'entrada associada.
     * @param idTeclat Identificador del teclat.
     * @param nom Nom del teclat.
     * @param numFiles Número de files del teclat.
     * @param numColumnes Número de columnes del teclat.
     * @param distribucio Llista amb la distribució de teclas.
     */
    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(teclatPath, true))) {
            // Conversión de ArrayList<String> a String per al CSV
            String distribucioString = String.join(",", distribucio);
            String[] teclatData = { idTeclat.toString(), nom, numFiles.toString(), numColumnes.toString(), distribucioString };
            writer.writeNext(teclatData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioEntradaTeclatPath, true))) {
            String[] relacioData = { idEntrada.toString(), idTeclat.toString() };
            writer.writeNext(relacioData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carrega els teclats associats a una entrada específica.
     * @param idEntrada Identificador de l'entrada.
     * @return ArrayList de arrays de Strings representant els teclats.
     */
    public ArrayList<String[]> carregarTeclats(Integer idEntrada) {
        ArrayList<String> idsTeclatsAssociats = new ArrayList<>();
        ArrayList<String[]> teclatsComplets = new ArrayList<>();

        // Leer relacioEntradaTeclatPath para encontrar teclados asociados a idEntrada
        try (CSVReader reader = new CSVReader(new FileReader(relacioEntradaTeclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idEntrada.toString())) {
                    idsTeclatsAssociats.add(row[1]); // Suponiendo que el id del teclado está en la segunda columna
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        // Leer teclatsPath para recopilar la información completa de cada teclado asociado
        try (CSVReader reader = new CSVReader(new FileReader(teclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (idsTeclatsAssociats.contains(row[0])) { // Suponiendo que el id del teclado está en la primera columna de teclats.csv
                    teclatsComplets.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return teclatsComplets;
    }


    /**
     * Elimina un teclat específic dels arxius CSV.
     * @param idTeclat Identificador del teclat a eliminar.
     */
    public void eliminarTeclat(Integer idTeclat) {
        // Eliminar teclat de teclat.csv
        List<String[]> updatedTeclats = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(teclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (!row[0].equals(idTeclat.toString())) {
                    updatedTeclats.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(teclatPath))) {
            writer.writeAll(updatedTeclats);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Eliminar relacions amb relacioEntradaTeclat.csv
        List<String[]> updatedRelaciones = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(relacioEntradaTeclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (!row[1].equals(idTeclat.toString())) {
                    updatedRelaciones.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioEntradaTeclatPath))) {
            writer.writeAll(updatedRelaciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void actualizarNumFilesTeclat(Integer idTeclat, Integer numFiles) {
        actualizarTeclat(idTeclat, null, numFiles, null, null);
    }

    public void actualizarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {
        actualizarTeclat(idTeclat, null, null, numColumnes, null);
    }

    public void actualizarDistribucioTeclat(Integer idTeclat, ArrayList<String> distribucio) {
        actualizarTeclat(idTeclat, null, null, null, distribucio);
    }

    private void actualizarTeclat(Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<String> distribucio) {
        List<String[]> teclatsActualizados = new ArrayList<>();
        boolean teclatEncontrado = false;

        try (CSVReader reader = new CSVReader(new FileReader(teclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idTeclat.toString())) {
                    String distribucioString = distribucio != null ? String.join(",", distribucio) : row[4];
                    String[] teclatActualizado = {
                            idTeclat.toString(),
                            nom != null ? nom : row[1],
                            numFiles != null ? numFiles.toString() : row[2],
                            numColumnes != null ? numColumnes.toString() : row[3],
                            distribucioString
                    };
                    teclatsActualizados.add(teclatActualizado);
                    teclatEncontrado = true;
                } else {
                    teclatsActualizados.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        if (teclatEncontrado) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(teclatPath))) {
                writer.writeAll(teclatsActualizados);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
