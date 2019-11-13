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

    public void guardar(Pelicula pelicula) {
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

    public enum PeliculaValidationType {
        PELICULA_OK, 
        PELICULA_DUPLICADA,
        PELICULA_DATOS_INVALIDOS
    }

    public PeliculaValidationType verificarPelicula(Pelicula pelicula) {
        if (pelicula.getNombre() == null) {
            return PeliculaValidationType.PELICULA_DATOS_INVALIDOS;
        }
        if (pelicula.getAnio() <= 0) {
            return PeliculaValidationType.PELICULA_DATOS_INVALIDOS;
        }

        Pelicula p = this.buscarPorNombre(pelicula.getNombre());
        if (p != null) {
            if (pelicula.get_id() != null) {
                if ((pelicula.get_id().toString()).equals(p.get_id().toString())) {
                    return PeliculaValidationType.PELICULA_OK;
                } else {
                    return PeliculaValidationType.PELICULA_DUPLICADA;
                }
            } 
            else
            return PeliculaValidationType.PELICULA_DUPLICADA;
        }

        return PeliculaValidationType.PELICULA_OK;
    }
}