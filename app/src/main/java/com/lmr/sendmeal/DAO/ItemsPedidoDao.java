package com.lmr.sendmeal.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.lmr.sendmeal.domain.ItemsPedido;

import java.util.List;

@Dao
public interface ItemsPedidoDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
    public  void  insertarItemPedido(ItemsPedido itemsPedido);

@Update(onConflict = OnConflictStrategy.ABORT)
    public  void actualizarItemPedido(ItemsPedido itemsPedido);

@Delete
    public  void  borrarItemPedido(ItemsPedido itemsPedido);

@Query("selec * from Item_pedido")
    public List<ItemsPedido> buscarItemPedido();


}
