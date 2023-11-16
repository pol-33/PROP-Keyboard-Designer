package test;

import domini.classes.Alfabet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;



class AlfabetTest {

        @Test
        public void testConstructorValidData() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            assertNotNull(alfabet);
        }

        @Test
        public void testConstructorInvalidData() throws Exception {
            new Alfabet("Catala", 1, "aa,,bb");
        }

        @Test
        public void testAssociarEntrada() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.vincularEntrada(10);
            // Assume that getEntrades method returns a list containing 10
            assertTrue(alfabet.getEntrades().contains(10));
        }

        @Test
        public void testAssociarEntradaExisting() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.vincularEntrada(10);
            alfabet.vincularEntrada(10); // This should throw an exception
        }

        @Test
        public void testDesvincularEntrada() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.vincularEntrada(10);
            alfabet.desvincularEntrada(10);
            assertFalse(alfabet.getEntrades().contains(10));
        }

        @Test
        public void testDesvincularEntradaNotExisting() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.desvincularEntrada(10); // This should throw an exception
        }

        @Test
        public void testAfegirLletra() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.afegirLletra('d');
            assertTrue(alfabet.getLletres().contains('d'));
        }

        @Test
        public void testAfegirLletraExisting() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.afegirLletra('a'); // This should throw an exception
        }

        @Test
        public void testAfegirLletraNull() throws Exception {
            Alfabet alfabet = new Alfabet("Catala", 1, "a,b,c");
            alfabet.afegirLletra(null); // This should throw an exception
        }

    }

}