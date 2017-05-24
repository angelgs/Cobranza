package com.cobranza.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by agutierrs on 21/05/17.
 */

@Entity
public class Direccion
{
    @Id
    private Long id;

    private String estado;

    private String municipio;

    private String codigoPostal;

    private String colonia;

    private String calle;

    private String numero;

    private String referencia;

    @Generated(hash = 1834972742)
    public Direccion(Long id, String estado, String municipio, String codigoPostal,
            String colonia, String calle, String numero, String referencia) {
        this.id = id;
        this.estado = estado;
        this.municipio = municipio;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.referencia = referencia;
    }

    @Generated(hash = 1682597139)
    public Direccion() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getMunicipio()
    {
        return municipio;
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = municipio;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia()
    {
        return colonia;
    }

    public void setColonia(String colonia)
    {
        this.colonia = colonia;
    }

    public String getCalle()
    {
        return calle;
    }

    public void setCalle(String calle)
    {
        this.calle = calle;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getReferencia()
    {
        return referencia;
    }

    public void setReferencia(String referencia)
    {
        this.referencia = referencia;
    }
}
