package com.example.manosyollas.actividades;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.manosyollas.R;
import com.example.manosyollas.clases.ForumItem;
import com.example.manosyollas.controladores.ForumAdapter;

import java.util.ArrayList;
import java.util.List;

public class ForoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ForumAdapter forumAdapter;
    private List<ForumItem> forumItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear y agregar ítems a la lista
        forumItemList = new ArrayList<>();
        forumItemList.add(new ForumItem("Foro 1", "Descripción del foro 1", R.drawable.ollita));
        forumItemList.add(new ForumItem("Foro 2", "Descripción del foro 2", R.drawable.ollita));
        // Añadir más ítems según sea necesario

        forumAdapter = new ForumAdapter(forumItemList);
        recyclerView.setAdapter(forumAdapter);
    }
}