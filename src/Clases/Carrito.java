package Clases;

import Enums.E_Envio;
import Genericas.GeneDosPU;

public class Carrito
{
    //atributos
    private GeneDosPU<String,Publicacion> publicaciones; //publicaciones disponibles en el carrito
    private MetodoDePago elegido; //metodo de pago que eligio el usuario
    private E_Envio envio; // envio que tiene que elegir el usuario

    //constructor
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

    //getters y setters
    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public MetodoDePago getElegido() {
        return elegido;
    }

    public E_Envio getEnvio() {
        return envio;
    }


    //no hace falta el equals ni el compareTo, un carrito nunca se va comparar con otro carrito.
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
