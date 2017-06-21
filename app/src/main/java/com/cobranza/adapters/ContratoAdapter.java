package com.cobranza.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cobranza.R;
import com.cobranza.model.Contrato;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agutierrs on 25/05/17.
 */

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder>
{
    final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
    final NumberFormat format = NumberFormat.getCurrencyInstance();


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

        holder.tvNumeroCuenta.setText(contrato.getNumeroCuenta());
        holder.tvContrato.setText(contrato.getNumero());
        holder.tvNombre.setText(contrato.getCliente().getNombre());
        holder.tvDireccion.setText(contrato.getCliente().getDireccion());
        holder.tvNoPago.setText(contrato.getPago());
        holder.tvAbono.setText(format.format(contrato.getMontoPago()));
        holder.tvTotal.setText(format.format(contrato.getMonto()));

        if (contrato.getPagosAtrazados() > 0) {
            holder.tvPagosAtrazados.setText(String.valueOf(contrato.getPagosAtrazados()));
        } else {
            holder.lyPagosAtrasados.setVisibility(View.GONE);
        }

        if (contrato.getCargoInteres() > 0) {
            holder.tvCargoInteres.setText(format.format(contrato.getCargoInteres()));
        } else {
            holder.lyCargoInteres.setVisibility(View.GONE);
        }

        if (contrato.getOtrosCargos() > 0) {
            holder.tvOtrosCargos.setText(format.format(contrato.getOtrosCargos()));
            holder.tvMotivoOtrosCargos.setText(contrato.getMotivoOtrosCargos());
        } else {
            holder.lyOtrosCargos.setVisibility(View.GONE);
        }

        holder.tvSaldo.setText(format.format(contrato.getSaldo()));
        holder.tvAviso.setText(contrato.getAviso());
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

        TextView tvNumeroCuenta;
        TextView tvContrato;
        TextView tvNombre;
        TextView tvDireccion;
        TextView tvNoPago;
        TextView tvAbono;
        TextView tvTotal;
        TextView tvPagosAtrazados;
        TextView tvCargoInteres;
        TextView tvOtrosCargos;
        TextView tvMotivoOtrosCargos;
        TextView tvSaldo;
        TextView tvAviso;

        LinearLayout lyPagosAtrasados;
        LinearLayout lyCargoInteres;
        LinearLayout lyOtrosCargos;

        public ContratoViewHolder(View itemView, final ContratoClickListener clickListener)
        {
            super(itemView);

            tvNumeroCuenta = (TextView) itemView.findViewById(R.id.numeroCuenta);
            tvContrato = (TextView) itemView.findViewById(R.id.contrato);
            tvNombre = (TextView) itemView.findViewById(R.id.nombre);
            tvDireccion = (TextView) itemView.findViewById(R.id.direccion);
            tvNoPago = (TextView) itemView.findViewById(R.id.noPago);
            tvAbono = (TextView) itemView.findViewById(R.id.montoAbono);
            tvTotal = (TextView) itemView.findViewById(R.id.total);
            tvPagosAtrazados = (TextView) itemView.findViewById(R.id.pagosAtrazados);
            tvCargoInteres = (TextView) itemView.findViewById(R.id.cargoInteres);
            tvOtrosCargos = (TextView) itemView.findViewById(R.id.otrosCargos);
            tvMotivoOtrosCargos = (TextView) itemView.findViewById(R.id.motivoOtrosCargos);
            tvSaldo = (TextView) itemView.findViewById(R.id.saldo);
            tvAviso = (TextView) itemView.findViewById(R.id.aviso);

            lyPagosAtrasados = (LinearLayout) itemView.findViewById(R.id.lyPagosAtrazados);
            lyCargoInteres = (LinearLayout) itemView.findViewById(R.id.lyCargoInteres);
            lyOtrosCargos = (LinearLayout) itemView.findViewById(R.id.lyOtrosCargos);

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
