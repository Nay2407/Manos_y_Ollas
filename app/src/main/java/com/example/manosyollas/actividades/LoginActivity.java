package com.example.manosyollas.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.manosyollas.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txtCorreo, txtContrasena;
    Button btnIngresar;
    CheckBox checkRecordar;
    ImageView icFlotante2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtCorreo = findViewById(R.id.logTxtEmail);
        txtContrasena = findViewById(R.id.logTxtPassword);
        btnIngresar = findViewById(R.id.LogBtnIngresar);
        checkRecordar = findViewById(R.id.logChkRecordar);
        icFlotante2 = findViewById(R.id.ic_flotante2);

        btnIngresar.setOnClickListener(this);
        icFlotante2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.LogBtnIngresar) {
            iniciarSesion(txtCorreo.getText().toString(), txtContrasena.getText().toString(), false);
        }else if(v.getId() == R.id.ic_flotante2) {
            Intent inicioIntent = new Intent(this, InicioActivity.class);
            startActivity(inicioIntent);
        }
    }
    private void iniciarSesion(String txtCorreo,String txtContrasena, boolean recordar) {
        if (txtCorreo.equals("novato@upn.pe")&& txtContrasena.equals("abc123$")){
            Intent principal = new Intent(this, PrincipalActivity.class);
            principal.putExtra("nombre","Cachimbo UPN");
            startActivity(principal);
            finish();
        }
        else {
            Toast.makeText(this,"Error: Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}