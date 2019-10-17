package ar.com.ada.challenge.nasachallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.challenge.nasachallenge.entities.Pais;
import ar.com.ada.challenge.nasachallenge.models.requests.PaisCreationRequest;
import ar.com.ada.challenge.nasachallenge.models.requests.PaisUpdateRequest;
import ar.com.ada.challenge.nasachallenge.models.responses.PaisResponse;
import ar.com.ada.challenge.nasachallenge.services.PaisService;

/**
 * PaisController
 */
@RestController
public class PaisController {

    @Autowired
    PaisService paisService;

    @PostMapping("/paises")
    public PaisResponse postCreatePais(@RequestBody PaisCreationRequest req)
    {
        PaisResponse pr = new PaisResponse();
        paisService.crearPais(req.codigoPais, req.nombre);

        pr.isOk = true;
        pr.message = "Country successfully created";
        return pr;
    }

    @GetMapping("/paises")
    public List<Pais> GetPaises() {
        List<Pais> lp = paisService.getPaises();

        return lp;
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> getCategoriaPorId(@PathVariable int id){
        Pais p = paisService.buscarPorId(id);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(p);
    }

    @PutMapping("/paises/{id}")
    public PaisResponse postCreatePais(@PathVariable int id, @RequestBody PaisUpdateRequest req)
    {
        PaisResponse pr = new PaisResponse();
        paisService.actualizarPais(req.paisId, req.nombre);

        pr.isOk = true;
        pr.message = "Country successfully updated";
        return pr;
    }

}