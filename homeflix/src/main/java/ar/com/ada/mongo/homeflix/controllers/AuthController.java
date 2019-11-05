package ar.com.ada.mongo.homeflix.controllers;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.mongo.homeflix.models.requests.LoginRequest;
import ar.com.ada.mongo.homeflix.models.requests.RegistrationRequest;
import ar.com.ada.mongo.homeflix.models.responses.JwtResponse;
import ar.com.ada.mongo.homeflix.models.responses.RegistrationResponse;
import ar.com.ada.mongo.homeflix.security.jwt.JWTTokenUtil;
import ar.com.ada.mongo.homeflix.services.JWTUserDetailsService;
import ar.com.ada.mongo.homeflix.services.UsuarioService;

/**
 * AuthController
 */
@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @PostMapping("auth/register")
    public RegistrationResponse postRegisterUser(@RequestBody RegistrationRequest req)  {
        RegistrationResponse r = new RegistrationResponse();

        ObjectId uId = usuarioService.crearUsuario(req.fullname, req.email, req.password);

        r.isOk = true;
        r.message = "Te registraste con exito";
        r.userId = uId;
        return r;

    }

    @PostMapping("auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
            throws Exception {

        usuarioService.login(authenticationRequest.username, authenticationRequest.password);

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

}