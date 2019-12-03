package com.lmr.sendmeal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lmr.sendmeal.DAO.BaseDeDatosRepositorio;
import com.lmr.sendmeal.DAO.ItemsPedidoDao;
import com.lmr.sendmeal.DAO.PedidoDao;
import com.lmr.sendmeal.DAO.PedidoRepositorio;
import com.lmr.sendmeal.DAO.rest.PedidoRes;
import com.lmr.sendmeal.domain.ItemsPedido;
import com.lmr.sendmeal.domain.Pedido;
import com.lmr.sendmeal.domain.Plato;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CrearPedidoActivity extends AppCompatActivity {
    private EditText etDia;
    private EditText etMes;
    private EditText etAnio;
    private EditText etCantidad;
    private TextView tvPlatoPedido;
    private Button btnCrearPedido;
    private Button btnEnviarPedido;
    private  Button btnAgregarPlato;
    //variables
    private  static  final int CODIGO = 0;
    private Pedido pedido;
    private ItemsPedido itemsPedido;
    private Calendar fechaPedido;
    private Integer cantidad;
    private Double precio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);
        etDia = (EditText) findViewById(R.id.ingresoDiaPedido);
        etMes = (EditText) findViewById(R.id.ingMesPed);
        etAnio = (EditText) findViewById(R.id.ingresoAnioPedido);
        etCantidad = (EditText) findViewById(R.id.ingCantPed);
        tvPlatoPedido = (TextView) findViewById(R.id.tvPlatoDelPedido);
        btnCrearPedido = (Button) findViewById(R.id.btnCrearPedido);
        btnEnviarPedido = (Button) findViewById(R.id.btnEnviarPedido);
btnAgregarPlato = (Button) findViewById(R.id.agregarPlato);

        etCantidad.setText("1");
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
Intent intent = new Intent(CrearPedidoActivity.this,MapsActivity.class);
startActivityForResult(intent,CODIGO);

                } else {
                    Toast.makeText(CrearPedidoActivity.this, "No se pudo crear el pedido", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pedido.setEstado(2);
                PedidoRepositorio.getInstance().crearPedido(pedido,miHandler);
            }
        });


    } // cierra onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case  CODIGO:
                if (resultCode == Activity.RESULT_OK && data != null){
//                    Toast.makeText(CrearPedidoActivity.this,"volvio a pedido",Toast.LENGTH_LONG).show();
                    Double latitud = data.getDoubleExtra("latitud",0);
                    Double longitud = data.getDoubleExtra("longitud",0);
                    pedido = new Pedido(fechaPedido, 1, latitud, longitud);
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
                    pedido.setToken(preferences.getString("registrar_id", ""));
                    GuardarPedido tareaGuardarPedido= new GuardarPedido();
                    tareaGuardarPedido.execute(pedido);

                    btnAgregarPlato.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cantidad = Integer.valueOf(etCantidad.getText().toString());

                            Intent intent = new Intent(CrearPedidoActivity.this, BuscarPlatoActivity.class);
                            intent.putExtra("agregar a pedido", true);
                            startActivityForResult(intent, 1);

                        }
                    });

                }
                break;
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Plato pl = data.getParcelableExtra("plato");
                    precio = cantidad * pl.getPrecio();
itemsPedido = new ItemsPedido(pedido.getId(), pl, cantidad, precio);
                    pedido.setEstado(2);
                    pedido.getItems().add(itemsPedido);
                    GuardarPedido tareaGuardarPedido= new GuardarPedido();
                    tareaGuardarPedido.execute(pedido);
GuardarItem tareaGuardarItem= new GuardarItem();
tareaGuardarItem.execute(itemsPedido);
                    Toast.makeText(CrearPedidoActivity.this,"El plato " + pl.getTitulo() + " se agregó al pedido", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(CrearPedidoActivity.this,"No se pudo agregar el plato al pedido",Toast.LENGTH_LONG).show();

                }
                break;
        }

    }

    public class GuardarPedido extends AsyncTask<Pedido, Void, Void> {

        @Override
        protected Void doInBackground(Pedido... pedidos) {
            PedidoDao dao = BaseDeDatosRepositorio.getInstance(CrearPedidoActivity.this)
                    .getMiBaseDeDatos().pedidoDao();
            if (pedidos[0].getId() != null && pedidos[0].getId() > 0) {
                dao.actualizarPedido(pedido);
            } else {
                dao.insertarPedido(pedido);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
btnAgregarPlato.setEnabled(true);
            Toast.makeText(CrearPedidoActivity.this, "su pedido se creó", Toast.LENGTH_LONG).show();

        /*
        Intent intent = new Intent(CrearPedidoActivity.this,MapsActivity.class);
            startActivity(intent);
*/
        }
    } //cierra la clase Guardar pedido

    public  class  GuardarItem extends  AsyncTask<ItemsPedido,Void,Void>{

        @Override
        protected Void doInBackground(ItemsPedido... itemsPedidos) {
            ItemsPedidoDao dao=BaseDeDatosRepositorio.getInstance(CrearPedidoActivity.this)
                    .getMiBaseDeDatos().itemsPedidoDao();
            if (itemsPedidos[0].getId()!= null && itemsPedidos[0].getId() > 0){
                dao.actualizarItemPedido(itemsPedido);
            } else {
                dao.insertarItemPedido(itemsPedido);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            etCantidad.setText("1");
            btnEnviarPedido.setEnabled(true);
            tvPlatoPedido.setVisibility(View.VISIBLE);
            tvPlatoPedido.setText("Si desea agregar mas platos vuelve a presionar 'Agregar platos al pedido'");

        }
    }//cierra guardarItem

Handler miHandler = new Handler(Looper.myLooper()){
    @Override
    public void handleMessage(@NonNull Message msg) {
switch (msg.arg1){
    case  PedidoRepositorio.ALTA_PEDIDO:
        Toast.makeText(CrearPedidoActivity.this,"Su pedido se envió con éxito",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CrearPedidoActivity.this,HomeActivity.class);
        startActivity(intent);
        break;
        case  PedidoRepositorio.ERROR_PEDIDO:
            Toast.makeText(CrearPedidoActivity.this,"No se pudo enviar el pedido", Toast.LENGTH_LONG).show();
            tvPlatoPedido.setText("Vuelve a intentar enviar el pedido");
}
    }
};
}

