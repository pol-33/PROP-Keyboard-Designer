package drivers;


import persistencia.controladors.ControladorPersistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DriverControladorPersistencia {
    private static ControladorPersistencia ctrl = ControladorPersistencia.obtenirInstancia();

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            int opcion = -1;
            String nomUsuari, contrasenya, nouText, nomTeclat;
            Character elem;
            Integer idEntradaTeclat, idTeclat, numFiles, numColumnes;
            ArrayList<Character> distribucio;


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
                        Character novesLletres = 'a';
                        ctrl.afegirLletresAlfabet(id, novesLletres);
                        break;

                    case 9:
                        System.out.println("Introdueix l'id de l'alfabet");
                        id = in.nextInt();
                        ctrl.eliminarAlfabet(id);
                        break;

                    case 10: // Crear Entrada
                        System.out.println("Introdueix l'id de l'alfabet");
                        Integer idAlfabet = in.nextInt();
                        System.out.println("Introdueix l'id de l'entrada");
                        Integer idEntrada = in.nextInt();
                        in.nextLine(); // neteja el buffer després de llegir un enter
                        System.out.println("Introdueix el nom de l'entrada");
                        String nomEntrada = in.nextLine();
                        System.out.println("Vols introduir text (T) o un LPF (L)? (T/L)");
                        String opcio = in.nextLine();
                        HashMap<String, Integer> lpf = null;
                        String text = "";

                        if ("L".equalsIgnoreCase(opcio)) {
                            lpf = new HashMap<>();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Introdueix una clau per al LPF (o 'end' per acabar):");
                                String clau = in.nextLine();
                                if ("end".equals(clau)) {
                                    continuar = false;
                                } else {
                                    System.out.println("Introdueix un valor per a " + clau + ":");
                                    Integer valor = in.nextInt();
                                    in.nextLine(); // neteja el buffer després de llegir un enter
                                    lpf.put(clau, valor);
                                }
                            }
                        } else {
                            System.out.println("Introdueix el text de l'entrada:");
                            text = in.nextLine();
                        }

                        ctrl.crearEntrada(idAlfabet, idEntrada, nomEntrada, lpf, text);
                        break;

                    case 11: // Cargar Entrades
                        System.out.println("Introdueix l'id de l'alfabet");
                        idAlfabet = in.nextInt();
                        ArrayList<String[]> entrades = ctrl.carregarEntrades(idAlfabet);
                        entrades.forEach(entrada -> System.out.println(String.join(", ", entrada)));
                        break;

                    case 12: // Eliminar Entrada
                        System.out.println("Introdueix l'id de l'entrada");
                        idEntrada = in.nextInt();
                        ctrl.eliminarEntrada(idEntrada);
                        break;

                    case 13: // Modificar Contingut entrada
                        System.out.println("Introdueix l'id de l'entrada a modificar");
                        idEntrada = in.nextInt();
                        in.nextLine();  // neteja el buffer del scanner

                        System.out.println("Introdueix el nom de l'entrada");
                        nomEntrada = in.nextLine();

                        System.out.println("Vols modificar el text (T) o la LPF (L)? (T/L)");
                        String opcio2 = in.nextLine();
                        HashMap<String, Integer> LPF = null;
                        String text2 = "";

                        if ("L".equalsIgnoreCase(opcio2)) {
                            LPF = new HashMap<>();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Introdueix una clau per al LPF (o 'end' per acabar):");
                                String clau = in.nextLine();
                                if ("end".equals(clau)) {
                                    continuar = false;
                                } else {
                                    System.out.println("Introdueix un valor per a " + clau + ":");
                                    Integer valor = in.nextInt();
                                    in.nextLine(); // neteja el buffer després de llegir un enter
                                    LPF.put(clau, valor);
                                }
                            }
                        } else {
                            System.out.println("Introdueix el nou text de l'entrada:");
                            text2 = in.nextLine(); // Permet llegir una linia completa amb espais
                        }

                        ctrl.modificarContingutEntrada(idEntrada, nomEntrada, LPF, text2);
                        break;


                    case 14: // Crear Teclat
                        System.out.println("Introdueix l'id de l'entrada");
                        idEntradaTeclat = in.nextInt();
                        System.out.println("Introdueix l'id del teclat");
                        idTeclat = in.nextInt();
                        in.nextLine(); // neteja el buffer del scanner
                        System.out.println("Introdueix el nom del teclat");
                        nomTeclat = in.nextLine();
                        System.out.println("Introdueix el número de files del teclat");
                        numFiles = in.nextInt();
                        System.out.println("Introdueix el número de columnes del teclat");
                        numColumnes = in.nextInt();
                        in.nextLine(); //neteja el buffer del scanner
                        distribucio = new ArrayList<>();
                        System.out.println("Introduce los elementos de la distribución (finaliza con 'end')");
                        while (!(elem = in.nextLine().charAt(0)).equals("end")) {
                            distribucio.add(elem);
                        }
                        ctrl.crearTeclat(idEntradaTeclat, idTeclat, nomTeclat, numFiles, numColumnes, distribucio);
                        break;

                    case 15: // Cargar Teclat
                        System.out.println("Introdueix l'id de l'entrada");
                        idEntradaTeclat = in.nextInt();
                        ArrayList<String[]> teclats = ctrl.carregarTeclats(idEntradaTeclat);
                        teclats.forEach(teclat -> System.out.println(String.join(", ", teclat)));
                        break;

                    case 16: // Eliminar Teclat
                        System.out.println("Introdueix l'id del teclat a eliminar");
                        idTeclat = in.nextInt();
                        ctrl.eliminarTeclat(idTeclat);
                        break;

                    case 17: // Modificar Número de Files del Teclat
                        System.out.println("Introdueix l'id del teclat");
                        idTeclat = in.nextInt();
                        System.out.println("Introdueix el nou número de files");
                        numFiles = in.nextInt();
                        ctrl.modificarNumFilesTeclat(idTeclat, numFiles);
                        break;

                    case 18: // Modificar  el Número de Columnes del teclat
                        System.out.println("Introdueix l'id del teclat");
                        idTeclat = in.nextInt();
                        System.out.println("Introdueix el nou número de columnes");
                        numColumnes = in.nextInt();
                        ctrl.modificarNumColumnesTeclat(idTeclat, numColumnes);
                        break;

                    case 19: // Modificar la  Distribució del teclta
                        System.out.println("Introdueix l'id del teclat");
                        idTeclat = in.nextInt();
                        in.nextLine(); // Lneteja el buffer del scanner
                        distribucio = new ArrayList<>();
                        System.out.println("Introduce los elementos de la nueva distribución (finaliza con 'end')");
                        while (!(elem = in.nextLine().charAt(0)).equals("end")) {
                            distribucio.add(elem);
                        }
                        ctrl.modificarDistribucio(idTeclat, distribucio);
                        break;






                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void imprimirOpcions() {
        System.out.println("0 - Finalitzar driver");
        System.out.println("-------------------");
        System.out.println("1 - Crear usuari");
        System.out.println("2 - Obtenir usernames");
        System.out.println("3 - Obtenir contrasenya usuari");
        System.out.println("4 - Modificar contrasenya usuari");
        System.out.println("5 - Eliminar usuari");
        System.out.println("-------------------");
        System.out.println("6 - Crear alfabet");
        System.out.println("7 - Carregar alfabets");
        System.out.println("8 - Modificar alfabet");
        System.out.println("9 - Eliminar alfabet");
        System.out.println("-------------------");
        System.out.println("10 - Crear entrada");
        System.out.println("11 - Carregar entrades");
        System.out.println("12 - Eliminar entrada");
        System.out.println("13 - Modificar contingut entrada");
        System.out.println("-------------------");
        System.out.println("14 - Crear teclat");
        System.out.println("15 - Carregar teclats");
        System.out.println("16 - Eliminar teclat");
        System.out.println("17 - Modificar número de files de teclat");
        System.out.println("18 - Modificar número de columnes de teclat");
        System.out.println("19 - Modificar distribució de teclat");
        System.out.println("-------------------");
    }
}
