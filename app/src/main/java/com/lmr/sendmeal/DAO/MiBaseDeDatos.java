package com.lmr.sendmeal.DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lmr.sendmeal.domain.ItemsPedido;
import com.lmr.sendmeal.domain.Pedido;
import com.lmr.sendmeal.domain.Usuario;

import java.security.PublicKey;

@Database(entities = {Pedido.class,ItemsPedido.class,Usuario.class},version = 5)
@TypeConverters(ConvertirFecha.class)
public abstract class MiBaseDeDatos extends RoomDatabase {
public abstract PedidoDao pedidoDao();
public  abstract ItemsPedidoDao itemsPedidoDao();
public  abstract UsuarioDao usuarioDao();
}
