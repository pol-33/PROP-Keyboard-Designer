package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class VistaPrincipal extends JFrame {
    // Llista
    private JList<String> llistaTeclats;

    // Botons de creació i eliminació
    private JButton btnCrearTeclat, btnEliminarTeclat;

    // Mapa per emmagatzemar noms de teclats i els seus IDs
    private Map<String, Set<Integer>> mapaTeclats;

    public VistaPrincipal() {
        // Configuració del frame
        setTitle("Gestió de Teclats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Inicialització del mapa
        mapaTeclats = new HashMap<>();
        // Exemple amb teclats que tenen el mateix nom
        afegirTeclat("Teclat 1", 1);
        afegirTeclat("Teclat 2", 2);
        afegirTeclat("Teclat 1", 3);

        // Inicialització de la llista
        llistaTeclats = new JList<>(mapaTeclats.keySet().toArray(new String[0]));

        // Inicialització dels botons
        btnCrearTeclat = new JButton("Crear Teclat");
        btnEliminarTeclat = new JButton("Eliminar Teclat");

        // Afegir listeners als botons
        btnEliminarTeclat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarConfirmacioEliminarTeclat();
            }
        });

        // Afegir la llista i els botons al frame
        add(new JScrollPane(llistaTeclats), BorderLayout.CENTER);

        JPanel panelBotons = new JPanel(new FlowLayout());
        panelBotons.add(btnCrearTeclat);
        panelBotons.add(btnEliminarTeclat);
        add(panelBotons, BorderLayout.SOUTH);

        // Mostrar el frame
        setVisible(true);
    }

    // Mètode per afegir teclat al mapa
    private void afegirTeclat(String nomTeclat, int id) {
        if (!mapaTeclats.containsKey(nomTeclat)) {
            mapaTeclats.put(nomTeclat, new HashSet<>());
        }
        mapaTeclats.get(nomTeclat).add(id);
    }

    // Mètode per eliminar el teclat seleccionat
    private void eliminarTeclatSeleccionat() {
        String nomTeclatSeleccionat = llistaTeclats.getSelectedValue(); // Obté el nom del teclat seleccionat
        if (nomTeclatSeleccionat != null) {
            int resposta = mostrarConfirmacioEliminarTeclat();
            if (resposta == JOptionPane.YES_OPTION) {
                Set<Integer> idsTeclat = mapaTeclats.get(nomTeclatSeleccionat); // Obté els IDs corresponents al nom del teclat
                // Aquí podríeu fer alguna lògica per seleccionar quin ID voleu eliminar
                // En aquest exemple, simplement eliminem el primer ID de la col·lecció
                if (!idsTeclat.isEmpty()) {
                    int idAEliminar = idsTeclat.iterator().next();
                    idsTeclat.remove(idAEliminar); // Elimina l'ID del conjunt de IDs del teclat
                    if (idsTeclat.isEmpty()) {
                        mapaTeclats.remove(nomTeclatSeleccionat); // Si no hi ha més IDs associats, esborra el teclat del mapa
                    }
                    actualizarLlista(); // Actualitza la llista de teclats
                }
            }
        }
    }

    // Mètode per actualitzar la llista de teclats després de l'eliminació
    private void actualizarLlista() {
        llistaTeclats.setListData(mapaTeclats.keySet().toArray(new String[0]));
    }

    // Mètode per mostrar la confirmació de l'eliminació del teclat
    private int mostrarConfirmacioEliminarTeclat() {
        return JOptionPane.showConfirmDialog(
                this,
                "Segur que vols eliminar aquest teclat?",
                "Confirmació",
                JOptionPane.YES_NO_OPTION
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaPrincipal());
    }
}
