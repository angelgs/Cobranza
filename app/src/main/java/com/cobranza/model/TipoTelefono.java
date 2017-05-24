package com.cobranza.model;

/**
 * Created by agutierrs on 21/05/17.
 */

public enum TipoTelefono
{
    CASA("CASA"),
    CELULAR("CELULAR"),
    TRABAJO("TRABAJO");

    final String tipo;

    private TipoTelefono(final String tipo)
    {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
