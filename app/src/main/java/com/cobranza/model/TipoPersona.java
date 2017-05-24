package com.cobranza.model;

/**
 * Created by agutierrs on 24/05/17.
 */

public enum TipoPersona
{
    CLIENTE("CLIENTE"),
    AVAL("AVAL");

    final String tipo;

    private TipoPersona(final String tipo) { this.tipo = tipo; }

    public String toString() { return tipo; }
}
