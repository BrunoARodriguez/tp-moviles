package com.lmr.sendmeal.DAO.rest;


import com.lmr.sendmeal.domain.CuentaBancaria;
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

public interface    PlatoRest {

    @GET("platos/")
    Call<List<Plato>> buscarTodosPlatos();

@GET("platos/")
Call<List<Plato>>buscarPorPrecio(@Query("precio_plato") Integer pminimo, @Query("precio_plato") Integer pMayor);

    @DELETE("/platos/{id}")
Call<Void> borrar(@Path("id") Integer id);

    @PUT("/platos/{id}")
    Call<Plato> ctualizar(@Path("id") Integer id, @Body Plato plato);

@POST("platos/")
    Call<Plato> crear(@Body Plato plato);
}
