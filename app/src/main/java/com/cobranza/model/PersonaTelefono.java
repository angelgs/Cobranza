package com.cobranza.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by agutierrs on 24/05/17.
 */

@Entity
class PersonaTelefono
{
    @Id(autoincrement = true)
    private Long id;

    private Long personaId;
    private Long telefonoId;
    @Generated(hash = 1788061929)
    public PersonaTelefono(Long id, Long personaId, Long telefonoId) {
        this.id = id;
        this.personaId = personaId;
        this.telefonoId = telefonoId;
    }
    @Generated(hash = 694428817)
    public PersonaTelefono() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPersonaId() {
        return this.personaId;
    }
    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }
    public Long getTelefonoId() {
        return this.telefonoId;
    }
    public void setTelefonoId(Long telefonoId) {
        this.telefonoId = telefonoId;
    }
}
