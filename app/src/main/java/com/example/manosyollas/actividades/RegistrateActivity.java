package com.example.manosyollas.actividades;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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
    }

    private void crearCuenta() {
    }

    private void mostrarTerminos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Terminos y condiciones");
        builder.setMessage("hola hola ");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chkTerminos.setChecked(true);
                dialog.dismiss();

            }
        });
        AlertDialog terminos = builder.create() ;
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










