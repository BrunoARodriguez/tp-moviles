    package com.lmr.sendmeal.domain;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
@Entity(tableName = "Usuario")
public class Usuario {
@PrimaryKey(autoGenerate = true)
@NonNull
@ColumnInfo(name = "id_usuario")
private Integer id;
@ColumnInfo(name = "nombre_usuario")
private String nombre;
@ColumnInfo(name = "contraseña_usuario")
private String contraseña;
@ColumnInfo(name = "correo_usuario")
private String correoElectronico;
@Embedded(prefix = "us_")
private TarjetaDeCredito tarjeta;
    @ColumnInfo(name = "credito_usuario")
    private Double credito;
    @Embedded
    private CuentaBancaria cuenta;

    public Usuario(String nombre,String contraseña,String correoElectronico,TarjetaDeCredito tarjeta,Double credito) {

        this.nombre = nombre;
        this.contraseña=contraseña;
        this.correoElectronico=correoElectronico;
this.tarjeta=tarjeta;
this.credito=credito;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TarjetaDeCredito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDeCredito tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return this.id == usuario.id;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

