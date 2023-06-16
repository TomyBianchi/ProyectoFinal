package Clases;

import Enums.E_Estado;

import java.util.Objects;

public class Periferico {
    private String nombre;
    private String id; // autogenerable
    // foto
    private E_Estado estado;
    private String marca;
    private String modelo;
    private String origen;
    private String tags;
    private String plataformas;
    private String color;
    private float peso;
    private boolean inalambrico; // true: inalámbrico, false: con cable

    public Periferico(String nombre, String id, E_Estado estado, String marca, String modelo, String origen, String tags, String plataformas, String color, float peso, boolean inalambrico) {
        this.nombre = nombre;
        this.id = id;
        this.estado = estado;
        this.marca = marca;
        this.modelo = modelo;
        this.origen = origen;
        this.tags = tags;
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
        tags = "";
        plataformas = "";
        color = "";
        peso = 0;
        inalambrico = true;
    }

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

    public String getTags() {
        return tags;
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

    @Override
    public String toString() {
        return "Periferico{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", estado=" + estado +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", origen='" + origen + '\'' +
                ", tags='" + tags + '\'' +
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
