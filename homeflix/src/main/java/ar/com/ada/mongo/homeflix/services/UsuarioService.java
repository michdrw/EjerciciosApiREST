package ar.com.ada.mongo.homeflix.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import ar.com.ada.mongo.homeflix.entities.Usuario;
import ar.com.ada.mongo.homeflix.repositories.UsuarioRepository;
import ar.com.ada.mongo.homeflix.security.Crypto;
import ar.com.ada.mongo.homeflix.sistema.comms.EmailService;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repoUsuario;

    @Autowired
    EmailService emailService;

    public List<Usuario> getUsuarios() {

        return repoUsuario.findAll();
    }

    public Usuario buscarPorId(ObjectId _id) {

        Optional<Usuario> u = repoUsuario.findBy_id(_id);

        if (u.isPresent())
            return u.get();
        return null;
    }

    public Usuario buscarPorEmail(String email) {

        Optional<Usuario> u = repoUsuario.findByEmail(email);
        
        if (u.isPresent())
        return u.get();
        return null;
    }

    public Usuario buscarPorUsername(String username) {

        Optional<Usuario> u = repoUsuario.findByUsername(username);

        if (u.isPresent())
            return u.get();
        return null;

    }

    public ObjectId crearUsuario(String fullname, String email, String password) {

        Usuario u = new Usuario();
        u.setFullname(fullname);
        u.setUsername(email);
        u.setEmail(email);

        String passwordEncriptada;
        passwordEncriptada = Crypto.encrypt(password, u.getUsername());
        u.setPassword(passwordEncriptada);

        repoUsuario.save(u);

        emailService.SendEmail(u.getEmail(), "Bienvenido a Homeflix!",
                "Hola " + u.getFullname()
                        + "\nGracias por registrarte.\n");

        return u.get_id();

    }

    public void login(String username, String password) {

        Optional<Usuario> u = repoUsuario.findByUsername(username);

        if (u == null || !u.get().getPassword().equals(Crypto.encrypt(password, u.get().getUsername()))) {

            throw new BadCredentialsException("Usuario o contraseña invalida");
        }

    }

}