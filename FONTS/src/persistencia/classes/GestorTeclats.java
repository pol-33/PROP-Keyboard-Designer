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
    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<Character> distribucio) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(teclatPath, true))) {
            // Conversió de ArrayList<String> a String per al CSV
            String distribucioString = convertirArrayListAString(distribucio);
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

        // Llegeix relacioEntradaTeclatPath per trobar els teclats associatsnidEntrada
        try (CSVReader reader = new CSVReader(new FileReader(relacioEntradaTeclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idEntrada.toString())) {
                    idsTeclatsAssociats.add(row[1]); // suposem que el id del telcta esta en la segona columna
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        //  Llegeix teclatPath per recopilar la infom de cada teclat associat
        try (CSVReader reader = new CSVReader(new FileReader(teclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (idsTeclatsAssociats.contains(row[0])) { // Suposem que el id del telcta esta en la primera columna de teclatas.csv
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

    /**
     * Actualitza el número de files d'un teclat específic.
     * Aquest mètode delega en la funció 'actualizarTeclat' per realitzar l'actualització.
     * @param idTeclat Identificador del teclat a actualitzar.
     * @param numFiles Nou número de files que tindrà el teclat.
     */
    public void actualizarNumFilesTeclat(Integer idTeclat, Integer numFiles) {
        actualizarTeclat(idTeclat, null, numFiles, null, null);
    }

    /**
     * Actualitza el número de columnes d'un teclat específic.
     * Aquest mètode delega en la funció 'actualizarTeclat' per realitzar l'actualització.
     * @param idTeclat Identificador del teclat a actualitzar.
     * @param numColumnes Nou número de columnes que tindrà el teclat.
     */
    public void actualizarNumColumnesTeclat(Integer idTeclat, Integer numColumnes) {
        actualizarTeclat(idTeclat, null, null, numColumnes, null);
    }


    /**
     * Actualitza la distribució de les tecles d'un teclat específic.
     * Aquest mètode delega en la funció 'actualizarTeclat' per realitzar l'actualització.
     * @param idTeclat Identificador del teclat a actualitzar.
     * @param distribucio Nova distribució de tecles (llista de Strings representant la distribució).
     */
    public void actualizarDistribucioTeclat(Integer idTeclat, ArrayList<Character> distribucio) {
        actualizarTeclat(idTeclat, null, null, null, distribucio);
    }


    /**
     * Actualitza les dades d'un teclat específic en els arxius CSV.
     * Permet actualitzar el nom, número de files, columnes i distribució del teclat.
     * @param idTeclat Identificador del teclat a actualitzar.
     * @param nom Nou nom per al teclat (null per no canviar).
     * @param numFiles Nou número de files (null per no canviar).
     * @param numColumnes Nou número de columnes (null per no canviar).
     * @param distribucio Nova distribució del teclat (null per no canviar).
     */
    private void actualizarTeclat(Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, ArrayList<Character> distribucio) {
        List<String[]> teclatsActualizados = new ArrayList<>();
        boolean teclatEncontrado = false;

        try (CSVReader reader = new CSVReader(new FileReader(teclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idTeclat.toString())) {
                    String distribucioString = convertirArrayListAString(distribucio);
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

    private static String convertirArrayListAString(ArrayList<Character> arrayList) {
        // Crear un StringBuilder para construir el String resultante
        StringBuilder stringBuilder = new StringBuilder();

        // Iterar sobre el ArrayList y agregar cada carácter al StringBuilder
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append(arrayList.get(i));

            // Agregar un punto después de cada carácter, excepto el último
            if (i < arrayList.size() - 1) {
                stringBuilder.append('.');
            }
        }

        // Convertir el StringBuilder a un String y devolverlo
        return stringBuilder.toString();
    }




}
