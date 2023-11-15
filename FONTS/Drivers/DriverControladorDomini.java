package Drivers;

import ControladorsDomini.ControladorDomini;

import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

public class DriverControladorDomini {
    //Instancia única del controlador de domini
    private ControladorDomini ctrlDomini = ControladorDomini.obtenInstancia();

    public static void main(String[] args) {
        DriverControladorDomini driverCtrlDomini = new DriverControladorDomini();

        Scanner in = new Scanner(System.in);
        String nomUsuari;
        String contrasenya;
        String idioma;
        String textAlfabet;

        int opcio = -1;
        while (opcio != 0) {
            imprimirOperacions();
            opcio = in.nextInt();

            switch(opcio) {
                case 0:
                    in.close();
                    break;

                case 1:
                    System.out.println("Introdueix el nom d'usuari:");
                    nomUsuari = in.next();
                    System.out.println("Introdueix la contrasenya:");
                    contrasenya = in.next();
                    driverCtrlDomini.testRegistrarUsuari(nomUsuari, contrasenya);
                    break;

                case 2:
                    System.out.println("Introdueix el nom d'usuari:");
                    nomUsuari = in.next();
                    System.out.println("Introdueix la contrasenya:");
                    contrasenya = in.next();
                    driverCtrlDomini.testIniciarSessio(nomUsuari, contrasenya);
                    break;

                case 3:
                    driverCtrlDomini.testTancarSessio();
                    break;

                case 4:
                    driverCtrlDomini.testLlistarUsuaris();
                    break;

                case 5:
                    if (!driverCtrlDomini.testUsuariAutenticat()) {
                        System.out.println("Has d'haver iniciat sessio per a poder fer aquesta funcionalitat");
                        break;
                    }
                    System.out.println("Introdueix l'idioma de l'alfabet");
                    idioma = in.next();
                    System.out.println("Introdueix les lletres de l'alfabet");
                    textAlfabet = in.next();
                    driverCtrlDomini.testCrearAlfabet(idioma, textAlfabet);
                    break;

                case 6:
                    if (!driverCtrlDomini.testUsuariAutenticat()) {
                        System.out.println("Has d'haver iniciat sessio per a poder fer aquesta funcionalitat");
                        break;
                    }
                    driverCtrlDomini.testLlistarAlfabets();
                    break;

                case 7:
                    if (!driverCtrlDomini.testUsuariAutenticat()) {
                        System.out.println("Has d'haver iniciat sessio per a poder fer aquesta funcionalitat");
                        break;
                    };
                    System.out.println("Introdueix el nom de l'alfabet a eliminar");
                    idioma = in.next();
                    driverCtrlDomini.testEliminarAlfabet(idioma);
            }
        }
    }

    private static void imprimirOperacions() {
        System.out.println("Introdueix el numero segons l'opcio que desitjis realitzar:");
        System.out.println("0 - Finalitza el programa");
        System.out.println("1 - Registra un usuari");
        System.out.println("2 - Inicia sessio");
        System.out.println("3 - Tanca sessio ");
        System.out.println("4 - Veure tots els usuaris registrats");
        System.out.println("5 - Crear alfabet");
        System.out.println("6 - Llistar alfabets");
        System.out.println("7 - Eliminar alfabet");
        System.out.println("8 - Crear text");
        System.out.println("9 - Crear llista de paraules amb frequencia");
        System.out.println("10 - Llistar textos");
        System.out.println("11 - Llistar llistes de paraules amb frequencia");
    }

    //Invoca la creacio d'usuari
    public void testRegistrarUsuari(String nomUsuari, String contrasenya) {
        try {
            ctrlDomini.registrarUsuari(nomUsuari, contrasenya);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Invoca l'inici de sessió d'un usuari
    public void testIniciarSessio(String nomUsuari, String contrasenya) {
        try {
            ctrlDomini.iniciarSessio(nomUsuari, contrasenya);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Invoca tancar sessió
    public void testTancarSessio() {
        try {
            ctrlDomini.tancarSessio();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Invoca consultar usuaris
    public void testLlistarUsuaris() {
        try {
            ctrlDomini.llistarUsuaris();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testCrearAlfabet(String idioma, String textAlfabet) {
        try {
            ctrlDomini.crearAlfabet(idioma, textAlfabet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testLlistarAlfabets() {
        try {
            ctrlDomini.llistarAlfabets();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testEliminarAlfabet(String idioma) {
        try {
            ctrlDomini.eliminarAlfabet(idioma);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testCrearText(String idioma, String nomEntrada, String text) {
        try {
            ctrlDomini.crearText(idioma, nomEntrada, text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testCrearLPF(String idioma, String nomEntrada, HashMap<String, Integer> lpf) {
        try {
            ctrlDomini.crearLPF(idioma, nomEntrada, lpf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testLlistarTexts() {
        try {
            ctrlDomini.llistarTexts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testLlistarLPFs() {
        try {
            ctrlDomini.llistarLPFs();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean testUsuariAutenticat() {
        return ctrlDomini.usuariAutenticat();
    }

}
