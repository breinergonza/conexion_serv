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
public class Productos_fragment extends Fragment {
    public static final String PRODUCTOS = "ARG_PAGE";
    private int mPage;

    public static Productos_fragment newInstance(int page) {
        // Required empty public constructor
        Productos_fragment productos_fragment = new Productos_fragment();
        Bundle args = new Bundle();
        args.putInt(PRODUCTOS,page);
        productos_fragment.setArguments(args);
        return productos_fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(PRODUCTOS);
        Log.d("oncreate","resta");
        //idTorneo = (String) getArguments().get(idTorneoFrag);//  pasamos el bundel a string


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productos_fragment, container, false);
    }

}
