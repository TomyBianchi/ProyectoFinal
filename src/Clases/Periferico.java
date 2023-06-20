package Clases;

import Enums.E_Estado;
import Interfaces.I_toJSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Es una clase la cual usa Publicacion, esta tiene las caracteristicas mas especificas de cada publicacion.
 *
 */
public class Periferico implements Serializable, I_toJSONObject
{
    // ATRIBUTOS
    private String nombre;
    private String id; // autogenerable
    private E_Estado estado;
    private String marca;
    private String modelo;
    private String origen;
    private String plataformas;
    private String color;
    private float peso;
    private boolean inalambrico; // true: inalámbrico, false: con cable

    // CONSTRUCTORES
    public Periferico(String nombre, String id, E_Estado estado, String marca, String modelo, String origen, String plataformas, String color, float peso, boolean inalambrico) {
        this.nombre = nombre;
        this.id = id;
        this.estado = estado;
        this.marca = marca;
        this.modelo = modelo;
        this.origen = origen;
        this.plataformas = plataformas;
        this.color = color;
        this.peso = peso;
        this.inalambrico = inalambrico;
    }
    public Periferico() {
        nombre = "";
        id = "";
        estado = null;
        marca = "";
        modelo = "";
        origen = "";
        plataformas = "";
        color = "";
        peso = 0;
        inalambrico = true;
    }

    // GETTERS, SETTERS Y OTROS
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public E_Estado isEstado() {
        return estado;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public String getColor() {
        return color;
    }

    public float getPeso() {
        return peso;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof Periferico)
            {
                Periferico aux = (Periferico) o;
                if(getId().equals(aux.getId()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public JSONObject toJSONObject()throws JSONException
    {
        JSONObject obj = new JSONObject();
        obj.put("nombre", getNombre());
        obj.put("id", getId());
        obj.put("estado", isEstado());
        obj.put("marca", getMarca());
        obj.put("origen", getOrigen());
        obj.put("plataformas", getPlataformas());
        obj.put("color", getColor());
        obj.put("peso", getPeso());
        obj.put("inalambrico", isInalambrico());

        return obj;
    }

    @Override
    public String toString() {
        return "Periferico{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", estado=" + estado +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", origen='" + origen + '\'' +
                ", plataformas='" + plataformas + '\'' +
                ", color='" + color + '\'' +
                ", peso=" + peso +
                ", inalambrico=" + inalambrico +
                '}';
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof Periferico)
            {
                Periferico aux = (Periferico)o;
                String auxUno = new String(getId());  //estos dos errores no impide que compile el programa
                String auxDos = new String(aux.getId());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
