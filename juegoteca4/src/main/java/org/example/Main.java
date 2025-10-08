package org.example;


import javax.swing.*;

//Esta e sla puerta de entrada de la app. El programa EMPIEZA aquí
public class Main {
    public static void main(String[] args) {

        // Crea un servicio de datos CsvDataService
        // que sabe leer dos archivos"juegos.csv" y "auth.properties"
        //Llama a Login y le pasa el servicio de datos
        //Usa Swing.utilities para que todo se ejecute en el hilo correcto


        SwingUtilities.invokeLater(() -> {
            try {
                DataService ds = new CsvDataService("juegos.csv", "auth.properties");
                Login loginDialog = new Login(ds);
                loginDialog.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());

                e.printStackTrace();
                System.exit(1);
            }
            });


        /*var juegos=(new CsvDataService("juegos.csv")).findAll();
        juegos.forEach((j)->{
            System.out.println(j.getNombre());
        });

        DataService ds=new CsvDataService("juegos.csv");
        (new Principal(ds)).start();*/

    }
}
