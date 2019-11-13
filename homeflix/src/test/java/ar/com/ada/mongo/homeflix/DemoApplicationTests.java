package ar.com.ada.mongo.homeflix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.mongo.homeflix.entities.*;
import ar.com.ada.mongo.homeflix.services.PeliculaService;
import ar.com.ada.mongo.homeflix.services.SerieService;
import ar.com.ada.mongo.homeflix.services.PeliculaService.PeliculaValidationType;
import ar.com.ada.mongo.homeflix.services.SerieService.SerieValidationType;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void verificarSerie() {
		SerieService service = new SerieService();

		Serie serie = new Serie();
		

		SerieValidationType validationType = service.verificarSerie(serie);
		
		assertEquals(SerieValidationType.SERIE_DATOS_INVALIDOS, validationType);

	}

	@Test
	void verificarSerieTempDuplicada() {
		SerieService service = new SerieService();

		Serie serie = new Serie();
		serie.setNombre("Breaking Bad");
		serie.setAnio(2008);
		serie.setGenero("Drama");
		Temporada temporada = new Temporada();

		temporada.setNroTemporada(3);

		serie.getTemporadas().add(temporada);
		
		Episodio e = new Episodio();

		temporada.getEpisodios().add(e);
		
		Temporada temporada2 = new Temporada();

		temporada2.setNroTemporada(3);
		
		serie.getTemporadas().add(temporada2);
		
		e = new Episodio();

		temporada2.getEpisodios().add(e);
		

		SerieValidationType serieValidationType = service.verificarSerie(serie);
		
		assertEquals(SerieValidationType.TEMPORADA_DUPLICADA, serieValidationType);

	}

	@Autowired
	PeliculaService peliculaService = new PeliculaService();

	@Test
	void verificarPeliculaDuplicada()
	{
		
		Pelicula p = new Pelicula();
		p.setNombre("The Nightmare before Christmas");
		p.setAnio(1993);
		p.setGenero("Musical");
		p.setGanoOscar(false);
		
		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);

		assertEquals(PeliculaValidationType.PELICULA_DUPLICADA, validationType);
	}

	@Test
	void verificarPeliculaNoDuplicada()
	{
		Pelicula p = new Pelicula();
		p.setNombre("Corpse Bride");
		p.setAnio(2005);
		p.setGenero("Musical");
		p.setGanoOscar(false);

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);

		assertEquals(PeliculaValidationType.PELICULA_OK, validationType);
	}

	@Test
	void modificarPeli()
	{

		Pelicula p = peliculaService.buscarPorNombre("The Nightmare before Christmas");
		p.setAnio(1993);

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);
		assertEquals(PeliculaValidationType.PELICULA_OK, validationType);

	}

}
