package com.cobranza.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cobranza.CobranzaApp;
import com.cobranza.R;
import com.cobranza.adapters.DetalleContratoPagerAdapter;
import com.cobranza.adapters.OnFragmentInteractionListener;
import com.cobranza.model.Contrato;
import com.cobranza.model.ContratoDao;
import com.cobranza.model.DaoSession;
import com.cobranza.model.Pago;
import com.cobranza.model.PagoDao;

import org.greenrobot.greendao.query.Query;

public class DetalleContratoActivity extends AppCompatActivity implements OnFragmentInteractionListener
{
    DetalleContratoPagerAdapter mDetalleContratoPagerAdapter;
    ViewPager mViewPager;

    private DaoSession daoSession;
    private ContratoDao contratoDao;
    private PagoDao pagoDao;
    private Query<Contrato> contratoQuery;
    private Query<Pago> pagoQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contrato);
        Intent intent = getIntent();
        Long contratoId = intent.getExtras().getLong("contratoId");

        mDetalleContratoPagerAdapter =
                new DetalleContratoPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDetalleContratoPagerAdapter);

        CobranzaApp cobranzaApp = (CobranzaApp) getApplication();
        daoSession = cobranzaApp.getDaoSession();
        contratoDao = daoSession.getContratoDao();
        pagoDao = daoSession.getPagoDao();
        contratoQuery = contratoDao.queryBuilder().build();
        pagoQuery = pagoDao.queryBuilder().build();

        final Contrato contrato = contratoDao.load(contratoId);

        mDetalleContratoPagerAdapter.setContrato(contrato);

    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
