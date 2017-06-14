package com.cobranza.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by agutierrs on 21/05/17.
 */

@Entity
public class Telefono
{
    @Id
    private Long id;

    @NotNull
    private String alias;

    @Convert(converter = TipoTelefonoConverter.class, columnType = String.class)
    private TipoTelefono tipo;

    @NotNull
    private String numero;

    @Generated(hash = 1893778106)
    public Telefono(Long id, @NotNull String alias, TipoTelefono tipo, @NotNull String numero) {
        this.id = id;
        this.alias = alias;
        this.tipo = tipo;
        this.numero = numero;
    }

    @Generated(hash = 613000039)
    public Telefono() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public TipoTelefono getTipo()
    {
        return tipo;
    }

    public void setTipo(TipoTelefono tipo)
    {
        this.tipo = tipo;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public static class TipoTelefonoConverter implements PropertyConverter<TipoTelefono, String> {

        @Override
        public TipoTelefono convertToEntityProperty(String databaseValue)
        {
            if(null == databaseValue) {
                return null;
            }
            for(TipoTelefono tipoTelefono : TipoTelefono.values()) {
                if(tipoTelefono.tipo == databaseValue) {
                    return tipoTelefono;
                }
            }

            return TipoTelefono.CASA;
        }

        @Override
        public String convertToDatabaseValue(TipoTelefono entityProperty)
        {
            return null == entityProperty ? null : entityProperty.tipo;
        }
    }
}
