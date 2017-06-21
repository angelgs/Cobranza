package com.cobranza;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.cobranza.model.DaoMaster;
import com.cobranza.model.DaoSession;
import com.cobranza.model.Persona;
import com.cobranza.model.PersonaTelefono;
import com.cobranza.model.Telefono;
import com.cobranza.model.TipoPersona;
import com.cobranza.model.TipoTelefono;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by agutierrs on 24/05/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PersonaDaoInstrumentedTest
{
    private static final String DB_NAME = "test-cobranza-db";

    private Context instrumentationCtx;
    private DaoSession daoSession;

    @Before
    public void setUp()
    {
        instrumentationCtx = InstrumentationRegistry.getTargetContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(instrumentationCtx, DB_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    @After
    public void finish()
    {
        daoSession.clear();
        daoSession.getDatabase().close();
        instrumentationCtx.deleteDatabase(DB_NAME);
    }

    @Test
    public void testPreconditions()
    {
        Assert.assertNotNull(daoSession);
    }

    @Test
    public void testAddPersona()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("Calle 17 # 182\n");
        sb.append("Guadalupe Proletaria\n");
        sb.append("07670\n");
        sb.append("Gustavo A. Madero, CDMX \n");
        sb.append("entre av. 28 y av. Guadalupe");

        String direccion = sb.toString();

        Telefono tel1 = new Telefono();
        tel1.setTipo(TipoTelefono.CASA);
        tel1.setAlias("Casa");
        tel1.setNumero("5553898989");
        long idTel1 = daoSession.getTelefonoDao().insert(tel1);

        Persona persona = new Persona();
        persona.setId(3l);
        persona.setNombre("Test cliente");
        persona.setTipoPersona(TipoPersona.CLIENTE);
        persona.setDireccion(direccion);
        persona.setEsAval(false);

        daoSession.getPersonaDao().insert(persona);

        PersonaTelefono personaTelefono = new PersonaTelefono();
        personaTelefono.setPersonaId(3l);
        personaTelefono.setTelefonoId(idTel1);
        daoSession.getPersonaTelefonoDao().insert(personaTelefono);

        List<Persona> personas = daoSession.getPersonaDao().loadAll();

        Assert.assertFalse(personas.isEmpty());
        Assert.assertTrue(personas.size() > 0);

        persona = daoSession.getPersonaDao().load(3l);
        Assert.assertNotNull(persona);

        Assert.assertTrue(persona.getTelefonos().size() > 0);

    }

    @Test
    public void testDeletePersona()
    {
        Persona persona = daoSession.getPersonaDao().load(3l);
        Assert.assertNotNull(persona);

        daoSession.getPersonaDao().delete(persona);

        List<Persona> personas = daoSession.getPersonaDao().loadAll();

        //Assert.assertTrue(personas.size() );

    }
}
