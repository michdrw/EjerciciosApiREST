package ar.com.ada.mongo.homeflix.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.mongo.homeflix.entities.Serie;
import ar.com.ada.mongo.homeflix.repositories.SerieRepository;

/**
 * SerieService
 */
@Service
public class SerieService {
    
    @Autowired
    SerieRepository repoSerie;


    public void guardar(Serie serie)
    {
        repoSerie.save(serie);
    }
    
    public Serie buscarPorId(ObjectId id) {
        return repoSerie.findBy_id(id);
    }

    public Serie buscarPorNombre(String nombre) {
        return repoSerie.findByNombre(nombre);
    }

    public List<Serie> getCatalogo() {
        return repoSerie.findAll();
    }



}