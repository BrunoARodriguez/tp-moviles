package com.lmr.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class BuscarPlatoActivity extends AppCompatActivity {
EditText etPrecioMinimo;
EditText etPrecioMaximo;
EditText etNombrePlato;
Button btnBuscarPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_plato);
    etPrecioMinimo=(EditText) findViewById(R.id.ingresoPrecioMinimo);
    etPrecioMaximo=(EditText) findViewById(R.id.editTextingresoPrecioMaximo);
    etNombrePlato=(EditText) findViewById(R.id.ingresoNombreABuscar);
    btnBuscarPlato=/Button) findViewById(R.id.btnBuscarPlato);

    }
}
