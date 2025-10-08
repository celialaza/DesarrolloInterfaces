package org.example;

import javax.swing.*;
import java.awt.event.*;



public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private DataService dataService;

//recibe el DataService(CsvDataService) de Main
    public Login(DataService ds) {
        dataService = ds;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setTitle("Iniciar Sesión");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        labelUsername.setText("Usuario: ");
        labelPassword.setText("Contraseña: ");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    //cuando haces clic en "OK", usa el método validarUsuario de CsvDataService
    //para chequear con "auth.properties"
    //Si es correcto, marca AppSession.isAuthenticates=true(como una banderita que dice"estás dentro") y abre Principal
    //pasándole el mismo DataService(CsvDataService)

    private void onOK() {
        String username = textFieldUsername.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Validar que no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor ingrese usuario y contraseña",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar credenciales
        try {
            if (((CsvDataService) dataService).validarUsuario(username, password)) {
                AppSession.isAuthenticated = true;
                dispose();
                SwingUtilities.invokeLater(() -> {
                    (new Principal(dataService)).start();
                });
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuario o contraseña incorrectos",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE
                );
                passwordField.setText("");
                textFieldUsername.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al validar credenciales: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}



