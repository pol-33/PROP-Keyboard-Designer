package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


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
        frame.setVisible(false);
    }
    public void mostrar() {
        frame.setVisible(true);
    }
    
    private void initComponents() {
        frame = new JFrame("LogIn");
        panel = new JPanel();

        userLabel = new JLabel("Usuario: ");
        passwordLabel = new JLabel("Contraseña: ");
        userTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sesión");
        signUpButton = new JButton("Registrarse");
    }

    private void initUI() {
        panel.setLayout(new GridLayout(4, 2, 5, 5)); // Grid layout para organizar los componentes
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); // Agregar el panel al frame

        // Hacer la ventana un poco más grande
        frame.setSize(400, 200);

        // Centrar la ventana en la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.pack();

    }

    private void setActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lògica per iniciar sessió
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                ControladorPresentacio.iniciarSessio(username, password.toString());
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lògica per registrar-se
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                ControladorPresentacio.crearUsuari(username, password.toString());
            }
        });
    }
}
