package ar.com.ada.api.challenge.challenge161019.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.challenge.challenge161019.entities.Categoria;

/**
 * CategoriaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>  {

    
}