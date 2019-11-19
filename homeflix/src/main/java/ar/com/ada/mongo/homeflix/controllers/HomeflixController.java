package ar.com.ada.mongo.homeflix.controllers;

import java.security.Principal;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import ar.com.ada.mongo.homeflix.services.UsuarioService;

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

    @GetMapping("/peliculas")
    public ResponseEntity<?> GetPeliculas(Principal principal) {

        List<Pelicula> peliculas = peliculaService.getCatalogo();
        if (peliculas.size() == 0)
        return (ResponseEntity<?>) ResponseEntity.notFound();

        return ResponseEntity.ok(peliculas);
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

    @PostMapping("/series/{id}/temporadas")
    public BasicResponse postTemporada(@PathVariable String id, @RequestBody Temporada reqTemporada)
    {
        ObjectId oId = new ObjectId(id);
        BasicResponse b = new BasicResponse();
        Serie s = serieService.buscarPorId(oId);
        s.agregarTemporada(reqTemporada);
        serieService.guardar(s);


        b.isOk = true;
        b.message = "Temporada cargada con exito";
        return b;
        
    }

    @PostMapping("/series/{id}/temporada/{nroTemporada}/episodios")
    public BasicResponse postTemporada(@PathVariable String id, int nroTemporada, @RequestBody Episodio reqEpisodio)
    {
        
        ObjectId oId = new ObjectId(id); //hexadecimal
        BasicResponse b = new BasicResponse();
        serieService.buscarPorId(oId).getTemporada(nroTemporada).agregarEpisodio(reqEpisodio);

        b.isOk = true;
        b.message = "Episodio cargado con exito";
        return b;
        
    }

    @GetMapping("/series")
    public ResponseEntity<?> GetSeries(Principal principal) {

        List<Serie> series = serieService.getCatalogo();
        if (series.size() == 0)
            return (ResponseEntity<?>) ResponseEntity.notFound();

        return ResponseEntity.ok(series);
    }

}