package ar.com.ada.challenge.nasachallenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.challenge.nasachallenge.entities.Pais;
import ar.com.ada.challenge.nasachallenge.entities.Temperatura;
import ar.com.ada.challenge.nasachallenge.repositories.TemperaturaRepository;

/**
 * TemperaturaService
 */
@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository repoTemp;
    @Autowired
    PaisService paisService;
    
    public Temperatura save(Temperatura t) {
        return repoTemp.save(t);
        
    }

    public int crearTemperatura(int codigoPais, int anio, int grado)
    {
        Temperatura t = new Temperatura();
        t.setAnio(anio);
        t.setGrado(grado);
        Pais p = paisService.buscarPorCodigoPais(codigoPais);
        t.setPais(p);

        repoTemp.save(t);
        return t.getTemperaturaId();
    }

    public Temperatura buscarPorId(int id)
    {
        Optional<Temperatura> t = repoTemp.findById(id);

        if (t.isPresent()) {
            return t.get();
        }
        return null;
    }

    public List<Temperatura> buscarPorAnio(int anio)
    {
        for (Temperatura t : repoTemp.findAll()) {
            if (t.getAnio() == anio){
                List<Temperatura> temperaturas = new ArrayList<Temperatura>();
                temperaturas.add(t);
                return temperaturas;
            }
        }
        return null;
    }

    

    public void bajaTemperatura(int temperaturaId)
    {
        Temperatura t = buscarPorId(temperaturaId);
        t.setAnio(0);

        repoTemp.save(t);
    }

}