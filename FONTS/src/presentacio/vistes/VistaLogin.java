package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin {

    private JFrame frame;
    private JPanel panel;
    private JLabel userLabel, passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public VistaLogin() {
        initComponents();
        initUI();
        setActionListeners();
    }

    public void tancar() {
        frame.dispose();
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    private void initComponents() {
        frame = new JFrame("LogIn");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir un margen al panel

        userLabel = new JLabel("Usuario: ");
        passwordLabel = new JLabel("Contraseña: ");
        userTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sesión");
        signUpButton = new JButton("Registrarse");

        // Estilizar los botones
        loginButton.setBackground(Color.LIGHT_GRAY);
        signUpButton.setBackground(Color.LIGHT_GRAY);
    }

    private void initUI() {
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Grid layout para organizar los componentes

        // Añadir componentes al panel
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Crear un panel separado para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        // Agregar paneles al frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla y ajustar tamaño
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        frame.setVisible(true); // Hacer visible la ventana
    }

    private void setActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                ControladorPresentacio.iniciarSessio(username, new String(password)); // Convertir la contraseña a String
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                ControladorPresentacio.crearUsuari(username, new String(password)); // Convertir la contraseña a String
            }
        });
    }
}
