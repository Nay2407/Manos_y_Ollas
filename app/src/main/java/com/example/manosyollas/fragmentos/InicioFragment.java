package com.example.manosyollas.fragmentos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manosyollas.R;
import com.example.manosyollas.actividades.ImageAdapter;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    // Parámetros opcionales
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Variables para los parámetros
    private String mParam1;
    private String mParam2;

    public InicioFragment() {
        // Required empty public constructor
    }

    // Método de fábrica para crear una nueva instancia del fragmento
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(R.drawable.caritaslima);
        arrayList.add(R.drawable.donacion);
        arrayList.add(R.drawable.ollascomunes1);
        arrayList.add(R.drawable.caritaslima);
        arrayList.add(R.drawable.donacion);
        arrayList.add(R.drawable.ollascomunes1);



        ImageAdapter adapter = new ImageAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
