package com.example.manosyollas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

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
    }
}

