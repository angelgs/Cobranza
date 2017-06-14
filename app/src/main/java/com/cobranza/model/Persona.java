package com.cobranza.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * Created by agutierrs on 24/05/17.
 */

@Entity
public class Persona
{
    @Id
    protected Long id;

    @NotNull
    protected String nombre;

    @NotNull
    private String direccion;

    @ToMany
    @JoinEntity(
            entity = PersonaTelefono.class,
            sourceProperty = "personaId",
            targetProperty = "telefonoId"
    )
    private List<Telefono> telefonos;

    @Convert(converter = TipoPersonaConverter.class, columnType = String.class)
    private TipoPersona tipoPersona;

    @NotNull
    private Boolean esAval = Boolean.FALSE;

    @ToMany(referencedJoinProperty = "clienteId")
    private List<Contrato> contratos;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 709859051)
    private transient PersonaDao myDao;


    @Generated(hash = 545191834)
    public Persona(Long id, @NotNull String nombre, @NotNull String direccion, TipoPersona tipoPersona,
                   @NotNull Boolean esAval)
    {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoPersona = tipoPersona;
        this.esAval = esAval;
    }


    @Generated(hash = 1270265349)
    public Persona() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return this.nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public TipoPersona getTipoPersona() {
        return this.tipoPersona;
    }


    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1217218271)
    public List<Telefono> getTelefonos() {
        if (telefonos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TelefonoDao targetDao = daoSession.getTelefonoDao();
            List<Telefono> telefonosNew = targetDao._queryPersona_Telefonos(id);
            synchronized (this) {
                if (telefonos == null) {
                    telefonos = telefonosNew;
                }
            }
        }
        return telefonos;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2019829760)
    public synchronized void resetTelefonos() {
        telefonos = null;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 478425053)
    public List<Contrato> getContratos() {
        if (contratos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ContratoDao targetDao = daoSession.getContratoDao();
            List<Contrato> contratosNew = targetDao._queryPersona_Contratos(id);
            synchronized (this) {
                if (contratos == null) {
                    contratos = contratosNew;
                }
            }
        }
        return contratos;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1952354408)
    public synchronized void resetContratos() {
        contratos = null;
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

    public String getDireccion()
    {
        return this.direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public Boolean getEsAval()
    {
        return this.esAval;
    }


    public void setEsAval(Boolean esAval)
    {
        this.esAval = esAval;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1301839720)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonaDao() : null;
    }


    public static class TipoPersonaConverter implements PropertyConverter<TipoPersona, String> {

        @Override
        public TipoPersona convertToEntityProperty(String databaseValue)
        {
            if (null == databaseValue) {
                return null;
            }
            for(TipoPersona tipoPersona : TipoPersona.values()) {
                if( tipoPersona.tipo == databaseValue) {
                    return tipoPersona;
                }
            }

            return TipoPersona.CLIENTE;
        }

        @Override
        public String convertToDatabaseValue(TipoPersona entityProperty)
        {
            return null == entityProperty ? null : entityProperty.tipo;
        }
    }
}
