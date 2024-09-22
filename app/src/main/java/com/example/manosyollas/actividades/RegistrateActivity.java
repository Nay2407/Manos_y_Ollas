package com.example.manosyollas.actividades;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.manosyollas.R;

public class RegistrateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtDni, txtNombre, txtApellido, txtFechanac, txtCorreo, txtContraseña, txtContraseña2;
    RadioGroup grpSexo;
    RadioButton rbtSinDefinir, rbtFemenino, rbtMasculino;
    Spinner cboDistritos;
    CheckBox chkTerminos;
    Button btnCrear, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtDni = findViewById(R.id.regTxtDni);
        txtNombre = findViewById(R.id.regTxtNombre);
        txtApellido = findViewById(R.id.regTxtApellido);
        txtFechanac = findViewById(R.id.regTxtFechanac);
        txtCorreo = findViewById(R.id.regTxtCorreo);
        txtContraseña = findViewById(R.id.regTxtContraseña);
        txtContraseña2 = findViewById(R.id.regTxtContraseña2);
        grpSexo = findViewById(R.id.regRgpSexo);
        rbtSinDefinir = findViewById(R.id.regRbtSinDefinir);
        rbtMasculino = findViewById(R.id.regRbtMasculino);
        rbtFemenino = findViewById(R.id.regRbtFemenino);
        cboDistritos = findViewById(R.id.regCboDistritos);
        chkTerminos = findViewById(R.id.regChkTerminos);
        btnCrear = findViewById(R.id.regBtnCrear);
        btnRegresar = findViewById(R.id.regBtnRegresar);
        txtFechanac.setOnClickListener(this);
        chkTerminos.setOnClickListener(this);
        btnCrear.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
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
        if (view.getId() == R.id.regTxtFechanac)
            seleccionarFecha();
        else if (view.getId() == R.id.regChkTerminos)
            mostrarTerminos();
        else if (view.getId() == R.id.regBtnCrear)
            crearCuenta();
        else if (view.getId() == R.id.regBtnRegresar)
            regresar();


    }

    private void regresar() {
        Intent sesion = new Intent(this,InicioActivity.class );
        startActivity(sesion);
        finish();

    }

    private void crearCuenta() {
        if (validarFormulario()){
            Toast.makeText(this," Cuenta Creada",Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarFormulario() {
        String dni = txtDni.getText().toString().trim();
        String nombre = txtNombre.getText().toString().trim();
        String apellido = txtApellido.getText().toString().trim();
        String fechaNac = txtFechanac.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();
        String contrasena = txtContraseña.getText().toString().trim();
        String contrasena2 = txtContraseña2.getText().toString().trim();

        if (dni.isEmpty()) {
            Toast.makeText(this, "DNI no puede estar vacio",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Nombre no puede estar vacío",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (apellido.isEmpty()) {
            Toast.makeText(this, "Apellido no puede estar vacío",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fechaNac.isEmpty()) {
            Toast.makeText(this, "Fecha de nacimiento no puede estar vacía",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (correo.isEmpty()) {
            Toast.makeText(this, "Correo no puede estar vacío",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (contrasena.isEmpty()) {
            Toast.makeText(this, "Contraseña no puede estar vacía",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (contrasena2.isEmpty()) {
            Toast.makeText(this, "Confirmación de contraseña no puede estar vacía",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!contrasena.equals(contrasena2)) {
            Toast.makeText(this, "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // Verificar que se haya aceptado los términos y condiciones
        if (!chkTerminos.isChecked()) {
            Toast.makeText(this, "Debe aceptar los términos y condiciones!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }


        int selectedSexId = grpSexo.getCheckedRadioButtonId();
        if (selectedSexId == -1) {
            Toast.makeText(this, "Debe seleccionar un sexo",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void mostrarTerminos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("            Terminos y condiciones");
        TextView messageView = new TextView(this);
        messageView.setText(
                "Al utilizar esta aplicación, aceptas cumplir con nuestros términos y condiciones, que regulan el uso de la plataforma, la gestión de la información compartida y las responsabilidades de los usuarios. Te comprometes a usar la app de manera legal y respetuosa, evitando contenido inapropiado. Reconoces que somos responsables únicamente por el funcionamiento de la aplicación y no por los daños que puedan surgir del uso de la misma. Además, nos reservamos el derecho de modificar estos términos en cualquier momento, y tu uso continuado de la app se considerará como aceptación de los cambios. Te recomendamos revisar los términos periódicamente. Si no estás de acuerdo con alguna parte, te solicitamos que no utilices la aplicación");
        messageView.setPadding(16, 16, 16, 16);
        messageView.setTextSize(16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            messageView.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        builder.setView(messageView); // Establece el TextView en el diálogo

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chkTerminos.setChecked(true);
                dialog.dismiss();

            }
        });
        AlertDialog terminos = builder.create() ;
        terminos.setCancelable(false);
        terminos.setCanceledOnTouchOutside(false);
        terminos.show();
    }

    private void seleccionarFecha() {

        DatePickerDialog dpd;
        final Calendar fechaActual = Calendar.getInstance();
        int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mes = fechaActual.get(Calendar.MONTH);
        int anho = fechaActual.get(Calendar.YEAR);
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                txtFechanac.setText(y+"-"+((m+1)<10 ?"0"+(m+1):(m+1))+"-"+(d<10?"0"+d:d));
            }
        }, anho, mes, dia);
        dpd.show();

    }
}










