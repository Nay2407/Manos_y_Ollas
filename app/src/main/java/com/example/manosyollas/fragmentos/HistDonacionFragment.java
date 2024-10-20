package com.example.manosyollas.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.manosyollas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistDonacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistDonacionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistDonacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistDonacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistDonacionFragment newInstance(String param1, String param2) {
        HistDonacionFragment fragment = new HistDonacionFragment();
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_hist_donacion, container, false);
        ImageView btnVolver = vista.findViewById(R.id.ic_flotante);
        btnVolver.setOnClickListener(v-> {
            selectFragment(new PerfilChatFragment());
        });

        return vista;
    }

    private void selectFragment(PerfilChatFragment pcf) {
        FragmentTransaction t = getParentFragmentManager().beginTransaction();
        t.replace(R.id.menRelArea,pcf).commit();

    }
}