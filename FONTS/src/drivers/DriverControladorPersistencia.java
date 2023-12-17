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
                        break;

                    case 6:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        System.out.println("Introdueix l'id de l'alfabet");
                        Integer id = in.nextInt();
                        System.out.println("Introdueix el nom de l'alfabet");
                        String nom = in.next();
                        ArrayList<Character> lletres = new ArrayList<>();
                        lletres.add('a');
                        lletres.add('b');
                        lletres.add('c');
                        ctrl.crearAlfabet(nomUsuari, id, nom, lletres);
                        break;

                    case 7:
                        System.out.println("Introdueix el nom d'usuari");
                        nomUsuari = in.next();
                        ArrayList<String> alfabets = ctrl.carregarAlfabets(nomUsuari);
                        alfabets.forEach(alfabet -> System.out.println(alfabet));
                        break;

                    case 8:
                        System.out.println("Introdueix l'id de l'alfabet");
                        id = in.nextInt();
                        ArrayList<Character> novesLletres = new ArrayList<>();
                        novesLletres.add('k');
                        novesLletres.add('z');
                        ctrl.afegirLletresAlfabet(id, novesLletres);
                        break;

                    case 9:
                        System.out.println("Introdueix l'id de l'alfabet");
                        id = in.nextInt();
                        ctrl.eliminarAlfabet(id);
                        break;
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
        System.out.println("-------------------");
        System.out.println("6 - Crear alfabet");
        System.out.println("7 - Carregar alfabets");
        System.out.println("8 - Modificar Alfabet");
        System.out.println("9 - Eliminar Alfabet");
        System.out.println("-------------------");
    }
}
