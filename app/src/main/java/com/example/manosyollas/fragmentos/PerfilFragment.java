package com.example.manosyollas.fragmentos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.manosyollas.R;
import com.example.manosyollas.actividades.AjustesActivity;
import com.example.manosyollas.actividades.EditarPerfilActivity;
import com.example.manosyollas.actividades.HistDonacionActivity;
import com.example.manosyollas.actividades.HistUsuarioActivity;
import com.example.manosyollas.actividades.InicioActivity;
import com.example.manosyollas.actividades.TransferenciaActivity;
import com.example.manosyollas.clases.Menu;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener{
    private Button btnCerrar;
    ImageButton btnAjustes,btnDonaciones;
     Button btnEditarPerfil;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnCerrar = vista.findViewById(R.id.vperbtnCerrarSesion);
        btnAjustes = vista.findViewById(R.id.vperimgajustes);
        btnDonaciones=vista.findViewById(R.id.vperimgHisDona);
        btnEditarPerfil = vista.findViewById(R.id.vperbtnEditarPerfil);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cerrarSesion();

            }
        });
        btnAjustes.setOnClickListener(this);
        btnEditarPerfil.setOnClickListener(this);
        btnDonaciones.setOnClickListener(this);
        return vista;
    }

    private void cerrarSesion() {
        // 1. Eliminar los datos de SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false); // Elimina todos los valores guardados (esto puede ajustarse según lo que quieras eliminar)
        editor.apply(); // Guarda los cambios

        // 2. Redirigir al usuario a la pantalla de inicio
        Intent intent = new Intent(getActivity(), InicioActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpia la pila de actividades
        startActivity(intent);
        getActivity().finish(); // Cierra la actividad actual para prevenir el regreso con el botón de atrás
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.vperimgajustes)
            ingresarAjustes();
        if (view.getId()==R.id.vperbtnEditarPerfil)
            editarPerfil();
        if (view.getId() == R.id.vperimgHisDona)
            ingresarDonaciones();
    }

    private void ingresarDonaciones() {
        Intent donaciones = new Intent(getActivity(), HistUsuarioActivity.class);
        startActivity(donaciones);
    }

    private void editarPerfil() {
        Intent editarP = new Intent(getActivity(), EditarPerfilActivity.class);
        startActivity(editarP);
    }

    private void ingresarAjustes() {
        Intent ajustes = new Intent(getActivity(), AjustesActivity.class);
        startActivity(ajustes);
    }
}