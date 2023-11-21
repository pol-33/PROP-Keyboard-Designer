package domini.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe LPF (Letter Position Frequency). Representa una entrada en el sistema que gestiona la frecuencia de paraules
 * i la seva associació amb teclats i alfabets específics.
 */
public class LPF extends Entrada {

    // ---------------------------------------------------------------------------- //
    //                                   Constructora
    // ---------------------------------------------------------------------------- //

    /**
     * Constructora de LPF.
     * @param nomEntrada Nom de la entrada LPF.
     * @param id Identificador de la entrada LPF.
     * @param contingutEntrada HashMap amb el contingut de la LPF (paraula i la seva freqüència).
     * @param idAlfabet Identificador de l'alfabet associat a aquesta entrada LPF.
     * @throws Exception Si alguna de les freqüències de les paraules és negativa.
     */
    public LPF(String nomEntrada, Integer id, HashMap<String, Integer> contingutEntrada, Integer idAlfabet) throws Exception {
        for (Map.Entry<String, Integer> paraulaAmbFreq : contingutEntrada.entrySet()) {
            if (paraulaAmbFreq.getValue() < 0) {
                throw new Exception("Una paraula no pot tenir frequencia negativa");
            }
        }
        this.nom = nomEntrada;
        this.id = id;
        this.lpf = contingutEntrada; // Asignación correcta después de la validación
        this.idAlfabet = idAlfabet;
        this.idTeclats = new ArrayList<>();
    }
}
