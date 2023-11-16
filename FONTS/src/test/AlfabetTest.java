package test;

import domini.classes.Alfabet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;


class AlfabetTest {

    @Test
    public void testConstructorValidData() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);
        assertNotNull(alfabet);
        assertEquals("Catala", alfabet.getNom());
        assertEquals(1, alfabet.getId());
        assertEquals(lletres, alfabet.getLletres());
    }

    @Test
    public void testVincularEntrada() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);
        alfabet.vincularEntrada(100);
        assertTrue(alfabet.getEntrades().contains(100));
        }

    @Test
    public void testDesvincularEntrada() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);
        alfabet.vincularEntrada(100);
        alfabet.desvincularEntrada(100);
        assertFalse(alfabet.getEntrades().contains(100));
    }

    @Test
    public void testAfegirLletra() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);
        alfabet.afegirLletra('d');
        assertTrue(alfabet.getLletres().contains('d'));
    }
}
