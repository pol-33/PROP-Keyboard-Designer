package test;

import domini.classes.Usuari;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestUsuari {
    @Test
    public void testUsuari() throws Exception {
        Usuari usuari = new Usuari("usuari", "contrasenya");
        assertEquals("usuari", usuari.getNomUsuari(), "El nom no és correcte");
        assertEquals("contrasenya", usuari.getContrasenya(), "La contrasenya no és correcte");
    }

    @Test
    public void testgetNomUsuari() throws Exception {
        String esperatNomUsuari = "usuariTest";
        Usuari usuari = new Usuari(esperatNomUsuari, "contrasenyaTest");

        String nomUsuari = usuari.getNomUsuari();
        assertEquals(esperatNomUsuari, nomUsuari, "El nom d'usuari no coincideix");
    }

    @Test
    public void testsetNomUsuari() throws Exception {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.setNomUsuari("usuariTest2");
        assertEquals("usuariTest2", usuari.getNomUsuari(), "El nom d'usuari no coincideix");
    }

    @Test
    public void testgetContrasenya() throws Exception {
        String esperatContrasenya = "contrasenyaTest";
        Usuari usuari = new Usuari("usuariTest", esperatContrasenya);

        String contrasenya = usuari.getContrasenya();
        assertEquals(esperatContrasenya, contrasenya, "La contrasenya no coincideix");
    }

    @Test
    public void testSetContrasenya() throws Exception {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.setContrasenya("contrasenyaTest2");
        assertEquals("contrasenyaTest2", usuari.getContrasenya(), "La contrasenya no coincideix");
    }

    @Test
    public void testContrasenyaCorrecta() throws Exception {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        assertEquals("contrasenyaTest", usuari.contrasenyaCorrecta("contrasenyaTest"), "La contrasenya no coincideix");
    }

    @Test
    public void testgetIDsAlfabets() throws Exception{
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.afegirAlfabet("idAlfabetTest");
        ArrayList<String> idsAlfabets = usuari.getIDsAlfabets();
        assertEquals("idAlfabetTest", idsAlfabets.get(0), "L'id de l'alfabet no coincideix");
    }

    @Test
    public void testafegirAlfabets() throws Exception {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.afegirAlfabet("idAlfabetTest");
        assertEquals(1, usuari.getIDsAlfabets().size(), "L'id de l'alfabet no coincideix");
    }

    @Test
    public void testeliminarAlfabet() throws Exception {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.afegirAlfabet("idAlfabetTest");
        usuari.eliminarAlfabet("idAlfabetTest");
        assertEquals(0, usuari.getIDsAlfabets().size(), "No s'ha eliminat l'alfabet");
    }

    @Test
    public void testteAlfabets() throws Exception {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.afegirAlfabet("idAlfabetTest");
        assertTrue(usuari.teAlfabets("idAlfabetTest"), "No te l'alfabet");
    }

    @Test
    public void testPrint() throws Exception{
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        String expectedPrint = "Usuari{nomUsuari='Usuario', contrasenya='Pass', idsAlfabets=[]}";
        assertEquals(expectedPrint, usuari.print());
    }


}