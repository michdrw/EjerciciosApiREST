package ar.com.ada.api.billeteravirtual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billeteravirtual.entities.Billetera;

/**
 * BilleteraRepository
 */
@Repository
public interface BilleteraRepository extends JpaRepository<Billetera, Integer> {
    
}