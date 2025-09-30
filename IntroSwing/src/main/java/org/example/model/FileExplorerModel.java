package org.example.model;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileExplorerModel {

    public ArrayList<String> listarArchivos(String ruta) {
        var salida = new ArrayList<String>();
        Path path = Path.of(ruta);

        if (Files.isDirectory(path)) {
            try {
                for (Path p : Files.list(path).toList()) {
                    salida.add(p.getFileName().toString());
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return salida;
    }
}
