package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Principal extends JFrame {
    private JList<String> lista;
    private JPanel panel1;
    private JButton boton;
    private JLabel info;

    public Principal() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Alumnos");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(panel1);
        lista.setModel(new DefaultListModel<String>());


        boton.addActionListener(e -> {
            Path archivo=Path.of("alumnos.csv");
            try {
                //Cada vez que hago una lectura de archivo borro la lista y la añado.
                var lineas=Files.readAllLines(archivo);
                ((DefaultListModel<String>)lista.getModel()).clear();

                lineas.forEach(linea -> {
                    ((DefaultListModel<String>)lista.getModel()).addElement(linea);
                });
                info.setText("Alumnos leídos: "+lineas.size());

            } catch (IOException ex) {
                info.setText("Error al leer el archivo");
            }


        });
        lista.addListSelectionListener(e -> {
            int indice = lista.getSelectedIndex();
            String alumno=lista.getSelectedValue();
            if(lista.getValueIsAdjusting()&&indice>=0){
                ((DefaultListModel<String>)lista.getModel()).remove(indice);
                info.setText("Alumno eliminado: "+alumno);


                //Escribir y modificar el archivo
                try(BufferedWriter bfw=new BufferedWriter(new FileWriter("alumnos.csv"))){
                    var modelo= ((DefaultListModel<String>)lista.getModel());
                    for(int i=0;i<modelo.size();i++){
                        bfw.write(modelo.get(i));
                        bfw.newLine();
                    }
                }catch(IOException ex){
                    info.setText("Error al escribir el archivo");
                }

            }

        });
    }
    public void start(Boolean b){
        this.setVisible(b);

    }
}
