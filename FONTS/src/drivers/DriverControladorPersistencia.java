package drivers;


import persistencia.controladors.ControladorPersistencia;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverControladorPersistencia {
    private static ControladorPersistencia ctrl = ControladorPersistencia.obtenirInstancia();

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            int opcion = -1;
            String nomUsuari, contrasenya;

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
                        ArrayList<String> usernames = ctrl.obtenirUsernames();
                        System.out.println(usernames);
                        break;

                    case 3:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        System.out.println(ctrl.obtenirPasswordUsuari(nomUsuari));
                        break;

                    case 4:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        System.out.println("Introdueix la contrasenya");
                        contrasenya = in.next();
                        ctrl.modificarPasswordUsuari(nomUsuari, contrasenya);
                        break;

                    case 5:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        ctrl.eliminarUsuari(nomUsuari);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void imprimirOpcions() {
        System.out.println("0 - Finalitzar driver");
        System.out.println("1 - Crear usuari");
        System.out.println("2 - Obtenir Usernames");
        System.out.println("3 - Obtenir contrasenya usuari");
        System.out.println("4 - Modificar contrasenya usuari");
        System.out.println("5 - Eliminar usuari");
    }
}
