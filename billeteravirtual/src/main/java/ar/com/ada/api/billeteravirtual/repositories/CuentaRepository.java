package ar.com.ada.api.billeteravirtual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billeteravirtual.entities.Cuenta;

/**
 * CuentaRepository
 */
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}