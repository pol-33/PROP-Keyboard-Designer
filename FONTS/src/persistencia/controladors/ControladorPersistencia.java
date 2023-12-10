package persistencia.controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.stream.Collectors;

public class ControladorPersistencia {
    
    private static ControladorPersistencia ctrl;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    
    private ControladorPersistencia() {
    }

    //Metode per obtenir l'instància singleton
    public static ControladorPersistencia obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorPersistencia();
        }
        return ctrl;
    }

    //--------------------------------Usuari---------------------------------//
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
    public ArrayList<String> getAlfabetsUsuari(String nomUsuari) {
        return new ArrayList<>();
    }


    /*
    alfabets.csv
    1,Español,a.b.c.d.e.f.g.h.i.j.k.l.m.n.ñ.o.p.q.r.s.t.u.v.w.x.y.z,101.102
    2,English,a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z,201.202
    3,Français,a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z,301.302

    retorna si id=2
    "2,English,a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z,201.202"
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
    public ArrayList<String> getTeclatsUsuari(String nomUsuari) {
        return new ArrayList<>();
    }

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
}
