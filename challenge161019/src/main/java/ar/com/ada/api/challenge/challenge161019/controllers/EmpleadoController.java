package ar.com.ada.api.challenge.challenge161019.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.ada.api.challenge.challenge161019.entities.Empleado;
import ar.com.ada.api.challenge.challenge161019.models.requests.EmployeeCreationRequest;
import ar.com.ada.api.challenge.challenge161019.models.responses.EmployeeCreationResponse;
import ar.com.ada.api.challenge.challenge161019.services.EmpleadoService;

/**
 * EmpleadoController
 */
@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/empleados")
    public EmployeeCreationResponse postCreateEmployee (@RequestBody EmployeeCreationRequest req){

        EmployeeCreationResponse ecr = new EmployeeCreationResponse();
        empleadoService.crearEmpleado(req.nombre, req.edad , req.sueldo, req.categoriaId);

        ecr.isOk = true;
        ecr.message = "Empleado creado con exito";
        return ecr;

    }
    
    @GetMapping("/empleados")
   public List<Empleado> GetEmpleados()
    {
        List<Empleado> le = empleadoService.getEmpleados();
        
        return le;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadosPorId(@PathVariable int id){
        Empleado e = empleadoService.buscarPorId(id);
        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(e);
    }
}