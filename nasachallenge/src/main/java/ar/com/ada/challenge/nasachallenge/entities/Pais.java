package ar.com.ada.challenge.nasachallenge.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Pais
 */
@Entity
@Table(name ="pais")
public class Pais {

    @Id
    @Column(name = "pais_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paisId;
    @Column(name = "codigo")
    private int codigoPais;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Temperatura> temperaturas = new ArrayList<Temperatura>();

    public Pais() {
    }

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Temperatura> getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(List<Temperatura> temperaturas) {
        this.temperaturas = temperaturas;
    }

    public void agregarTemperatura(Temperatura t) {
        t.setPais(this);
        temperaturas.add(t);
    }
}