package ar.com.ada.api.billeteravirtual.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billeteravirtual.entities.Usuario;

/**
 * UsuarioRepository
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String userName);
    Usuario findByEmail(String email);
    
    
}