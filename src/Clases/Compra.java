package Clases;

import java.io.Serializable;

import Genericas.GeneDosPU;

public class Compra implements Serializable
{
    private GeneDosPU<String,Publicacion> publicaciones; //coleccion de publicaciones que se compraron
    private float totalGastado; //total gastado en esas compras

    public Compra(float totalGastado) {
        this.publicaciones = new GeneDosPU<>();
        this.totalGastado = totalGastado;
    }
    public Compra() {
        this.publicaciones = new GeneDosPU<>();
        this.totalGastado = 0;
    }

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
