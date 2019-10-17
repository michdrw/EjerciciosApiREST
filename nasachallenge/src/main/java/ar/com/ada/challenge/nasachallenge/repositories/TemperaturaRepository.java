package ar.com.ada.challenge.nasachallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.challenge.nasachallenge.entities.Temperatura;

/**
 * TemperaturaRepository
 */
@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer>{

    
}