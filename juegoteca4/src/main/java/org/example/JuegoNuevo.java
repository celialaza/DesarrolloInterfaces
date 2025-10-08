package org.example;

import javax.swing.*;
import java.awt.event.*;

public class JuegoNuevo extends JDialog {
    private JTextField textFieldNombre;
    private JTextField textFieldAño;
    private JTextField textFieldPlataforma;
    private JLabel lblNombre;
    private JLabel lblAño;
    private JLabel lblPlataforma;
    private JButton buttonGuardar;
    private JButton buttonCancelar;
    private JPanel panelNuevo;
    private DataService dataService;

    public JuegoNuevo(DataService ds) {
        this.dataService = ds;
        setContentPane(panelNuevo);
        setModal(true);
        setTitle("Añadir Nuevo Juego");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblNombre.setText("Nombre: ");
        lblAño.setText("Año: ");
        lblPlataforma.setText("Plataforma: ");
        buttonGuardar.setText("Guardar");
        buttonCancelar.setText("Cancelar");


        buttonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGuardar();
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        });

        // Cerrar con la cruz
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancelar();
            }
        });

        // Cerrar con ESC
        panelNuevo.registerKeyboardAction(e -> onCancelar(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onGuardar() {
        String nombre = textFieldNombre.getText().trim();
        String añoStr = textFieldAño.getText().trim();
        String plataforma = textFieldPlataforma.getText().trim();

        // Validar campos
        if (nombre.isEmpty() || añoStr.isEmpty() || plataforma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int año;
        try {
            año = Integer.parseInt(añoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear nuevo juego
        Juego juego = new Juego();
        juego.setNombre(nombre);
        juego.setAño(año);
        juego.setPlataforma(plataforma);

        // Guardar en el CSV
        try {
            ((CsvDataService) dataService).agregarJuego(juego);
            JOptionPane.showMessageDialog(this, "Juego añadido correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el juego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancelar() {
        dispose();
    }
}