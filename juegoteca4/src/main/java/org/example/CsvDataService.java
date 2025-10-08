
package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CsvDataService implements DataService {
    private String archivo;
    private String authFile;

    CsvDataService(String csvFile, String authFile) {
        archivo = csvFile;
        this.authFile = authFile;
    }

    @Override
    public List<Juego> findAll() {
        var salida = new ArrayList<Juego>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            var contenido = br.lines();
            contenido.forEach(line -> {
                String[] lineArray = line.split(",");
                Juego juego = new Juego();
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontro el archivo " + archivo);
        } catch (IOException e) {
            throw new RuntimeException("Error en el archivo " + archivo);
        }
        return salida;
    }

    public boolean validarUsuario(String username, String password) {
        Properties props = new Properties();
        try (FileInputStream is = new FileInputStream(authFile)) {
            props.load(is);
            String storedUsername = props.getProperty("username");
            String storedPassword = props.getProperty("password");
            System.out.println("=== DEBUG LOGIN ===");
            System.out.println("Usuario ingresado: '" + username + "'");
            System.out.println("Password ingresado: '" + password + "'");
            System.out.println("Usuario guardado: '" + storedUsername + "'");
            System.out.println("Password guardado: '" + storedPassword + "'");
            System.out.println("==================");
            if (storedUsername == null || storedPassword == null) {
                System.out.println("ERROR: Credenciales no encontradas en auth.properties");
                return false;
            }
            storedUsername = storedUsername.trim();
            storedPassword = storedPassword.trim();
            boolean isValid = storedUsername.equals(username) && storedPassword.equals(password);
            System.out.println("Resultado validación: " + isValid);
            return isValid;
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: No se encontró el archivo " + authFile);
            e.printStackTrace();
            throw new RuntimeException("No se encontró el archivo " + authFile);
        } catch (IOException e) {
            System.err.println("ERROR: Error al leer el archivo " + authFile);
            e.printStackTrace();
            throw new RuntimeException("Error en el archivo " + authFile);
        }
    }

    public void agregarJuego(Juego juego) {
        // Obtener el ID más alto para asignar uno nuevo
        List<Juego> juegos = findAll();
        int maxId = juegos.stream().mapToInt(Juego::getId).max().orElse(0);
        juego.setId(maxId + 1);

        // Establecer valores predeterminados para campos no proporcionados
        if (juego.getDescripcion() == null) juego.setDescripcion("");
        if (juego.getDesarrollador() == null) juego.setDesarrollador("");
        if (juego.getValoracion() == null) juego.setValoracion(0);
        if (juego.getTipo() == null) juego.setTipo("");
        if (juego.getImagenUrl() == null) juego.setImagenUrl("");
        if (juego.getVideoUrl() == null) juego.setVideoUrl("");
        if (juego.getOwnerId() == null) juego.setOwnerId(0);
        if (juego.getStatus() == null) juego.setStatus("ACTIVO");

        // Escribir el nuevo juego en el archivo CSV
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.newLine();
            bw.write(String.format("%d,%s,%d,%s,%s,%s,%d,%s,%s,%s,%s,%d,%s",
                    juego.getId(),
                    juego.getNombre(),
                    juego.getAño(),
                    juego.getPlataforma(),
                    "", // Campo vacío (no usado en el formulario)
                    juego.getDesarrollador(),
                    juego.getValoracion(),
                    juego.getTipo(),
                    juego.getDescripcion(),
                    juego.getImagenUrl(),
                    juego.getVideoUrl(),
                    juego.getOwnerId(),
                    juego.getStatus()));
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo " + archivo);
        }
    }
}















