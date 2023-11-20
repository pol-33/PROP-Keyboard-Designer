import domini.controladors.ControladorDomini;
import java.util.*;

public class ProgramaConsola {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ControladorDomini controlador = ControladorDomini.obtenirInstancia();

    public static void main(String[] args) {
        try {
            controlador.crearUsuari("UsuariDefault", "1234");

            int opcio;
            do {
                mostrarMenu();
                opcio = obtenirOpcio();

                try {
                    processarOpcio(opcio);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (opcio != 9);

            controlador.tancarSessio();
            System.out.println("Sortint...");
        } catch (Exception e) {
            System.out.println("Error en iniciar sessió: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("====== Menú ======");
        System.out.println("1. Crear Alfabet");
        System.out.println("2. Crear Text");
        System.out.println("3. Crear LPF");
        System.out.println("4. Crear Teclat");
        System.out.println();
        System.out.println("5. Veure Alfabets");
        System.out.println("6. Veure Entrades");
        System.out.println("7. Veure Teclats");
        System.out.println();
        System.out.println("8. Sortir");
        System.out.println("==================");
        System.out.println("Introdueix una opció:");
    }

    private static int obtenirOpcio() {
        int opcio;
        while (true) {
            try {
                opcio = scanner.nextInt();
                if (opcio >= 1 && opcio <= 9) {
                    break;
                } else {
                    System.out.println("Introdueix una opció vàlida (1-8):");
                }
            } catch (Exception e) {
                System.out.println("Introdueix una opció vàlida (1-8):");
                scanner.next();
            }
        }
        return opcio;
    }

    private static void processarOpcio(int opcio) throws Exception {
        switch (opcio) {
            case 1:
                crearAlfabet();
                break;
            case 2:
                crearText();
                break;
            case 3:
                crearLPF();
                break;
            case 4:
                crearTeclat();
                break;
            case 5:
                veureAlfabets();
                break;
            case 6:
                veureEntrades();
                break;
            case 7:
                veureTeclats();
                break;
            case 8:
                System.out.println("Sortint...");
                break;
            default:
                System.out.println("Opció no vàlida");
                break;
        }
    }

    private static void crearAlfabet() {
        try {
            System.out.println("==== Crear Nou Alfabet ====");
            System.out.println("Introdueix el nom del nou alfabet:");
            scanner.nextLine();
            String nomAlfabet = scanner.nextLine();
            System.out.println("Introdueix les lletres de l'alfabet separades per espais:");
            String[] lletresInput = scanner.nextLine().split(" ");
            ArrayList<Character> lletres = new ArrayList<>();
            for (String lletra : lletresInput) {
                lletres.add(lletra.charAt(0));
            }
            controlador.crearAlfabet(nomAlfabet, lletres);
            System.out.println("Alfabet '" + nomAlfabet + "' creat amb èxit.");
        } catch (Exception e) {
            System.out.println("Error en crear l'alfabet: " + e.getMessage());
        }
    }

    private static void crearText() {
        try {
            System.out.println("==== Crear Nou Text ====");
            System.out.println("Introdueix el nom del nou text:");
            scanner.nextLine();
            String nomText = scanner.nextLine();
            System.out.println("Introdueix el contingut del text:");
            String contingut = scanner.nextLine();
            System.out.println("Selecciona l'alfabet per al text:");
            mostrarAlfabets();
            int idAlfabet = scanner.nextInt();
            controlador.crearText(nomText, contingut, idAlfabet);
            System.out.println("Text '" + nomText + "' creat amb èxit.");
        } catch (Exception e) {
            System.out.println("Error en crear el text: " + e.getMessage());
        }
    }

    private static void crearLPF() {
        try {
            System.out.println("==== Crear Nou LPF ====");
            System.out.println("Introdueix el nom del nou LPF:");
            scanner.nextLine();
            String nomLPF = scanner.nextLine();
            System.out.println("Introdueix els parells de valors (String, int) separats per espais:");
            String[] valors = scanner.nextLine().split(" ");
            HashMap<String, Integer> lpf = new HashMap<>();
            for (int i = 0; i < valors.length; i += 2) {
                String clau = valors[i];
                int valor = Integer.parseInt(valors[i + 1]);
                lpf.put(clau, valor);
            }
            System.out.println("Selecciona l'alfabet per al LPF:");
            mostrarAlfabets();
            int idAlfabet = scanner.nextInt();
            controlador.crearLPF(nomLPF, lpf, idAlfabet);
            System.out.println("LPF '" + nomLPF + "' creat amb èxit.");
        } catch (Exception e) {
            System.out.println("Error en crear el LPF: " + e.getMessage());
        }
    }

    private static void crearTeclat() {
        try {
            System.out.println("==== Crear Nou Teclat ====");
            System.out.println("Introdueix el nom del nou teclat:");
            scanner.nextLine();
            String nomTeclat = scanner.nextLine();
            System.out.println("Selecciona el text associat al teclat:");
            mostrarTextos();
            int idText = scanner.nextInt();
            System.out.println("Introdueix el nombre de files del teclat:");
            int files = scanner.nextInt();
            System.out.println("Introdueix el nombre de columnes del teclat:");
            int columnes = scanner.nextInt();
            
            System.out.println("Quin algoritme vols utilitzar?");
            System.out.println("0. Algoritme per a teclat de portatil");
            System.out.println("1. Algoritme per a teclat de mobil");
            int tipusTeclat = scanner.nextInt();
            if (tipusTeclat == 0) {
                controlador.crearTeclatDuesMans(nomTeclat, idText, files, columnes);
            }
            else {
                controlador.crearTeclatPolzes(nomTeclat, idText, files, columnes);
            }

            System.out.println("Teclat '" + nomTeclat + "' creat amb èxit.");
        } catch (Exception e) {
            System.out.println("Error en crear el teclat: " + e.getMessage());
        }
    }

    private static void veureAlfabets() {
        try {
            ArrayList<Integer> idsAlfabets = controlador.getIdAlfabets();
            System.out.println("==== Els teus alfabets ====");
            for (Integer id : idsAlfabets) {
                String nom = controlador.getNomAlfabet(id);
                ArrayList<Character> lletres = controlador.getLletresAlfabet(id);
                System.out.println(nom + ": " + lletres.toString());
            }
        } catch (Exception e) {
            System.out.println("Error en veure els alfabets: " + e.getMessage());
        }
    }

    private static void veureEntrades() {
        try {
            ArrayList<Integer> idsTextos = controlador.getIdEntrades();
            System.out.println("==== Les teves entrades ====");
            for (Integer id : idsTextos) {
                String nom = controlador.getNomEntrada(id);
                String tipus = controlador.getTypeEntrada(id);
                System.out.println(nom + ": " + tipus);
            }
        } catch (Exception e) {
            System.out.println("Error en veure els textos: " + e.getMessage());
        }
    }

    private static void veureTeclats() {
        try {
            ArrayList<Integer> idsTeclats = controlador.getIdTeclats();
            System.out.println("==== Els teus teclats ====");
            for (Integer id : idsTeclats) {
                String nom = controlador.getNomTeclat(id);
                int files = controlador.getFilesTeclat(id);
                int columnes = controlador.getColumnesTeclat(id);
                ArrayList<Character> distribucio = controlador.getDistribucioTeclat(id);
    
                System.out.println();
                System.out.println(nom + ":");
                System.out.println("Files: " + files);
                System.out.println("Columnes: " + columnes);
                System.out.println("Distribució:");
    
                // Imprimir borde superior
                for (int i = 0; i < columnes * 4 + 1; i++) {
                    System.out.print("-");
                }
                System.out.println();
    
                int contador = 0;
                for (int i = 0; i < files; i++) {
                    System.out.print("| "); // Línea vertical izquierda
                    for (int j = 0; j < columnes; j++) {
                        System.out.print("[" + distribucio.get(contador) + "] ");
                        contador++;
                    }
                    System.out.println("|"); // Línea vertical derecha
                }
    
                // Imprimir borde inferior
                for (int i = 0; i < columnes * 4 + 1; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error al ver los teclats: " + e.getMessage());
        }
    }
    
    private static void mostrarAlfabets() {
        try {
            ArrayList<Integer> idsAlfabets = controlador.getIdAlfabets();
            System.out.println("Alfabets disponibles:");
            for (int i = 0; i < idsAlfabets.size(); i++) {
                String nom = controlador.getNomAlfabet(idsAlfabets.get(i));
                System.out.println((i) + ". " + nom);
            }
        } catch (Exception e) {
            System.out.println("Error en mostrar els alfabets: " + e.getMessage());
        }
    }

    private static void mostrarTextos() {
        try {
            ArrayList<Integer> idsTextos = controlador.getIdEntrades();
            System.out.println("Textos disponibles:");
            for (int i = 0; i < idsTextos.size(); i++) {
                String nom = controlador.getNomEntrada(idsTextos.get(i));
                System.out.println((i) + ". " + nom);
            }
        } catch (Exception e) {
            System.out.println("Error en mostrar els textos: " + e.getMessage());
        }
    }
}
