package com.lmr.sendmeal.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.lmr.sendmeal.domain.Pedido;

import java.util.List;

@Dao
public interface PedidoDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
public  void  insertarPedido(Pedido pedido);

@Update
    public  void  actualizarPedido(Pedido pedido);

@Delete
    public  void borrarPedido(Pedido pedido);

@Query("selec * from Pedidos")
   public List<Pedido> buscarPedidos();


}
