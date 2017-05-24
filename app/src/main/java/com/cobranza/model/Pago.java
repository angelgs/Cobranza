package com.cobranza.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by agutierrs on 21/05/17.
 */

@Entity
public class Pago
{
    @Id
    private Long id;

    @NotNull
    private Long idCobrador;

    @NotNull
    private Date fecha;

    @NotNull
    private Date fechaPago;

    private double monto;

    private Long contratoId;

    @ToOne(joinProperty = "contratoId")
    private Contrato contrato;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 435262577)
    private transient PagoDao myDao;

    @Generated(hash = 40570290)
    public Pago(Long id, @NotNull Long idCobrador, @NotNull Date fecha,
            @NotNull Date fechaPago, double monto, Long contratoId) {
        this.id = id;
        this.idCobrador = idCobrador;
        this.fecha = fecha;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.contratoId = contratoId;
    }

    @Generated(hash = 1615826956)
    public Pago() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCobrador() {
        return this.idCobrador;
    }

    public void setIdCobrador(Long idCobrador) {
        this.idCobrador = idCobrador;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaPago() {
        return this.fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getContratoId() {
        return this.contratoId;
    }

    public void setContratoId(Long contratoId) {
        this.contratoId = contratoId;
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
    @Generated(hash = 1466875384)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPagoDao() : null;
    }

}
