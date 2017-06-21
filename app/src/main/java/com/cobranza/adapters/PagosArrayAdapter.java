package com.cobranza.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cobranza.R;
import com.cobranza.model.Pago;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by agutierrs on 20/06/17.
 */

public class PagosArrayAdapter extends ArrayAdapter<Pago>
{
    final NumberFormat format = NumberFormat.getCurrencyInstance();
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss ");
    private final Context context;
    private List<Pago> mPagos;

    public PagosArrayAdapter(Context context, List<Pago> pagos)
    {
        super(context, R.layout.pagos_list_item, pagos);
        this.context = context;
        mPagos = pagos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.pagos_list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvFecha = (TextView) rowView.findViewById(R.id.fechaAbono);
            viewHolder.tvMontoAbono = (TextView) rowView.findViewById(R.id.montoAbono);

            rowView.setTag(viewHolder);
            rowView.setLongClickable(true);
        }


        Pago pago = mPagos.get(position);

        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.tvFecha.setText(dateFormat.format(pago.getFechaPago()));
        holder.tvMontoAbono.setText(format.format(pago.getMonto()));

        return rowView;
    }

    static class ViewHolder
    {
        public TextView tvFecha;
        public TextView tvMontoAbono;
    }

}
