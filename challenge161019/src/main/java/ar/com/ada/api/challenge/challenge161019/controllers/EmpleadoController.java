package ar.com.ada.api.challenge.challenge161019.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.challenge.challenge161019.entities.Empleado;
import ar.com.ada.api.challenge.challenge161019.models.requests.EmployeeCreationRequest;
import ar.com.ada.api.challenge.challenge161019.models.requests.IdRequest;
import ar.com.ada.api.challenge.challenge161019.models.requests.NoSueldoRequest;
import ar.com.ada.api.challenge.challenge161019.models.requests.SueldoRequest;
import ar.com.ada.api.challenge.challenge161019.models.responses.EmployeeCreationResponse;
import ar.com.ada.api.challenge.challenge161019.services.CategoriaService;
import ar.com.ada.api.challenge.challenge161019.services.EmpleadoService;

/**
 * EmpleadoController
 */
@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    CategoriaService categoriaService;

    //Trae la lista de empleados
    @PostMapping("/empleados")
    public EmployeeCreationResponse postCreateEmployee (@RequestBody EmployeeCreationRequest req)
    {

        EmployeeCreationResponse ecr = new EmployeeCreationResponse();
        empleadoService.crearEmpleado(req.nombre, req.edad , req.sueldo, req.categoriaId);

        ecr.isOk = true;
        ecr.message = "Empleado creado con exito";
        return ecr;

    }

    //Trae un empleado en especifico por el id
    @GetMapping("/empleados")
    public List<Empleado> GetEmpleados()
    {
        List<Empleado> le = empleadoService.getEmpleados();
        
        return le;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadosPorId(@PathVariable int id)
    {    
        Empleado e = empleadoService.buscarPorId(id);
        
        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(e);
    }

    // Obtener la lista de empleados de una categoria desde su id
    @GetMapping("/empleados/categorias/{id}")
    public ResponseEntity<List<Empleado>> getEmpleadosPorCategoriaId(@PathVariable int id)
    {
            
        List<Empleado> le = empleadoService.getEmpleadosPorCategoriaId(id);
        if (le == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(le);
    }

    @PutMapping("/empleados/{id}")
    public EmployeeCreationResponse putEmpleadosPorId(@PathVariable int id, @RequestBody NoSueldoRequest req){

        EmployeeCreationResponse ecr = new EmployeeCreationResponse();
        empleadoService.actualizarEmpleado(req.empleadoId, req.nombre, req.edad, req.categoriaId);
    
        ecr.isOk = true;
        ecr.message = "Empleado actualizado con exito";
        return ecr;
    }

    @PutMapping("/empleados/{id}/sueldos")
    public EmployeeCreationResponse putSueldo(@PathVariable int id, @RequestBody SueldoRequest req)
    {
        EmployeeCreationResponse ecr = new EmployeeCreationResponse();
        empleadoService.actualizarSueldo(req.empleadoId, req.sueldo);
    
        ecr.isOk = true;
        ecr.message = "Sueldito actualizado con exito";
        return ecr;
    }

    @DeleteMapping("/empleados/{id}")
    public EmployeeCreationResponse deleteEmployee(@PathVariable int id, @RequestBody IdRequest req)
    {
        EmployeeCreationResponse ecr = new EmployeeCreationResponse();
        empleadoService.bajaEmpleado(req.empleadoId);

        ecr.isOk = true;
        ecr.message = "Siempre desempleado nunca empleado";
        return ecr;

    }

    
}