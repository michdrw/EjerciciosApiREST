package ar.com.ada.mongo.homeflix.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.mongo.homeflix.entities.Episodio;
import ar.com.ada.mongo.homeflix.entities.Pelicula;
import ar.com.ada.mongo.homeflix.entities.Serie;
import ar.com.ada.mongo.homeflix.entities.Temporada;

/**
 * Homeflix
 */
@Service
public class HomeflixService {

    @Autowired
    SerieService serieService;

    @Autowired
    PeliculaService peliculaService;


    public void grabar(Serie serie) {
        serieService.guardar(serie);
    }

    public Serie buscarSerie(String nombre) {
        return serieService.buscarPorNombre(nombre);
    }

    public Serie buscarSerie(ObjectId id) {
        return serieService.buscarPorId(id);
    }

    public List<Serie> getCatalogoSeries() {
        return serieService.getCatalogo();
    }

    public void cargarTemporadas(ObjectId oid, Temporada temporada)
    {
        Serie s = buscarSerie(oid);
        s.agregarTemporada(temporada);
        serieService.guardar(s);

    }

    public void cargarEpisodios(ObjectId oid, int nroTemporada, Episodio episodio)
    {
        Serie s = buscarSerie(oid);
        s.getTemporada(nroTemporada).agregarEpisodio(episodio);
        serieService.guardar(s);
    }

    public Pelicula buscarPelicula(String nombre) {
        return peliculaService.buscarPorNombre(nombre);
    }

    public Pelicula buscarPelicula(ObjectId id) {
        return peliculaService.buscarPorId(id);
    }

    public List<Pelicula> getCatalogoPeliculas() {
        return peliculaService.getCatalogo();
    }

}
