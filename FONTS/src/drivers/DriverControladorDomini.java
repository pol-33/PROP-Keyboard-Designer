package drivers;

import domini.controladors.ControladorDomini;
import persistencia.controladors.ControladorPersistencia;

import java.util.Scanner;

public class DriverControladorDomini {
    private static final ControladorDomini ctrl = ControladorDomini.obtenirInstancia();

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            int opcion = -1;
            String nomUsuari, contrasenya, nouText, nomTeclat;

            while (opcion != 0) {
                imprimirOpcions();
                opcion = in.nextInt();
                switch (opcion) {
                    case 0:
                        in.close();
                        break;
                    case 1:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        System.out.println("Introdueix la contrasenya");
                        contrasenya = in.next();
                        ctrl.crearUsuari(nomUsuari, contrasenya);
                        break;
                    case 2:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        System.out.println("Introdueix la contrasenya");
                        contrasenya = in.next();
                        ctrl.iniciarSessio(nomUsuari, contrasenya);
                        break;
                    case 3:
                        ctrl.tancarSessio();
                        break;
                    case 4:
                        System.out.println("Introdueix la contrasenya");
                        contrasenya = in.next();
                        ctrl.modificarContrasenyaUsuari(contrasenya);
                        break;
                    case 5:
                        if (ctrl.usuariIniciatSessio()) {
                            System.out.println("Si que esta iniciat");
                        } else {
                            System.out.println("No ho esta");
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void imprimirOpcions() {
        System.out.println("0 - Parar el driver");
        System.out.println("1 - Registrarse");
        System.out.println("2 - Iniciar sessio");
        System.out.println("3 - Tancar sessio");
        System.out.println("4 - Modificar contrasenya");
        System.out.println("5 - Imprimir si hi ha usuari actiu");
    }
}
