package com.lmr.sendmeal.DAO;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lmr.sendmeal.DAO.rest.PedidoRes;
import com.lmr.sendmeal.DAO.rest.PlatoRest;
import com.lmr.sendmeal.domain.Pedido;
import com.lmr.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoRepositorio {
    public static String _SERVER = "http://192.168.0.103:5000/";
    //public  static  String _SERVER="http://10.15.155.134:5000/";

    private List<Pedido> listaPedidos;

    public  static  final int ALTA_PEDIDO=10;
    public  static final int ACTUALIZAR_PEDIDO=11;
    public  static  final  int BORRAR_PEDIDO = 12;
    public  static  final int ERROR_PEDIDO = 13;

    private static PedidoRepositorio INSTANCIA;

private  PedidoRepositorio(){
}

public  static  PedidoRepositorio getInstance(){
    if (INSTANCIA == null){
        INSTANCIA = new PedidoRepositorio();
INSTANCIA.configurarRetrofit();
INSTANCIA.listaPedidos = new ArrayList<>();
    }
return  INSTANCIA;
}

    private Retrofit rf;
    private PedidoRes pedidoRes;

    private void configurarRetrofit() {
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("sendmeal", "configurarRetrofit: INSTANCIA CREADA");

        this.pedidoRes = this.rf.create(PedidoRes.class);
    }

    public void actualizarPedido(final Pedido p, final Handler h) {
        Call<Pedido> llamada = this.pedidoRes.actualizar(p.getId(), p);
        llamada.enqueue(new
                                Callback<Pedido>() {
                                    @Override
                                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                                        Log.d("sendmeal", "despues de que responde" + response.isSuccessful());
                                        Log.d("sendmeal", "codigo: " + response.code());

                                        if (response.isSuccessful()) {
                                            Log.d("sendmeal", "ejecutando");
                                            listaPedidos.remove(p);
                                            listaPedidos.add(response.body());
                                            Message m = new Message();
                                            m.arg1 = ACTUALIZAR_PEDIDO;
                                            h.sendMessage(m);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Pedido> call, Throwable t) {
                                        Log.d("sendmeal", "Error: " + t.getMessage());
                                        Message m = new Message();
                                        m.arg1 = ERROR_PEDIDO;
                                        h.sendMessage(m);
                                    }
                                });
    }//cierra actualizar

    public void crearPedido(Pedido p, final Handler h) {
        Call<Pedido> llamada = this.pedidoRes.crear(p);
        llamada.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                Log.d("sendmeal", "despues de ejecutar" + response.isSuccessful());
                Log.d("sendmeal", "codigo: " + response.code());
                if (response.isSuccessful()) {
                    listaPedidos.add(response.body());
                    Message m = new Message();
                    m.arg1 = ALTA_PEDIDO;
                    h.sendMessage(m);

                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("sendmeal", "error " + t.getMessage());
                Message m = new Message();
                m.arg1 = ERROR_PEDIDO;
                h.sendMessage(m);
            }
        });
    } //cierra crearPlato

    public void eliminarPedido(final Pedido p, final Handler h) {
        Call<Pedido> llamada = this.pedidoRes.borrar(p.getId());
        llamada.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                Log.d("sendmeal", "mientras se ejecuta" + response.isSuccessful());
                Log.d("sendmeal", "codigo: " + response.code());
                if (response.isSuccessful()) {
                    listaPedidos.remove(p);
                    Message m = new Message();
                    m.arg1 = BORRAR_PEDIDO;

                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("sendmeal", "error: " + t.getMessage());
                Message m = new
                        Message();
                m.arg1 = ERROR_PEDIDO;
                h.sendMessage(m);
            }
        });
    }// cierra eliminarPlato

}
