package com.example.manosyollas.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manosyollas.R;
import com.example.manosyollas.clases.ForumItem;
import com.example.manosyollas.controladores.ForumAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForosFragment extends Fragment {
    private RecyclerView recyclerView;
    private ForumAdapter forumAdapter;
    private List<ForumItem> forumItemList;
    private View bottomNavBar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForosFragment newInstance(String param1, String param2) {
        ForosFragment fragment = new ForosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_foros, container, false);

        //super.onCreate(savedInstanceState);
        bottomNavBar= vista.findViewById(R.id.ContenedorLista);

        recyclerView = vista.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear y agregar ítems a la lista
        cargarForos();
        // Añadir más ítems según sea necesario
        
        forumAdapter=new ForumAdapter(forumItemList, new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ForumItem foro) {
                muestranavegacion();

                abrirChat(foro);
            }
        });
        //forumAdapter = new ForumAdapter(forumItemList);
        recyclerView.setAdapter(forumAdapter);

        return  vista;
    }

    private void muestranavegacion() {
        bottomNavBar.setVisibility(View.GONE);
    }
    private void ocultanavegacion() {
        bottomNavBar.setVisibility(View.VISIBLE);
    }

    private void abrirChat(ForumItem foro) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChatFragment chatFragment = new ChatFragment();

        // Aquí puedes pasar datos del foro seleccionado
        Bundle args = new Bundle();
        args.putString("foroId", foro.getForoId());
        args.putString("foroTitle", foro.getTitle());
        chatFragment.setArguments(args);



        fragmentTransaction.replace(R.id.menusito, chatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private List<ForumItem> cargarForos() {
        // Aquí iría la lógica para cargar la lista de foros (desde una API, base de datos, etc.)
        forumItemList = new ArrayList<>();
        forumItemList.add(new ForumItem("Foro 1", "Descripción del foro 1", R.drawable.ollita));
        forumItemList.add(new ForumItem("Foro 2", "Descripción del foro 2", R.drawable.ollita));
        return forumItemList;
    }
}