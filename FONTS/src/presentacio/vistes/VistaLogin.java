package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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

    private void initComponents() {
        frame = new JFrame("LogIn");
        panel = new JPanel();

        userLabel = new JLabel("Usuari: ");
        passwordLabel = new JLabel("Password: ");
        userTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sessió");
        signUpButton = new JButton("Registrar-se");
    }

    private void initUI() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void setActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lògica per iniciar sessió
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                ControladorPresentacio.iniciarSessio(username, password.toString());
                frame.setVisible(false);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lògica per registrar-se
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                ControladorPresentacio.crearUsuari(username, password.toString());
                frame.setVisible(false);
            }
        });
    }
}
