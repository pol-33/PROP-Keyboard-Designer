package test;

import domini.classes.Teclat;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TeclatTest {
    @Test
    public void testConstructorInitialization() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Character> distribucio = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Integer idEntrada = 1;
        Integer numFiles = 3;
        Integer numColumnes = 2;
        Integer id = 4;
        String nom = "TeclatTest";

        Teclat teclat = new Teclat(nom, distribucio, idEntrada, numFiles, numColumnes, id);


        Field distribucioField = teclat.getClass().getDeclaredField("distribucio");
        distribucioField.setAccessible(true);
        assertEquals(distribucio, distribucioField.get(teclat), "Field 'distribucio' no s'ha inicialitzat correctament.");

        Field nomField = teclat.getClass().getDeclaredField("nom");
        nomField.setAccessible(true);
        assertEquals(nom, nomField.get(teclat), "Field 'nom' no s'ha inicialitzat correctament.");

        Field numFilesField = teclat.getClass().getDeclaredField("numFiles");
        numFilesField.setAccessible(true);
        assertEquals(numFiles, numFilesField.get(teclat), "Field 'numFiles' no s'ha inicialitzat correctament.");

        Field numColumnesField = teclat.getClass().getDeclaredField("numColumnes");
        numColumnesField.setAccessible(true);
        assertEquals(numColumnes, numColumnesField.get(teclat), "Field 'numColumnes' no s'ha inicialitzat correctament.");

        Field idField = teclat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        assertEquals(id, idField.get(teclat), "Field 'id' no s'ha inicialitzat correctament.");

        Field idEntradaField = teclat.getClass().getDeclaredField("idEntrada");
        idEntradaField.setAccessible(true);
        assertEquals(idEntrada, idEntradaField.get(teclat), "Field 'idEntrada' no s'ha inicialitzat correctament.");

    }
}