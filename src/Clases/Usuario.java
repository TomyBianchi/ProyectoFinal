package Clases;

import Enums.E_TipoUsuario;
import java.io.Serializable;
import Genericas.GeneUnoDM;
import Genericas.GeneDosPU;

public class Usuario implements Serializable
{
    // ATRIBUTOS
    private String mail;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private E_TipoUsuario tipoUsuario;
    private String dni; // vale como ID
    private GeneDosPU<String,Publicacion> publicaciones; // lista genérica de publicaciones
    private Venta ventas; // adentro de venta hay una lista de todas las ventas que hizo el usuario
    private GeneUnoDM<Direccion> direcciones; // lista genérica en donde guardo las direcciones; en caso de los de sólo venta, es la dirección desde donde se envía el producto
    private GeneUnoDM<MetodoDePago> metodosDePago; // lista genérica de métodos de pago
    private float promedioVentas;

    // CONSTRUCTORES
    public Usuario(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, Venta ventas, float promedioVentas) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.tipoUsuario = tipoUsuario;
        this.dni = dni;
        this.publicaciones = new GeneDosPU<>();
        this.ventas = new Venta();
        this.direcciones = new GeneUnoDM();
        this.metodosDePago = new GeneUnoDM();
        this.promedioVentas = promedioVentas;
    }
    public Usuario(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.tipoUsuario = tipoUsuario;
        this.dni = dni;
        this.publicaciones = new GeneDosPU<>();
        this.ventas = new Venta();
        this.direcciones = new GeneUnoDM();
        this.metodosDePago = new GeneUnoDM();
        this.promedioVentas = 0;
    }
    public Usuario() {
        this.mail = "";
        this.contrasena = "";
        this.nombre = "";
        this.apellido = "";
        this.numeroTelefono = "";
        this.tipoUsuario = null;
        this.dni = "";
        this.publicaciones = null;
        this.ventas = null;
        this.direcciones = null;
        this.metodosDePago = null;
        this.promedioVentas = 0;
    }

    // MÉTODOS

    /**
     * Método que agrega una publicación a la lista genérica de publicaciones.
     * @param publicacion
     */
    public void agregarPublicacion(Publicacion publicacion)
    {
        publicaciones.agregar(publicacion.getId(),publicacion);
    }
    public void agregarMetodoDePago(MetodoDePago metodoDePago)
    {
        metodosDePago.agregar(metodoDePago);
    }

    public void borrarMetodoDePago(MetodoDePago metodoDePago)
    {
        metodosDePago.borrar(metodoDePago);
    }
    public void agregarDireccion(Direccion direccion)
    {
        direcciones.agregar(direccion);
    }
    public void borrarDireccion(Direccion direccion){direcciones.borrar(direccion);}


    // GETTERS, SETTERS Y OTROS
    public String getMail() {
        return mail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public E_TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getDni() {
        return dni;
    }

    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public Venta getVentas() {
        return ventas;
    }

    public GeneUnoDM getDirecciones() {
        return direcciones;
    }

    public GeneUnoDM getMetodosDePago() {
        return metodosDePago;
    }

    public float getPromedioVentas() {
        return promedioVentas;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof Usuario)
            {
                Usuario aux = (Usuario) o;
                if(getDni().equals(aux.getDni()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    public int hashCode()
    {
        return 2;
    }
    @Override
    public String toString() {
        return "  Usuario (CLASE USUARIO){ " +
                "mail='" + mail + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", dni='" + dni + '\'' +
                ", listaPublicaciones=" + publicaciones +
                ", ventas=" + ventas +
                ", direcciones=" + direcciones +
                ", metodosDePago=" + metodosDePago +
                ", promedioVentas=" + promedioVentas +
                '}' + "\n";
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof Usuario)
            {
                Usuario aux = (Usuario)o;
                String auxUno = new String(getDni());  // estos dos errores no impiden que compile el programa
                String auxDos = new String(getDni());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
