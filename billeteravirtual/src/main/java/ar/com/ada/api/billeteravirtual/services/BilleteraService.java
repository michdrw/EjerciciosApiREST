package ar.com.ada.api.billeteravirtual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Billetera;
import ar.com.ada.api.billeteravirtual.entities.Usuario;
import ar.com.ada.api.billeteravirtual.repositories.BilleteraRepository;


/**
 * MovimientoService
 */
@Service
public class BilleteraService {

    @Autowired
    BilleteraRepository repoBilletera;
    @Autowired
    UsuarioService usuarioService;

    public Billetera save(Billetera b) {
        return repoBilletera.save(b);
        
    }

    public Billetera buscarPorId(Integer id) {

        Optional<Billetera> b = repoBilletera.findById(id);
        
        if (b.isPresent())
            return b.get();
        return null;
    }

    public void crearTransferencia(Double importe, Integer billeteraIdOrigen, String emailDestino) {
        Billetera b = repoBilletera.findById(billeteraIdOrigen).get();
        Usuario u = usuarioService.buscarPorEmail(emailDestino);
        b.movimientoTransferencia(importe, b.getPersona().getUsuario(), u);

        repoBilletera.save(b);
        repoBilletera.save(u.getPersona().getBilletera());
    
    }

    public double consultarSaldo(Integer billeteraId){
        Billetera b = repoBilletera.findById(billeteraId).get();
        return b.cuentaPrincipal().getSaldo();
    }

   

    public void crearDeposito(Double importe, Integer id){
        Billetera b = repoBilletera.findById(id).get();
        b.movimientoDeposito(importe, b.getPersona().getUsuario());

        repoBilletera.save(b);
        
    }
}