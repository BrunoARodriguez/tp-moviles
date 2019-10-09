package com.lmr.sendmeal;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Plato implements Parcelable {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Integer calorias;
    private  Boolean estaEnOferta;
    public static List<Plato> platoLis=new ArrayList<>();


    public Plato(Integer id, String titulo, String descripcion, Double precio, Integer calorias) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;

        this.precio = precio;
        this.calorias = calorias;
        this.estaEnOferta=false;
        platoLis.add(this);
    }

    //constructor para el parcelable
private   Plato(Parcel parcel){
this.readFromParcel(parcel);
    }
    //generando el creator
    public  static  final  Parcelable.Creator<Plato> CREATOR=new Parcelable.Creator<Plato>() {
public  Plato createFromParcel(Parcel parcel){
return  new Plato(parcel);
}

    };
//getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Boolean getEstaEnOferta() {
        return estaEnOferta;
    }

    public void setEstaEnOferta(Boolean estaEnOferta) {
        this.estaEnOferta = estaEnOferta;
    }

@Override
public  int describeContenst(){
        return  0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(titulo);
    parcel.writeString(descripcion);
    parcel.writeDouble(precio);
    parcel.writeInt(calorias);
    parcel.writeBoolean(estaEnOferta);
    }
    @Override
    private  void  readFromParcel(Parcel parcel){
        titulo = parcel.readString();
        descripcion = parcel.readString();
        precio = parcel.readDouble();
        calorias = parcel.readInt();
        estaEnOferta = parcel.readBoolean();

    }
}


