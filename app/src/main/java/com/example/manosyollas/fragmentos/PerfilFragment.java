package com.example.manosyollas.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manosyollas.R;
import com.example.manosyollas.actividades.AjustesActivity;
import com.example.manosyollas.actividades.EditarPerfilActivity;
import com.example.manosyollas.actividades.InicioActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener{
    private final static int BOTONES []= {R.id.btnPerDona, R.id.btnPerNoti};
    private Button btnCerrar;
    ImageButton btnAjustes,btnDonaciones;
    Button btnEditarPerfil;

    TextView perNombre, perCorreo, perApellido;
    private SharedPreferences sharedPreferences;
    Integer idUsuario;
    ImageView vperImagen;

    private static final String URL_MOSTRAR_USUARIO = "http://manosyollas.atwebpages.com/services/MostrarUsuarioxid.php";


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);

        Button btnDona = vista.findViewById(R.id.btnPerDona);
        Button btnNoti = vista.findViewById(R.id.btnPerNoti);

        perCorreo = vista.findViewById(R.id.perCorreo);
        perNombre = vista.findViewById(R.id.perNombre);
        vperImagen = vista.findViewById(R.id.vperImagen);
        perApellido = vista.findViewById(R.id.perApellido);

        sharedPreferences = getActivity().getSharedPreferences("IdUsuario", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getInt("idUsuario", -1);

        if (idUsuario != -1) {
            mostrarUsuario(idUsuario);
        }

        btnCerrar = vista.findViewById(R.id.vperbtnCerrarSesion);
        btnEditarPerfil = vista.findViewById(R.id.vperbtnEditarPerfil);
        btnAjustes = vista.findViewById(R.id.vperimgajustes);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cerrarSesion();

            }
        });
        btnEditarPerfil.setOnClickListener(this);
        btnAjustes.setOnClickListener(this);

        loadFragment(new HistDonUsuFragment());

        btnDona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HistDonacionFragment());  // Cambiar a MenuListaFragment
            }
        });

        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new NotificacionesFragment());  // Cambiar a MenuMapaFragment
            }
        });
        return vista;
    }


    private void mostrarUsuario(int idUsuario) {
        String url = URL_MOSTRAR_USUARIO + "?idUsuario=" + idUsuario;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    //vperImagen.setI
                    perNombre.setText(response.getString("nombre"));
                    perApellido.setText(response.getString("apellidos"));
                    perCorreo.setText(response.getString("correo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getActivity().getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void cerrarSesion() {
        // 1. Eliminar los datos de SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false); // Elimina todos los valores guardados (esto puede ajustarse según lo que quieras eliminar)
        editor.apply(); // Guarda los cambios

        // 2. Redirigir al usuario a la pantalla de inicio
        Intent intent = new Intent(getActivity(), InicioActivity.class);
        FirebaseAuth.getInstance().signOut();
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpia la pila de actividades
        startActivity(intent);
        getActivity().finish(); // Cierra la actividad actual para prevenir el regreso con el botón de atrás
    }

    private void loadFragment(Fragment Fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frgContainerCambio, Fragment);  // Cambia el FrameLayout por el nuevo fragmento
        transaction.addToBackStack(null);  // Opción para agregar a la pila de retroceso
        transaction.commit();
    }
    public void onClick(View view) {
        if (view.getId() == R.id.vperimgajustes)
            ingresarAjustes();
        if (view.getId()==R.id.vperbtnEditarPerfil)
            editarPerfil();
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