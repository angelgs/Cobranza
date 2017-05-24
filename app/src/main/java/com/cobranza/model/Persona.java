package com.cobranza.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

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

    private Long direccionId;

    @ToOne(joinProperty = "direccionId")
    private Direccion direccion;

    @ToMany
    @JoinEntity(
            entity = PersonaTelefono.class,
            sourceProperty = "personaId",
            targetProperty = "telefonoId"
    )
    private List<Telefono> telefonos;

    @Convert(converter = TipoPersonaConverter.class, columnType = String.class)
    private TipoPersona tipoPersona;

    @ToMany(referencedJoinProperty = "clienteId")
    private List<Contrato> contratos;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 709859051)
    private transient PersonaDao myDao;


    @Generated(hash = 837512235)
    public Persona(Long id, @NotNull String nombre, Long direccionId, TipoPersona tipoPersona) {
        this.id = id;
        this.nombre = nombre;
        this.direccionId = direccionId;
        this.tipoPersona = tipoPersona;
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


    public Long getDireccionId() {
        return this.direccionId;
    }


    public void setDireccionId(Long direccionId) {
        this.direccionId = direccionId;
    }


    public TipoPersona getTipoPersona() {
        return this.tipoPersona;
    }


    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }


    @Generated(hash = 2117566951)
    private transient Long direccion__resolvedKey;


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1208985994)
    public Direccion getDireccion() {
        Long __key = this.direccionId;
        if (direccion__resolvedKey == null || !direccion__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DireccionDao targetDao = daoSession.getDireccionDao();
            Direccion direccionNew = targetDao.load(__key);
            synchronized (this) {
                direccion = direccionNew;
                direccion__resolvedKey = __key;
            }
        }
        return direccion;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 691962620)
    public void setDireccion(Direccion direccion) {
        synchronized (this) {
            this.direccion = direccion;
            direccionId = direccion == null ? null : direccion.getId();
            direccion__resolvedKey = direccionId;
        }
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
