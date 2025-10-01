package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Principal extends JFrame {
    private JPanel raiz;
    private JList<String> lista;
    private JButton boton;
    private JTextField texto;

    Principal(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setTitle("Ventana principal");
        this.setContentPane(this.raiz);


        boton.addActionListener(e -> {
            ((DefaultListModel<String>)lista.getModel()).addElement(texto.getText());

        });

        lista.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && lista.getSelectedIndex() >= 0){
                System.out.println(lista.getSelectedIndex());
                ((DefaultListModel<String>)lista.getModel()).remove(lista.getSelectedIndex());

            }

        });
    }
    public void start(Boolean b){
        this.setVisible(b);
    }
}
