package ar.com.ada.api.billeteravirtual.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
/**
 * Cuenta
 */
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @Column(name = "cuenta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuentaId;
    @Column(name = "numero_cuenta")
    private int numeroCuenta;
    private double saldo;
    @Column(name = "dinero_disponible")
    private double dineroDisponible;
    private String moneda;

    public Cuenta() {
    }

    @ManyToOne
    @JoinColumn(name = "billetera_id", referencedColumnName = "billetera_id")
    private Billetera billetera;

    @OneToMany( mappedBy = "cuenta", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }


    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(double dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimiento(List<Movimiento> movimientos) {
        this.movimientos = movimientos;

    }

    public void agregarMovimiento(Movimiento movimiento){
        this.movimientos.add(movimiento);
        movimiento.setCuenta(this);
        this.setSaldo(this.getSaldo() + movimiento.getImporte());
        this.setDineroDisponible(this.getSaldo());
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
}