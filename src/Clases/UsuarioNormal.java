package Clases;

import java.io.Serializable;

import Enums.E_TipoUsuario;
import Genericas.GeneDosPU;

/**
 * Es la clase hija de Usuario.Tiene las caracteristicas de un usuarioNormal, es decir, que puede comprar como vender.
 * Es el tipo de usuario que mas se suele usar. Se usa en la clase envoltorio implementada con una clase generica.
 */
public class UsuarioNormal extends Usuario implements Serializable
{
    // ATRIBUTOS
    private GeneDosPU<String, Publicacion> favoritas;
    private Carrito carrito; 
    private Compra compras; // clase la cual tiene una lista de publicaciones las cuales ya fueron compradas por este usuario

    // CONSTRUCTORES
    public UsuarioNormal(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, Venta ventas, float promedioVentas) {
        super(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, ventas, promedioVentas);
        this.favoritas = new GeneDosPU<>();
        this.carrito = new Carrito();
        this.compras = new Compra();
    }
    public UsuarioNormal(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni) {
        super(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni);
        this.favoritas = new GeneDosPU<>();
        this.carrito = new Carrito();
        this.compras = new Compra();
    }
    public UsuarioNormal() {
        super();
        this.favoritas = null;
        this.carrito = null;
        this.compras = null;
    }

    // MÉTODOS



    /**
     * Método que agrega una publicación al carrito.
     * @param publicacion
     */
    public void agregarCarrito(Publicacion publicacion)
    {
        carrito.agregarPublicacion(publicacion);
    }
    /**
     * Método que agrega una compra a la variable compras
     * @param publicacion
     */
    public void agregarcompra(Publicacion publicacion)
    {
        compras.agregarCompra(publicacion);
    }
    /**
     * Método que agrega a favoritos una publicacion
     * @param publicacion
     */
    public void agregarFavorito(Publicacion publicacion)
    {
        favoritas.agregar(publicacion.getId(),publicacion);
    }

    // GETTERS, SETTERS Y OTROS
    public GeneDosPU<String, Publicacion> getFavoritas() {
        return favoritas;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Compra getCompras() {
        return compras;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof UsuarioNormal)
            {
                UsuarioNormal aux = (UsuarioNormal) o;
                if(getDni().equals(aux.getDni()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public String toString() {
        return super.toString() + "     UsuarioNormal{" +
                "favoritas=" + favoritas +
                ", carrito=" + carrito +
                ", compras=" + compras + "\n\n\n" +
                '}';
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof UsuarioNormal)
            {
                UsuarioNormal aux = (UsuarioNormal)o;
                String auxUno = new String(getDni());  //estos dos errores no impide que compile el programa
                String auxDos = new String(aux.getDni());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
