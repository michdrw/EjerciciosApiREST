package ar.com.ada.challenge.nasachallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.challenge.nasachallenge.entities.Pais;
import ar.com.ada.challenge.nasachallenge.entities.Temperatura;
import ar.com.ada.challenge.nasachallenge.models.requests.IdRequest;
import ar.com.ada.challenge.nasachallenge.models.requests.TemperaturaCreationRequest;
import ar.com.ada.challenge.nasachallenge.models.responses.TemperaturaDeleteResponse;
import ar.com.ada.challenge.nasachallenge.models.responses.TemperaturaResponse;
import ar.com.ada.challenge.nasachallenge.services.PaisService;
import ar.com.ada.challenge.nasachallenge.services.TemperaturaService;

/**
 * TemperaturaController
 */
@RestController
public class TemperaturaController {

    @Autowired
    TemperaturaService temperaturaService;
    @Autowired
    PaisService paisService;

    @PostMapping("/temperaturas")
    public TemperaturaResponse postCreateTemperatura(@RequestBody TemperaturaCreationRequest req) {
        TemperaturaResponse tr = new TemperaturaResponse();
        int temperaturaCreadaId = temperaturaService.crearTemperatura(req.codigoPais, req.anio, req.grado);

        tr.temperaturaId = temperaturaCreadaId;
        return tr;
    }

    @GetMapping("/temperaturas/paises/{codigoPais}")
    public List<Temperatura> getTemperaturaPorId(@PathVariable int codigoPais) {
        Pais p = paisService.buscarPorCodigoPais(codigoPais);
        return p.getTemperaturas();
        
    }

    @DeleteMapping("/temperaturas/{id}")
    public TemperaturaDeleteResponse deleteTemperatura(@PathVariable int id)
    {
        TemperaturaDeleteResponse tdr = new TemperaturaDeleteResponse();
        temperaturaService.bajaTemperatura(id);

        tdr.isOk = true;
        tdr.message = "Temperature'deleted'";
        return tdr;

    }

    @GetMapping("/temperaturas/anios/{anio}")
    public List<Temperatura> getTemperaturaPorAnio(@PathVariable int anio) {
        return temperaturaService.buscarPorAnio(anio);        
    }


}