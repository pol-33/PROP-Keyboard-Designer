package test;

import domini.classes.Usuari;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsuariTest {

    @Test
    public void testUsuari() throws Exception {
        String[] userNames = {"usuari1", "usuari2", "usuari3"};
        String[] passwords = {"contrasenya1", "contrasenya2", "contrasenya3"};

        for (int i = 0; i < userNames.length; i++) {
            Usuari usuari = new Usuari(userNames[i], passwords[i]);
            assertEquals("El nom no és correcte", userNames[i], usuari.getNom());
            assertEquals("La contrasenya no és correcte", passwords[i], usuari.getContrasenya());
        }
    }

    @Test
    public void testVerificarIniciSessio() throws Exception {
        HashMap<String, String> usuarisContrasenyes = new HashMap<>();
        String[] userNames = {"usuariTest1", "usuariTest2", "usuariTest3"};
        String[] passwords = {"contrasenyaT1", "contrasenyaT2", "contrasenyaT3"};

        for (int i = 0; i < userNames.length; i++) {
            usuarisContrasenyes.put(userNames[i], passwords[i]);
        }

        for (int i = 0; i < userNames.length; i++) {
            Usuari usuari = new Usuari(userNames[i], passwords[i]);
            assertTrue("La verificació de l'inici de sessió hauria de ser correcta", usuari.verificarIniciSessio(userNames[i], passwords[i], usuarisContrasenyes));
        }
    }

    @Test
    public void testCrearUsuariExistent() {
        ArrayList<String> nomUsuarisExistents = new ArrayList<>();
        String[] existingUserNames = {"usuariExistent1", "usuariExistent2", "usuariExistent3"};

        for (String nom : existingUserNames) {
            nomUsuarisExistents.add(nom);
        }

        for (String nomUsuariExist : existingUserNames) {
            assertThrows("Hauria de llançar una excepció ja que l'usuari " + nomUsuariExist + " ja existeix",
                    Exception.class,
                    () -> new Usuari(nomUsuariExist, "contrasenyaTest").crearUsuari(nomUsuariExist, "contrasenyaTest", nomUsuarisExistents));
        }
    }
}