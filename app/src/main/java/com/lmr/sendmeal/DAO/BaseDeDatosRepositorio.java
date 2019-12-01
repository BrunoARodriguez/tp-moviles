package com.lmr.sendmeal.DAO;

import android.content.Context;

import androidx.room.Room;

import com.lmr.sendmeal.R;
import com.lmr.sendmeal.domain.ItemsPedido;
import com.lmr.sendmeal.domain.Pedido;

import java.util.List;

public class BaseDeDatosRepositorio {
private  static  BaseDeDatosRepositorio REPO = null;
private  MiBaseDeDatos miBaseDeDatos;
private PedidoDao pedidoDao;
private  ItemsPedidoDao itemsPedidoDao;
private  UsuarioDao usuarioDao;

private BaseDeDatosRepositorio(Context ctx){
    miBaseDeDatos= Room.databaseBuilder(ctx,MiBaseDeDatos.class, "BD sendmeal 2019") //R.string.nombre_base_datos)
            .fallbackToDestructiveMigration().build();
pedidoDao=miBaseDeDatos.pedidoDao();
itemsPedidoDao=miBaseDeDatos.itemsPedidoDao();
usuarioDao=miBaseDeDatos.usuarioDao();
}

public  static BaseDeDatosRepositorio getInstance(Context ctx){
if (REPO == null){
    REPO = new BaseDeDatosRepositorio(ctx);
}
return REPO;
}

    public MiBaseDeDatos getMiBaseDeDatos() {
        return miBaseDeDatos;
    }


}


