package drivers;


import persistencia.controladors.ControladorPersistencia;

public class DriverControladorPersistencia {
    private final ControladorPersistencia ctrl = ControladorPersistencia.obtenirInstancia();

    public static void main(String[] args) {
        try {
            System.out.println("Hello world");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
