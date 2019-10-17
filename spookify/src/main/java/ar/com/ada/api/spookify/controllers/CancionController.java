package ar.com.ada.api.spookify.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.spookify.entities.Cancion;

/**
 * CancionController
 */
@RestController
public class CancionController {

    @GetMapping(value = "/temas")
    public List<Cancion> getTodas(){

        List<Cancion> lista = new ArrayList<Cancion>();
        

        Cancion c = new Cancion();
        c.setTitulo("This is halloween");
        c.setDuracion(3.0);

        lista.add(c);

        c = new Cancion();
        c.setTitulo("Let it snow");
        c.setDuracion(2.0);

        lista.add(c);
        
        return lista;
    }

    @GetMapping(value = "/primertema")
    public Cancion getPrimerTema(){

        return getTodas().get(0);
    }

}