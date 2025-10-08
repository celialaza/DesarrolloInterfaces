package org.example;


    import java.io.*;
    import java.util.ArrayList;
import java.util.List;
    import java.util.Properties;

public class CsvDataService implements DataService {
        private String archivo;
        private String authFile;

        CsvDataService(String csvFile, String authFile)
        {
            archivo=csvFile;
            this.authFile=authFile;
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
        public boolean validarUsuario(String username, String password){
            Properties props = new Properties();

            try (FileInputStream is = new FileInputStream(authFile)) {
                props.load(is);

                String storedUsername = props.getProperty("username");
                String storedPassword = props.getProperty("password");

                // Debug - Imprime en consola para verificar
                System.out.println("=== DEBUG LOGIN ===");
                System.out.println("Usuario ingresado: '" + username + "'");
                System.out.println("Password ingresado: '" + password + "'");
                System.out.println("Usuario guardado: '" + storedUsername + "'");
                System.out.println("Password guardado: '" + storedPassword + "'");
                System.out.println("==================");

                // Verificar que no sean null
                if (storedUsername == null || storedPassword == null) {
                    System.out.println("ERROR: Credenciales no encontradas en auth.properties");
                    return false;
                }

                // Trim para eliminar espacios
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
            }}
    }

