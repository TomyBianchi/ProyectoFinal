package Clases;

import Enums.E_TipoUsuario;
import Genericas.G_Publicacion;

public class UsuarioNormal extends Usuario
{
    //atributos
    private G_Publicacion<String, Publicacion> favoritas;
    private Carrito carrito; 
    private Compra compras; // clase la cual tiene una coleccion de publicaciones las cuales ya fueron compradas por este comprador

    public UsuarioNormal(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, boolean verificado, Venta ventas, float promedioVentas) {
        super(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, verificado, ventas, promedioVentas);
        this.favoritas = new G_Publicacion<>();
        this.carrito = new Carrito();
        this.compras = new Compra();
    }
    public UsuarioNormal() {
        super();
        this.favoritas = null;
        this.carrito = null;
        this.compras = null;
    }

    //getters y setters
    public G_Publicacion<String, Publicacion> getFavoritas() {
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
        return super.toString() + "UsuarioNormal{" +
                "favoritas=" + favoritas +
                ", carrito=" + carrito +
                ", compras=" + compras +
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
