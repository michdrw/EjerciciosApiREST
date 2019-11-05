package ar.com.ada.mongo.homeflix.entities;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Pelicula
 */
@Document(collection = "Peliculas")
public class Pelicula extends Contenido {

    private boolean ganoOscar;

    public boolean isGanoOscar() {
        return ganoOscar;
    }

    public void setGanoOscar(boolean ganoOscar) {
        this.ganoOscar = ganoOscar;
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