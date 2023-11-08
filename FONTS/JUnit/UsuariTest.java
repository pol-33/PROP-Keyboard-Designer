package JUnit;

import Domini.Usuari;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class UsuariTest {

    @Test
    public void testUsuari() {
        Usuari usuari = new Usuari("usuari", "contrasenya");
        assertEquals("usuari", usuari.obtenNom(), "El nom no es correcte");
        assertEquals("contrasenya", usuari.obtenContrasenya(), "La contrasenya no es correcte");
    }

    @Test
    public void testCrearAlfabets() {
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.crearAlfabet("alfabet1", "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");

        Alfabet alfabetCreado = usuari.getAlfabet("alfabet1");

        assertNotNull(alfabetCreado, "L'alfabet no s'ha creat correctament");
        assertEquals("alfabet1", alfabetCreado.getIdioma(), "El nom de l'alfabet no coincideix");
        assertEquals("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z", String.join(",", alfabetCreado.getLletres()), "El texto del alfabeto no coincide");
    }

    @Test
    public void testgetAlfabet(){
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        String nomAlfabet("alfabet1");
        String textAlfabet("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        usuari.crearAlfabet(nomAlfabet, textAlfabet);

        Alfabet alfabetObtingut = usuari.getAlfabet(nomAlfabet);
        //preguntar a pol
        assertNotNull(alfabetObtingut, "L'alfabet no s'ha creat correctament");
        assertEquals("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z", String.join(",", alfabetObtingut.getLletres()), "El text de l'alfabet no coincideix");
    }

    @Test
    public void testgetAlfabets(){
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.crearAlfabet("Català", "a,b,c,ç,d,e,f,g,h,i,j,k,l,ł,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        usuari.crearAlfabet("Castellà", "a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z");

        HashMap<String, Alfabet> alfabets = usuari.getAlfabets();

        assertNotNull(alfabets, "L'usuari no té alfabets");
        assertEquals(2, alfabets.size(), "El nombre d'alfabets no coincideix");
        assertTrue(alfabets.constainsKey("Català"), "L'alfabet no s'ha creat correctament");
        assertTrue(alfabets.constainsKey("Castellà"), "L'alfabet no s'ha creat correctament");
    }

    @Test
    public void testllistarAlfabets(){
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");

        usuari.crearAlfabet("Català", "a,b,c,ç,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        usuari.crearAlfabet("Español", "a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        usuari.llistarAlfabets();

        System.setOut(System.out);

        String expectedOutput = "Idioma: Català\nLletres de l'idioma: a,b,c,ç,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z\n" + "Idioma: Español\nLletres de l'idioma: a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z\n";
        assertEquals("La sortida no coincideix amb lo esperat", expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void TestcrearText(){ // Inicialización
        String nomUsuari = "testUser";
        String contrasenya = "testPass";
        Usuari usuari = new Usuari(nomUsuari, contrasenya);

        // Configuración del alfabeto
        String idiomaAlfabet = "Català";
        ArrayList<Character> lletres = new ArrayList<>();
        lletres.add('a');
        lletres.add('b');
        // ... añadir todas las letras necesarias
        Alfabet alfabet = new Alfabet(idiomaAlfabet, lletres);

        // Añadir el alfabeto al usuario manualmente
        usuari.getAlfabets().put(idiomaAlfabet, alfabet);

        // Datos para la creación del texto
        String nomAlfabet = idiomaAlfabet;
        String nomText = "TestText";
        String contingutText = "Contingut de prova";

        // Ejecución: Crear el texto mediante el método a probar
        usuari.crearText(nomAlfabet, nomText, contingutText);

        // Validación: Comprobar si el texto se ha creado correctamente dentro del alfabeto
        Text text = usuari.getAlfabet(nomAlfabet).getText(nomText);

        assertNotNull("El text no s'ha creat correctament o no s'ha afegit a l'alfabet", text);
        assertEquals("El contingut del text no coincideix", contingutText, text.getContingut());
    }

    @Test
    public void testgetNomUsuari() throws Exception {
        String esperatNomUsuari = "usuariTest";
        Usuari usuari = new Usuari(esperatNomUsuari, "contrasenyaTest");

        String nomUsuari = usuari.getNomUsuari();
        assertEquals(esperatNomUsuari, nomUsuari, "El nom d'usuari no coincideix");
    }
    @Test
    public void testsetNomUsuari(){
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.setNomUsuari("usuariTest2");
        assertEquals("usuariTest2", usuari.getNomUsuari(), "El nom d'usuari no coincideix");
    }

    @Test
    public void testgetContrasenya(){
        String esperatContrasenya = "contrasenyaTest";
        Usuari usuari = new Usuari("usuariTest", esperatContrasenya);

        String contrasenya = usuari.getContrasenya();
        assertEquals(esperatContrasenya, contrasenya, "La contrasenya no coincideix");
    }
    @Test
    public void testSetContrasenya(){
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        usuari.setContrasenya("contrasenyaTest2");
        assertEquals("contrasenyaTest2", usuari.getContrasenya(), "La contrasenya no coincideix");
    }

    @Test
    public void testContrasenyaCorrecta(){
        Usuari usuari = new Usuari("usuariTest", "contrasenyaTest");
        assertEquals("contrasenyaTest", usuari.contrasenyaCorrecta("contrasenyaTest"), "La contrasenya no coincideix");
    }

}
