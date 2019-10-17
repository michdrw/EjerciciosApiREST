package ar.com.ada.api.spookify.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.spookify.entities.Artista;

/**
 * ArtistaController
 */
@RestController
public class ArtistaController {

    @GetMapping ( value = "/artistas" )
    public List<Artista> getTodas(){
    
        List<Artista> lista = new ArrayList<Artista>();

        Artista a = new Artista();
        a.setNombre("Miku Hatsune");
        a.setEdad(16);
        a.setOrigen("Japon");

        lista.add(a);

        a = new Artista();
        a.setNombre("Billie Eilish");
        a.setEdad(17);
        a.setOrigen("USA");

        lista.add(a);

        
        return lista;
    }
}