package org.example.view;
import javax.swing.*;
import java.awt.*;

    public class FileExplorerView {

        public JFrame frame;
        public JLabel titulo;
        public JButton boton;
        public JTextField texto;
        public JList<String> lista;
        public DefaultListModel<String> modelo;

        public FileExplorerView() {
            frame = new JFrame("Explorador");
            titulo = new JLabel("Explorador de archivos", JLabel.CENTER);
            boton = new JButton("Buscar");
            texto = new JTextField(40);
            lista = new JList<>();
            modelo = new DefaultListModel<>();

            lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lista.setModel(modelo);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            frame.add(titulo, BorderLayout.NORTH);

            JPanel panel = new JPanel(new FlowLayout());
            panel.add(boton);
            panel.add(texto);

            frame.add(panel, BorderLayout.SOUTH);
            frame.add(new JScrollPane(lista), BorderLayout.CENTER);
        }

        public void show() {
            frame.setVisible(true);
        }
    }


