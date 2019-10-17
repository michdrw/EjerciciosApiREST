package ar.com.ada.api.billeteravirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Cuenta;
import ar.com.ada.api.billeteravirtual.repositories.CuentaRepository;

/**
 * CuentaService
 */
@Service
public class CuentaService {

    @Autowired
    CuentaRepository repoCuenta;

	public List<Cuenta> getCuentas() {
		
        return repoCuenta.findAll();
    }
    
    public Cuenta save(Cuenta c) {
        return repoCuenta.save(c);
        
    }

	public Cuenta buscarPorId(int id) {
		Optional<Cuenta> c = repoCuenta.findById(id);
        
        if (c.isPresent())
            return c.get();
        return null;
	}

    
}