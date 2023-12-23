package domini.controladors;

import domini.classes.Usuari;
import persistencia.controladors.ControladorPersistencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * Classe ControladorDomini. Gestiona la capa de domini del sistema.
 */

public class ControladorDomini {
    //Instancia singleton del Controlador de Domini
    private static ControladorDomini ctrl;
    private static ControladorPersistencia ctrlPersistencia;
    private static ControladorTeclat ctrlTeclat;
    private static ControladorEntrada ctrlEntrada;
    private static ControladorAlfabet ctrlAlfabet;

    private static Usuari usuariActiu = null;

    //-------------------------------Contructora------------------------------//
    /**
     * Constructor.
     */
    public ControladorDomini() {
        ctrlPersistencia = ControladorPersistencia.obtenirInstancia();
        ctrlTeclat = ControladorTeclat.obtenirInstancia();
        ctrlEntrada = ControladorEntrada.obtenirInstancia();
        ctrlAlfabet = ControladorAlfabet.obtenirInstancia();
    }

    //Metode per obtenir l'instància singleton
    public static ControladorDomini obtenirInstancia() {
        if (ctrl == null) {
            ctrl = new ControladorDomini();
        }
        return ctrl;
    }

    //--------------------------------Getters---------------------------------//

    /**
     * Retorna una llista de noms dels alfabets existents de l'usuari loggejat.
     * @return ArrayList<Integer> que representa la llista de noms dels alfabets.
     * @throws Exception Si l'usuari no ha inciat sessió
     */
    public ArrayList<Integer> getIdAlfabets() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus teclats");
        return ctrlAlfabet.getIdAlfabets();
    }

    /**
     * Obté els identificadors de les entrades
     * @return
     * @throws Exception
     */
    public ArrayList<Integer> getIdEntrades() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus teclats");
        return ctrlEntrada.getIdEntrades();
    }

    /**
     * Obte els identificadors dels teclats
     * @return
     * @throws Exception
     */
    public ArrayList<Integer> getIdTeclats() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus teclats");
        return ctrlTeclat.getIdTeclats();
    }
    /**
     * Retorna el nom de l'idioma de l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public String getNomAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus alfabets");
        return ctrlAlfabet.getNomAlfabet(idAlfabet);
    }

    /**
     * Retorna les lletres de l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Character> getLletresAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure els teus alfabets");
        return ctrlAlfabet.getLletresAlfabet(idAlfabet);
    }

    /**
     * Retorna les entrades associades a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Integer> getIdEntradesVinculadesAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
    }

    /**
     * Retorna el tipus de l'entrada demanada, "text" o "lpf" que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return String que representa el tipus de l'entrada. Pot ser "text" o "lpf"
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public Integer getTipusEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlEntrada.getTipusEntrada(idEntrada);
    }

    public String getTextEntrada(Integer idEntrada) throws Exception {
         if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlEntrada.getTextEntrada(idEntrada);
    }

    /**
     * Retorna la LPF de l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return HashMap<String, Integer> que representa la LPF de l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public HashMap<String, Integer> getLpfEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlEntrada.getLpfEntrada(idEntrada);
    }
    
    /**
     * Retorna el nom de l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return String que representa el nom de l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public String getNomEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlEntrada.getNomEntrada(idEntrada);
    }

    /**
     * Retorna el teclat associat a l'entrada demanada, que pertany a l'usuari loggejat.
     * @param idEntrada Identificador de l'entrada
     * @return Integer que representa l'identificador del teclat associat a l'entrada
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te una entrada amb aquest identificador
     */
    public ArrayList<Integer> getIdTeclatsVinculatsAEntrada(Integer idEntrada) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlEntrada.getIdTeclatsVinculatsAEntrada(idEntrada);
    }

    /**
     * Retorna les tecles del teclat demanat, que pertany a l'usuari loggejat.
     * @param idTeclat Identificador del teclat
     * @return ArrayList<Character> que representa les tecles del teclat
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un teclat amb aquest identificador
     */
    public ArrayList<Character> getDistribucioTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getDistribucioTeclat(idTeclat);
    }

    /**
     * Retorna el número de files del teclat demanat, que pertany a l'usuari loggejat.
     * @param idTeclat Identificador del teclat
     * @return int que representa el número de files del teclat
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un teclat amb aquest identificador
     */
    public int getFilesTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getFilesTeclat(idTeclat);
    }

    /**
     * Retorna el número de columnes del teclat demanat, que pertany a l'usuari loggejat.
     * @param idTeclat Identificador del teclat
     * @return int que representa el número de columnes del teclat
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un teclat amb aquest identificador
     */
    public int getColumnesTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getColumnesTeclat(idTeclat);
    }

    /**
     * obté el nom del teclat identificat per idTeclat
     * @param idTeclat
     * @return
     * @throws Exception
     */
    public String getNomTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getNomTeclat(idTeclat);
    }
    
    public Integer getIdEntradaVinculadaTeclat(Integer idTeclat) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        return ctrlTeclat.getIdEntradaVinculadaTeclat(idTeclat);
    }
    
    /**
     * Retorna els identificadors dels teclats associats a l'alfabet demanat, que pertany a l'usuari loggejat.
     * @param idAlfabet Identificador de l'alfabet
     * @return ArrayList<Integer> que representa els identificadors dels teclats associats a l'alfabet
     * @throws Exception Si l'usuari no ha inciat sessió
     * @throws Exception Si l'usuari no te un alfabet amb aquest identificador
     */
    public ArrayList<Integer> getTeclatsAlfabet(Integer idAlfabet) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure les teves entrades");
        if (!ctrlAlfabet.getIdAlfabets().contains(idAlfabet)) throw new Exception("L'usuari no te un alfabet amb aquest identificador");
        ArrayList<Integer> idEntrades = ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
        ArrayList<Integer> idsTeclats = new ArrayList<>();
        for (Integer idEntrada : idEntrades) {
            idsTeclats.addAll(ctrlEntrada.getIdTeclatsVinculatsAEntrada(idEntrada));
        }
        return idsTeclats;
    }

    public int getTipusTeclat(int idTeclado) throws Exception {
        return ctrlTeclat.getTipusTeclat(idTeclado);
    }

    /**
     * Comprova si hi ha un usuari amb la sessió iniciada.
     * @return boolean que representa si hi ha un usuari amb la sessió iniciada.
     */
    public boolean usuariIniciatSessio() {
        return (usuariActiu != null);
    }

    //-------------------------Private class methods-------------------------//

    /**
     * Carrega de persistencia totes les classes de l'usuari i les instancia en el domini
     * @param nomUsuari
     * @throws Exception
     */
    private void carregarInfoUsuari(String nomUsuari) throws Exception {
        //Carregar alfabets de l'usuari
        ArrayList<String> alfabetsEnCSV = ctrlPersistencia.carregarAlfabets(nomUsuari);
        carregarAlfabets(alfabetsEnCSV);


        //Carregar entrades i teclats de cada un dels alfabets
        for (String alfabetEnCSV : alfabetsEnCSV) {
            Integer idAlfabet = Integer.valueOf(alfabetEnCSV.split(",")[0]); //obtenim l'id de l'alfabet
            ArrayList<String> entradesEnCSV = ctrlPersistencia.carregarEntrades(idAlfabet);//ctrlPersistencia.carregarEntrades(Integer.valueOf(idAlfabet)); //obtenim les entrades associades a l'alfabet
            carregarEntrades(entradesEnCSV, idAlfabet); //instanciem les entrades
            for (String entradaEnCSV : entradesEnCSV) {
                Integer idEntrada = Integer.valueOf(entradaEnCSV.split(",")[0]); //obtenim l'id de l'entrada
                ArrayList<String> teclats = ctrlPersistencia.carregarTeclats(idEntrada);
                carregarTeclats(teclats, idEntrada);
            }
        }

        //Carregar ids maxims als controladors
        Integer idMaxAlfabets = ctrlPersistencia.carregarIdMaxAlfabet();
        Integer idMaxEntrades = ctrlPersistencia.carregarIdMaxEntrada();
        Integer idMaxTeclats = ctrlPersistencia.carregarIdMaxTeclat();

        ctrlAlfabet.carregarComptador(idMaxAlfabets+1);
        ctrlEntrada.carregarComptador(idMaxEntrades+1);
        ctrlTeclat.carregarComptador(idMaxTeclats+1);
    }

    /**
     * Carrega els alfabets de persistència i instancia les classses.
     * Els alfabets segueixen el següent format:
     * id,nom,lletra1.lletra2. ... .lletran
     * @param alfabets
     */
    private void carregarAlfabets(ArrayList<String> alfabets) {
        for (String alfabet : alfabets) {
            Integer id;
            String nomAlfabet;
            ArrayList<Character> lletres = new ArrayList<>();
            //Definim tots els atributs necessaris en tipus natius de JAVA
            String[] atributs = alfabet.split(",");

            id = Integer.valueOf(atributs[0]);
            nomAlfabet = atributs[1];

            String[] lletresEnString = atributs[2].split("\\.");
            for (String lletraString : lletresEnString) {
                lletres.add(lletraString.charAt(0));
            }

            //Instanciem l'alfabet
            ctrlAlfabet.carregarAlfabet(id, nomAlfabet, lletres);
        }
    }

    /**
     * Carrega les entrades de persistencia i instancia les classses.
     * "id,nom,tipus,Paraula1:Freq1.Paraula2:Freq2. ... .Paraulan:Freqn, text"
     * L'atribut tipus pot prendre el valor de 0 si es un text o 1 si es un lpf.
     * L'atribut text només estarà present si el tipus és "text"
     * @param entrades
     * @throws Exception
     */
    private void carregarEntrades(ArrayList<String> entrades, Integer idAlfabet) throws Exception {
        for (String entrada : entrades) {
            Integer tipus;
            Integer id;
            String nomEntrada;
            String text;
            HashMap<String, Integer> lpf = new HashMap<>();

            //Definim tots els atributs necessaris en tipus natius de JAVA
            String[] atributs = entrada.split(",");


            id = Integer.valueOf(atributs[0]);
            nomEntrada = atributs[1];
            tipus = Integer.valueOf(atributs[2]);

            String[] paraulesIFrequencies = atributs[3].split("\\.");
            for (String paraulaIFreq : paraulesIFrequencies) {
                String paraula = paraulaIFreq.split(":")[0];
                Integer frequencia = Integer.valueOf(paraulaIFreq.split(":")[1]);
                lpf.put(paraula, frequencia);
            }

            //Instanciem el text o lpf a través del controlador
            if (tipus == 0) {
                text = atributs[4];
                ctrlEntrada.carregarText(id, nomEntrada, lpf, text, idAlfabet);
                ctrlAlfabet.vincularEntradaAlfabet(idAlfabet, id);
            } else {
                ctrlEntrada.carregarLPF(id, nomEntrada, lpf, idAlfabet);
                ctrlAlfabet.vincularEntradaAlfabet(idAlfabet, id);
            }

        }
    }

    /**
     * Carrega els teclats de persistencia i instancia les classses.
     * Els teclats segueixen el seguent format
     * id,nom, tipus, numFiles,numColumnes,idEntrada,tecla1.tecla2. ... .teclan
     * @param teclat
     * @throws Exception
     */
    private void carregarTeclats(ArrayList<String> teclats, Integer idEntrada) throws Exception {
        for (String teclat : teclats) {
            ArrayList<Character> distribucio = new ArrayList<>();

            String[] atributs = teclat.split(",");

            Integer id = Integer.valueOf(atributs[0]);
            String nomTeclat = atributs[1];
            Integer tipus = Integer.valueOf(atributs[2]);
            Integer numFiles = Integer.valueOf(atributs[3]);
            Integer numColumnes = Integer.valueOf(atributs[4]);


            String[] lletres = atributs[5].split("\\.");
            for (String lletra : lletres) {
                distribucio.add(lletra.charAt(0));
            }

            //Instanciem el teclat
            ctrlTeclat.carregarTeclat(nomTeclat, distribucio, id, idEntrada, numFiles, numColumnes, tipus);
            ctrlEntrada.vincularTeclatAEntrada(idEntrada, id);
        }
    }

    /**
     * Borra de memòria totes les instàncies de les classes
     */
    private void resetInfoPrograma() {
        ctrlAlfabet.resetAlfabets();
        ctrlEntrada.resetEntrades();
        ctrlTeclat.resetTeclats();
    }

    //--------------------------------Usuari---------------------------------//
    public String getNomUsuariActiu() throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder veure el teu nom");
        return usuariActiu.getNom();
    }

    /**
     * Crea un usuari i inicia sessió a partir d'un nom i contrasenya, també el persisteix.
     * @param nomUsuari Nom de l'usuari a crear
     * @param contrasenya Contrasenya de l'usuari
     * @throws Exception Si ja existeix un usuari amb aquest nom.
     */
    public void crearUsuari(String nomUsuari, String contrasenya) throws Exception{
        ArrayList<String> nomUsuarisExistents = ctrlPersistencia.obtenirUsernames();
        usuariActiu = Usuari.crearUsuari(nomUsuari, contrasenya, nomUsuarisExistents);

        ctrlPersistencia.crearUsuari(nomUsuari, contrasenya);

        // Crear alfabets per defecte
        ArrayList<Character> lletresCAT = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> lletresCAST = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> lletresANG = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

        crearAlfabet("Català", lletresCAT);
        crearAlfabet("Castellà", lletresCAST);
        crearAlfabet("Anglès", lletresANG);
    }

    /**
     * Inicia la sessio d'un usuari.
     * @param nomUsuari Nom de l'usuari
     * @param contrasenya Contrasenya de l'usuari
     * @throws Exception Si ja hi ha un usuari amb la sessió iniciada
     * @throws Exception Si la combinació usuari-contraseya és incorrecta
     */
    public void iniciarSessio(String nomUsuari, String contrasenya) throws Exception {
        if (usuariActiu != null) tancarSessio();
        ArrayList<String> usernames = ctrlPersistencia.obtenirUsernames();
        if (!usernames.contains(nomUsuari)) throw new Exception("No existeix un usuari amb aquest nom");

        String contrasenyaUsuari = ctrlPersistencia.obtenirPasswordUsuari(nomUsuari);
        usuariActiu = Usuari.iniciarSessio(nomUsuari, contrasenya, contrasenyaUsuari);
        carregarInfoUsuari(nomUsuari);
    }

    /**
     * Tanca la sessio de l'usuari actiu.
     * @throws Exception Si no hi ha cap usuari amb la sessió iniciada
     */
    public void tancarSessio() throws Exception {
        if (usuariActiu == null) {
            throw new Exception("Has d'haver iniciat sessio per a poder tancar-la");
        }
        resetInfoPrograma();
        usuariActiu = null;
    }

    /**
     * Modifica la contrasenya de l'usuari actiu.
     * @param contrasenya Nova contrasenya
     * @throws Exception Si no hi ha cap usuari amb la sessió iniciada
     */
    public void modificarContrasenyaUsuari(String contrasenya) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder modificar un usuari");
        usuariActiu.modificarContrasenya(contrasenya);
        ctrlPersistencia.modificarPasswordUsuari(usuariActiu.getNom(), contrasenya);
    }

    //--------------------------------Teclats---------------------------------//

    /**
     * Crea un teclat optimitzat per a dues mans a partir d'un alfabet i el nombre de files i columnes que ha de tenir el teclat.
     * @param idEntrada Identificador de l'entrada
     * @param files Nombre de files del teclat
     * @param columnes Nombre de columnes del teclat
     * @return Integer que representa l'identificador del teclat creat
     */
    public Integer crearTeclatDuesMans(String nom, Integer idEntrada, int files, int columnes) throws Exception {
        HashMap<String, Integer> lfp = ctrlEntrada.getLpfEntrada(idEntrada);
        Integer idAlfabet = ctrlEntrada.getIdAlfabetVinculatAEntrada(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletresAlfabet(idAlfabet);
 
        Integer idTeclat = ctrlTeclat.crearTeclatDuesMans(nom, lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.vincularTeclatAEntrada(idEntrada, idTeclat);
        ctrlPersistencia.crearTeclat(idEntrada, idTeclat, nom, 0, files, columnes, ctrlTeclat.getDistribucioTeclat(idTeclat));
        return idTeclat;
    }

    /**
     * Crea un teclat optiitzat per a polzes a partir d'un alfabet i el nombre de files i columnes que ha de tenir el teclat.
     * @param idEntrada Identificador de l'entrada
     * @param files Nombre de files del teclat
     * @param columnes Nombre de columnes del teclat
     * @return Integer que representa l'identificador del teclat creat
     */
    public Integer crearTeclatPolzes(String nom, Integer idEntrada, int files, int columnes) throws Exception {
        HashMap<String, Integer> lfp = ctrlEntrada.getLpfEntrada(idEntrada);
        Integer idAlfabet = ctrlEntrada.getIdAlfabetVinculatAEntrada(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletresAlfabet(idAlfabet);

        Integer idTeclat = ctrlTeclat.crearTeclatPolzes(nom, lfp, alfabet, idEntrada, files, columnes);

        ctrlEntrada.vincularTeclatAEntrada(idEntrada, idTeclat);
        ctrlPersistencia.crearTeclat(idEntrada, idTeclat, nom, 1, files, columnes, ctrlTeclat.getDistribucioTeclat(idTeclat));
        return idTeclat;
    }

    public Integer importarTeclat(String nom, Integer idEntrada, int files, int columnes, ArrayList<Character> distribucio, Integer tipus) throws Exception {
        if (usuariActiu == null) throw new Exception("Has d'haver iniciat sessio per a poder importar un teclat");
        Integer idTeclat = ctrlTeclat.importarTeclat(nom, idEntrada, files, columnes, distribucio, tipus);

        ctrlEntrada.vincularTeclatAEntrada(idEntrada, idTeclat);
        ctrlPersistencia.crearTeclat(idTeclat, idTeclat, nom, tipus, files, columnes, ctrlTeclat.getDistribucioTeclat(idTeclat));
        return idTeclat;
    }

    /**
     * Elimina el teclat identificat per idTeclat
     * @param idTeclat
     * @throws Exception
     */
    public void eliminarTeclat(Integer idTeclat) throws Exception {
        ctrlTeclat.eliminarTeclat(idTeclat);
        ctrlPersistencia.eliminarTeclat(idTeclat);
    }

    public void modificarFilesColumnesTeclat(Integer idTeclat, int files, int columnes) throws Exception {
        int idEntrada = ctrlTeclat.getIdEntradaVinculadaTeclat(idTeclat);
        HashMap<String, Integer> lpf = ctrlEntrada.getLpfEntrada(idEntrada);
        Integer idAlfabet = ctrlEntrada.getIdAlfabetVinculatAEntrada(idEntrada);
        ArrayList<Character> alfabet = ctrlAlfabet.getLletresAlfabet(idAlfabet);

        ctrlTeclat.modificarFilesColumnesTeclat(idTeclat, lpf, alfabet, files, columnes);
        ctrlPersistencia.modificarNumColumnesTeclat(idTeclat, columnes);
        ctrlPersistencia.modificarNumFilesTeclat(idTeclat, files);
        ctrlPersistencia.modificarDistribucio(idTeclat, ctrlTeclat.getDistribucioTeclat(idTeclat));
    }

    public Integer getFilesOptimesTeclat(Integer idTeclat, Integer numCols) throws Exception {
        return ctrlTeclat.getFilesOptimesTeclat(idTeclat, numCols);
    }

    public Integer getColumnesOptimesTeclat(Integer idTeclat, Integer numFiles) throws Exception {
        return ctrlTeclat.getColumnesOptimesTeclat(idTeclat, numFiles);
    }


    //--------------------------------Entrades---------------------------------//

    /**
     * Crea un text amb els paràmetres donats
     * @param nomEntrada
     * @param contingutEntrada
     * @param idAlfabet
     * @throws Exception
     */
    public Integer crearText(String nomEntrada, String contingutEntrada, Integer idAlfabet) throws Exception {
        // Suponemos que ctrlEntrada.crearText(...) devuelve un identificador para la nueva entrada de texto
        Integer idText = ctrlEntrada.crearText(nomEntrada, contingutEntrada, ctrlAlfabet.getLletresAlfabet(idAlfabet), idAlfabet);

        // Vinculamos el texto al alfabeto
        ctrlAlfabet.vincularEntradaAlfabet(idAlfabet, idText);

        // Guardamos el texto en persistencia
        // Suponemos que no necesitas los teclados vinculados para esta acción, así que comentamos la siguiente línea
        // ArrayList<Integer> idTeclats = ctrlEntrada.getIdTeclatsVinculatsAEntrada(idText);
        ctrlPersistencia.crearEntrada(idAlfabet, idText, nomEntrada, ctrlEntrada.getLpfEntrada(idText), contingutEntrada);

        return idText;
    }

    /**
     * Importa el text donat un path i el crea
     * @param nomEntrada
     * @param localitzacio_fitxer
     * @param idAlfabet
     * @throws Exception
     */
    public void importarText(String nomEntrada, String localitzacio_fitxer, Integer idAlfabet) throws Exception {
        ctrlEntrada.importarText(nomEntrada, localitzacio_fitxer, ctrlAlfabet.getLletresAlfabet(idAlfabet), idAlfabet);
    }

    /**
     * Crea una lpf a partir dels paràmetres donats
     * @param nomEntrada
     * @param contingutEntrada
     * @param idAlfabet
     * @throws Exception
     */
    public Integer crearLPF(String nomEntrada, HashMap<String, Integer> contingutEntrada, Integer idAlfabet) throws Exception {
        Integer idLPF = ctrlEntrada.crearLPF(nomEntrada, contingutEntrada, ctrlAlfabet.getLletresAlfabet(idAlfabet), idAlfabet);

        // Vinculem el text a l'alfabet
        ctrlAlfabet.vincularEntradaAlfabet(idAlfabet, idLPF);

        //Guardem la lpf a persistencia
        ArrayList<Integer> idTeclats = ctrlEntrada.getIdTeclatsVinculatsAEntrada(idLPF);
        ctrlPersistencia.crearEntrada(idAlfabet, idLPF, nomEntrada, contingutEntrada, null);

        return idLPF;
    }

    /**
     * Importa la lpf donat un path i el crea
     * @param nomEntrada
     * @param localitzacio_fitxer
     * @param idAlfabet
     * @throws Exception
     */
    public void importarLPF(String nomEntrada, String localitzacio_fitxer, Integer idAlfabet) throws Exception {
        ctrlEntrada.importarLPF(nomEntrada, localitzacio_fitxer, ctrlAlfabet.getLletresAlfabet(idAlfabet), idAlfabet);
    }

    /**
     * Elimina l'endtrada identificada per idEntrada
     * @param idEntrada
     * @throws Exception
     */
    public void eliminarEntrada(Integer idEntrada) throws Exception {
        int idAlfabet = ctrlEntrada.getIdAlfabetVinculatAEntrada(idEntrada);
        ArrayList<Integer> idsTeclats = ctrlEntrada.getIdTeclatsVinculatsAEntrada(idEntrada);

        for (Integer idTeclat : idsTeclats) {
            eliminarTeclat(idTeclat);
        }

        ctrlEntrada.eliminarEntrada(idEntrada);
        ctrlAlfabet.desvincularEntradaAlfabet(idAlfabet, idEntrada);

        //Borrem l'entrada de persistencia
        ctrlPersistencia.eliminarEntrada(idEntrada);
    }

    /**
     * Modifica el contingut de l'entrada identificada per idEntrada
     * @param idEntrada
     * @param contingut
     * @throws Exception
     */
    public void modificarContingutText(Integer idEntrada, String contingut) throws Exception {
        ctrlEntrada.modificarContingutText(idEntrada, contingut);
        ctrlPersistencia.modificarContingutEntrada(idEntrada, ctrlEntrada.getLpfEntrada(idEntrada), contingut);
    }

    public void modificarContingutLPF(Integer idEntrada, HashMap<String, Integer> newContent) throws Exception {
        ctrlEntrada.modificarContingutLPF(idEntrada, newContent);
        ctrlPersistencia.modificarContingutEntrada(idEntrada, ctrlEntrada.getLpfEntrada(idEntrada), null);
    }

    //--------------------------------Alfabets---------------------------------//

    /**
     * Crea l'alfabet amb els paràmetres donats
     * @param nomAlfabet
     * @param lletres
     * @return
     * @throws Exception
     */
    public Integer crearAlfabet(String nomAlfabet, ArrayList<Character> lletres) throws Exception {
        Integer idAlfabet = ctrlAlfabet.crearAlfabet(nomAlfabet, lletres);

        ArrayList<Integer> idEntrades = ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
        ctrlPersistencia.crearAlfabet(usuariActiu.getNom(), idAlfabet, nomAlfabet,  lletres);
        return idAlfabet;
    }

    /**
     * Importa l'alfabet donat un path i el crea
     * @param nomAlfabet
     * @param localitzacio_fitxer
     * @throws Exception
     */
    public void importarAlfabet(String nomAlfabet, String localitzacio_fitxer) throws Exception {
        ctrlAlfabet.importarAlfabet(nomAlfabet, localitzacio_fitxer);
    }

    /**
     * Afegeix la lletra lletra a l'alfabet identificat per idAlfabet
     * @param idAlfabet
     * @param lletra
     * @throws Exception
     */
    public void afegirLletraAlfabet(Integer idAlfabet, Character lletra) throws Exception {
        ctrlAlfabet.afegirLletraAlfabet(idAlfabet, lletra);
        ctrlPersistencia.afegirLletresAlfabet(idAlfabet, lletra);
    }

    //Elimina l'alfabet identificat per idAlfabet
    public void eliminarAlfabet(Integer idAlfabet) throws Exception {
        ArrayList<Integer> entradesVinculades = ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet);
        for (Integer idEntrada : entradesVinculades) {
            eliminarEntrada(idEntrada);
        }
        ctrlAlfabet.eliminarAlfabet(idAlfabet);
        ctrlPersistencia.eliminarAlfabet(idAlfabet);
    }

    public ArrayList<Character> getAlfabetEntrada(int idEntrada) throws Exception {
        for (Integer idAlfabet : ctrlAlfabet.getIdAlfabets()) {
            if (ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet).contains(idEntrada)) {
                return ctrlAlfabet.getLletresAlfabet(idAlfabet);
            }
        }
        throw new Exception("No s'ha trobat cap alfabet per aquesta entrada");
    }

    public String getNomAlfabetEntrada(int idEntrada) throws Exception {
        for (Integer idAlfabet : ctrlAlfabet.getIdAlfabets()) {
            if (ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet).contains(idEntrada)) {
                return ctrlAlfabet.getNomAlfabet(idAlfabet);
            }
        }
        return null;
    }

    public Integer getIdAlfabetEntrada(int idEntrada) throws Exception {
        for (Integer idAlfabet : ctrlAlfabet.getIdAlfabets()) {
            if (ctrlAlfabet.getEntradesVinculadesAlfabet(idAlfabet).contains(idEntrada)) {
                return idAlfabet;
            }
        }
        return null;
    }
}
