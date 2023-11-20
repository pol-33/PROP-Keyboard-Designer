package test;

import domini.classes.Usuari;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsuariTest {

    @Test
    public void provaNomUsuariExcedeixLongitudMaxima() {
        Exception excepcio = assertThrows(Exception.class, () -> new Usuari("nomMoltLlargQueSuperaElLimit", "contrasenya"));
        assertEquals("El nom d'usuari no pot superar els 15 caracters", excepcio.getMessage());
    }

    @Test
    public void provaContrasenyaExcedeixLongitudMaxima() {
        Exception excepcio = assertThrows(Exception.class, () -> new Usuari("usuari", "contrasenyaMoltLlargaQueSupera"));
        assertEquals("La contrasenya no pot superar els 15 caracters", excepcio.getMessage());
    }

    @Test
    public void testUsuari() throws Exception {
        String[] userNames = {"usuari1", "usuari2", "usuari3"};
        String[] passwords = {"contrasenya1", "contrasenya2", "contrasenya3"};

        for (int i = 0; i < userNames.length; i++) {
            Usuari usuari = new Usuari(userNames[i], passwords[i]);
            assertEquals(userNames[i], usuari.getNom(), "El nom no és correcte");
            assertEquals(passwords[i], usuari.getContrasenya(), "La contrasenya no és correcte");
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
            assertTrue(usuari.verificarIniciSessio(userNames[i], passwords[i], usuarisContrasenyes),"La verificació de l'inici de sessió hauria de ser correcta");
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
            assertThrows(Exception.class,
                    () -> new Usuari(nomUsuariExist, "contrasenyaTest").crearUsuari(nomUsuariExist, "contrasenyaTest", nomUsuarisExistents),
                    "Hauria de llançar una excepció ja que l'usuari " + nomUsuariExist + " ja existeix");
        }
    }
}