package Clases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Genericas.GeneDosPU;

/**
 * Clase la cual implementa UsuarioNormal. Tiene una lista generica de publicaciones, la cuales son todas
 * las publicaciones que se compraron por un usuario, y el totalGastado que se calcula con el metodo
 * getTotalGastado()
 */
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

    /**
     * Se agrega una publicacion a la lista de compras del usuarioNormal
     * @param pub Publicacion a agregar
     */
    public void agregarCompra(Publicacion pub)
    {
        publicaciones.agregar(pub.getId(),pub);
    }

    /**
     * Recorre todas las publicaciones que fueron compradas, y en base a eso calcula el total gastado.
     * @return returna el total gastado en compras del usuarioNormal
     */
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
        return total;
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
