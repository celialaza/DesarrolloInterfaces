package org.example.controller;
import org.example.model.FileExplorerModel;
import org.example.view.FileExplorerView;


import javax.swing.*;
import java.awt.event.ActionEvent;

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
                String ruta = view.texto.getText();
                var archivos = model.listarArchivos(ruta);
                view.modelo.clear();
                view.modelo.addAll(archivos);
            });

            view.lista.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String seleccionado = view.lista.getSelectedValue();
                    JOptionPane.showMessageDialog(view.frame, seleccionado);
                }
            });
        }
    }


