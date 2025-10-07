package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {


        var juegos=(new CsvDataService("juegos.csv")).findAll();
        juegos.forEach((j)->{
            System.out.println(j.getNombre());
        });

        DataService ds=new CsvDataService("juegos.csv");
        (new Principal(ds)).start();

        }
    }

