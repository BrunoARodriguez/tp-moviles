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

private BaseDeDatosRepositorio(Context ctx){
    miBaseDeDatos= Room.databaseBuilder(ctx,MiBaseDeDatos.class, "BD sendmeal 2019") //R.string.nombre_base_datos)
            .fallbackToDestructiveMigration().build();
pedidoDao=miBaseDeDatos.pedidoDao();
itemsPedidoDao=miBaseDeDatos.itemsPedidoDao();
}

public  static BaseDeDatosRepositorio getInstance(Context ctx){
if (REPO == null){
    REPO = new BaseDeDatosRepositorio(ctx);
}
return REPO;
}

/*
implementando los metodos para
@pedidoDao que es la interfaz de pedido
@ItemsPedidoDao que es la interfaz de los itemsPedidos
 */

//para crear
public  void crearPedido(Pedido pedido){
    pedidoDao.insertarPedido(pedido);
}

public void  crearItemsPedido(ItemsPedido itemsPedido){
    itemsPedidoDao.insertarItemPedido(itemsPedido);
}

//para actualizar
public void actualizarPedido(Pedido pedido){
pedidoDao.actualizarPedido(pedido);
}

public  void  actualizarItemsPedido(ItemsPedido itemsPedido){
    itemsPedidoDao.actualizarItemPedido(itemsPedido);
}

//para las búsquedas
public List<Pedido> getPedidos(){
    return  pedidoDao.buscarPedidos();
}

public  List<ItemsPedido> getItemsPedidos(){
return  itemsPedidoDao.buscarItemPedido();
}

public  Pedido buscarPedido(Integer id){
return  pedidoDao.busccarPorId(id);
}

//para los borrados
public  void  eliminarPedido(Pedido pedido){
     pedidoDao.borrarPedido(pedido);
}

public  void eliminarItemsPedido(ItemsPedido itemsPedido){
 itemsPedidoDao.borrarItemPedido(itemsPedido);
}
}
