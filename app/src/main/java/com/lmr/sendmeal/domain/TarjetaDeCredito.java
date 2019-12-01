package com.lmr.sendmeal.domain;

import java.util.Calendar;

import java.util.Objects;

public class TarjetaDeCredito {
    private Integer idTarjeta;
    private String numeroDeTarjeta;
    private Calendar fechaVencimiento;
    private Integer digitoVerificacion;

    public TarjetaDeCredito(Calendar fechaVencimiento, Integer digitoVerificacion, Integer idTarjeta, String numeroDeTarjeta) {
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificacion = digitoVerificacion;
        this.idTarjeta = idTarjeta;
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public void setNumeroDeTarjeta(String numeroDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public Calendar getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Calendar fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Integer digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
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