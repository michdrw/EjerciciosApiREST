package ar.com.ada.api.spookify.entities;

/**
 * Artista
 */
public class Artista {

    private String nombre;
    private int edad;
    private String origen;

    public String getNombre() {
        return nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



}