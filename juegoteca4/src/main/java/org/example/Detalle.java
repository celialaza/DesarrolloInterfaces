package org.example;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.event.*;

public class Detalle extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelId;
    private JLabel labelNombre;
    private JLabel labelDescripcion;
    private JLabel labelAño;
    private JLabel labelImagen;
    private JLabel labelPlataforma;
    private JLabel labelDesarrollador;
    private JLabel labelValoracion;
    private JLabel labelTipo;
    private JLabel labelVideo;


    public Detalle() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Juego juego = AppSession.juegoSeleccionado;
        //juego = ContextService.getInstance().getData("juego");

        labelId.setText("ID: "+juego.getId().toString());
        labelNombre.setText("NOMBRE: "+juego.getNombre());
        labelDescripcion.setText("DESCRIPCIÓN: "+juego.getDescripcion());
        labelAño.setText("AÑO: "+juego.getAño().toString());
        labelImagen.setText("IMAGEN: "+juego.getImagenUrl().toString());
        labelPlataforma.setText("PLATAFORMA: "+juego.getPlataforma());
        labelDesarrollador.setText("DESARROLLADOR: "+juego.getDesarrollador());
        labelValoracion.setText("VALORACIÓN: "+juego.getValoracion().toString());
        labelTipo.setText("TIPO: "+juego.getTipo().toString());
        labelVideo.setText("VIDEO: "+juego.getVideoUrl().toString());

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

        pack();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}