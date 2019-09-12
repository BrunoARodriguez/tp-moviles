package com.lmr.sendmeal.domain;

import java.util.Date;
import java.util.Objects;

public class TarjetaDeCredito {
private Integer id;
    private String  numeroDeTarjeta;
private String fechaVencimiento;
private  Integer digitoVerificacion;

    public TarjetaDeCredito(String  fechaVencimiento,Integer digitoVerificacion,Integer id,String numeroDeTarjeta) {
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificacion=digitoVerificacion;
        this.id    =id;
        this.numeroDeTarjeta=numeroDeTarjeta;
    }

    public String ngetNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public void setNumeroDeTarjeta(String numeroDeTarjeta) {

        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public String getFechaVencimiento() {

        return fechaVencimiento;
    }

    public void setFechaVencimiento(String  fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getDigitoVerificacion() {

        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Integer digitoVerificacion) {

        this.digitoVerificacion = digitoVerificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaDeCredito that = (TarjetaDeCredito) o;
        return Objects.equals(numeroDeTarjeta, that.numeroDeTarjeta) &&
                Objects.equals(fechaVencimiento, that.fechaVencimiento) &&
                this.digitoVerificacion.equals(that.digitoVerificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDeTarjeta, fechaVencimiento, digitoVerificacion);
    }

    @Override
    public String toString() {
        return "TarjetaDeCredito{" +
                "numeroDeTarjeta=" + numeroDeTarjeta +
                '}';

    }
}