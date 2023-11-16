package test;

import domini.classes.Usuari;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestUsuari {
    @Test
    public void testUsuari() throws Exception {
        Usuari usuari = new Usuari("usuari", "contrasenya");
        assertEquals("usuari", usuari.getNom(), "El nom no és correcte");
        assertEquals("contrasenya", usuari.getContrasenya(), "La contrasenya no és correcte");
    }

    @Test
    public void testUsuariConstructor() {
        assertDoesNotThrow(() -> new Usuari("usuariTest", "contrasenyaTest"));
    }

    @Test
    public void testUsuariConstructorLongitudExcessiva() {
        assertThrows(Exception.class, () -> new Usuari("usuariMoltLlargQueSuperaElLimit", "contrasenyaTest"));
    }

    @Test
    public void testVerificarIniciSessio() throws Exception {
        HashMap<String, String> usuarisContrasenyes = new HashMap<>();
        usuarisContrasenyes.put("usuariTest", "contrasenyaTest");
        assertTrue(new Usuari("usuariTest", "contrasenyaTest").verificarIniciSessio("usuariTest", "contrasenyaTest", usuarisContrasenyes), "La verificació de l'inici de sessió hauria de ser correcta");
    }
}