import domini.controladors.ControladorDomini;
import java.util.*;

public class ProgramaConsola {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ControladorDomini controlador = ControladorDomini.obtenirInstancia();

    public static void main(String[] args) {
        try {
            controlador.iniciarSessio("nombreUsuario", "contrasenya");

            int opcion;
            do {
                mostrarMenu();
                opcion = obtenerOpcion();

                try {
                    procesarOpcion(opcion);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (opcion != 9);

            controlador.tancarSessio();
            System.out.println("Saliendo...");
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("====== Menú ======");
        System.out.println("1. Crear Alfabeto");
        System.out.println("2. Crear Texto");
        System.out.println("3. Crear LPF");
        System.out.println("4. Crear Teclado");
        System.out.println("5. Ver Alfabetos");
        System.out.println("6. Ver Textos");
        System.out.println("7. Ver Teclados");
        System.out.println("8. Ver Teclados, Alfabetos y Textos");
        System.out.println("9. Salir");
        System.out.println("==================");
        System.out.println("Ingrese una opción:");
    }

    private static int obtenerOpcion() {
        int opcion;
        while (true) {
            try {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 9) {
                    break;
                } else {
                    System.out.println("Ingrese una opción válida (1-9):");
                }
            } catch (Exception e) {
                System.out.println("Ingrese una opción válida (1-9):");
                scanner.next();
            }
        }
        return opcion;
    }

    private static void procesarOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                crearAlfabeto();
                break;
            case 2:
                crearTexto();
                break;
            case 3:
                crearLPF();
                break;
            case 4:
                crearTeclado();
                break;
            case 5:
                verAlfabetos();
                break;
            case 6:
                verTextos();
                break;
            case 7:
                verTeclados();
                break;
            case 8:
                verTodo();
                break;
            case 9:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void crearAlfabeto() {
        try {
            System.out.println("==== Crear Nuevo Alfabeto ====");
            System.out.println("Ingrese el nombre del nuevo alfabeto:");
            scanner.nextLine();
            String nombreAlfabeto = scanner.nextLine();
            System.out.println("Ingrese las letras del alfabeto separadas por espacios:");
            String[] letrasInput = scanner.nextLine().split(" ");
            ArrayList<Character> letras = new ArrayList<>();
            for (String letra : letrasInput) {
                letras.add(letra.charAt(0));
            }
            controlador.crearAlfabet(nombreAlfabeto, letras);
            System.out.println("Alfabeto '" + nombreAlfabeto + "' creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el alfabeto: " + e.getMessage());
        }
    }
    
    private static void crearTexto() {
        try {
            System.out.println("==== Crear Nuevo Texto ====");
            System.out.println("Ingrese el nombre del nuevo texto:");
            scanner.nextLine();
            String nombreTexto = scanner.nextLine();
            System.out.println("Ingrese el contenido del texto:");
            String contenido = scanner.nextLine();
            System.out.println("Seleccione el alfabeto para el texto:");
            mostrarAlfabetos();
            int idAlfabeto = scanner.nextInt();
            controlador.crearText(nombreTexto, contenido, idAlfabeto);
            System.out.println("Texto '" + nombreTexto + "' creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el texto: " + e.getMessage());
        }
    }
    
    private static void crearLPF() {
        try {
            System.out.println("==== Crear Nuevo LPF ====");
            System.out.println("Ingrese el nombre del nuevo LPF:");
            scanner.nextLine();
            String nombreLPF = scanner.nextLine();
            System.out.println("Ingrese los pares de valores (String, int) separados por espacios:");
            String[] valores = scanner.nextLine().split(" ");
            HashMap<String, Integer> lpf = new HashMap<>();
            for (int i = 0; i < valores.length; i += 2) {
                String key = valores[i];
                int value = Integer.parseInt(valores[i + 1]);
                lpf.put(key, value);
            }
            System.out.println("Seleccione el alfabeto para el LPF:");
            mostrarAlfabetos();
            int idAlfabeto = scanner.nextInt();
            controlador.crearLPF(nombreLPF, lpf, idAlfabeto);
            System.out.println("LPF '" + nombreLPF + "' creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el LPF: " + e.getMessage());
        }
    }
    
    private static void crearTeclado() {
        try {
            System.out.println("==== Crear Nuevo Teclado ====");
            System.out.println("Ingrese el nombre del nuevo teclado:");
            scanner.nextLine();
            String nombreTeclado = scanner.nextLine();
            System.out.println("Seleccione el texto asociado al teclado:");
            mostrarTextos();
            int idTexto = scanner.nextInt();
            System.out.println("Ingrese el número de filas del teclado:");
            int filas = scanner.nextInt();
            System.out.println("Ingrese el número de columnas del teclado:");
            int columnas = scanner.nextInt();
            controlador.crearTeclatPolzes(nombreTeclado, idTexto, filas, columnas);
            System.out.println("Teclado '" + nombreTeclado + "' creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el teclado: " + e.getMessage());
        }
    }
    
    private static void verAlfabetos() {
        try {
            ArrayList<Integer> idsAlfabetos = controlador.getIdAlfabets();
            System.out.println("Alfabetos:");
            for (Integer id : idsAlfabetos) {
                String nombre = controlador.getNomAlfabet(id);
                System.out.println(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al ver los alfabetos: " + e.getMessage());
        }
    }
    
    private static void verTextos() {
        try {
            ArrayList<Integer> idsTextos = controlador.getIdEntrades();
            System.out.println("Textos:");
            for (Integer id : idsTextos) {
                String nombre = controlador.getNomEntrada(id);
                System.out.println(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al ver los textos: " + e.getMessage());
        }
    }
    
    private static void verTeclados() {
        try {
            ArrayList<Integer> idsTeclados = controlador.getIdTeclats();
            System.out.println("Teclados:");
            for (Integer id : idsTeclados) {
                String nombre = controlador.getNomTeclat(id);
                int columnas = controlador.getColumnesTeclat(id);
                ArrayList<Character> distribucion = controlador.getDistribucioTeclat(id);
    
                System.out.println("Nombre: " + nombre);
                System.out.println("Columnas: " + columnas);
                System.out.println("Distribución:");
    
                int contador = 0;
                for (char letra : distribucion) {
                    System.out.print(letra + " ");
                    contador++;
                    if (contador == columnas) {
                        System.out.println();
                        contador = 0;
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error al ver los teclados: " + e.getMessage());
        }
    }
    
    private static void verTodo() {
        try {
            System.out.println("==== Ver Todo ====");
            System.out.println("Alfabetos:");
            verAlfabetos();
            System.out.println("Textos:");
            verTextos();
            System.out.println("Teclados:");
            verTeclados();
        } catch (Exception e) {
            System.out.println("Error al ver todo: " + e.getMessage());
        }
    }
    
    private static void mostrarAlfabetos() {
        try {
            ArrayList<Integer> idsAlfabetos = controlador.getIdAlfabets();
            System.out.println("Alfabetos disponibles:");
            for (int i = 0; i < idsAlfabetos.size(); i++) {
                String nombre = controlador.getNomAlfabet(idsAlfabetos.get(i));
                System.out.println((i) + ". " + nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los alfabetos: " + e.getMessage());
        }
    }
    
    private static void mostrarTextos() {
        try {
            ArrayList<Integer> idsTextos = controlador.getIdEntrades();
            System.out.println("Textos disponibles:");
            for (int i = 0; i < idsTextos.size(); i++) {
                String nombre = controlador.getNomEntrada(idsTextos.get(i));
                System.out.println((i) + ". " + nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los textos: " + e.getMessage());
        }
    }
}
