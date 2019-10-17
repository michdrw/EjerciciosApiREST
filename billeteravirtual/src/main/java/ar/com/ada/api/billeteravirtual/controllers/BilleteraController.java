package ar.com.ada.api.billeteravirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.billeteravirtual.models.requests.DepositoRequest;
import ar.com.ada.api.billeteravirtual.models.requests.TransferRequest;
import ar.com.ada.api.billeteravirtual.models.responses.DepositoResponse;
import ar.com.ada.api.billeteravirtual.models.responses.SaldoResponse;
import ar.com.ada.api.billeteravirtual.models.responses.TransferResponse;
import ar.com.ada.api.billeteravirtual.services.BilleteraService;

/**
 * BilleteraController
 */
@RestController
public class BilleteraController {

    @Autowired
    BilleteraService billeteraService;

    @PostMapping("transferencia")
    public TransferResponse postTransfer(@RequestBody TransferRequest req){

        TransferResponse t = new TransferResponse();
        billeteraService.crearTransferencia(req.importe, req.billeteraIdOrigen, req.emailDestino);
        
        
        t.isOk = true;
        t.message = "Transferencia exitosa";
        return t;
}

    @PostMapping("deposito")
    public DepositoResponse postDeposito (@RequestBody DepositoRequest req){

        DepositoResponse d = new DepositoResponse();
        billeteraService.crearDeposito(req.importe, req.billeteraId);

        d.isOk = true;
        d.message = "Deposito exitoso";
        return d;

    }

    @GetMapping("/billeteras/{id}/saldos")
    public SaldoResponse GetSaldoBilleteraById(@PathVariable int id)
    {
        SaldoResponse s = new SaldoResponse();
        s.idBilletera = id;
        s.saldo = billeteraService.buscarPorId(id).cuentaPrincipal().getSaldo();
        s.moneda = billeteraService.buscarPorId(id).cuentaPrincipal().getMoneda();
        return s;
       
   }
}