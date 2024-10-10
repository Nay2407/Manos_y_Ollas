package com.example.manosyollas.actividades;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.manosyollas.R;
import com.example.manosyollas.clases.MenuLocal;
import com.example.manosyollas.fragmentos.DonacionesFragment;
import com.example.manosyollas.fragmentos.ForosFragment;
import com.example.manosyollas.fragmentos.InicioFragment;
import com.example.manosyollas.fragmentos.MapaFragment;
import com.example.manosyollas.fragmentos.PerfilFragment;
import com.example.manosyollas.fragmentos.SedesFragment;

public class MenuLocalActivity extends AppCompatActivity implements MenuLocal {
    Fragment[] fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_local);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLocal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fragments = new Fragment[2];
        fragments[0] = new SedesFragment();
        fragments[1] = new MapaFragment();

        int id = getIntent().getIntExtra("id", -1);
        onClickMenuLocal(id);
    }

    @Override
    public void onClickMenuLocal(int id) {
        FragmentManager fr = getSupportFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
        ft.replace(R.id.menLocalRelArea, fragments[id]);
        ft.commit();
    }
}