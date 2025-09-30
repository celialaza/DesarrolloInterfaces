package org.example.model;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

//En el modelo está la inteligencia del programa. ->Acceder al sistema de archivos.

public class FileExplorerModel {

    public ArrayList<String> listarArchivos(String ruta) {
        var salida = new ArrayList<String>();
        Path path = Path.of(ruta); //Convierte el texto a ruta

        if (Files.isDirectory(path)) {
            try {
                for (Path p : Files.list(path).toList()) { //Files.list(path) Lista archivos en esa ruta                    salida.add(p.getFileName().toString());
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return salida;
    }
}
