package com.lmr.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
List<Plato> platosBuscados=new ArrayList<>();
private  Double precioMin;
private  Double precioMax;
private  String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_plato);
    etPrecioMinimo=(EditText) findViewById(R.id.ingresoPrecioMinimo);
    etPrecioMaximo=(EditText) findViewById(R.id.editTextingresoPrecioMaximo);
    etNombrePlato=(EditText) findViewById(R.id.ingresoNombreABuscar);
    btnBuscarPlato = (Button) findViewById(R.id.btnBuscarPlato);

    precioMax=Double.parseDouble(etPrecioMaximo.getText().toString());
precioMin=Double.parseDouble(etPrecioMinimo.getText().toString());
nombre=etNombrePlato.getText().toString();
btnBuscarPlato.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       buscarPlatos();
        Message m=new Message();
        m.obj=platosBuscados;
       Intent i=new Intent(BuscarPlatoActivity.this, ListaItemsActivity.class);
       startActivity(i);
    }
});

    }

    private   void  buscarPlatos(){
if (precioMin.equals(null) && precioMax.equals(null) && nombre.isEmpty()){
PlatoRepositorio.getInstance()    .buscarPlatos(miHandler);

}


else {
    if (nombre.isEmpty()) {
        buscarPorRangoPrecio();
    }
    else {
        buscarPorNombre();
    }

}
    }
//metodo por nombre
private  void  buscarPorNombre(){
for (Plato pl: PlatoRepositorio.getInstance().getListaPlatos()) {
if (pl.getTitulo().equals(nombre)){
platosBuscados.add(pl);
}
} // cierra for
    }

    //metodo por precio
private  void  buscarPorRangoPrecio(){
for (Plato pl: PlatoRepositorio.getInstance().getListaPlatos()){
if (precioMin <= pl.getPrecio() && pl.getPrecio() <= precioMax && platosBuscados.contains(pl)) {
platosBuscados.add(pl);
}

}//cierra for

}

Handler miHandler=new Handler(Looper.myLooper()){
    @Override
    public void handleMessage(@NonNull Message msg) {
    switch (msg.arg1){
        case PlatoRepositorio.PLATO_CONSULTA:
platosBuscados=PlatoRepositorio.getInstance().getListaPlatos();
    break;
        case  PlatoRepositorio.PLATO_ERROR:
            Toast.makeText(BuscarPlatoActivity.this,"No se pudieron buscar platos",Toast.LENGTH_LONG).show();
            break;
    }//cierra swich
    }
};
}

