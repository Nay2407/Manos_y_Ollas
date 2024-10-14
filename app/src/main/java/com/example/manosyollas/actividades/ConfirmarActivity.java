package com.example.manosyollas.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.manosyollas.R;

import java.util.ArrayList;

public class ConfirmarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> itemsSeleccionados = getIntent().getStringArrayListExtra("itemsSeleccionados");

        if (itemsSeleccionados != null && itemsSeleccionados.size() > 0) {
            Log.d("ConfirmarActivity", "Items recibidos: " + itemsSeleccionados.toString());
            Toast.makeText(this, "Datos recibidos", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("ConfirmarActivity", "No se recibieron datos.");
            Toast.makeText(this, "No se recibieron datos", Toast.LENGTH_SHORT).show();
        }

        LinearLayout layout = findViewById(R.id.linearLayoutItems);

        for (String item : itemsSeleccionados) {
            String[] parts = item.split(" - ");

            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rowLayout.setPadding(16, 16, 16, 16);
            rowLayout.setGravity(Gravity.CENTER_HORIZONTAL);

            TextView productoTextView = new TextView(this);
            productoTextView.setText(parts[0]);
            productoTextView.setTextSize(16);
            productoTextView.setGravity(Gravity.CENTER);
            productoTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView cantidadTextView = new TextView(this);
            cantidadTextView.setText(parts[1]);
            cantidadTextView.setTextSize(16);
            cantidadTextView.setGravity(Gravity.CENTER);
            cantidadTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            rowLayout.addView(productoTextView);
            rowLayout.addView(cantidadTextView);

            layout.addView(rowLayout);
        }

        ImageView icFlotante = findViewById(R.id.ic_flotante);
        icFlotante.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmarActivity.this, SuministroActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmarActivity.this, SuministroActivity.class);
                intent.putStringArrayListExtra("itemsSeleccionados", itemsSeleccionados);
                startActivity(intent);
                finish();
            }
        });
    }
}