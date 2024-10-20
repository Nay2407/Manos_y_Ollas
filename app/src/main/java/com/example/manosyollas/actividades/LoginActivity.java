package com.example.manosyollas.actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.manosyollas.fragmentos.InicioFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.security.MessageDigest;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txtCorreo, txtContrasena;
    Button btnIngresar;
    CheckBox checkRecordar;
    ImageView icFlotante2;
    private static final String URL_LOGIN = "http://manosyollas.atwebpages.com/services/InicioSesion.php";
    private static final String URL_MOSTRAR_ID = "http://manosyollas.atwebpages.com/services/MostrarID.php";

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

            SharedPreferences sharedPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", checkRecordar.isChecked()); // Save logged in state
            editor.apply();


            iniciarSesion(txtCorreo.getText().toString(), txtContrasena.getText().toString(), false);
        }else if(v.getId() == R.id.ic_flotante2) {
            Intent inicioIntent = new Intent(this, InicioActivity.class);
            startActivity(inicioIntent);
        }
    }
    private void iniciarSesion(String txtCorreo,String txtContrasena, boolean recordar) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String clavehash=hashPassword(txtContrasena);
        params.put("correo", txtCorreo);
        params.put("clave", clavehash);

        client.post(URL_LOGIN, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.has("idUsuario")) {
                        // Guarda el ID de usuario en SharedPreferences
                        obtenerIdUsuario(txtCorreo, response.getInt("idUsuario")); // Pasa el idUsuario a obtenerIdUsuario

                        // Inicio de sesión exitoso, redirigir a la siguiente Activity
                        Intent principal = new Intent(LoginActivity.this, MenuActivity.class);
                        principal.putExtra("id", 2);
                        startActivity(principal);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(LoginActivity.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerIdUsuario(String correo, int idUsuario) {
        // Guardar en SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("IdUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idUsuario", idUsuario); // Usar el idUsuario pasado
        editor.putString("correo", correo);
        editor.apply();
    }

    // Método para hashear la contraseña con SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}