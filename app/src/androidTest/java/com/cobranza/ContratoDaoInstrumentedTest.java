package com.cobranza;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.cobranza.model.Articulo;
import com.cobranza.model.Contrato;
import com.cobranza.model.DaoMaster;
import com.cobranza.model.DaoSession;
import com.cobranza.model.Persona;
import com.cobranza.model.TipoPersona;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

/**
 * Created by agutierrs on 28/05/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContratoDaoInstrumentedTest
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
    public void testAddContrato()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Calle 17 # 182\n");
        sb.append("Guadalupe Proletaria\n");
        sb.append("Gustavo A. Madero, 07670 CDMX \n");
        sb.append("entre av. 28 y av. Guadalupe");

        Persona cliente = new Persona();
        cliente.setId(1L);
        cliente.setTipoPersona(TipoPersona.CLIENTE);
        cliente.setEsAval(false);
        cliente.setNombre("Angel Gutiérrez Servin");
        cliente.setDireccion(sb.toString());

        sb = new StringBuilder();
        sb.append("Calle 17 # 207\n");
        sb.append("Guadalupe Proletaria\n");
        sb.append("Gustavo A. Madero, 07670 CDMX \n");
        sb.append("entre av. 28 y av. Santiago");

        Persona referencia = new Persona();
        referencia.setId(2L);
        referencia.setTipoPersona(TipoPersona.REFERENCIA);
        referencia.setEsAval(true);
        referencia.setNombre("Juan Servin");
        referencia.setDireccion(sb.toString());

        daoSession.getPersonaDao().insert(cliente);
        daoSession.getPersonaDao().insert(referencia);

        List<Persona> personas = daoSession.getPersonaDao().loadAll();
        Assert.assertTrue(personas.size() > 0);

        Contrato contrato = new Contrato();
        contrato.setId(1L);
        contrato.setNumero("0823728372");
        contrato.setDireccion(cliente.getDireccion());
        contrato.setCliente(cliente);
        contrato.setFecha(new Date());
        contrato.setMonto(10000.00);
        contrato.setSaldo(5000.00);
        contrato.setMontoLiquidar(3000.00);

        daoSession.getContratoDao().insert(contrato);

        Contrato c = daoSession.getContratoDao().load(1L);
        Assert.assertNotNull(c);
        Assert.assertNotNull(c.getCliente());

        c.getReferencias().add(referencia);

        Articulo articulo = new Articulo();
        articulo.setId(1L);
        articulo.setContrato(c);
        articulo.setCantidad(1);
        articulo.setDescripcion("TV LG 32");
        articulo.setPrecioUnitario(8000.00);
        articulo.setPrecioVenta(10000.00);

        c.getArticulos().add(articulo);

        daoSession.getContratoDao().update(c);

        c = daoSession.getContratoDao().load(1L);

        Assert.assertTrue(c.getReferencias().size() > 0);
        Assert.assertTrue(c.getArticulos().size() > 0);
    }

}
