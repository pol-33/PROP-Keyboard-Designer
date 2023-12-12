package persistencia.controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;

    private ControladorPersistencia() {
    }

    /**
     * Mètode per obtenir la instància singleton del controlador.
     * @return La instància única del ControladorPersistencia.
     */
    public static ControladorPersistencia obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPersistencia();
        }
        return ctrl;
    }

    //--------------------------------Usuari---------------------------------//


    /**
     * Obté una llista de noms d'usuari existents en l'arxiu.
     * @return ArrayList amb els noms dels usuaris.
     */
    public ArrayList<String> getUsuarisExistents() {
        ArrayList<String> usuaris = new ArrayList<>();
        File file = new File("usuaris.csv");

        if (!file.exists()) {
            return usuaris;  // Retorna sense res si no exiteix el docu
        }

        // Llegeix l'arxiu i extreu les dades dels usuaris
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Asumim que cada linia té el format "usuario,contraseña"
                String[] userData = line.split(",");
                if (userData.length > 0) {
                    usuaris.add(userData[0]); // només fiquem l'usuari
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuaris;
    }


    /**
     * Obté un mapa de noms d'usuari i les seves contrasenyes.
     * @return HashMap on les claus són noms d'usuari i els valors són contrasenyes.
     */
    public HashMap<String, String> getUsuarisContrasenyes() {
        HashMap<String, String> usuarisContrasenyes = new HashMap<>();
        File file = new File("usuaris.csv");

        if (!file.exists()) {
            return usuarisContrasenyes;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Asumim que cada linia tindra el format "usuario,contraseña"
                String[] userData = line.split(",");
                if (userData.length > 1) {
                    usuarisContrasenyes.put(userData[0], userData[1]); // fiquem al hashmap la dulpa nomusuari-contrasenya
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarisContrasenyes;
    }

    /**
     * Guarda un nou usuari i la seva contrasenya a l'arxiu.
     * @param nomUsuari El nom de l'usuari a guardar.
     * @param contrasenya La contrasenya de l'usuari a guardar.
     * @throws IOException Si hi ha un error en l'escriptura de l'arxiu.
     */
    public void guardarUsuari(String nomUsuari, String contrasenya) throws IOException {
        File file = new File("usuaris.csv");
        // mirar que existeixi el document
        if (!file.exists()) {
            file.createNewFile();
        }

        // Usem BufferedWriter y FileWriter per escriure en l'arxiu
        // Usem 'true' en FileWriter per usar el mode adicion
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String line = nomUsuari + "," + contrasenya;
            bw.write(line);
            bw.newLine();  // Asegura que cada usuari es guardi a una linia diferent
        }
    }


    /**
     * Elimina un usuari de l'arxiu.
     * @param nomUsuari El nom de l'usuari a eliminar.
     * @throws IOException Si hi ha un error en la lectura o escriptura de l'arxiu.
     */
    public void eliminarUsuari(String nomUsuari) throws IOException {
        File inputFile = new File("usuaris.csv");
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


    //--------------------------------Alfabet---------------------------------//

    /**
     * Obté una llista d'IDs d'alfabets associats amb un usuari específic.
     * @param nomUsuari El nom de l'usuari pel qual es busquen els alfabets.
     * @return ArrayList<Integer> Llista d'IDs d'alfabets associats amb l'usuari.
     * @throws IOException Si es produeix un error durant la lectura de l'arxiu.
     */
    public ArrayList<Integer> getAlfabetsDeUsuari(String nomUsuari) throws IOException {
        ArrayList<Integer> idAlfabets = new ArrayList<>();
        File file = new File("usuari_alfabets.csv");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(nomUsuari)) {
                        idAlfabets.add(Integer.parseInt(data[1]));
                    }
                }
            }
        }
        return idAlfabets;
    }

    /*
    alfabets.csv
    1,Español,a.b.c.d.e.f.g.h.i.j.k.l.m.n.ñ.o.p.q.r.s.t.u.v.w.x.y.z,101.102
    2,English,a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z,201.202
    3,Français,a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z,301.302

    retorna si id=2
    "2,English,a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z,201.202"
     */

    /**
     * Obté les dades d'un alfabet específic a partir del seu identificador.
     * @param id Identificador de l'alfabet a obtenir.
     * @return String amb la informació de l'alfabet, o un string buit si no es troba.
     */
    public String getAlfabet(Integer id) {
        File file = new File("alfabets.csv");

        if (!file.exists()) {
            return "";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Asumim que té el format "id,nombre,lletra1.lletra2. ...,idEntrada1.idEntrada2. ..."
                String[] alfabetData = line.split(",");
                if (alfabetData.length > 0 && alfabetData[0].equals(String.valueOf(id))) {
                    // retorna la linia si troba l'alfabet
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";  //si no troba res
    }

    /**
     * Guarda un nou alfabet a l'arxiu CSV.
     * @param id Identificador de l'alfabet.
     * @param nomAlfabet Nom de l'alfabet.
     * @param lletres Llista de caràcters que formen l'alfabet.
     * @param idEntrades Llista d'identificadors d'entrades associats a l'alfabet.
     * @throws IOException Si hi ha un error en l'escriptura de l'arxiu.
     */
    public void guardarAlfabet(Integer id, String nomAlfabet, ArrayList<Character> lletres, ArrayList<Integer> idEntrades) throws IOException {
        File file = new File("alfabets.csv");
        if (!file.exists()) {
            file.createNewFile();
        }

        // Convertir la lista de letras y la lista de identificadores de entradas a cadenas de texto separados por puntos -> transforma [a,b,c] a "a.b.c"
        String lletresStr = lletres.stream().map(String::valueOf).collect(Collectors.joining("."));
        String idEntradesStr = idEntrades.stream().map(String::valueOf).collect(Collectors.joining("."));

        // Format: id,nombre,lletra1.lletra2. ...,idEntrada1.idEntrada2. ...
        String line = id + "," + nomAlfabet + "," + lletresStr + "," + idEntradesStr;

        // Escriu en el csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(line);
            bw.newLine();
        }
    }


    /**
     * Elimina un alfabet de l'arxiu CSV a partir del seu identificador.
     * @param idAlfabet Identificador de l'alfabet a eliminar.
     * @throws IOException Si hi ha un error en la lectura o escriptura de l'arxiu.
     */
    public void eliminarAlfabet(Integer idAlfabet) throws IOException {
        File inputFile = new File("alfabets.csv");
        ArrayList<String> alfabets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // cada linia tindra el format "id,nombre,lletra1.lletra2,..."
                String[] alfabetData = currentLine.split(",");
                Integer id = Integer.parseInt(alfabetData[0]);
                if (!id.equals(idAlfabet)) {
                    alfabets.add(currentLine);
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            for (String alfabetLine : alfabets) {
                writer.write(alfabetLine);
                writer.newLine();
            }
        }
    }

    //--------------------------------Entrada---------------------------------//
    public ArrayList<String> getEntradesUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

    public String getEntrada(Integer id) {
        return new String();
    }

    public void guardarText(Integer id, String nomEntrada, String text, ArrayList<Integer> idTeclats) {
    }

    public void guardarLPF(Integer id, String nomEntrada, HashMap<String, Integer> lpf, ArrayList<Integer> idTeclats) {
    }

    public void eliminarEntrada(Integer id) {
    }

    //--------------------------------Teclat---------------------------------//

    /**
     * Obté una llista d'IDs de teclats associats amb un usuari específic.
     * @param nomUsuari El nom de l'usuari pel qual es busquen els teclats.
     * @return ArrayList<Integer> Llista d'IDs de teclats associats amb l'usuari.
     * @throws IOException Si es produeix un error durant la lectura de l'arxiu.
     */
    public ArrayList<Integer> getTeclatsUsuari(String nomUsuari) throws IOException {
        ArrayList<Integer> idTeclats = new ArrayList<>();
        File file = new File("usuari_teclats.csv");

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(nomUsuari)) {
                        idTeclats.add(Integer.parseInt(data[1]));
                    }
                }
            }
        }
        return idTeclats;
    }


    /**
     * Obté les dades d'un teclat específic a partir del seu identificador.
     * @param id Identificador del teclat a obtenir.
     * @return String amb la informació del teclat, o un string buit si no es troba.
     */
    public String getTeclat(Integer id) {
        File file = new File("teclats.csv");

        if (!file.exists()) {
            return "";
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Asumim que cada linia tindra el format "id,nom,numFiles,numColumnes,distribucio,idEntrada"
                String[] teclatData = line.split(",");
                if (teclatData.length > 0 && teclatData[0].equals(String.valueOf(id))) {
                    // retorna la linia si troba el teclat
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; //si no troba res
    }

    /**
     * Guarda un nou teclat a l'arxiu CSV.
     * @param id Identificador del teclat.
     * @param nom Nom del teclat.
     * @param numFiles Número de files del teclat.
     * @param numColumnes Número de columnes del teclat.
     * @param distribucio Llista de caràcters que formen la distribució del teclat.
     * @param idEntrada Identificador de l'entrada associada al teclat.
     * @throws IOException Si hi ha un error en l'escriptura de l'arxiu.
     */
    public void guardarTeclat(Integer id, String nom, Integer numFiles, Integer numColumnes, ArrayList<Character> distribucio, Integer idEntrada) throws IOException {
        File file = new File("teclats.csv");
        if (!file.exists()) {
            file.createNewFile();
        }

        //transformar la distribucio de carcaters a una cadea de text
        String distribucioStr = distribucio.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("."));

        // Format: id,nom,numFiles,numColumnes,distribucio,idEntrada
        String line = id + "," + nom + "," + numFiles + "," + numColumnes + "," + distribucioStr + "," + idEntrada;

        // Escriure en el csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(line);
            bw.newLine(); //genera una nova linia
        }
    }


    /**
     * Elimina un teclat de l'arxiu CSV a partir del seu identificador.
     * @param idTeclat Identificador del teclat a eliminar.
     * @throws IOException Si hi ha un error en la lectura o escriptura de l'arxiu.
     */
    public void eliminarTeclat(Integer idTeclat) throws IOException {
        File inputFile = new File("teclats.csv");
        ArrayList<String> teclats = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // asumim que cada lina tindra el format"id,nom,numFiles,numColumnes,idEntrada,distribucio"
                String[] teclatData = currentLine.split(",");
                Integer id = Integer.parseInt(teclatData[0]);
                if (!id.equals(idTeclat)) {
                    teclats.add(currentLine);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            for (String teclatLine : teclats) {
                writer.write(teclatLine);
                writer.newLine();
            }
        }
    }

    //--------------------------------Relació---------------------------------//


    /**
     * Afegeix una nova relació entre un usuari i un teclat a l'arxiu CSV.
     * @param nomUsuari El nom de l'usuari.
     * @param idTeclat L'identificador del teclat associat amb l'usuari.
     * @throws IOException Si es produeix un error durant l'escriptura a l'arxiu.
     */
    public void afegirRelacionUsuariTeclat(String nomUsuari, Integer idTeclat) throws IOException {
        File file = new File("usuari_teclats.csv");
        if (!file.exists()) {
            file.createNewFile();
        }

        // ficar la nova relacio al csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String line = nomUsuari + "," + idTeclat;
            bw.write(line);
            bw.newLine();
        }
    }

    /**
     * Afegeix una nova relació entre un usuari i un alfabet a l'arxiu CSV.
     *
     * @param nomUsuari El nom de l'usuari.
     * @param idAlfabet L'identificador de l'alfabet associat amb l'usuari.
     * @throws IOException Si es produeix un error durant l'escriptura a l'arxiu.
     */
    public void afegirRelacionUsuariAlfabet(String nomUsuari, Integer idAlfabet) throws IOException {
        File file = new File("usuari_alfabets.csv");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String line = nomUsuari + "," + idAlfabet;
            bw.write(line);
            bw.newLine();
        }
    }

    /**
     * Elimina la relació entre un usuari i un teclat del fitxer CSV.
     * @param nomUsuari El nom de l'usuari implicat en la relació a eliminar.
     * @param idTeclat Identificador del teclat implicat en la relació a eliminar.
     * @throws IOException Si hi ha un error en la lectura o escriptura de l'arxiu.
     */
    public void eliminarRelacionUsuariTeclat(String nomUsuari, Integer idTeclat) throws IOException {
        File inputFile = new File("usuari_teclats.csv");

        if (!inputFile.exists()) {
            System.out.println("ERROR: no existeix el document");
            return;
        }

        List<String> relacionsModificades;
        relacionsModificades = new ArrayList<>();

        // LLEGIR EL DOCUMENT I GUARDAR LES RELACIONS QUE NO ES VOLEN ELIMINAR
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (!(data[0].equals(nomUsuari) && data[1].equals(String.valueOf(idTeclat)))) {
                    relacionsModificades.add(currentLine);
                }
            }
        }

        //  Reescriu l'arxiu amb les relacions modificades
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            for (String linea : relacionsModificades) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    /**
     * Elimina la relació entre un usuari i un alfabet del fitxer CSV.
     * @param nomUsuari El nom de l'usuari implicat en la relació a eliminar.
     * @param idAlfabet Identificador de l'alfabet implicat en la relació a eliminar.
     * @throws IOException Si es produeix un error durant la lectura o escriptura de l'arxiu.
     */
    public void eliminarRelacionUsuariAlfabet(String nomUsuari, Integer idAlfabet) throws IOException {
        File inputFile = new File("usuari_alfabets.csv");
        List<String> relacionsModificades = new ArrayList<>();

        if (!inputFile.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (!(data[0].equals(nomUsuari) && data[1].equals(String.valueOf(idAlfabet)))) {
                    relacionsModificades.add(currentLine);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            for (String linea : relacionsModificades) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }

}

