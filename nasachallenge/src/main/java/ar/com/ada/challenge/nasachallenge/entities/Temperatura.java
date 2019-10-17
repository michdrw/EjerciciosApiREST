
package ar.com.ada.challenge.nasachallenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Temperatura
 */
@Entity
@Table(name ="temperatura")
public class Temperatura {

    @Id
    @Column(name = "temperatura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int temperaturaId;
    private int anio;
    private int grado;

    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "pais_id")
    private Pais pais;

    public Temperatura() {
    }

	public int getTemperaturaId() {
		return temperaturaId;
	}

	public void setTemperaturaId(int temperaturaId) {
		this.temperaturaId = temperaturaId;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}