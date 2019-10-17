package ar.com.ada.api.billeteravirtual.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.billeteravirtual.entities.Cuenta;
import ar.com.ada.api.billeteravirtual.services.CuentaService;

/**
 * CuentaController
 */
@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping("/cuentas")
    public List<Cuenta> GetCuentas()
    {
        List<Cuenta> lp = cuentaService.getCuentas();
        
        return lp;
    }

    @GetMapping("/cuentas/{id}")
    public Cuenta GetCuentaById(@PathVariable int id)
    {
        Cuenta c = cuentaService.buscarPorId(id);
        
        return c;
    }
}