package org.example;
import java.util.List;

/**
 * Interfaz encargada de gestionar la persistencia de los datos
 */

public interface DataService {



        public List<Juego> findAll();
        boolean validarUsuario(String username, String password);
        void agregarJuego(Juego juego);


    }

