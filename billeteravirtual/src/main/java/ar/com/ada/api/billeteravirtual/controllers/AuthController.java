package ar.com.ada.api.billeteravirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.billeteravirtual.models.requests.RegistrationRequest;
import ar.com.ada.api.billeteravirtual.models.responses.RegistrationResponse;
import ar.com.ada.api.billeteravirtual.services.UsuarioService;

/**
 * AuthController
 */
@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("auth/register")
    public RegistrationResponse postRegisterUser(@RequestBody RegistrationRequest req) {


        RegistrationResponse r = new RegistrationResponse();
        int usuarioCreadoId = usuarioService.crearUsuario(req.nombre, req.dni, req.edad, req.email, req.password);
        

        r.isOk = true;
        r.message = "Te registraste con exito";
        r.usuarioId = usuarioCreadoId; 

        return r;
        
    }
}