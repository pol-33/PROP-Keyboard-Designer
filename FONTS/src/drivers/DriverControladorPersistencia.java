package drivers;


import persistencia.controladors.ControladorPersistencia;

public class DriverControladorPersistencia {
    private static ControladorPersistencia ctrl = ControladorPersistencia.obtenirInstancia();

    public static void main(String[] args) {
        try {
            ctrl.crearUsuari("Jordi", "hola");

            System.out.println("Hola");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
