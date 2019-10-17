package ar.com.ada.api.billeteravirtual.exceptions;

import ar.com.ada.api.billeteravirtual.entities.Persona;

/**
 * PersonaInfoException
 */
public class PersonaInfoException extends Exception{

    private Persona persona;
    public PersonaInfoException(Persona p, String mensaje) {
        
        super(p.getNombre()+":"+ mensaje);
        this.persona = p;
    }
}