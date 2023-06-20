package Clases;

import java.io.Serializable;

import Genericas.GeneTresE;
import Interfaces.I_toJSONObject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Es una de las clases principales. Tiene como atributo todo lo necesario para poder crear una publicacion. Se usa
 * en la clase envoltorio, implementada con una lista generica.
 */
public class Publicacion implements Serializable, I_toJSONObject
{
    // ATRIBUTOS
    private String nombrePublicacion;
    private Periferico periferico;
    private float precio;
    private int stock;
    private String id;
    private String urlFoto;
    private Usuario dueno; // dueño de la publicación
    private GeneTresE<Envio> envios; // lista de tipos de envío disponibles de la publicación

    // CONSTRUCTORES
    public Publicacion(String nombrePublicacion, Periferico periferico, float precio, int stock, String id,Usuario dueno, String urlFoto) {
        this.nombrePublicacion = nombrePublicacion;
        this.periferico = periferico;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
        this.dueno = dueno;
        this.envios = new GeneTresE<>();
        this.urlFoto = urlFoto;
    }
    // Constructor sin reviews ni rating para agregar publicacion
    public Publicacion(String nombrePublicacion,Periferico periferico, float precio, int stock, Usuario dueno, String urlFoto, String id) {
        this.nombrePublicacion = nombrePublicacion;
        this.periferico = periferico;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
        this.dueno = dueno;
        this.envios = new GeneTresE<>();
        this.urlFoto = urlFoto;
    }
    public Publicacion() {
        this.periferico = null;
        this.precio = 0;
        this.stock = 0;
        this.id = "";
        this.dueno = null;
        this.envios = new GeneTresE<>();
        this.urlFoto = "";
    }

    // MÉTODOS

    /**
     * Este método agrega un envío a la lista de envíos. Se pueden agregar todos los que el vendedor quiera.
     */
    public void agregarEnvio(Envio envio)
    {
        envios.agregar(envio);
    }

    // GETTERS, SETTERS Y OTROS
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public Periferico getPeriferico() {
        return periferico;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getId() {
        return id;
    }


    public Usuario getDueno() {
        return dueno;
    }

    public GeneTresE<Envio> getEnvios() {
        return envios;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof Publicacion)
            {
                Publicacion aux = (Publicacion) o;
                if(getId().equals(aux.getId()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    public int hashCode()
    {
        return 7;
    }

    public JSONObject toJSONObject()throws JSONException
    {
        JSONObject obj = new JSONObject();
        obj.put("nombrePublicacion", getNombrePublicacion());
        obj.put("periferico", getPeriferico().toJSONObject());
        obj.put("precio", getPrecio());
        obj.put("stock", getStock());
        obj.put("id", getId());
        obj.put("urlFoto", getUrlFoto());
        obj.put("nombreDueno", getDueno().getNombre());
        obj.put("envios", envios.toJSONArray());

        return obj;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "nombrePublicacion='" + nombrePublicacion + '\'' +
                ", periferico=" + periferico +
                ", precio=" + precio +
                ", stock=" + stock +
                ", id='" + id + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", dueno=" + dueno +
                ", envios=" + envios +
                '}';
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof Publicacion)
            {
                Publicacion aux = (Publicacion)o;
                String auxUno = new String(getId());  //estos dos errores no impide que compile el programa
                String auxDos = new String(aux.getId());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
