package com.lmr.sendmeal.DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lmr.sendmeal.domain.ItemsPedido;
import com.lmr.sendmeal.domain.Pedido;

import java.security.PublicKey;

@Database(entities = {Pedido.class,ItemsPedido.class},version = 3)
@TypeConverters(ConvertirFecha.class)
public abstract class MiBaseDeDatos extends RoomDatabase {
public abstract PedidoDao pedidoDao();
public  abstract ItemsPedidoDao itemsPedidoDao();
}
