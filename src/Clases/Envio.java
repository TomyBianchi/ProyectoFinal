package Clases;

import Enums.E_Envio;

import java.util.Objects;

public class Envio {
    private boolean express;
    private float precio;
    private E_Envio tipoEnvio;
    private int distancia;

    public Envio(boolean express, float precio, E_Envio tipoEnvio, int distancia) {
        this.express = express;
        this.precio = precio;
        this.tipoEnvio = tipoEnvio;
        this.distancia = distancia;
    }
    public Envio() {
        express = false;
        precio = 0;
        tipoEnvio = tipoEnvio; // iniciar con un tipo de enum
        distancia = 0;
    }

    public boolean isExpress() {
        return express;
    }

    public void setExpress(boolean express) {
        this.express = express;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public E_Envio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(E_Envio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envio envio = (Envio) o;
        return express == envio.express && Float.compare(envio.precio, precio) == 0 && distancia == envio.distancia && tipoEnvio == envio.tipoEnvio;
    }

    @Override
    public int hashCode() {
        return 9;
    }

    @Override
    public String toString() {
        return "Envio{" +
                "express=" + express +
                ", precio=" + precio +
                ", tipoEnvio=" + tipoEnvio +
                ", distancia=" + distancia +
                '}';
    }
}
