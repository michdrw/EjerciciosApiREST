package ar.com.ada.api.billeteravirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Billetera;
import ar.com.ada.api.billeteravirtual.entities.Cuenta;
import ar.com.ada.api.billeteravirtual.entities.Persona;
import ar.com.ada.api.billeteravirtual.entities.Usuario;
import ar.com.ada.api.billeteravirtual.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repoUsuario;
    @Autowired
    PersonaService personaService;
    @Autowired
    BilleteraService billeteraService;
    @Autowired
    CuentaService cuentaService;

    public int crearUsuario(String nombre, String dni, Integer edad, String email, String password){
    
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setUsername(email);
        u.setPassword(password);
        

        Persona p = new Persona();
        p.setNombre(nombre);
        p.setEmail(u.getEmail());
        p.setDni(dni);
        p.setEdad(edad);
        p.setUsuario(u);
        personaService.save(p);

        Billetera b = new Billetera();
        b.setPersona(p);
        billeteraService.save(b);
            
        Cuenta c = new Cuenta();
        c.setMoneda("ARS");
        c.setBilletera(b);
        cuentaService.save(c);
        b.agregarCuenta(c);
        billeteraService.save(b);

        personaService.save(p);

        return u.getUsuarioId();
    }
    

    public List<Usuario> getUsuarios() {

        return repoUsuario.findAll();
    }



    public Usuario buscarPorId(int id) {

        Optional<Usuario> u = repoUsuario.findById(id);
        
        if (u.isPresent())
            return u.get();
        return null;
    }
    
    public Usuario save(Usuario u) {
        return repoUsuario.save(u);
        
    }

    public Usuario buscarPorEmail(String email){
    
        return repoUsuario.findByEmail(email);
    }
}
