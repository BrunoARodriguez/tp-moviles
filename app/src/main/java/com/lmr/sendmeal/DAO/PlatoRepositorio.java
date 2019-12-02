package com.lmr.sendmeal.DAO;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lmr.sendmeal.DAO.rest.PlatoRest;

import com.lmr.sendmeal.domain.CuentaBancaria;
import com.lmr.sendmeal.domain.Pedido;
import com.lmr.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlatoRepositorio {
    public static String _SERVER = "http://192.168.0.103:5000/";
    //public  static  String _SERVER="http://10.15.155.134:5000/";

    private List<Plato> listaPlatos;

    public static final int PLATO_ALTA = 1;
    public static final int PLATO_UPDATE = 2;
    public static final int PLATO_BORRAR = 3;
    public static final int PLATO_CONSULTA = 4;
    public static final int PLATO_ERROR = 9;

    private static PlatoRepositorio INSTANCIA;

    private PlatoRepositorio() {
    }

    public static PlatoRepositorio getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new PlatoRepositorio();
            INSTANCIA.configurarRetrofit();
            INSTANCIA.listaPlatos = new ArrayList<>();
        }
        return INSTANCIA;
    }

    private Retrofit rf;
    private PlatoRest platoRest;

    private void configurarRetrofit() {
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("sendmeal", "configurarRetrofit: INSTANCIA CREADA");

        this.platoRest = this.rf.create(PlatoRest.class);
    }

    public void actualizarPlato(final Plato pl, final Handler h) {
        Call<Plato> llamada = this.platoRest.actualizar(pl.getId(), pl);
        llamada.enqueue(new
                                Callback<Plato>() {
                                    @Override
                                    public void onResponse(Call<Plato> call, Response<Plato> response) {
                                        Log.d("sendmeal", "despues de que responde" + response.isSuccessful());
                                        Log.d("sendmeal", "codigo: " + response.code());

                                        if (response.isSuccessful()) {
                                            Log.d("sendmeal", "ejecutando");
                                            listaPlatos.remove(pl);
                                            listaPlatos.add(response.body());
                                            Message m = new Message();
                                            m.arg1 = PLATO_UPDATE;
                                            h.sendMessage(m);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Plato> call, Throwable t) {
                                        Log.d("sendmeal", "Error: " + t.getMessage());
                                        Message m = new Message();
                                        m.arg1 = PLATO_ERROR;
                                        h.sendMessage(m);
                                    }
                                });
    }//cierra actualizar

    public void crearPlato(Plato pl, final Handler h) {
        Call<Plato> llamada = this.platoRest.crear(pl);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("sendmeal", "despues de ejecutar" + response.isSuccessful());
                Log.d("sendmeal", "codigo: " + response.code());
                if (response.isSuccessful()) {
                    listaPlatos.add(response.body());
                    Message m = new Message();
                    m.arg1 = PLATO_ALTA;
                    h.sendMessage(m);

                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("sendmeal", "error " + t.getMessage());
                Message m = new Message();
                m.arg1 = PLATO_ERROR;
                h.sendMessage(m);
            }
        });
    } //cierra crearPlato

    public void eliminarPlato(final Plato pl, final Handler h) {
        Call<Plato> llamada = this.platoRest.borrar(pl.getId());
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("sendmeal", "mientras se ejecuta" + response.isSuccessful());
                Log.d("sendmeal", "codigo: " + response.code());
                if (response.isSuccessful()) {
                    listaPlatos.remove(pl);
                    Message m = new Message();
                    m.arg1 = PLATO_BORRAR;
                    Log.d("sendmeals", "llega? repo");

                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("sendmeal", "error: " + t.getMessage());
                Message m = new
                        Message();
                m.arg1 = PLATO_ERROR;
                h.sendMessage(m);
            }
        });
    }// cierra eliminarPlato

    //get de la lista
    public List<Plato> getListaPlatos() {
        return listaPlatos;
    }

    public void buscarPlatos(final Handler h) {
        Call<List<Plato>> llamada = this.platoRest.buscarTodosPlatos();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                Log.d("sendmeal", "se ejecuta " + response.isSuccessful());
                Log.d("sendmeal", "codigo es " + response.code());
                if (response.isSuccessful()) {
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Message m = new
                            Message();
                    m.arg1 = PLATO_CONSULTA;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("sendmeal", "error: " + t.getMessage());
                Message m = new Message();
                m.arg1 = PLATO_ERROR;
                h.sendMessage(m);
            }
        });
    }

    public void buscarPorParametros(Double precioMin, Double precioMax, String titulo, final Handler h) {
        Call<List<Plato>> llamada = this.platoRest.buscarPorPrecioONombre(precioMin, precioMax, titulo);
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                Log.d("sendmeals", "se ejecuta buscar por parametros" + response.isSuccessful());
                if (response.isSuccessful()) {
                    Log.d("sendmeals", "se ejecuta");
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Log.d("lista", listaPlatos.toString());
                    Message m = new Message();
                    m.arg1 = PLATO_CONSULTA;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("sendmeals", "error " + t.getMessage());

                Message m = new Message();
                m.arg1 = PLATO_ERROR;
                h.sendMessage(m);

            }
        });

    } //cierra buscarPorParametros
}


