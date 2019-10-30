package com.lmr.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "Item_pedido", foreignKeys = @ForeignKey(entity = Pedido.class,parentColumns = "id_item",childColumns = "id_pedido"),@ForeignKey(entity =  Plato.class,parentColumns = "id",childColumns = "id_plato"))
public class ItemsPedido {
@PrimaryKey(autoGenerate = true)
@NonNull
@ColumnInfo(name = "id_item")
    private  Integer id;
@ColumnInfo(name = "id_pedido")
private Pedido pedido;
@ColumnInfo(name = "id_plato")
    private  Plato plato;
    @ColumnInfo(name = "cantidad_item")
private  Integer cantidad;
@ColumnInfo(name = "precio_item")
    private  Double precio;

    public ItemsPedido(Pedido pedido,Plato plato,Integer cantidad,Double precio) {
        this.id = Integer.valueOf(UUID.randomUUID().toString());
        this.pedido=pedido;
        this.plato=plato;
        this.cantidad=cantidad;
        this.precio=precio;
    }

    //setters y getters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsPedido that = (ItemsPedido) o;
        return id.equals(that.id) &&
                pedido.equals(that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedido);
    }
}
