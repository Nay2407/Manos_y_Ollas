package com.example.manosyollas.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.manosyollas.R;
import com.example.manosyollas.clases.Menu;

import java.util.ArrayList;

public class SuministroActivity extends AppCompatActivity implements Menu {

    private ArrayList<String> itemsSeleccionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suministro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        itemsSeleccionados = new ArrayList<>();

        ImageView btnVolver = findViewById(R.id.ic_flotante);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        configurarProductos();

        Button btnAceptar = findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemsSeleccionados.isEmpty()) {
                    Toast.makeText(SuministroActivity.this, "No se ha seleccionado ningún ítem", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SuministroActivity.this, ConfirmarActivity.class);
                intent.putStringArrayListExtra("itemsSeleccionados", itemsSeleccionados);
                startActivity(intent);
            }
        });
    }

    private void configurarProductos() {
        ImageView icArroz = findViewById(R.id.ic_arroz);
        ImageView icAceite = findViewById(R.id.ic_aceite);
        ImageView icHarina = findViewById(R.id.ic_harina);
        ImageView icSal = findViewById(R.id.ic_sal);
        ImageView icAzucar = findViewById(R.id.ic_azucar);
        ImageView icLeche = findViewById(R.id.ic_leche);
        ImageView icFideos = findViewById(R.id.ic_fideos);
        ImageView icLentejas = findViewById(R.id.ic_lentejas);
        ImageView icConservas = findViewById(R.id.ic_conservas);
        ImageView icHuevos = findViewById(R.id.ic_huevos);
        ImageView icAtun = findViewById(R.id.ic_atun);
        ImageView icCereales = findViewById(R.id.ic_cereales);

        icArroz.setOnClickListener(v -> toggleSelection("Arroz - 5kg", icArroz));
        icAceite.setOnClickListener(v -> toggleSelection("Aceite - 1L", icAceite));
        icHarina.setOnClickListener(v -> toggleSelection("Harina - 3kg", icHarina));
        icSal.setOnClickListener(v -> toggleSelection("Sal - 10kg", icSal));
        icAzucar.setOnClickListener(v -> toggleSelection("Azúcar - 2kg", icAzucar));
        icLeche.setOnClickListener(v -> toggleSelection("Leche - 1L", icLeche));
        icFideos.setOnClickListener(v -> toggleSelection("Fideos - 500g", icFideos));
        icLentejas.setOnClickListener(v -> toggleSelection("Lentejas - 1kg", icLentejas));
        icConservas.setOnClickListener(v -> toggleSelection("Conservas - 400g", icConservas));
        icHuevos.setOnClickListener(v -> toggleSelection("Huevos - docena", icHuevos));
        icAtun.setOnClickListener(v -> toggleSelection("Atún - lata", icAtun));
        icCereales.setOnClickListener(v -> toggleSelection("Cereales - 500g", icCereales));
    }

    private void toggleSelection(String item, ImageView imageView) {
        if (itemsSeleccionados.contains(item)) {
            itemsSeleccionados.remove(item);
            imageView.setAlpha(1.0f);
            Toast.makeText(this, item + " deseleccionado", Toast.LENGTH_SHORT).show();
        } else {
            itemsSeleccionados.add(item);
            imageView.setAlpha(0.5f);
            Toast.makeText(this, item + " seleccionado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickMenu(int id) {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("id", id);
        startActivity(menu);
        finish();
    }
}
