package Clases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        float total = 0;
        HashMap<String,Publicacion> mapa = getPublicaciones().getMapa(); //obtengo el mapa para poder recorrerlo y mostrar todas las publicaciones
        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion aux = entry.getValue();
            total += aux.getPrecio();
        }
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
