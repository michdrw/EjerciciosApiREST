package ar.com.ada.mongo.homeflix.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Contenido
 */

public class Contenido {

    private String nombre;
    private String genero;
    private int anio;
    @Id
    private ObjectId _id;

    public Contenido() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

}