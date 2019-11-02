package com.lmr.sendmeal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lmr.sendmeal.DAO.BaseDeDatosRepositorio;
import com.lmr.sendmeal.domain.ItemsPedido;
import com.lmr.sendmeal.domain.Pedido;
import com.lmr.sendmeal.domain.Plato;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CrearPedidoActivity extends AppCompatActivity {
private EditText etDia;
private EditText etMes;
private EditText etAnio;
private  EditText etCantidad;
private Button btnCrearPedido;
private Button btnEnviarPedido;
//variables
    private Pedido pedido;
    private Calendar fechaPedido;
private  Integer cantidad;
private Double precio;
List<ItemsPedido> lista=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);
etDia=(EditText) findViewById(R.id.ingresoDiaPedido);
etMes = (EditText) findViewById(R.id.ingresoMesPedido);
etAnio = (EditText) findViewById(R.id.ingresoAnioPedido);
etCantidad=(EditText) findViewById(R.id.ingresoCantidadPedido);
btnCrearPedido = (Button) findViewById(R.id.btnCrearPedido);
btnEnviarPedido = (Button) findViewById(R.id.btnEnviarPedido);

btnCrearPedido.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Integer dia = Integer.valueOf(etDia.getText().toString());
        Integer mes = Integer.valueOf(etMes.getText().toString());
        Integer anio = Integer.valueOf(etAnio.getText().toString());

        if ((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12)) {
            fechaPedido = Calendar.getInstance();
            fechaPedido.set(Calendar.DAY_OF_MONTH, dia);
            fechaPedido.set(Calendar.MONTH, mes);
            fechaPedido.set(Calendar.YEAR, anio);
            pedido = new Pedido(fechaPedido, 1, 0.0, 0.0);
            BaseDeDatosRepositorio.getInstance(CrearPedidoActivity.this).crearPedido(pedido);
            Toast.makeText(CrearPedidoActivity.this, "su pedido se creó", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CrearPedidoActivity.this, "No se pudo crear el pedido", Toast.LENGTH_LONG).show();
        }
        btnEnviarPedido.setEnabled(true);
    }
    });

btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(CrearPedidoActivity.this,BuscarPlatoActivity.class);
intent.putExtra("agregar a pedido",true);
startActivityForResult(intent,1);
    }
});

cantidad = Integer.valueOf(etCantidad.getText().toString());
    } // cierra onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
switch (requestCode){
    case 1:
if (resultCode= Activity.RESULT_OK) {
    Plato pl = data.getParcelableExtra("plato");
    precio = cantidad * pl.getPrecio();
    ItemsPedido item = new ItemsPedido(pedido.getId(), pl, cantidad,precio);
lista.add(item);
    pedido.setItems(lista);

}
        break;
}

    }
}

