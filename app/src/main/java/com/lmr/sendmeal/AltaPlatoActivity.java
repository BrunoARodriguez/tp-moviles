package com.lmr.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lmr.sendmeal.DAO.PlatoRepositorio;

import com.lmr.sendmeal.domain.Plato;



public class AltaPlatoActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText etTitulo;
    private EditText etDescripcion;
    private EditText etPrecio;
    private EditText etCalorias;
    private Button btnGuardar;
    private Plato plato;
    private Toolbar toolbar;
    private Boolean editando = false;
    private Integer idPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);
        etTitulo = (EditText) findViewById(R.id.ingresoTituloPlato);
        etDescripcion = (EditText) findViewById(R.id.ingresoDescripcionPlato);
        etPrecio = (EditText) findViewById(R.id.ingresoPrecioPlato);
        etCalorias = (EditText) findViewById(R.id.ingresoCaloriaPlato);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        //para que el toolbar muestre la opccion de ir hacia atras
        this.toolbar = findViewById(R.id.myTolbar);


/*
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
*/

        plato = getIntent().getParcelableExtra("parametro");

        if (plato != null) {

            etTitulo.setText(plato.getTitulo());
            etDescripcion.setText(plato.getDescripcion());
            etPrecio.setText(String.valueOf(plato.getPrecio()));
            etCalorias.setText(String.valueOf(plato.getCalorias()));
        }
        Boolean desavilitarCampos = false;
        desavilitarCampos = getIntent().getExtras().getBoolean("mostrandoOferta");
        if (desavilitarCampos) {
            etTitulo.setEnabled(false);
            etDescripcion.setEnabled(false);
            etPrecio.setEnabled(false);
            etCalorias.setEnabled(false);
            btnGuardar.setEnabled(false);
        } else {
            btnGuardar.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        String titulo = this.etTitulo.getText().toString();
        String descripcion = this.etDescripcion.getText().toString();
        Double precio = Double.parseDouble(this.etPrecio.getText().toString());
        Integer caloria = Integer.parseInt(this.etCalorias.getText().toString());
//validamos que no sean nulos los campos
        if (titulo.isEmpty() || descripcion.isEmpty() || (caloria <= 0)) {
            this.etTitulo.setError("Obligatorio");
            this.etDescripcion.setError("Obligatorio");

            this.etCalorias.setError("Valor mayor a 0");
        } else {
            if (titulo.length() >= 5 && descripcion.length() >= 10) {
                if (plato != null) {
                    plato.setTitulo(titulo);
                    plato.setDescripcion(descripcion);
                    plato.setPrecio(precio);
                    plato.setCalorias(caloria);
                    Toast.makeText(AltaPlatoActivity.this, "¡Su plato se editó!", Toast.LENGTH_LONG).show();
                    PlatoRepositorio.getInstance().actualizarPlato(plato, miHandler);

                } else {

                    this.plato = new Plato(titulo, descripcion, precio, caloria);
                    PlatoRepositorio.getInstance().crearPlato(plato, miHandler);

                }
            } else
                Toast.makeText(AltaPlatoActivity.this, "El titulo debe ser mayor a 4 caracteres y la descripcion mayor a 9", Toast.LENGTH_LONG).show();

        }
    } //cierra el metodo onClick

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//responde si es el boton home
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Handler miHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Log.d("sendmeal", "Vuelve al handler " + msg.arg1);

            switch (msg.arg1) {
                case PlatoRepositorio.PLATO_ALTA:
                    Toast.makeText(AltaPlatoActivity.this, "Su plato a sido dado de alta! ", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AltaPlatoActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case PlatoRepositorio.PLATO_UPDATE:
                    Intent intent_1 = new Intent(AltaPlatoActivity.this, ListaPlatosActivity.class);
                    intent_1.putExtra("parametro", plato)
                            .putExtra("parametro2", PlatoRepositorio.getInstance().getListaPlatos().indexOf(plato));

                    setResult(Activity.RESULT_OK, intent_1);
                    finish();
                    break;
case PlatoRepositorio.PLATO_ERROR:
    Toast.makeText(AltaPlatoActivity.this,"No se pudo guardar el plato creado",Toast.LENGTH_LONG).show();
    break;
            }//cierra swich

        }
    }; //cierra handler
}
