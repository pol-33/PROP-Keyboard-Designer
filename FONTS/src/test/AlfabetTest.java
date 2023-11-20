package test;

import domini.classes.Alfabet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;


class AlfabetTest {

    @Test
    public void testConstructorValidData() throws Exception {
        // Test with multiple sets of letters
        ArrayList<ArrayList<Character>> listOfLetterSets = new ArrayList<>();
        listOfLetterSets.add(new ArrayList<>(Arrays.asList('a', 'b', 'c')));
        listOfLetterSets.add(new ArrayList<>(Arrays.asList('d', 'e', 'f')));
        listOfLetterSets.add(new ArrayList<>(Arrays.asList('g', 'h', 'i')));

        for (int i = 0; i < listOfLetterSets.size(); i++) {
            Alfabet alfabet = new Alfabet("Catala" + i, i, listOfLetterSets.get(i));
            assertNotNull(alfabet);
            assertEquals("Catala" + i, alfabet.getNom());
            assertEquals(i, alfabet.getId());
            assertEquals(listOfLetterSets.get(i), alfabet.getLletres());
        }
    }

    @Test
    public void testVincularEntrada() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);

        for (int entradaId = 100; entradaId <= 102; entradaId++) {
            alfabet.vincularEntrada(entradaId);
            assertTrue(alfabet.getEntrades().contains(entradaId));
        }
    }

    @Test
    public void testDesvincularEntrada() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);

        for (int entradaId = 100; entradaId <= 102; entradaId++) {
            alfabet.vincularEntrada(entradaId);
        }
        for (int entradaId = 100; entradaId <= 102; entradaId++) {
            alfabet.desvincularEntrada(entradaId);
            assertFalse(alfabet.getEntrades().contains(entradaId));
        }
    }

    @Test
    public void testAfegirLletra() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Alfabet alfabet = new Alfabet("Catala", 1, lletres);

        for (char lletra = 'd'; lletra <= 'f'; lletra++) {
            alfabet.afegirLletra(lletra);
            assertTrue(alfabet.getLletres().contains(lletra));
        }
    }
}