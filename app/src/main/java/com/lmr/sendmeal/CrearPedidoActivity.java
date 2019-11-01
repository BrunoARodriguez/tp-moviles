package com.lmr.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lmr.sendmeal.DAO.BaseDeDatosRepositorio;
import com.lmr.sendmeal.domain.Pedido;

import java.time.LocalDateTime;
import java.util.Calendar;

public class CrearPedidoActivity extends AppCompatActivity {
private EditText etDia;
private EditText etMes;
private EditText etAnio;
private Button btnCrearPedido;
private Button btnEnviarPedido;
//variables
    private Pedido pedido;
    private Calendar fechaPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);
etDia=(EditText) findViewById(R.id.ingresoDiaPedido);
etMes = (EditText) findViewById(R.id.ingresoMesPedido);
etAnio = (EditText) findViewById(R.id.ingresoAnioPedido);
btnCrearPedido = (Button) findViewById(R.id.btnCrearPedido);
btnEnviarPedido = (Button) findViewById(R.id.btnEnviarPedido);

btnCrearPedido.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Integer dia= Integer.valueOf(etDia.getText().toString());
Integer mes= Integer.valueOf(etMes.getText().toString());
Integer anio= Integer.valueOf(etAnio.getText().toString());

    if ((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12)){
    fechaPedido= Calendar.getInstance();
    fechaPedido.set(Calendar.DAY_OF_MONTH,dia);
    fechaPedido.set(Calendar.MONTH,mes);
    fechaPedido.set(Calendar.YEAR,anio);
    pedido = new Pedido(fechaPedido,1,0.0,0.0);
        BaseDeDatosRepositorio.getInstance(CrearPedidoActivity.this).crearPedido(pedido);
        Toast.makeText(CrearPedidoActivity.this,"su pedido se creó",Toast.LENGTH_LONG).show();
    }
else
    Toast.makeText(CrearPedidoActivity.this,"No se pudo crear el pedido",Toast.LENGTH_LONG).show();
    }
});
    } // cierra onCreate

}

