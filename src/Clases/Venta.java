package Clases;

import Genericas.GeneDosPU;

public class Venta
{
    private GeneDosPU<String,Publicacion> publicaciones; //coleccion de publicaciones que se vendieron
    private float totalRecaudado; //total recaudado con la lista de publicaciones

    public Venta(float totalRecaudado) {
        this.publicaciones = new GeneDosPU<>();
        this.totalRecaudado = totalRecaudado;
    }
    public Venta() {
        this.publicaciones = new GeneDosPU<>();
        this.totalRecaudado = 0;
    }

    //getters y setters
    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public float getTotalRecaudado() {
        return totalRecaudado;
    }


    //aca tambien consideramos que no es necesario hacer un equals ni un compareTo
    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "publicaciones=" + publicaciones +
                ", totalRecaudado=" + totalRecaudado +
                '}';
    }
}
