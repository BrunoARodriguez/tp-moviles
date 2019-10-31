package com.lmr.sendmeal.DAO;

import android.content.Context;

import androidx.room.Room;

import com.lmr.sendmeal.R;

public class BaseDeDatosRepositorio {
private  static  BaseDeDatosRepositorio REPO = null;
private PedidoDao pedidoDao;
private  ItemsPedidoDao itemsPedidoDao;

public BaseDeDatosRepositorio(Context ctx){
    MiBaseDeDatos bd= Room.databaseBuilder(ctx,MiBaseDeDatos.class, "BD sendmeal 2019") //R.string.nombre_base_datos)
            .fallbackToDestructiveMigration().build();
pedidoDao=bd.pedidoDao();
itemsPedidoDao=bd.itemsPedidoDao();
}

public  static BaseDeDatosRepositorio getInstance(Context ctx){
if (REPO == null){
    REPO = new BaseDeDatosRepositorio(ctx);
}
return REPO;
}

}
