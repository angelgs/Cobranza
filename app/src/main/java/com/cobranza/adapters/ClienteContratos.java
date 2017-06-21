package com.cobranza.adapters;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cobranza.R;
import com.cobranza.model.Contrato;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClienteContratos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClienteContratos extends ListFragment
{

    private List<Contrato> mContratos;
    private OnFragmentInteractionListener mListener;


    public ClienteContratos()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClienteContratos.
     */
    // TODO: Rename and change types and number of parameters
    public static ClienteContratos newInstance(List<Contrato> contratos)
    {
        ClienteContratos fragment = new ClienteContratos();
        fragment.setContratos(contratos);
        return fragment;
    }

    public void setContratos(List<Contrato> contratos)
    {
        mContratos = contratos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        List<String> items = new ArrayList<String>();
        for (Contrato contrato : mContratos) {
            items.add(String.format("Contrato No.: %s", contrato.getNumero()));
        }

        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.contrato_item, items));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_cliente_contratos, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View popupView = inflater.inflate(R.layout.popup_articulos, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        ListView lvArticulos = (ListView) popupView.findViewById(R.id.articulos);
        lvArticulos.setAdapter(new ArticulosArrayAdapter(getView().getContext(),
                mContratos.get(position).getArticulos()));

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        v.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY,
                location[0], location[1] + v.getHeight());
/*
        // Mostramos un mensaje con el elemento pulsado
        Toast.makeText(getActivity(), "Ha pulsado " + mContratos.get(position).getNumero(),
                Toast.LENGTH_SHORT).show();
*/
    }
/*
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
*/
}
