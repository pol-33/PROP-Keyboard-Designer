package test;

import domini.classes.Text;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class TextTest{
    @Test
    public void provaConstructor() throws Exception {
        Text text = new Text("ProvaText", 1, "hello world", 2);

        // Verify the LPF field was calculated properly using reflection
        Field campLPF = Text.class.getSuperclass().getDeclaredField("lpf");
        campLPF.setAccessible(true);
        HashMap<String, Integer> lpf = (HashMap<String, Integer>) campLPF.get(text);
        assertNotNull(lpf, "El hashmap LPF no pot ser null");
        assertTrue(lpf.containsKey("hello") && lpf.containsKey("world"), "El hashmap LPF hauria de contenir les paraules del text");
    }

    @Test
    public void provaConstructorAmbContingutNull() {
        Exception excepcio = assertThrows(Exception.class, () -> new Text("ProvaText", 1, null, 2));
        assertEquals("ERROR: El contingut del text no pot ser nul", excepcio.getMessage());
    }
@Test
    public void provaSetTextNull() throws Exception {
        Text text = new Text("ProvaText", 1, "hello world", 2);
        Exception excepcio = assertThrows(IllegalArgumentException.class, () -> text.setText(null));
        assertEquals("ERROR: El contingut del text no pot ser null", excepcio.getMessage());
    }

    @Test
    public void provaCalculateLPFOnConstruction() throws Exception {
        Text text = new Text("ProvaText", 1, "hello hello world", 2);

        // Access and test the private LPF field
        Field campLPF = Text.class.getSuperclass().getDeclaredField("lpf");
        campLPF.setAccessible(true);
        HashMap<String, Integer> lpf = (HashMap<String, Integer>) campLPF.get(text);
        assertNotNull(lpf, "El hashmap LPF no hauria de ser null");
        assertEquals(2, (int) lpf.get("hello"), "El recompte de LPF per 'hello' hauria de ser 2");
        assertEquals(1, (int) lpf.get("world"), "El recompte de LPF per 'world' hauria de ser 1");
    }
@Test
    public void provaCalculateLPFOnSetText() throws Exception {
        Text text = new Text("ProvaText", 1, "text inicial", 2);
        text.setText("new new text");

        // Access and test the private LPF field
        Field campLPF = Text.class.getSuperclass().getDeclaredField("lpf");
        campLPF.setAccessible(true);
        HashMap<String, Integer> lpf = (HashMap<String, Integer>) campLPF.get(text);
        assertNotNull(lpf, "El hashmap LPF no hauria de ser null");
        assertEquals(2, (int) lpf.get("new"), "El recompte de LPF per 'new' hauria de ser 2");
        assertEquals(1, (int) lpf.get("text"), "El recompte de LPF per 'text' hauria de ser 1");
    }
}
