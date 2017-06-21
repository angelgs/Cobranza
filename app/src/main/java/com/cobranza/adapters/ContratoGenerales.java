package com.cobranza.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cobranza.R;
import com.cobranza.model.Contrato;
import com.cobranza.model.Telefono;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContratoGenerales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContratoGenerales extends Fragment
{
    final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
    final NumberFormat format = NumberFormat.getCurrencyInstance();

    private Contrato contrato;

    private OnFragmentInteractionListener mListener;

    public ContratoGenerales()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contrato Parameter 1.
     * @return A new instance of fragment ContratoGenerales.
     */
    // TODO: Rename and change types and number of parameters
    public static ContratoGenerales newInstance(@NonNull Contrato contrato)
    {
        ContratoGenerales fragment = new ContratoGenerales();
        fragment.setContrato(contrato);

        return fragment;
    }

    public void setContrato(@NonNull Contrato contrato)
    {
        this.contrato = contrato;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contrato_generales, container, false);

        StringBuilder sb = new StringBuilder();
        List<Telefono> telefonos = contrato.getCliente().getTelefonos();
        for (Telefono telefono : telefonos) {
            sb.append(String.format("%s: %s\n", telefono.getTipo(), telefono.getNumero()));
        }

        ((TextView) rootView.findViewById(R.id.numeroCuenta)).setText(contrato.getNumeroCuenta());
        ((TextView) rootView.findViewById(R.id.contrato)).setText(contrato.getNumero());
        ((TextView) rootView.findViewById(R.id.nombre)).setText(contrato.getCliente().getNombre());
        ((TextView) rootView.findViewById(R.id.direccion)).setText(contrato.getCliente().getDireccion());
        ((TextView) rootView.findViewById(R.id.telefono)).setText(sb.toString());
        ((TextView) rootView.findViewById(R.id.noPago)).setText(contrato.getPago());
        ((TextView) rootView.findViewById(R.id.montoAbono)).setText(format.format(contrato.getMontoPago()));
        ((TextView) rootView.findViewById(R.id.total)).setText(format.format(contrato.getMonto()));
        ((TextView) rootView.findViewById(R.id.pagosAtrazados)).setText(String.valueOf(contrato.getPagosAtrazados()));
        ((TextView) rootView.findViewById(R.id.cargoInteres)).setText(format.format(contrato.getCargoInteres()));
        ((TextView) rootView.findViewById(R.id.otrosCargos)).setText(format.format(contrato.getOtrosCargos()));
        ((TextView) rootView.findViewById(R.id.motivoOtrosCargos)).setText(contrato.getMotivoOtrosCargos());
        ((TextView) rootView.findViewById(R.id.saldo)).setText(format.format(contrato.getSaldo()));
        ((TextView) rootView.findViewById(R.id.aviso)).setText(contrato.getAviso());

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

}
