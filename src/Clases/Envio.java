package Clases;

import Enums.E_Envio;

import java.io.Serializable;

public class Envio implements Serializable
{
    // ATRIBUTOS
    private boolean express;
    private float precio;
    private E_Envio tipoEnvio;
    private int distancia;

    // CONSTRUCTORES
    public Envio(boolean express, float precio, E_Envio tipoEnvio, int distancia) {
        this.express = express;
        this.precio = precio;
        this.tipoEnvio = tipoEnvio;
        this.distancia = distancia;
    }
    public Envio() {
        express = false;
        precio = 0;
        tipoEnvio = E_Envio.Tierra; // inicia en Tierra por default
        distancia = 0;
    }

    // GETTERS, SETTERS Y OTROS
    public boolean isExpress() {
        return express;
    }

    public float getPrecio() {
        return precio;
    }

    public E_Envio getTipoEnvio() {
        return tipoEnvio;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof Envio)
            {
                Envio aux = (Envio) o;
                if(getDistancia() == aux.getDistancia() && getTipoEnvio().equals(aux.getTipoEnvio()) && getPrecio() == aux.getPrecio())
                {
                    rta = true;
                }
            }
        }
        return rta;
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

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof Envio)
            {
                Envio aux = (Envio)o;
                Float auxUno = new Float(getPrecio());  //estos dos errores no impide que compile el programa
                Float auxDos = new Float(aux.getPrecio());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
