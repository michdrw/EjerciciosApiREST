package ar.com.ada.api.challenge.challenge161019.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.challenge.challenge161019.entities.Categoria;
import ar.com.ada.api.challenge.challenge161019.repositories.CategoriaRepository;

/**
 * CategoriaService
 */
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repoCategoria;

    public Categoria save(Categoria c) {
        return repoCategoria.save(c);
        
    }

    public void crearCategoria(String nombre, BigDecimal sueldoBase) {
        
        Categoria c = new Categoria();
        c.setNombre(nombre);
        c.setSueldoBase(sueldoBase);

        repoCategoria.save(c);
    }
    
    public List<Categoria> getCategorias() {

        return repoCategoria.findAll();
    }

    public Categoria buscarPorId(Integer id) {

        Optional<Categoria> c = repoCategoria.findById(id);

        if (c.isPresent()) {
            return c.get();
        }
        return null;
    }

    
}