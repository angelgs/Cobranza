package com.cobranza.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by agutierrs on 27/05/17.
 */

@Entity
class ContratoPersona
{
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private Long contratoId;

    @NotNull
    private Long personaId;

    @Generated(hash = 889330120)
    public ContratoPersona(Long id, @NotNull Long contratoId,
                           @NotNull Long personaId)
    {
        this.id = id;
        this.contratoId = contratoId;
        this.personaId = personaId;
    }

    @Generated(hash = 628305253)
    public ContratoPersona()
    {
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getContratoId()
    {
        return this.contratoId;
    }

    public void setContratoId(Long contratoId)
    {
        this.contratoId = contratoId;
    }

    public Long getPersonaId()
    {
        return this.personaId;
    }

    public void setPersonaId(Long personaId)
    {
        this.personaId = personaId;
    }
}
