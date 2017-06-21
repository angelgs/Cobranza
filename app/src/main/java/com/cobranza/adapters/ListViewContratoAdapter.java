package com.cobranza.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cobranza.R;
import com.cobranza.model.Contrato;

import java.util.List;

/**
 * Created by agutierrs on 19/06/17.
 */

public class ListViewContratoAdapter extends BaseAdapter
{
    private List<Contrato> mContratos;
    private LayoutInflater mInflater;


    public ListViewContratoAdapter(Context contratosFragment, List<Contrato> contratos)
    {
        mContratos = contratos;
        mInflater = LayoutInflater.from(contratosFragment);
    }

    @Override
    public int getCount()
    {
        return mContratos.size();
    }

    @Override
    public Object getItem(int i)
    {
        return mContratos.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contrato_item, null);
            holder = new ViewHolder();
            holder.tvContrato = (TextView) convertView.findViewById(R.id.contrato);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvContrato.setText(mContratos.get(position).getNumero());

        return convertView;
    }

    static class ViewHolder
    {
        TextView tvContrato;
    }

}
