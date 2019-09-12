package com.lmr.sendmeal.domain;

import android.provider.ContactsContract;
import java.lang.Object;
import java.util.ArrayList;

public class Usuario {

    private Integer id;
    private String nombre;
    private String contraseña;
    private String correoElectronico;
    private TarjetaDeCredito tarjeta;
    private Double credito;
    private CuentaBancaria cuenta;

    public Usuario(Integer id,String nombre,String contraseña,String correoElectronico,TarjetaDeCredito tarjeta,Double credito) {
this.id=id;
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

