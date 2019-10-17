package ar.com.ada.api.billeteravirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.repositories.PersonaRepository;
import ar.com.ada.api.billeteravirtual.entities.*;

/**
 * PersonaService
 */
@Service
public class PersonaService {

    @Autowired
    PersonaRepository repoPersona;

    public void crearPersona(Persona p){
        repoPersona.save(p);
    
    }
    
    public void save(Persona p) {
         repoPersona.save(p);
        
    }

    public List<Persona> getPersonas() {

        return repoPersona.findAll();
    }

    public Persona buscarPorNombre(String nombre) {

        return repoPersona.findByNombre(nombre);
    }
 
    
    public Persona buscarPorDni(String dni) {

        return repoPersona.findByDni(dni);
    }

    public Persona buscarPorId(Integer id) {

        Optional<Persona> p = repoPersona.findById(id);
        
        if (p.isPresent())
            return p.get();
        return null;
    }
        
    }



