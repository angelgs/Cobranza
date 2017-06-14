package com.cobranza.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cobranza.model.Contrato;

/**
 * Created by agutierrs on 04/06/17.
 */

public class DetalleContratoPagerAdapter extends FragmentPagerAdapter
{
    final int PAGE_COUNT = 3;
    private String tabtitles[] = new String[]{"Generales", "Contratos", "Referencias"};

    private Contrato contrato;

    public DetalleContratoPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void setContrato(@NonNull Contrato contrato)
    {
        this.contrato = contrato;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position) {
            case 0:
                return ContratoGenerales.newInstance(contrato);
            case 1:
                return ClienteContratos.newInstance(null, null);
            case 2:
                return ClienteReferencias.newInstance(null, null);

        }
        return null;
/*
        Fragment fragment = new ContratoFragment();
        Bundle args = new Bundle();
        args.putInt(ContratoFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);

        return fragment;
*/
    }

    @Override
    public int getCount()
    {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabtitles[position];

    }
/*
    public static class ContratoFragment extends Fragment
    {
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.fragment_page, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return  rootView;
        }
    }
*/
}

