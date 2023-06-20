package Clases;

import Enums.E_Pago;
import Enums.E_TipoUsuario;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

import Genericas.GeneUnoDM;
import Genericas.GeneDosPU;

/**
 * Es una clase abstracta. Padre de UsuarioNormal y UsuarioVenta. Esta tiene todas las caracteristicas que comarten
 * entre esas dos. Es de las clases mas importantes, que se usa en la Envoltorio, implementada con una lista generica.
 */
public abstract class Usuario implements Serializable
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
     * Agrega una publicacion a las publicaciones del usuario
     * @param publicacion
     */
    public void agregarPublicacion(Publicacion publicacion)
    {
        publicaciones.agregar(publicacion.getId(),publicacion);
    }
    /**
     * Agrega un metodo de pago a la lista de metodos de pago
     * @param metodoDePago
     */
    public void agregarMetodoDePago(MetodoDePago metodoDePago)
    {
        metodosDePago.agregar(metodoDePago);
    }

    /**
     * Borra un metodo de pago de la lista de metodos de pago
     * @param metodoDePago
     */
    public void borrarMetodoDePago(MetodoDePago metodoDePago)
    {
        metodosDePago.borrar(metodoDePago);
    }
    /**
     * Agrega una direccion a la lista de direcciones
     * @param direccion
     */
    public void agregarDireccion(Direccion direccion)
    {
        direcciones.agregar(direccion);
    }
    /**
     * Borra una direccion de la lista de direcciones
     * @param direccion
     */
    public void borrarDireccion(Direccion direccion){direcciones.borrar(direccion);}

    /**
     * Agrega una nueva venta a la variable ventas.
     * @param publicacion
     */
    public void agregarVenta(Publicacion publicacion)
    {
        ventas.agregarPublicacion(publicacion);
    }

    /**
     * Metodo que ayuda para la verificacoin de los usuarios de tipo venta. Esta funcion te dice si tienen al menos un tipo de Metodo de pago de cada uno.
     * @return Te devuelve true si tiene al menos un metodo de pago de cada uno, en caso contrario returna false.
     */
    public boolean tieneDebitoYcredito()
    {
        HashSet<MetodoDePago> set = getMetodosDePago().getSet();
        Iterator<MetodoDePago> it = set.iterator();
        int contdorDebito = 0;
        int contadorCredito = 0;
        boolean rta = false;
        while(it.hasNext())
        {
            MetodoDePago metodo = it.next();
            if(metodo.getTipoPago().equals(E_Pago.DEBITO))
            {
                contdorDebito++;
            }
            else
            {
                contadorCredito++;
            }
        }
        if(contdorDebito >= 1 && contadorCredito >= 1)
        {
            rta = true;
        }
        return rta;
    }


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
