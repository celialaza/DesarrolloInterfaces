package org.example;

import javax.swing.*;
import java.awt.event.*;
//opción1
/*public class Detalle extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblDescripcion;





    public Detalle(Juego juego) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        lblID.setText(juego.getId().toString());
        lblNombre.setText(juego.getNombre());
        lblDescripcion.setText(juego.getDescripcion());

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
        dispose();//cierra la ventana
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}*/

//opción2
public class Detalle extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblAño;
    private JLabel lblImagen;


    public Detalle(Juego juego) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        lblID.setText(juego.getId().toString());
        lblNombre.setText(juego.getNombre());
        lblDescripcion.setText(juego.getDescripcion());
        lblAño.setText(juego.getAño().toString());
        lblImagen.setText(juego.getImagenUrl());

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
        dispose();//cierra la ventana
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}


