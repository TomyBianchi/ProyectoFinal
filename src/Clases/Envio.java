package Clases;

import Enums.E_Envio;
import Interfaces.I_toJSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Es una clase la cual se usa en Publicacion, implementada con una lista generica. Esta clase tiene todas las variables necesarias
 * para hacer un envio. El cual se le va a agregar a una publicacion obligatoriamente.
 */
public class Envio implements Serializable, I_toJSONObject
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

    public JSONObject toJSONObject()throws JSONException
    {
        JSONObject obj = new JSONObject();
        obj.put("tipoEnvio", getTipoEnvio());
        obj.put("precioEnvio", getPrecio());
        obj.put("expressEnvio", isExpress());

        return obj;
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
