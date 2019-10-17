package ar.com.ada.api.billeteravirtual.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Billetera
 */
@Entity
@Table(name ="billetera")
public class Billetera {

    @Id
    @Column(name = "billetera_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billeteraId;

    @OneToMany(mappedBy = "billetera", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cuenta> cuentas = new ArrayList<Cuenta>();

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    private Persona persona;

    public Billetera() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
        
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta cuentaPrincipal() {
        return getCuentas().get(0);
    }


    public Integer getBilleteraId() {
        return billeteraId;
    }

    public void setBilleteraId(Integer billeteraId) {
        this.billeteraId = billeteraId;
    }
    

    public void agregarCuenta(Cuenta cuenta) {
        cuenta.setBilletera(this);
        cuentas.add(cuenta);
    }

    public void movimientoDeposito(double importe, Usuario usuarioDestino){
        Movimiento m = new Movimiento();
        m.setTipoOperacion("");
        m.setConceptoOperacion("Deposito");
        m.setDetalle("Deposito");
        m.setEstado(0);
        m.setImporte(importe);
        m.setFechaMovimiento(new Date());
        m.setDeUsuario(usuarioDestino.getUsuarioId());
        m.setaUsuario(usuarioDestino.getUsuarioId());
        m.setCuentaOrigenId(usuarioDestino.getPersona().getBilletera().cuentaPrincipal().getCuentaId());
        m.setCuentaDestinatarioId(usuarioDestino.getPersona().getBilletera().cuentaPrincipal().getCuentaId());
        this.cuentas.get(0).agregarMovimiento(m);

    }


    public void movimientoTransferencia(double importe, Usuario usuarioOrigen, Usuario usuarioDestino){
        Movimiento m1 = new Movimiento();
        m1.setTipoOperacion("");
        m1.setConceptoOperacion("Transferencia");
        m1.setDetalle("Sacado de dinero");
        m1.setEstado(0);
        m1.setImporte(-importe);
        m1.setFechaMovimiento(new Date());
        m1.setDeUsuario(usuarioOrigen.getUsuarioId());
        m1.setaUsuario(usuarioOrigen.getUsuarioId());
        m1.setCuentaOrigenId(usuarioOrigen.getPersona().getBilletera().cuentaPrincipal().getCuentaId());
        m1.setCuentaDestinatarioId(usuarioOrigen.getPersona().getBilletera().cuentaPrincipal().getCuentaId());
        this.cuentas.get(0).agregarMovimiento(m1);

        Movimiento m2 = new Movimiento();
        m2.setTipoOperacion("");
        m2.setConceptoOperacion("Transferencia");
        m2.setDetalle("Recibo de dinero");
        m2.setEstado(0);
        m2.setImporte(importe);
        m2.setFechaMovimiento(new Date());
        m2.setDeUsuario(usuarioDestino.getUsuarioId());
        m2.setaUsuario(usuarioDestino.getUsuarioId());
        m2.setCuentaOrigenId(usuarioDestino.getPersona().getBilletera().cuentaPrincipal().getCuentaId());
        m2.setCuentaDestinatarioId(usuarioDestino.getPersona().getBilletera().cuentaPrincipal().getCuentaId());
        usuarioDestino.getPersona().getBilletera().getCuentas().get(0).agregarMovimiento(m2);
    }


}