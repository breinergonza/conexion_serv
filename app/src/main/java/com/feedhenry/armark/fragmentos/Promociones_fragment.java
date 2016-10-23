package com.feedhenry.armark.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feedhenry.armark.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class Promociones_fragment extends Fragment {

    public static final String PROMOCIONES = "ARG_PAGE";
    private int mPage;

    public static Promociones_fragment newInstance(int page) {
        // Required empty public constructor
        Promociones_fragment promociones_fragment = new Promociones_fragment();
        Bundle args = new Bundle();
        args.putInt(PROMOCIONES,page);
        promociones_fragment.setArguments(args);
        return promociones_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(PROMOCIONES);
        Log.d("oncreate","resta");
        //idTorneo = (String) getArguments().get(idTorneoFrag);//  pasamos el bundel a string


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promociones_fragment, container, false);
    }

}
