package Drivers;

import ControladorsDomini.ControladorDomini;

import java.util.Scanner;

public class DriverControladorDomini {
    //Instancia única del controlador de domini
    private ControladorDomini ctrlDomini = ControladorDomini.obtenInstancia();
    public static void main(String[] args) {
        DriverControladorDomini driverCtrlDomini = new DriverControladorDomini();

        Scanner in = new Scanner(System.in);
        String nomUsuari;
        String contrasenya;

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
}
