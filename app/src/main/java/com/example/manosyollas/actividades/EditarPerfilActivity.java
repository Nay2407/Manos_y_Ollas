package com.example.manosyollas.actividades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.manosyollas.R;

public class EditarPerfilActivity extends AppCompatActivity implements View.OnClickListener{
    Spinner cboDistritos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cboDistritos = findViewById(R.id.editPerCboDistritos);
        llenarDistritos();
    }
    private void llenarDistritos() {
        String distritos[ ]={"Seleccione Distrito" , "San Juan de Lurigancho","Lince", "Comas"
                , "San Miguel","Chorrillos", "San Anita","Los Olivos"};
        cboDistritos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.
                simple_spinner_dropdown_item,distritos                           ));
    }

    @Override
    public void onClick(View view) {

    }
}