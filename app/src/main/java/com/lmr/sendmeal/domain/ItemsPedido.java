package com.lmr.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.util.TableInfo;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "Item_pedido", foreignKeys = @ForeignKey(entity = Pedido.class, parentColumns = "id_pedido", childColumns = "id_pedido_h", onDelete = ForeignKey.CASCADE))
public class ItemsPedido {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idItem")
    private Integer id;
    @ColumnInfo(name = "id_pedido_h")
    private int idPedido;
    @Embedded
    private Plato plato;
    @ColumnInfo(name = "cantidad_item")
    private Integer cantidad;
    @ColumnInfo(name = "precio_item")
    private Double precio;

    public int getIdPedido() {
        return idPedido;
    }

    public ItemsPedido(int idPedido, Plato plato, Integer cantidad, Double precio) {
        //this.id = Integer.valueOf(UUID.randomUUID().toString());
        this.idPedido = idPedido;
        this.plato = plato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    //setters y getters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Plato getPlato() {
        return this.plato;
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


    /*
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
    */
}
