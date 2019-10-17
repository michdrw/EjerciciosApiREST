package ar.com.ada.api.billeteravirtual.entities;
import java.util.Date;

import javax.persistence.*;
/**
 * Movimiento
 */
@Entity

@Table(name ="movimiento")
public class Movimiento {

    @Id
    @Column(name = "movimiento_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movimientoId;
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;
    @Column(name = "tipo_operacion")
    private String tipoOperacion;
    @Column(name = "concepto_operacion")
    private String conceptoOperacion;
    private String detalle;
    private Integer estado;
    private double importe;
    @Column(name = "cuenta_origen_id")
    private Integer cuentaOrigenId;
    @Column(name = "cuenta_destinatario_id")
    private Integer cuentaDestinatarioId;
    @Column(name = "de_usuario")
    private Integer deUsuario;
    @Column(name = "a_usuario")
    private Integer aUsuario;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "cuenta_id")
    private Cuenta cuenta;

    public Movimiento() {
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getConceptoOperacion() {
        return conceptoOperacion;
    }

    public void setConceptoOperacion(String conceptoOperacion) {
        this.conceptoOperacion = conceptoOperacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(Integer movimientoId) {
        this.movimientoId = movimientoId;
    }

    public Integer getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Integer cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public Integer getCuentaDestinatarioId() {
        return cuentaDestinatarioId;
    }

    public void setCuentaDestinatarioId(Integer cuentaDestinatarioId) {
        this.cuentaDestinatarioId = cuentaDestinatarioId;
    }

    public Integer getDeUsuario() {
        return deUsuario;
    }

    public void setDeUsuario(Integer deUsuario) {
        this.deUsuario = deUsuario;
    }

    public Integer getaUsuario() {
        return aUsuario;
    }

    public void setaUsuario(Integer aUsuario) {
        this.aUsuario = aUsuario;
    }
}