package persistencia.classes;

import java.lang.reflect.Array;

public class GestorTeclats {
    private String teclatPath = "../../DATA/teclat.csv";
    private String relacioEntradaTeclatPath = "../../DATA/relacioEntradaTeclat.csv";

    public void crearTeclat(Integer idEntrada, Integer idTeclat, String nom, Integer numFiles, Integer numColumnes, Array<String> distribucio) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(teclatPath, true))) {
            // Convertir Array<String> a String pel el CSV
            String distribucioString = "";
            int length = Array.getLength(distribucio);
            for (int i = 0; i < length; i++) {
                distribucioString += Array.get(distribucio, i);
                if (i < length - 1) {
                    distribucioString += ",";
                }
            }

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

    public List<String[]> carregarTeclats(Integer idEntrada) {
        List<String[]> teclats = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(relacioEntradaTeclatPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row[0].equals(idEntrada.toString())) {
                    teclats.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teclats;
    }


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
}
