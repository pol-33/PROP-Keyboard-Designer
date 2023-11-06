package JUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import static org.junit.Assert.*;

public class TextTest {
    @Test
    public void testTextCreacio() {
        String nomEsperat = "ExempleNom";
        String contingutEsperat = "ExempleContingut";

        Text text = new Text(nomEsperat, contingutEsperat);

        assertEquals(nomEsperat, text.getNom(), "Els noms són diferents");
        assertEquals(contingutEsperat, text.getContingut(), "Els continguts són diferents");
        assertNotNull(text.getLPF(), "El LPF és null");
    }


    @Test
    public void testSetContingut() {
        Text text = new Text("ExempleNom", "ExempleContingut");
        String nouContingut = "nou contingut";
        text.setContingut(nouContingut);
        assertEquals(nouContingut, text.getContingut(), "El contingut no s'ha modificat correctament");
        assertNotNull(text.getLPF(), "El LPF és null");
    }

    @Test
    public void testAppendContingut() {
        Text text = new Text("ExempleNom", "ExempleContingut");
        String additionalContent = "contingut adicional";
        text.appendContingut(additionalContent);
        assertEquals(additionalContent, text.getContingut(), "El contingut no s'ha modificat correctament");
        assertNotNull(text.getLPF(), "El LPF és null");
    }

    @Test
    public void testLenght() {
        Text text = new Text("ExempleNom", "ExempleContingut");
        assertEquals(15, text.length(), "La longitud no és correcta");

        //cas de text buit
        text.setContingut("");
        assertEquals(0, text.length(), "La longitud no és correcta");

        //cas de text llarg
        String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse";
        text.setContingut(longText);
        assertEquals(longText.length(), text.length(), "La longitud no és correcta");
    }

    @Test
    public void testDisplay() {
        Text text = new Text("ExempleNom", "ExempleContingut");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        text.display();

        System.setOut(System.out);

        String expectedOutput = "ExempleContingut" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "El contingut no s'ha mostrat correctament");
    }

    @Test
    public void testCalculateLPF(){
        T try {
            Text text = new Text("TestName", "word1 word2, word1! word3 word2.");

            text.setContingut("word1 word2, word1! word3 word2");


            // Obtener el mapa de frecuencias después de que se haya llamado a calculateLPF() internamente
            HashMap<String, Integer> lpf = text.getLPF();

            //verificar que el hasmap no sigui null
            asserNotNull(lpf, "El LPF és null");

            // Verifica el contingut de les frequencies
            assertEquals(2, lpf.getOrDefault("word1", 0), "La frecuencia de 'word1' debería ser 2");
            assertEquals(2, lpf.getOrDefault("word2", 0), "La frecuencia de 'word2' debería ser 2");
            assertEquals(1, lpf.getOrDefault("word3", 0), "La frecuencia de 'word3' debería ser 1");

            // Verifica que no hi hagin signes de puntuacio
            assertFalse(lpf.containsKey("word2,"), "Las palabras no deberían contener signos de puntuación");
            assertFalse(lpf.containsKey("word1!"), "Las palabras no deberían contener signos de puntuación");

            // Verifica que no hi hagin espain en blanc
            assertFalse(lpf.containsKey(""), "No deberían incluirse palabras vacías en el mapa LPF");

        } catch (Exception e) {
            fail("No se esperaba que el constructor lanzara una excepción.");
        }
    }







}