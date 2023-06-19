package Clases;

import java.io.Serializable;

import Genericas.GeneDosPU;

public class Compra implements Serializable
{
    // ATRIBUTOS
    private GeneDosPU<String,Publicacion> publicaciones; // lista de publicaciones que se compraron
    private float totalGastado; // total gastado en esas compras

    // CONSTRUCTORES
    public Compra(float totalGastado) {
        this.publicaciones = new GeneDosPU<>();
        this.totalGastado = totalGastado;
    }
    public Compra() {
        this.publicaciones = new GeneDosPU<>();
        this.totalGastado = 0;
    }

    // GETTERS, SETTERS Y OTROS
    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public float getTotalGastado() {
        return totalGastado;
    }

    @Override
    public int hashCode() {
        return 9;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "publicaciones=" + publicaciones +
                ", totalGastado=" + totalGastado +
                '}';
    }
}
