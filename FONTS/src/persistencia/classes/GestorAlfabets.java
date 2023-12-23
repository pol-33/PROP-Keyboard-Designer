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

/**
 * Classe GestorAlfabets. Gestiona els alfàbets emmagatzemats en arxius CSV.
 */
public class GestorAlfabets {

    // ---------------------------------------------------------------------------- //
    //                                   Atributs
    // ---------------------------------------------------------------------------- //
    private final String alfabetsPath = "../../DATA/alfabet.csv";
    private final String relacioUsuariAlfabetPath = "../../DATA/relacioUsuariAlfabet.csv";

    private final String relacioAlfabetEntradaPath = "../../DATA/relacioAlfabetEntrada.csv";

    // ---------------------------------------------------------------------------- //
    //                                   Mètodes
    // ---------------------------------------------------------------------------- //


    /**
     * Crea un nou alfabet i l'emmagatzema en un arxiu CSV.
     * @param username Nom d'usuari.
     * @param idAlfabet Identificador de l'alfabet.
     * @param nom Nom de l'alfabet.
     * @param lletres Llista de caràcters de l'alfabet.
     */
    public void crearAlfabet(String username, Integer idAlfabet, String nom, ArrayList<Character> lletres) {
        //Creem l'alfabet al fitxer alfabet.csv
        try (CSVWriter writer = new CSVWriter(new FileWriter(alfabetsPath, true)))  {
            String lletresString = convertirArrayListToString(lletres);
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


    /**
     * Crea un nou alfabet a partir d'un array.
     * @param alfabet Array amb la informació de l'alfabet.
     */
    public void crearAlfabet(String[] alfabet) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(alfabetsPath, true))) {
            writer.writeNext(alfabet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carrega un alfabet específic.
     * @param idAlfabet Identificador de l'alfabet a carregar.
     * @return Array de Strings amb la informació de l'alfabet.
     */
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


    /**
     * Carrega tots els alfàbets d'un usuari.
     * @param username Nom d'usuari.
     * @return ArrayList amb la informació de cada alfabet.
     */
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


    /**
     * Elimina un alfabet específic dels arxius CSV.
     * @param idAlfabet Identificador de l'alfabet a eliminar.
     */
    public ArrayList<Integer> eliminarAlfabet(Integer idAlfabet) {
        List<String[]> updatedRows = new ArrayList<>();

        //Eliminem l'alfabet
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


        //Eliminem la relacio usuariAlfabet
        updatedRows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(relacioUsuariAlfabetPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row.length > 0 && !row[1].equals(String.valueOf(idAlfabet))) {
                    updatedRows.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(relacioUsuariAlfabetPath))) {
            writer.writeAll(updatedRows);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Retornem les entrades de l'alfabet
        ArrayList<Integer> idsEntrades = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(relacioAlfabetEntradaPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row.length > 0 && row[0].equals(String.valueOf(idAlfabet))) {
                    idsEntrades.add(Integer.valueOf(row[1]));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return idsEntrades;
    }

    /**
     * Elimina un alfabet específic dels arxius CSV de forma soft.
     * @param idAlfabet Identificador de l'alfabet a eliminar.
     */
    public void eliminarAlfabetSoft(Integer idAlfabet) {
        List<String[]> updatedRows = new ArrayList<>();

        //Eliminem l'alfabet
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

    /**
     * Carrega l'identificador màxim dels alfàbets.
     * @return Identificador màxim dels alfàbets.
     */
    public Integer carregarIdMaxim() {
        Integer idMax = 0;
        try (CSVReader reader = new CSVReader(new FileReader(alfabetsPath))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                Integer id = Integer.valueOf(row[0]);
                if (id > idMax) idMax = id;
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return idMax;
    }


    /**
     * Converteix una llista d'objectes Character en una cadena de text.
     * Cada caràcter es separa per un punt.
     * @param llista ArrayList de Character a convertir.
     * @return String resultant de la conversió.
     */
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


    /**
     * Converteix un array de Strings en una única cadena de text.
     * Cada element de l'array es separa per una coma i un espai.
     * @param array Array de Strings a convertir.
     * @return String resultant de la conversió.
     */
    private static String convertirArrayAString(String[] array) {
        return String.join(",", array);
    }

}
