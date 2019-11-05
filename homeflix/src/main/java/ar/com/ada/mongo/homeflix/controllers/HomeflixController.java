package ar.com.ada.mongo.homeflix.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.mongo.homeflix.entities.Episodio;
import ar.com.ada.mongo.homeflix.entities.Pelicula;
import ar.com.ada.mongo.homeflix.entities.Serie;
import ar.com.ada.mongo.homeflix.entities.Temporada;
import ar.com.ada.mongo.homeflix.models.responses.BasicResponse;
import ar.com.ada.mongo.homeflix.services.HomeflixService;
import ar.com.ada.mongo.homeflix.services.PeliculaService;
import ar.com.ada.mongo.homeflix.services.SerieService;

/**
 * HomeflixController
 */
@RestController
public class HomeflixController {

    @Autowired
    HomeflixService homeflixService;

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    SerieService serieService;

    @PostMapping("/peliculas")
    public BasicResponse postPelicula(@RequestBody Pelicula reqPelicula)
    {
        BasicResponse b = new BasicResponse();
        peliculaService.guardar(reqPelicula);

        b.isOk = true;
        b.message = "Pelicula cargada con exito";
        return b;
    }

    @PostMapping("/series")
    public BasicResponse postSerie(@RequestBody Serie reqSerie)
    {
        BasicResponse b = new BasicResponse();
        serieService.guardar(reqSerie);

        b.isOk = true;
        b.message = "Serie cargada con exito";
        return b;
        
    }

    @PostMapping("/temporadas")
    public BasicResponse postTemporada(@PathVariable String hexadecimal, @RequestBody Temporada reqTemporada)
    {
        ObjectId id = new ObjectId(hexadecimal);
        BasicResponse b = new BasicResponse();
        serieService.buscarPorId(id).agregarTemporada(reqTemporada);

        b.isOk = true;
        b.message = "Temporada cargada con exito";
        return b;
        
    }

    @PostMapping("/episodios")
    public BasicResponse postTemporada(@PathVariable String hexadecimal, int nroTemporada, @RequestBody Episodio reqEpisodio)
    {
        
        ObjectId id = new ObjectId(hexadecimal);
        BasicResponse b = new BasicResponse();
        serieService.buscarPorId(id).getTemporada(nroTemporada).agregarEpisodio(reqEpisodio);

        b.isOk = true;
        b.message = "Episodio cargado con exito";
        return b;
        
    }


}