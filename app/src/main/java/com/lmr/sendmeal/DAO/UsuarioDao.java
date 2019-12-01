package com.lmr.sendmeal.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.lmr.sendmeal.domain.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
@Insert(onConflict = OnConflictStrategy.ABORT)
    public  void  insertarUsuario(Usuario usuario);
@Delete
    public  void  eliminarUsuario(Usuario usuario);

@Update
    public  void  actualizarUsuario(Usuario usuario);
/*
@Query("SELECT * FROM Usuario")
    public List<Usuario> buscarUsuarios();
*/
}
