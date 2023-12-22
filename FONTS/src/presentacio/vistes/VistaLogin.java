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

        userLabel = new JLabel("Usuari: ");
        passwordLabel = new JLabel("Contrasenya: ");
        userTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sessió");
        signUpButton = new JButton("Registrar-se");

        // Estilizar los botones
        loginButton.setBackground(Color.LIGHT_GRAY);
        signUpButton.setBackground(Color.LIGHT_GRAY);
    }

    private void initUI() {
        // Create a GridBagLayout and GridBagConstraints for the panel
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add userLabel and userTextField to the panel
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(userTextField, gbc);

        // Add passwordLabel and passwordField to the panel
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Create a JPanel for the buttons and add it to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Center the window on the screen and adjust size
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        frame.setVisible(true); // Make the window visible
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
