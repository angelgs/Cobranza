package com.cobranza.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

/**
 * Created by agutierrs on 21/05/17.
 */

@Entity
public class Contrato
{
    @Id
    private Long id;

    @NotNull
    private String numero;

    private Long clienteId;

    @ToOne(joinProperty = "clienteId")
    private Persona cliente;

    @NotNull
    private Date fecha;

    @NotNull
    private double monto;

    @NotNull
    private double saldo;

    @NotNull
    private double montoLiquidar;

    @NotNull
    private String direccion;

    @NotNull
    private String numeroCuenta;

    @NotNull
    private String pago;

    @NotNull
    private double montoPago;

    private int pagosAtrazados;

    private double cargoInteres;

    private double otrosCargos;

    private String motivoOtrosCargos;

    private String aviso;

    @ToMany
    @JoinEntity(
            entity = ContratoPersona.class,
            sourceProperty = "contratoId",
            targetProperty = "personaId"
    )
    private List<Persona> referencias;

    @ToMany(referencedJoinProperty = "contratoId")
    private List<Articulo> articulos;

    @ToMany(referencedJoinProperty = "contratoId")
    @OrderBy("fechaPago ASC")
    private List<Pago> pagos;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1829289339)
    private transient ContratoDao myDao;
    @Generated(hash = 1668724671)
    private transient Long cliente__resolvedKey;

    @Generated(hash = 2048565279)
    public Contrato(Long id, @NotNull String numero, Long clienteId, @NotNull Date fecha, double monto,
                    double saldo, double montoLiquidar, @NotNull String direccion, @NotNull String numeroCuenta,
                    @NotNull String pago, double montoPago, int pagosAtrazados, double cargoInteres,
                    double otrosCargos, String motivoOtrosCargos, String aviso)
    {
        this.id = id;
        this.numero = numero;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.monto = monto;
        this.saldo = saldo;
        this.montoLiquidar = montoLiquidar;
        this.direccion = direccion;
        this.numeroCuenta = numeroCuenta;
        this.pago = pago;
        this.montoPago = montoPago;
        this.pagosAtrazados = pagosAtrazados;
        this.cargoInteres = cargoInteres;
        this.otrosCargos = otrosCargos;
        this.motivoOtrosCargos = motivoOtrosCargos;
        this.aviso = aviso;
    }

    @Generated(hash = 1339756915)
    public Contrato() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 9301335)
    public Persona getCliente() {
        Long __key = this.clienteId;
        if (cliente__resolvedKey == null || !cliente__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonaDao targetDao = daoSession.getPersonaDao();
            Persona clienteNew = targetDao.load(__key);
            synchronized (this) {
                cliente = clienteNew;
                cliente__resolvedKey = __key;
            }
        }
        return cliente;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1887176634)
    public void setCliente(Persona cliente)
    {
        synchronized (this) {
            this.cliente = cliente;
            clienteId = cliente == null ? null : cliente.getId();
            cliente__resolvedKey = clienteId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1590577427)
    public List<Articulo> getArticulos() {
        if (articulos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ArticuloDao targetDao = daoSession.getArticuloDao();
            List<Articulo> articulosNew = targetDao._queryContrato_Articulos(id);
            synchronized (this) {
                if (articulos == null) {
                    articulos = articulosNew;
                }
            }
        }
        return articulos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 120614566)
    public synchronized void resetArticulos() {
        articulos = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 664052070)
    public List<Pago> getPagos() {
        if (pagos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PagoDao targetDao = daoSession.getPagoDao();
            List<Pago> pagosNew = targetDao._queryContrato_Pagos(id);
            synchronized (this) {
                if (pagos == null) {
                    pagos = pagosNew;
                }
            }
        }
        return pagos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 850050038)
    public synchronized void resetPagos() {
        pagos = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public double getMontoLiquidar()
    {
        return this.montoLiquidar;
    }

    public void setMontoLiquidar(double montoLiquidar)
    {
        this.montoLiquidar = montoLiquidar;
    }

    public String getDireccion()
    {
        return this.direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1119562186)
    public List<Persona> getReferencias()
    {
        if (referencias == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonaDao targetDao = daoSession.getPersonaDao();
            List<Persona> referenciasNew = targetDao._queryContrato_Referencias(id);
            synchronized (this) {
                if (referencias == null) {
                    referencias = referenciasNew;
                }
            }
        }
        return referencias;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 170618034)
    public synchronized void resetReferencias()
    {
        referencias = null;
    }

    public String getNumeroCuenta()
    {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta)
    {
        this.numeroCuenta = numeroCuenta;
    }

    public int getPagosAtrazados()
    {
        return this.pagosAtrazados;
    }

    public void setPagosAtrazados(int pagosAtrazados)
    {
        this.pagosAtrazados = pagosAtrazados;
    }

    public double getCargoInteres()
    {
        return this.cargoInteres;
    }

    public void setCargoInteres(double cargoInteres)
    {
        this.cargoInteres = cargoInteres;
    }

    public double getOtrosCargos()
    {
        return this.otrosCargos;
    }

    public void setOtrosCargos(double otrosCargos)
    {
        this.otrosCargos = otrosCargos;
    }

    public String getMotivoOtrosCargos()
    {
        return this.motivoOtrosCargos;
    }

    public void setMotivoOtrosCargos(String motivoOtrosCargos)
    {
        this.motivoOtrosCargos = motivoOtrosCargos;
    }

    public String getAviso()
    {
        return this.aviso;
    }

    public void setAviso(String aviso)
    {
        this.aviso = aviso;
    }

    public String getPago()
    {
        return this.pago;
    }

    public void setPago(String pago)
    {
        this.pago = pago;
    }

    public double getMontoPago()
    {
        return this.montoPago;
    }

    public void setMontoPago(double montoPago)
    {
        this.montoPago = montoPago;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1913655080)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getContratoDao() : null;
    }
    
}
