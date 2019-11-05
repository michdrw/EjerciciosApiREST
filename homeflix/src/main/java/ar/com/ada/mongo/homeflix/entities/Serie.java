package ar.com.ada.mongo.homeflix.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Serie
 */
@Document(collection = "Series")
public class Serie extends Contenido {

    private List<Temporada> temporadas = new ArrayList<Temporada>();

    public Temporada getTemporada(int nro) {
        for (Temporada tempo : this.temporadas) {
            if (tempo.getNroTemporada() == nro) {
                return tempo;
            }

        }

        return null;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public void agregarTemporada(Temporada temporada)
    {
        temporadas.add(temporada);
    }

    public int getAnio() {
        return super.getAnio();
    }

    public String getGenero() {
        return super.getGenero();
    }

    public String getNombre() {
        return super.getNombre();
    }

    public void setAnio(int anio) {
        super.setAnio(anio);
    }

    public void setGenero(String genero) {
        super.setGenero(genero);
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }
    
}