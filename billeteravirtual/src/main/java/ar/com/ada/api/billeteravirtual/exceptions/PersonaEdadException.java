package ar.com.ada.api.billeteravirtual.exceptions;

import ar.com.ada.api.billeteravirtual.entities.Persona;

/**
 * PersonaEdadException
 */
public class PersonaEdadException extends PersonaInfoException{

    public PersonaEdadException(Persona p, String mensaje) {
        super(p, mensaje);
    } 
}