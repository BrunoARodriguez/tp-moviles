package com.lmr.sendmeal.DAO.rest;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lmr.sendmeal.Plato;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlatoRepositorio {
public  static  String _SERVER="http//10.0.2.2:5000/";
private List<Plato> listaPlatos;

public  static  final  int PLATO_ALTA=1;
public  static  final  int PLATO_UPDATE=2;
public  static  final int PLATO_BORRAR=3;
public  static  final  int PLATO_CONSULTA=4;
public  static  final int PLATO_ERROR=9;

private static PlatoRepositorio INSTANCIA;

private  PlatoRepositorio(){}

public  static  PlatoRepositorio getInstance(){
if (INSTANCIA==null){
    INSTANCIA=new PlatoRepositorio();
INSTANCIA.configurarRetrofit();
    INSTANCIA.listaPlatos=new ArrayList<>();
}
return  INSTANCIA;
}

private Retrofit rf;
private  PlatoRest platoRest;

private  void  configurarRetrofit(){
    this.rf=new Retrofit.Builder()
    .baseUrl(_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
.build();
    Log.d("sendmeal", "configurarRetrofit: INSTANCIA CREADA");

this.platoRest=this.rf.create(PlatoRest.class);
}

public void  actualizarPlato(final  Plato pl, final Handler h){
    Call<Plato> llamada=this.platoRest.ctualizar(pl.getId(),pl);
    llamada.enqueue(new
                            Callback<Plato>() {
                                @Override
                                public void onResponse(Call<Plato> call, Response<Plato> response) {
Log.d("sendmeal", "despues de que responde"+response.isSuccessful());
Log.d("sendmeal", "codigo: "+response.code());

if (response.isSuccessful()){
    Log.d("sendmeal", "ejecutando");
listaPlatos.remove(pl);
listaPlatos.add(response.body());
    Message m=new
            Message();
    m.arg1=PLATO_UPDATE;
    h.sendMessage(m);
}
                                }

                                @Override
                                public void onFailure(Call<Plato> call, Throwable t) {
Log.d("sendmeal", "Error: "+t.getMessage());
Message m= new Message();
m.arg1=PLATO_ERROR;
h.sendMessage(m);
                                }
                            });
}//cierra actualizar

public void  crearPlato(final  Plato pl, final Handler h){
    Call<Plato> llamada=this.platoRest.crear(pl);
llamada.enqueue(new Callback<Plato>() {
    @Override
    public void onResponse(Call<Plato> call, Response<Plato> response) {
       Log.d("sendmeal", "despues de ejecutar"+response.isSuccessful());
       Log.d("sendmeal", "codigo: "+response.code());
if (response.isSuccessful()){
listaPlatos.add(response.body());
Message m=new
        Message();
m.arg1=PLATO_ALTA;
h.sendMessage(m);

}
    }

    @Override
    public void onFailure(Call<Plato> call, Throwable t) {
Log.d("sendmeal", "error "+t.getMessage());
Message m=new Message();
m.arg1=PLATO_ERROR;
h.sendMessage(m);
    }
});
} //cierra crearPlato

public  void eliminarPlato(final  Plato pl,final  Handler h){
Call<Plato> llamada = this.platoRest.borrar(pl.getId());
llamada.enqueue(new Callback<Plato>() {
    @Override
    public void onResponse(Call<Plato> call, Response<Plato> response) {
Log.d("sendmeal", "mientras se ejecuta"+response.isSuccessful());
Log.d("sendmeal", "codigo: "+response.code());
if (response.isSuccessful()){
listaPlatos.remove(response.body());
    Message m=new Message();
    m.arg1=PLATO_BORRAR;
    h.sendMessage(m);
}
    }

    @Override
    public void onFailure(Call<Plato> call, Throwable t) {
Log.d("sendmeal", "error: "+t.getMessage());
Message m=new
        Message();
m.arg1=PLATO_ERROR;
h.sendMessage(m);
    }
});
}// cierra eliminarPlato

    //get de la lista
public List<Plato> getListaPlatos() {
    return  listaPlatos;
}


}


