package test;

import domini.classes.Teclat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeclatTest {
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
        assertEquals("Field 'distribucio' no s'ha inicialitzat correctament.", distribucio, distribucioField.get(teclat));

        Field nomField = teclat.getClass().getDeclaredField("nom");
        nomField.setAccessible(true);
        assertEquals("Field 'nom' no s'ha inicialitzat correctament.", nom, nomField.get(teclat));

        Field numFilesField = teclat.getClass().getDeclaredField("numFiles");
        numFilesField.setAccessible(true);
        assertEquals("Field 'numFiles' no s'ha inicialitzat correctament.", numFiles, numFilesField.get(teclat));

        Field numColumnesField = teclat.getClass().getDeclaredField("numColumnes");
        numColumnesField.setAccessible(true);
        assertEquals("Field 'numColumnes' no s'ha inicialitzat correctament.", numColumnes, numColumnesField.get(teclat));

        Field idField = teclat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        assertEquals("Field 'id' no s'ha inicialitzat correctament.", id, idField.get(teclat));

        Field idEntradaField = teclat.getClass().getDeclaredField("idEntrada");
        idEntradaField.setAccessible(true);
        assertEquals("Field 'idEntrada' no s'ha inicialitzat correctament.", idEntrada, idEntradaField.get(teclat));

    }
}