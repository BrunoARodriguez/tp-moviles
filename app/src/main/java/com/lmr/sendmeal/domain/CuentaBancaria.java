package com.lmr.sendmeal.domain;



public class CuentaBancaria {

    private Integer id;
    private String alias;
    private String cbu;

    public CuentaBancaria(Integer id, String alias, String cbu) {
        this.alias = alias;
        this.id = id;
        this.cbu = cbu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {

        this.cbu = cbu;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", cbu='" + cbu + '\'' +
                '}';
    }
}


