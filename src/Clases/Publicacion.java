package Clases;

import java.io.Serializable;

import Genericas.GeneTresE;

public class Publicacion implements Serializable{
    private String nombrePublicacion;
    private Periferico periferico;
    private float precio;
    private int stock;
    private String id;
    private String reviews;
    private int rating;
    private String urlFoto;
    private Usuario dueno; //dueno de la publicacion
    private GeneTresE<Envio> envios; //lista de envios disponibles de la publicacion.

    public Publicacion(String nombrePublicacion, Periferico periferico, float precio, int stock, String id, String reviews, int rating, Usuario dueno) {
        this.nombrePublicacion = nombrePublicacion;
        this.periferico = periferico;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
        this.reviews = reviews;
        this.rating = rating;
        this.dueno = dueno;
        this.envios = new GeneTresE<>();
    }

    //constructor sin reviews, rating ni id (porque se autogenera) para agregar publicacion
    public Publicacion(String nombrePublicacion,Periferico periferico, float precio, int stock, Usuario dueno,String urlFoto,String id) {

        this.nombrePublicacion = nombrePublicacion;
        this.periferico = periferico;
        this.precio = precio;
        this.stock = 0;
        this.id = id;
        this.reviews = "";
        this.rating = 0;
        this.dueno = dueno;
        this.envios = new GeneTresE<>();
        this.urlFoto = urlFoto;
        this.stock = stock;
    }
    public Publicacion() {
        this.periferico = null;
        this.precio = 0;
        this.stock = 0;
        this.id = "";
        this.reviews = "";
        this.rating = 0;
        this.dueno = null;
        this.envios = new GeneTresE<>();
    }

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

    public String getReviews() {
        return reviews;
    }

    public int getRating() {
        return rating;
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


    @Override
    public String toString() {
        return "Publicacion{" +
                "periferico=" + periferico +
                ", precio=" + precio +
                ", stock=" + stock +
                ", id='" + id + '\'' +
                ", reviews='" + reviews + '\'' +
                ", rating=" + rating +
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

    //metodos

    /**
     * Agrega un envio a la lista de envios, se pueden agregar todos los que el vendedor quiera.
     */
    public void agregarEnvio(Envio envio)
    {
        envios.agregar(envio);
    }


}
