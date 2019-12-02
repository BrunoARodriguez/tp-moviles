package com.lmr.sendmeal.DAO.rest;

import com.lmr.sendmeal.domain.Pedido;
import com.lmr.sendmeal.domain.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PedidoRes {
    @GET("pedidos/")
    Call<List<Pedido>> buscarTodosPedidos();


    @DELETE("pedidos/{id}")
    Call<Pedido> borrar(@Path("id") Integer id);

    @PUT("pedidos/{id}")
    Call<Pedido> actualizar(@Path("id") Integer id, @Body Pedido pedido);

    @POST("pedidos/")
    Call<Pedido> crear(@Body Pedido pedido);


}
