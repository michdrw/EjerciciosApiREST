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

	
	@Autowired
	SerieService serieService = new SerieService();

	@Test
	void verificarSerie() {

		Serie serie = new Serie();
		SerieValidationType validationType = serieService.verificarSerie(serie);
		assertEquals(SerieValidationType.SERIE_DATOS_INVALIDOS, validationType);
	}

	@Test
	void verificarSerieDuplicada()
	{
		Serie s = new Serie();
		s.setNombre("Friends");
		s.setAnio(1994);
		s.setGenero("Comedia");

		Temporada t = new Temporada();
		t.setNroTemporada(2);
		s.getTemporadas().add(t);
		
		Episodio e = new Episodio();
		e.setNroEpisodio(5);
		e.setTitulo("The One with Five Steaks and an Eggplant");
		e.setDuracion(25);

		e = new Episodio();
		e.setNroEpisodio(10);
		e.setTitulo("The One with Russ");
		e.setDuracion(26);

		t.getEpisodios().add(e);
		
		SerieValidationType validationType = serieService.verificarSerie(s);
		assertEquals(SerieValidationType.SERIE_DUPLICADA, validationType);
	}

	@Test
	void verificarSerieNoDuplicada()
	{
		Serie s = new Serie();
		s.setNombre("Brooklyn 99");
		s.setAnio(2013);
		s.setGenero("Comedia");

		Temporada t = new Temporada();
		t.setNroTemporada(5);
		s.getTemporadas().add(t);
		
		Episodio e = new Episodio();
		e.setNroEpisodio(4);
		e.setTitulo("Halloveen");
		e.setDuracion(22);

		e = new Episodio();
		e.setNroEpisodio(16);
		e.setTitulo("NutriBoom");
		e.setDuracion(22);

		t.getEpisodios().add(e);
		
		SerieValidationType validationType = serieService.verificarSerie(s);
		assertEquals(SerieValidationType.SERIE_OK, validationType);
	}

	@Test
	void verificarSerieTempDuplicada() {

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
		

		SerieValidationType serieValidationType = serieService.verificarSerie(serie);
		
		assertEquals(SerieValidationType.TEMPORADA_DUPLICADA, serieValidationType);

	}

	@Autowired
	PeliculaService peliculaService = new PeliculaService();

	@Test
	void verificarPeliculaDatosIncompletos()
	{
		Pelicula p = new Pelicula();
		p.setAnio(2012);
		p.setGanoOscar(false);
		p.setGenero("Animada");

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);

		assertEquals(PeliculaValidationType.PELICULA_DATOS_INVALIDOS, validationType);
	}

	@Test
	void verificarPeliculaDatosCompletos()
	{
		Pelicula p = new Pelicula();
		p.setNombre("Frankenweenie");
		p.setAnio(2012);
		p.setGanoOscar(false);
		p.setGenero("Animada");

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);

		assertEquals(PeliculaValidationType.PELICULA_OK, validationType);
	}

	@Test
	void verificarPeliculaAanioInexistente()
	{
		Pelicula p = new Pelicula();
		p.setNombre("Frankenweenie");
		p.setAnio(0);
		p.setGanoOscar(false);
		p.setGenero("Animada");

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);

		assertEquals(PeliculaValidationType.PELICULA_DATOS_INVALIDOS, validationType);
	}
	
	@Test
	void verificarPeliculaAanioExistente()
	{
		Pelicula p = new Pelicula();
		p.setNombre("Frankenweenie");
		p.setAnio(2012);
		p.setGanoOscar(false);
		p.setGenero("Animada");

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);

		assertEquals(PeliculaValidationType.PELICULA_OK, validationType);
	}

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
	void verificarPeliculaConMismoId()
	{
		Pelicula p = peliculaService.buscarPorNombre("The Nightmare before Christmas");
		p.setAnio(1993);

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);
		assertEquals(PeliculaValidationType.PELICULA_OK, validationType);
	}

	@Test
	void verificarPeliculaConDistintoId()
	{
		Pelicula p = peliculaService.buscarPorNombre("BeetleJuice");
		p.setNombre("The Nightmare before Christmas");

		PeliculaValidationType validationType = peliculaService.verificarPelicula(p);
		assertEquals(PeliculaValidationType.PELICULA_DUPLICADA, validationType);
	}


}
