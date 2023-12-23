package presentacio.vistes;

import presentacio.controladors.ControladorPresentacio;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
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
        frame = new JFrame("Inici de sessió");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Afegir marges al panell

        userLabel = new JLabel("Nom d'Usuari: ");
        passwordLabel = new JLabel("Contrasenya: ");
        userTextField = new JTextField(20);
        userTextField.setMinimumSize(new Dimension(160, 20)); // Establir la mida mínima
        ((AbstractDocument) userTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) < 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        passwordField = new JPasswordField(20);
        passwordField.setMinimumSize(new Dimension(160, 20)); // Establir la mida mínima
        ((AbstractDocument) passwordField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) < 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        loginButton = new JButton("Inicia la sessió");
        signUpButton = new JButton("<html><center>No tens un compte?<br>Registra't-hi ara!</center></html>");

        // Estilitzar els botons
        loginButton.setBackground(Color.LIGHT_GRAY);
        signUpButton.setBackground(Color.LIGHT_GRAY);
        signUpButton.setBorder(null); // Treure borde al botó
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
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loginButton);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Add passwordLabel and passwordField to the panel
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(signUpButton, gbc);
        gbc.gridx = 1;

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Center the window on the screen and adjust size
        frame.setSize(350, 275);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        frame.setVisible(true); // Make the window visible

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
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
                // Obre la vista de registrar usuari
                VistaCrearUsuari vistaCrearUsuari = new VistaCrearUsuari();
                vistaCrearUsuari.mostrar();

                // Tancar la vista de login
                tancar();
            }
        });
    }
}
