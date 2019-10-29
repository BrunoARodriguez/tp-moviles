package com.lmr.sendmeal.domain;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lmr.sendmeal.DAO.PlatoRepositorio;

public class Plato implements Parcelable {
    @SerializedName("plato_id")
    @Expose
    private Integer id;
    @SerializedName("titulo_plato")

    @Expose
    private String titulo;
    @SerializedName("descripcion_plato")
    @Expose
    private String descripcion;
    @SerializedName("precio_plato")
    @Expose
    private Double precio;
    @SerializedName("calorias_plato")
    @Expose
    private Integer calorias;
    @SerializedName("plato_en_oferta")
    @Expose
    private  Boolean estaEnOferta;


    public Plato(Integer id, String titulo, String descripcion, Double precio, Integer calorias) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;

        this.precio = precio;
        this.calorias = calorias;
        this.estaEnOferta = false;
        PlatoRepositorio.getInstance().getListaPlatos().add(this);
    }

    //constructor para el parcelable
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private   Plato(Parcel parcel){
        this.readFromParcel(parcel);
    }
    //generando el creator
    public  static  final  Creator<Plato> CREATOR=new Creator<Plato>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Plato createFromParcel(Parcel parcel){
            return  new Plato(parcel);
        }

        @Override
        public Plato[] newArray(int i) {
            return new Plato[0];
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


    public  int describeContenst(){
        return  0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(descripcion);
        parcel.writeDouble(precio);
        parcel.writeInt(calorias);
        parcel.writeBoolean(estaEnOferta);
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)

    private  void  readFromParcel(Parcel parcel){
        titulo = parcel.readString();
        descripcion = parcel.readString();
        precio = parcel.readDouble();
        calorias = parcel.readInt();
        estaEnOferta = parcel.readBoolean();

    }
}



