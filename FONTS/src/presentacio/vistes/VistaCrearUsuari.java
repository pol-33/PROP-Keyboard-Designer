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

public class VistaCrearUsuari {

    private JFrame frame;
    private JPanel panel;
    private JLabel userLabel, passwordLabel, passwordRepeatLabel;
    private JTextField userTextField;
    private JPasswordField passwordField, passwordRepeatField;
    private JButton loginButton, signUpButton;

    public VistaCrearUsuari() {
        initComponents();
        initUI();
        setActionListeners();
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void tancar() {
        frame.dispose();
    }

    private void initComponents() {
        frame = new JFrame("Crear Usuari");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add a margin to the panel

        userLabel = new JLabel("Nom d'Usuari: ");
        passwordLabel = new JLabel("Contrasenya: ");
        passwordRepeatLabel = new JLabel("Repetir contrasenya: ");
        userTextField = new JTextField(20);
        userTextField.setMinimumSize(new Dimension(160, 20)); // Set the minimum size
        ((AbstractDocument) userTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) < 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        passwordField = new JPasswordField(20);
        passwordField.setMinimumSize(new Dimension(160, 20)); // Set the minimum size
        ((AbstractDocument) passwordField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) < 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        passwordRepeatField = new JPasswordField(20);
        passwordRepeatField.setMinimumSize(new Dimension(160, 20)); // Set the minimum size
        ((AbstractDocument) passwordRepeatField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) < 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        loginButton = new JButton("<html><center>Ja tens un compte?<br>Inicia la sessió!</center></html>");
        signUpButton = new JButton("Registra-t'hi");

        // Style the buttons
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setBorder(null); // Remove border from the button
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

        // Add passwordRepeatLabel and passwordRepeatField to the panel
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(passwordRepeatLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordRepeatField, gbc);

        // Create a JPanel for the buttons and add it to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(signUpButton);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Add passwordLabel and passwordField to the panel
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(loginButton, gbc);
        gbc.gridx = 1;

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Center the window on the screen and adjust size
        frame.setSize(350, 270);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        frame.setVisible(true); // Make the window visible
    }

    private void setActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista de login
                VistaLogin vistaLogin = new VistaLogin();
                vistaLogin.mostrar();

                tancar();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());
                String passwordRepeat = new String(passwordRepeatField.getPassword());
                if (!password.equals(passwordRepeat)) {
                    JOptionPane.showMessageDialog(frame, "Les contrasenyes no coincideixen", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    ControladorPresentacio.crearUsuari(username, new String(password)); // Convert the password to a String
                }
            }
        });
    }
}