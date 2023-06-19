package Clases;

import Enums.E_Envio;

import java.io.Serializable;

public class Envio implements Serializable
{
    // ATRIBUTOS
    private boolean express;
    private float precio;
    private E_Envio tipoEnvio;

    // CONSTRUCTORES
    public Envio(boolean express, float precio, E_Envio tipoEnvio) {
        this.express = express;
        this.precio = precio;
        this.tipoEnvio = tipoEnvio;
    }
    public Envio() {
        express = false;
        precio = 0;
        tipoEnvio = E_Envio.Tierra; // inicia en Tierra por default
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



    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof Envio)
            {
                Envio aux = (Envio) o;
                if(getTipoEnvio().equals(aux.getTipoEnvio()) && getPrecio() == aux.getPrecio())
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
                '}';
    }


}
