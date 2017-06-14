package com.cobranza.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cobranza.R;
import com.cobranza.model.Contrato;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agutierrs on 25/05/17.
 */

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder>
{
    final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

    private ContratoClickListener clickListener;
    private List<Contrato> contratos;

    public ContratoAdapter(ContratoClickListener contratoClickListener)
    {
        this.clickListener = contratoClickListener;
        this.contratos = new ArrayList<Contrato>();
    }

    public void setContratos(@NonNull List<Contrato> contratos)
    {
        this.contratos = contratos;
        notifyDataSetChanged();
    }

    public Contrato getContrato(int position)
    {
        return contratos.get(position);
    }

    @Override
    public ContratoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new ContratoViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(ContratoViewHolder holder, int position)
    {
        Contrato contrato = contratos.get(position);

        holder.tvNombre.setText(contrato.getCliente().getNombre());
        holder.tvDireccion.setText(contrato.getCliente().getDireccion());
    }

    @Override
    public int getItemCount()
    {
        return contratos.size();
    }

    public interface ContratoClickListener
    {
        void onContratoClick(int position);

        void onContratoLongClick(int position);
    }

    class ContratoViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvNombre;
        TextView tvDireccion;

        public ContratoViewHolder(View itemView, final ContratoClickListener clickListener)
        {
            super(itemView);

            tvNombre = (TextView) itemView.findViewById(R.id.nombre);
            tvDireccion = (TextView) itemView.findViewById(R.id.direccion);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (clickListener != null) {
                        clickListener.onContratoClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {

                @Override
                public boolean onLongClick(View view)
                {
                    if (clickListener != null) {
                        clickListener.onContratoLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
