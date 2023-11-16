package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Entrada {

    protected String nom;
    protected Integer id;
    protected HashMap<String, Integer> lpf;
    protected ArrayList<Integer> idTeclats;
    protected Integer idAlfabet;

    //------------------------------Getters------------------------------//
    public String getNom() {
        return this.nom;
    }

    public HashMap<String, Integer> getLPF() {
        return this.lpf;
    }

    public ArrayList<Integer> getTeclatsVinculats() {
        return idTeclats;
    }

    public Integer getIdAlfabetVinculat() {
        return idAlfabet;
    }
    //---------------------------Metodes publics---------------------------//
    public void vincularTeclat(Integer idTeclat) throws Exception {
        if (idTeclats.contains(idTeclat)) {
            throw new Exception("L'entrada ja té aquest teclat vinculat");
        }
        idTeclats.add(idTeclat);
    }
    public void desvincularTeclat(Integer idTeclat) throws Exception {
        if (idTeclats.contains(idTeclat)) {
            idTeclats.remove(idTeclat);
        }
        else throw new Exception("L'entrda no tenia aquest teclat vinculat");
    }
}
