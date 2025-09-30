package org.example.controller;
import org.example.model.FileExplorerModel;
import org.example.view.FileExplorerView;


import javax.swing.*;
import java.awt.event.ActionEvent;

//El controlador es el puente entre vista y modelo.
//Hace dos cosas importantes:
//1. Escucha cuando el usuario hace click en el botón "Buscar".
//2. Pide al modelo los archivos y se los pasa a la vista

    public class FileExplorerController {

        private final FileExplorerView view;
        private final FileExplorerModel model;

        public FileExplorerController(FileExplorerView view, FileExplorerModel model) {
            this.view = view;
            this.model = model;
            initController();
        }

        private void initController() {
            view.boton.addActionListener((ActionEvent e) -> {
                String ruta = view.texto.getText(); //Pide la ruta al campo de texto
                var archivos = model.listarArchivos(ruta); //Llama al modelo para obtener archivos
                view.modelo.clear();
                view.modelo.addAll(archivos); //Muestra los archivos en la lista
            });

            view.lista.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String seleccionado = view.lista.getSelectedValue();
                    JOptionPane.showMessageDialog(view.frame, seleccionado);
                }
            });
        }
    }


