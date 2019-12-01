package com.lmr.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity(tableName = "Pedidos")

public class Pedido {
@PrimaryKey(autoGenerate = true)
@NonNull
@ColumnInfo(name = "id_pedido")
    private  Integer id;
@ColumnInfo(name = "fecha_pedido")
private Calendar fecha;
@ColumnInfo(name = "estado_pedido")
/*
El estado de un pedido puede tomar los siguientes valores
1. PENDIENTE: el pedido fue creado, pero no fue enviado al servidor por lo que aún está
almacenado localmente
2. ENVIADO: el pedido fue enviado para que un sea aceptado.
3. ACEPTADO: el pedido fue aceptado por un administrador del sistema y será derivado a
un repartidor
4. RECHAZADO: el pedido fue rechazado por un administrador del sistema y no ser
realizará
5. EN_PREPARACION: el repartidor aceptó su pedido comenzó a trabajar en el.
6. EN_ENVIO: el repartidor tiene todos los ítems pedidos y está en envio.
7. ENTREGADO: el pedido ha sido entregado.
8. CANCELADO: Cuando el pedido está en los estados 1 o 2 se puede cancelar.
*/

private  Integer estado;
@ColumnInfo(name = "latitud_pedido")
private  Double latitud;
@ColumnInfo(name = "longitud_pedido")
private  Double longitud;
        @Ignore
    List<ItemsPedido> items;
@ColumnInfo(name = "token_pedido")
private  String token;

public  Pedido(Calendar fecha,Integer estado,Double latitud,Double longitud){
    //this.id=Integer.valueOf(UUID.randomUUID().toString());
    this.fecha=fecha;
    this.estado=estado;
    this.latitud=latitud;
    this.longitud=longitud;
this.items = new ArrayList<>();
this.token = null;
}

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Pedido(){
}
    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

