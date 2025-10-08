package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Principal extends javax.swing.JFrame {
    private JTable table1;
    private JPanel panel1;

    private DataService dataservice;
    private ArrayList<Juego> juegos = new ArrayList<>();

    public Principal(DataService ds) {
        dataservice = ds;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Juegoteca");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);

        var modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        table1.setModel(modelo);

        juegos = (ArrayList<Juego>) dataservice.findAll();
        juegos.forEach( (j)->{
            var fila =  new Object[]{ j.getId(), j.getNombre(),j.getDescripcion() };
            modelo.addRow(fila);
        });

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getSelectionModel().addListSelectionListener( (e)->{
                    if(!e.getValueIsAdjusting() && table1.getSelectedRow()>=0 ){
                        System.out.println("seleccionado: "+table1.getSelectedRow());
                        Juego juego = juegos.get(table1.getSelectedRow());

                        AppSession.juegoSeleccionado = juego;

                        //ContextService.getInstance().setData("juego",juego);

                        (new Detalle()).setVisible(true);
                    }
                }
        );

    }

    public void start(){
        this.setVisible(true);
    }
}