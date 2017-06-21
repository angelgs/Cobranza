package com.cobranza.adapters;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cobranza.R;
import com.cobranza.model.Pago;

import java.util.List;

/**
 * Created by agutierrs on 20/06/17.
 */

public class ClientePagos extends ListFragment
{
    private List<Pago> mPagos;

    public ClientePagos()
    {
    }

    public static ClientePagos newInstance(List<Pago> pagos)
    {
        ClientePagos clientePagos = new ClientePagos();
        clientePagos.setPagos(pagos);
        return clientePagos;
    }

    public void setPagos(List<Pago> pagos)
    {
        mPagos = pagos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_cliente_pagos, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new PagosArrayAdapter(getActivity(), mPagos));
        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);


        // Mostramos un mensaje con el elemento pulsado
        Toast.makeText(getActivity(), "Ha pulsado " + mPagos.get(position).getMonto(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.pagos_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_reimprimir:
                Toast.makeText(getActivity(), "Ha pulsado " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();

                return true;
            case R.id.menu_editar:

                Toast.makeText(getActivity(), "Ha pulsado " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();

                return true;
            case R.id.menu_cancelar:

                Toast.makeText(getActivity(), "Ha pulsado " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
        /*
        switch (item.getItemId()) {
            case MENU_CONTEXT_DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Log.d(TAG, "removing item pos=" + info.position);
                //mAdapter.remove(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
        */

    }


}
