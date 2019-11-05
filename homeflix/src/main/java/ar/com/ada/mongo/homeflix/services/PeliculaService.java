package ar.com.ada.mongo.homeflix.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.mongo.homeflix.entities.Pelicula;
import ar.com.ada.mongo.homeflix.repositories.PeliculaRepository;

/**
 * PeliculaService
 */
@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository repoPelicula;

    public void guardar(Pelicula pelicula)
    {
        repoPelicula.save(pelicula);
    }

    public Pelicula buscarPorId(ObjectId id) {
        return repoPelicula.findBy_id(id);
    }

    public Pelicula buscarPorNombre(String nombre) {
        return repoPelicula.findByNombre(nombre);
    }

    public List<Pelicula> getCatalogo() {
        return repoPelicula.findAll();
    }

}