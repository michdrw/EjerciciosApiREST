package ar.com.ada.mongo.homeflix.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Temporada
 */
public class Temporada {

    private int nroTemporada;
    private List<Episodio> episodios = new ArrayList<Episodio>();
    
    public void agregarEpisodio(Episodio episodio)
    {
        episodios.add(episodio);
    }

    public Episodio getEpisodio(int nro)
    {
        for (Episodio epi : this.episodios) {
            if(epi.getNroEpisodio() == nro)
            {
                return epi;
            }
            
        }

        return null;
    }

    public int getNroTemporada() {
        return nroTemporada;
    }

    public void setNroTemporada(int nroTemporada) {
        this.nroTemporada = nroTemporada;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

}