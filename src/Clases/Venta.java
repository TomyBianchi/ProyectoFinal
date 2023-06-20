package Clases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Genericas.GeneDosPU;

/**
 * Clase que tiene una lista de publicaciones un usuario vendio. Esta clase esta en Usuario. Implementa una lista
 * generica para poder guardar todas las publicaciones que un usuario pudo vender.
 */
public class Venta implements Serializable
{
    // ATRIBUTOS
    private GeneDosPU<String,Publicacion> publicaciones; //coleccion de publicaciones que se vendieron
    private float totalRecaudado; //total recaudado con la lista de publicaciones

    // CONSTRUCTORES
    public Venta(float totalRecaudado) {
        this.publicaciones = new GeneDosPU<>();
        this.totalRecaudado = totalRecaudado;
    }
    public Venta() {
        this.publicaciones = new GeneDosPU<>();
        this.totalRecaudado = 0;
    }

    // MÉTODOS

    /**
     * Se agrega una publicacion a la lista de publicaciones vendidas.
     * @param publicacion
     */
    public void agregarPublicacion(Publicacion publicacion)
    {
        publicaciones.agregar(publicacion.getId(),publicacion);
    }

    // GETTERS, SETTERS Y OTROS
    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }

    /**
     * MÉtodo que recorre todas las publicaciones que se vendieron y calcula el total recaudado.
     * @return Retorna el total recaudado.
     */
    public float getTotalRecaudado()
    {
        float total = 0;
        HashMap<String,Publicacion> mapa = getPublicaciones().getMapa(); //obtengo el mapa para poder recorrerlo y mostrar todas las publicaciones
        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion aux = entry.getValue();
            total += aux.getPrecio();
        }
        return total;
    }

    // Consideramos que no es necesario hacer un equals ni un compareTo
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
