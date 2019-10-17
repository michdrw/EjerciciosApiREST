package ar.com.ada.api.challenge.challenge161019.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.challenge.challenge161019.entities.Categoria;
import ar.com.ada.api.challenge.challenge161019.models.requests.CategoryCreationRequest;
import ar.com.ada.api.challenge.challenge161019.models.responses.CategoryCreationResponse;
import ar.com.ada.api.challenge.challenge161019.services.CategoriaService;

/**
 * CategoriaController
 */
@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/categorias")
    public CategoryCreationResponse postCreateCategory(@RequestBody CategoryCreationRequest req) {

        CategoryCreationResponse ccr = new CategoryCreationResponse();
        categoriaService.crearCategoria(req.nombre, req.sueldoBase);

        ccr.isOk = true;
        ccr.message = "Categoria creada con exito";
        return ccr;
    }

    // Traer todas las categorias
    @GetMapping("/categorias")
    public List<Categoria> GetCategorias() {
        List<Categoria> lc = categoriaService.getCategorias();

        return lc;
    }

    //Traer categorias por Id (sirve para el metodo de abajo)
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getCategoriaPorId(@PathVariable int id){
        Categoria c = categoriaService.buscarPorId(id);
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(c);
    }


    
}