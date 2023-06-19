package Clases;

import java.io.Serializable;

import Enums.E_Envio;
import Genericas.GeneDosPU;

public class Carrito implements Serializable
{
    // ATRIBUTOS
    private GeneDosPU<String,Publicacion> publicaciones; // publicaciones disponibles en el carrito
    private MetodoDePago elegido; // método de pago que eligio por el usuario
    private E_Envio envio; // envío que elige el usuario

    // CONSTRUCTORES
    public Carrito(GeneDosPU publicaciones, MetodoDePago elegido, E_Envio envio) {
        this.publicaciones = new GeneDosPU<>();
        this.elegido = elegido;
        this.envio = envio;
    }
    public Carrito() {
        this.publicaciones = new GeneDosPU<>();
        this.elegido = null;
        this.envio = null;
    }

    // GETTERS, SETTERS Y OTROS
    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public MetodoDePago getElegido() {
        return elegido;
    }

    public E_Envio getEnvio() {
        return envio;
    }

    public void agregarPublicacion(Publicacion publicacion)
    {
        publicaciones.agregar(publicacion.getId(),publicacion);
    }

    public void borrarPublicacion(Publicacion publicacion)
    {
        publicaciones.borrar(publicacion.getId());
    }

    // No hace falta el equals ni el compareTo, un carrito nunca se va comparar con otro carrito.
    @Override
    public int hashCode() {
        return 8;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "publicaciones=" + publicaciones +
                ", elegido=" + elegido +
                ", envio=" + envio +
                '}';
    }
}
