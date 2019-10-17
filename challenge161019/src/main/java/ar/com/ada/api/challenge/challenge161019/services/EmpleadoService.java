package ar.com.ada.api.challenge.challenge161019.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.challenge.challenge161019.entities.Categoria;
import ar.com.ada.api.challenge.challenge161019.entities.Empleado;
import ar.com.ada.api.challenge.challenge161019.repositories.EmpleadoRepository;

/**
 * EmpleadoService
 */
@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository repoEmpleado;
    @Autowired
    CategoriaService categoriaService;

    public Empleado save(Empleado e) {
        return repoEmpleado.save(e);
        
    }

    public void crearEmpleado(String nombre, int edad , BigDecimal sueldo, Integer categoriaId){

        Empleado e = new Empleado();
        e.setNombre(nombre);
        e.setEdad(edad);
        e.setSueldo(sueldo);
        e.setFechaAlta(new Date());
        e.setEstado("activo");
        Categoria c = categoriaService.buscarPorId(categoriaId);
        e.setCategoria(c);
        c.agregarEmpleado(e);
        
        repoEmpleado.save(e);
    }

    public List<Empleado> getEmpleados() {

        return repoEmpleado.findAll();
    }

    public Empleado buscarPorId(int id) {

        Optional<Empleado> e = repoEmpleado.findById(id);
        
        if (e.isPresent())
            return e.get();
        return null;
    }

    public List<Empleado> getEmpleadosPorCategoriaId(int id){

        Categoria c = categoriaService.buscarPorId(id);
        return c.getEmpleados();
    
    }
}