package ar.com.ada.api.billeteravirtual.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.services.PersonaService;


/**
 * SegundaController
 */
@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/personas")
    public List<Persona> GetPersonas()
    {
        List<Persona> lp = personaService.getPersonas();
        
        return lp;
    }

    @GetMapping("/personas/{id}")
    public Persona GetPersonaById(@PathVariable int id)
    {
        Persona p = personaService.buscarPorId(id);
        
        return p;
    }

    
}