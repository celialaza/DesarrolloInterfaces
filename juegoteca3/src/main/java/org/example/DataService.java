package org.example;

import java.util.List;
/**
 * Interfaz encargada de gestionar la persistencia de los datos
 */
public interface DataService<juego> {

    public List<Juego> findAll();

}


