package JUnit;

import Domini.Text;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    @Test
    void testText() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Exception exception = assertThrows(Exception.class, () -> {
            new Text("TestText", lletres, null);
        });
        assertEquals("ERROR: El text del text no pot ser null", exception.getMessage());
    }

    @Test
    void testgetText() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Text text = new Text("TestText", lletres, "Test");
        assertEquals("Test", text.getText(), "El text no coincideix");
    }

    @Test
    void testsetText() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Text text = new Text("TestText", lletres, "Test");
        text.setText("Test2");
        assertEquals("Test2", text.getText(), "El text no coincideix");
    }

    @Test
    void testappendText() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Text text = new Text("TestText", lletres, "Test");
        text.appendText("2");
        assertEquals("Test2", text.getText(), "El text no coincideix");
    }

    @Test
    void testlength() throws Exception {
        ArrayList<Character> lletres = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        Text text = new Text("TestText", lletres, "Test");
        assertEquals(4, text.length(), "La longitud no coincideix");
    }



}