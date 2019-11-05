package ar.com.ada.mongo.homeflix.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.mongo.homeflix.entities.Pelicula;

/**
 * PeliculaRepository
 */
@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula, ObjectId>{
    Pelicula findBy_id(ObjectId _id); 
    Pelicula findByNombre(String nombre);
    
}