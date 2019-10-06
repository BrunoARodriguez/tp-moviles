package com.lmr.sendmeal;


import java.util.ArrayList;
import java.util.List;

public class Plato {
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
}