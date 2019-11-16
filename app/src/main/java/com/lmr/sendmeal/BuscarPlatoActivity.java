package com.lmr.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lmr.sendmeal.DAO.PlatoRepositorio;

import com.lmr.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.List;

public class BuscarPlatoActivity extends AppCompatActivity {
    EditText etPrecioMinimo;
    EditText etPrecioMaximo;
    EditText etNombrePlato;
    Button btnBuscarPlato;
    //variables
    List<Plato> platosBuscados = new ArrayList<>();
    private Double precioMin;
    private Double precioMax;
    private String nombre;
    private Boolean buscarParaPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_plato);
        etPrecioMinimo = (EditText) findViewById(R.id.ingresoPrecioMinimo);
        etPrecioMaximo = (EditText) findViewById(R.id.editTextingresoPrecioMaximo);
        etNombrePlato = (EditText) findViewById(R.id.ingresoNombreABuscar);
        btnBuscarPlato = (Button) findViewById(R.id.btnBuscarPlato);

        etPrecioMinimo.setText("0.0");
        etPrecioMaximo.setText("0.0");
        buscarParaPedido = getIntent().getBooleanExtra("agregar a pedido", false);
        btnBuscarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precioMax = Double.parseDouble(etPrecioMaximo.getText().toString());
                precioMin = Double.parseDouble(etPrecioMinimo.getText().toString());
                nombre = etNombrePlato.getText().toString();

                PlatoRepositorio.getInstance().buscarPorParametros(precioMin, precioMax, nombre, miHandler);

            }
        });

    }


    Handler miHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.arg1) {
                case PlatoRepositorio.PLATO_CONSULTA:
                    platosBuscados = PlatoRepositorio.getInstance().getListaPlatos();
                    if (buscarParaPedido) {

                        Plato plato = platosBuscados.get(0);
                        if (plato != null) {
                            Intent i = new Intent(BuscarPlatoActivity.this, CrearPedidoActivity.class);
i.putExtra("plato",plato);

                            setResult(Activity.RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(BuscarPlatoActivity.this, "No se encontraron platos, vuelva a intentarlo", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("platoBuscados", platosBuscados.toString());
                        Intent i = new Intent(BuscarPlatoActivity.this, ListaPlatosActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("listaPlatos", (ArrayList<? extends Parcelable>) platosBuscados);
                        i.putExtras(bundle);
                        i.putExtra("actualizar", true);
                        startActivity(i);
                    }

                    break;
                case PlatoRepositorio.PLATO_ERROR:
                    Toast.makeText(BuscarPlatoActivity.this, "No se pudieron buscar platos", Toast.LENGTH_LONG).show();
                    break;
            }//cierra swich
        }
    };

}

