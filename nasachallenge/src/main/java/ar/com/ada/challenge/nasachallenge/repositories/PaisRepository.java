package ar.com.ada.challenge.nasachallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.challenge.nasachallenge.entities.Pais;

/**
 * PaisRepository
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

    Pais findByName(String nombre);
}