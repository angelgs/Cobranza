package com.cobranza.model;

/**
 * Created by agutierrs on 24/05/17.
 */

public enum TipoPersona
{
    CLIENTE("CLIENTE"),
    REFERENCIA("REFERENCIA");

    final String tipo;

    TipoPersona(final String tipo)
    {
        this.tipo = tipo;
    }

    public String toString() { return tipo; }
}
