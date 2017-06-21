package com.cobranza.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cobranza.R;
import com.cobranza.model.Articulo;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by agutierrs on 19/06/17.
 */

public class ArticulosArrayAdapter extends ArrayAdapter<Articulo>
{
    final NumberFormat format = NumberFormat.getCurrencyInstance();
    private final Context context;
    private final List<Articulo> articulos;

    public ArticulosArrayAdapter(Context context, List<Articulo> articulos)
    {
        super(context, R.layout.articulo_item, articulos);
        this.context = context;
        this.articulos = articulos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.articulo_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvCantidad = (TextView) rowView.findViewById(R.id.cantidad);
            viewHolder.tvDescripcion = (TextView) rowView.findViewById(R.id.descripcion);
            viewHolder.tvPrecio = (TextView) rowView.findViewById(R.id.precio);

            rowView.setTag(viewHolder);
        }


        Articulo articulo = articulos.get(position);
        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.tvCantidad.setText(String.valueOf(articulo.getCantidad()));
        holder.tvDescripcion.setText(articulo.getDescripcion());
        holder.tvPrecio.setText(format.format(articulo.getPrecioVenta()));

        return rowView;
    }

    static class ViewHolder
    {
        public TextView tvCantidad;
        public TextView tvDescripcion;
        public TextView tvPrecio;
    }
}
