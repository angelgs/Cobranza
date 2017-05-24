package com.cobranza.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by agutierrs on 21/05/17.
 */

@Entity
public class Articulo
{
    @Id
    private Long id;

    private Long contratoId;

    @ToOne(joinProperty = "contratoId")
    private Contrato contrato;

    @NotNull
    private String descripcion;

    @NotNull
    private Integer cantidad;

    private double precioUnitario;

    private double precioVenta;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1788488102)
    private transient ArticuloDao myDao;

    @Generated(hash = 107380088)
    public Articulo(Long id, Long contratoId, @NotNull String descripcion,
            @NotNull Integer cantidad, double precioUnitario, double precioVenta) {
        this.id = id;
        this.contratoId = contratoId;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioVenta = precioVenta;
    }

    @Generated(hash = 1908453761)
    public Articulo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContratoId() {
        return this.contratoId;
    }

    public void setContratoId(Long contratoId) {
        this.contratoId = contratoId;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Generated(hash = 1127129734)
    private transient Long contrato__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 207467265)
    public Contrato getContrato() {
        Long __key = this.contratoId;
        if (contrato__resolvedKey == null || !contrato__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ContratoDao targetDao = daoSession.getContratoDao();
            Contrato contratoNew = targetDao.load(__key);
            synchronized (this) {
                contrato = contratoNew;
                contrato__resolvedKey = __key;
            }
        }
        return contrato;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1672594394)
    public void setContrato(Contrato contrato) {
        synchronized (this) {
            this.contrato = contrato;
            contratoId = contrato == null ? null : contrato.getId();
            contrato__resolvedKey = contratoId;
        }
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 990936458)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getArticuloDao() : null;
    }

}
