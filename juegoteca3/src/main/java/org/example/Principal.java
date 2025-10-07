package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Principal extends javax.swing.JFrame {
    private JTable table1;
    private JPanel panel1;

    private DataService dataservice;

    //opción2
    private ArrayList<Juego> juegos=new ArrayList<>();


    public Principal(DataService ds) {
        dataservice=ds;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Juegoteca");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);

        var modelo=new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        table1.setModel(modelo);

        //op1
        /*ArrayList<Juego> juegos = (ArrayList<Juego>) dataservice.findAll();*/

        //opción2
        juegos = (ArrayList<Juego>) dataservice.findAll();

        juegos.forEach((j)->{
            var fila= new Object[]{j.getId(),j.getNombre(),j.getDescripcion()};
            modelo.addRow(fila);
        });
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getSelectionModel().addListSelectionListener((e)->{
            if(!e.getValueIsAdjusting()&&table1.getSelectedRow()>=0){
                System.out.println("seleccionado: "+table1.getSelectedRow());

                //op2
                Juego juego=juegos.get(table1.getSelectedRow());
                (new Detalle(juego)).setVisible(true);





                /*opción1
                Juego juego=new Juego();
                juego.setId((Integer)table1.getValueAt(table1.getSelectedRow(), 0));
                juego.setNombre((String)table1.getValueAt(table1.getSelectedRow(), 1));
                juego.setDescripcion((String)table1.getValueAt(table1.getSelectedRow(), 2));
                (new Detalle(juego)).setVisible(true);

                 */
            }

                }
                );

    }
    public void start(){
        this.setVisible(true);
    }
}
