package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataService implements DataService {
    private String archivo;
    CsvDataService(String csvFile) {
        archivo=csvFile;
    }
    @Override
    public List<Juego> findAll() {
        var salida=new ArrayList<Juego>();



        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
            var contenido=br.lines();

        contenido.forEach(line -> {

            String[] lineArray=line.split(",");
            Juego juego=new Juego();
            juego.setId(Integer.parseInt(lineArray[0]));
            juego.setNombre(lineArray[1]);
            juego.setAño(Integer.parseInt(lineArray[2]));
            juego.setPlataforma(lineArray[3]);
            juego.setDesarrollador(lineArray[5]);
            juego.setValoracion(Integer.parseInt(lineArray[6]));
            juego.setTipo(lineArray[7]);
            juego.setDescripcion(lineArray[8]);
            juego.setImagenUrl(lineArray[9]);
            juego.setVideoUrl(lineArray[10]);
            juego.setOwnerId(Integer.parseInt(lineArray[11]));
            juego.setStatus(lineArray[12]);


            salida.add(juego);


            });
        }catch(FileNotFoundException e){
            throw new RuntimeException("No se encontro el archivo "+archivo);
        }catch(IOException e){
            throw new RuntimeException("Error en el archivo "+archivo);
        }

        return salida;
    }
}
