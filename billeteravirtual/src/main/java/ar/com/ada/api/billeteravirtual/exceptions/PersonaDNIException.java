package ar.com.ada.api.billeteravirtual.exceptions;

import ar.com.ada.api.billeteravirtual.entities.Persona;

/**
 * PersonaDniException
 */
public class PersonaDNIException extends PersonaInfoException{

    public PersonaDNIException(Persona p, String mensaje) {
        super(p, mensaje);
    }
}