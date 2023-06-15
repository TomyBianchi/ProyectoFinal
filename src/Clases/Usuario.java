package Clases;

import Enums.E_TipoUsuario;
import Genericas.G_Direccion;
import Genericas.G_MetodoDePago;
import Genericas.G_Publicacion;

public class Usuario {
    private String mail;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private E_TipoUsuario tipoUsuario;
    private String dni; // vale como ID
    private boolean verificado; // true: identidad verificada, false: no verificada
    private G_Publicacion<String,Publicacion> listaPublicaciones; //lista generica de publicaciones
    private Venta ventas; //adentro de venta hay una lista de todas las ventas que hizo el usuario
    private G_Direccion direcciones; //lista generica, en donde guardo las direcciones
    private G_MetodoDePago metodosDePago; //lista generica de metodos de pago
    private float promedioVentas;

    //constructores


    public Usuario(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, boolean verificado, Venta ventas, float promedioVentas) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.tipoUsuario = tipoUsuario;
        this.dni = dni;
        this.verificado = verificado;
        this.listaPublicaciones = new G_Publicacion<>();
        this.ventas = ventas;
        this.direcciones = new G_Direccion();
        this.metodosDePago = new G_MetodoDePago();
        this.promedioVentas = promedioVentas;
    }
    public Usuario() {
        this.mail = "";
        this.contrasena = "";
        this.nombre = "";
        this.apellido = "";
        this.numeroTelefono = "";
        this.tipoUsuario = null;
        this.dni = "";
        this.verificado = false;
        this.listaPublicaciones = null;
        this.ventas = ventas;
        this.direcciones = null;
        this.metodosDePago = null;
        this.promedioVentas = 0;
    }

    //setters y getters
    public String getMail() {
        return mail;
    }

    public String getNombre() {
        return nombre;
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

    public boolean isVerificado() {
        return verificado;
    }

    public G_Publicacion<String, Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public Venta getVentas() {
        return ventas;
    }

    public G_Direccion getDirecciones() {
        return direcciones;
    }

    public G_MetodoDePago getMetodosDePago() {
        return metodosDePago;
    }

    public float getPromedioVentas() {
        return promedioVentas;
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
        return "Usuario{" +
                "mail='" + mail + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", dni='" + dni + '\'' +
                ", verificado=" + verificado +
                ", listaPublicaciones=" + listaPublicaciones +
                ", ventas=" + ventas +
                ", direcciones=" + direcciones +
                ", metodosDePago=" + metodosDePago +
                ", promedioVentas=" + promedioVentas +
                '}';
    }


    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof Usuario)
            {
                Usuario aux = (Usuario)o;
                String auxUno = new String(getDni());  //estos dos errores no impide que compile el programa
                String auxDos = new String(getDni());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }


}
