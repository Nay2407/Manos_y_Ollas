package com.example.manosyollas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manosyollas.R;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        ArrayList<Integer> arrayList = new ArrayList<>();

        // Agrega las imágenes locales desde res/drawable
        arrayList.add(R.drawable.imagen2);
        arrayList.add(R.drawable.imagen2);
        arrayList.add(R.drawable.imagen2);
        arrayList.add(R.drawable.imagen2);
        arrayList.add(R.drawable.imagen2);
        arrayList.add(R.drawable.imagen2);

        ImageAdapter adapter = new ImageAdapter(PrincipalActivity.this, arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, int resourceId) {
                Intent intent = new Intent(PrincipalActivity.this, ImageViewActivity.class);
                intent.putExtra("image", resourceId);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        PrincipalActivity.this,   // Contexto de la actividad actual
                        imageView,           // Vista que será usada para la transición
                        "image"              // Nombre de la transición compartida
                );
                startActivity(intent, options.toBundle());
            }
        });
        //Boton Perfil

        ImageButton btnVerPerfil = findViewById(R.id.prinBtnVerPerfil);//
        TextView txtVerPerfil = findViewById(R.id.prinTxtVerPerfil);

        View.OnClickListener verPerfilClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, VerPerfilActivity.class);
                startActivity(intent);
            }
        };
        btnVerPerfil.setOnClickListener(verPerfilClickListener);
        txtVerPerfil.setOnClickListener(verPerfilClickListener);

        //Boton Donaciones

        ImageButton btnDonaciones = findViewById(R.id.prinBtnDonaciones);
        TextView txtDonaciones = findViewById(R.id.prinTxtDonaciones);

        View.OnClickListener donacionesClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, DonacionesActivity.class);
                startActivity(intent);
            }
        };
        btnDonaciones.setOnClickListener(donacionesClickListener);
        txtDonaciones.setOnClickListener(donacionesClickListener);

        //Boton Ver Sedes

        ImageButton btnVerSedes = findViewById(R.id.prinBtnVerSedes);
        TextView txtVerSedes = findViewById(R.id.prinTxtVerSedes);

        View.OnClickListener verSedesClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, VerSedesActivity.class);
                startActivity(intent);
            }
        };
        btnVerSedes.setOnClickListener(verSedesClickListener);
        txtVerSedes.setOnClickListener(verSedesClickListener);

        //Ver Sobre Nosotros

        ImageButton btnSobreNosotros = findViewById(R.id.prinBtnSobreNosotros);
        TextView txtSobreNosotros = findViewById(R.id.prinTxtVerSedes);

        View.OnClickListener sobreNosotrosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, SobreNosotrosActivity.class);
                startActivity(intent);
            }
        };
        btnSobreNosotros.setOnClickListener(sobreNosotrosClickListener);
        txtSobreNosotros.setOnClickListener(sobreNosotrosClickListener);

        //Ver Foros
        ImageButton btnForos = findViewById(R.id.prinBtnForos);
        TextView txtForos = findViewById(R.id.prinTxtForos);

        View.OnClickListener forosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, ForoActivity.class);
                startActivity(intent);
            }
        };
        btnForos.setOnClickListener(forosClickListener);
        txtForos.setOnClickListener(forosClickListener);

    }
}

