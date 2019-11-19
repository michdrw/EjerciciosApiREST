package ar.com.ada.challenge.nasachallenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.challenge.nasachallenge.entities.Pais;
import ar.com.ada.challenge.nasachallenge.repositories.PaisRepository;

/**
 * PaisService
 */
@Service
public class PaisService {

    @Autowired
    PaisRepository repoPais;

    public Pais save(Pais p) {
        return repoPais.save(p);
        
    }

    public void crearPais(int codigoPais, String nombre)
    {
        Pais p = new Pais();
        p.setCodigoPais(codigoPais);
        p.setNombre(nombre);

        repoPais.save(p);
    }

    public List<Pais> getPaises() {

        return repoPais.findAll();
    }

    public Pais buscarPorId(int id)
    {
        Optional<Pais> p = repoPais.findById(id);

        if (p.isPresent()) {
            return p.get();
        }
        return null;
    }

    public Pais buscarPorNombre(String nombre)
    {
        Pais p = repoPais.findByName(nombre);
            return p;
    }

    public Pais buscarPorCodigoPais(int codigoPais)
    {
        for (Pais p : repoPais.findAll()) {
            if (p.getCodigoPais() == codigoPais){
                return p;
            }
        }
        return null;
    }


    public void actualizarPais(int paisId, String nombre)
    {
        Pais p = buscarPorId(paisId);
        p.setNombre(nombre);

        repoPais.save(p);
    }

    public enum PaisValidationType 
    {
        PAIS_OK, 
        PAIS_DUPLICADO,
        PAIS_DATOS_INVALIDOS,
        TEMPERATURA_VACIA
    }

    public PaisValidationType verificarPais (Pais pais)
    {
        if (pais.getNombre() == null) {
            return PaisValidationType.PAIS_DATOS_INVALIDOS;
        }
        if (pais.getCodigoPais() == 0) {
            return PaisValidationType.PAIS_DATOS_INVALIDOS;
        }
        if (pais.getTemperaturas() == null)
        {
            return PaisValidationType.TEMPERATURA_VACIA;
        }
        
        Pais p = buscarPorNombre(pais.getNombre());
        if (p != null)
        {
            if (pais.getPaisId() == 0)
            {
                if (pais.getPaisId() == p.getPaisId())
                {
                     return PaisValidationType.PAIS_OK;
                }  
                else {
                    return PaisValidationType.PAIS_DUPLICADO;
                     }
            }
            else
            {
                return PaisValidationType.PAIS_DUPLICADO;
            }
        }
        return PaisValidationType.PAIS_OK;
    }

}