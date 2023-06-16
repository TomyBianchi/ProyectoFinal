package Clases;

import Enums.E_TipoUsuario;
import Genericas.GeneUnoDM;
import Genericas.GeneDosPU;

public class Usuario {
    private String mail;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private E_TipoUsuario tipoUsuario;
    private String dni; // vale como ID
    private GeneDosPU<String,Publicacion> listaPublicaciones; //lista generica de publicaciones
    private Venta ventas; //adentro de venta hay una lista de todas las ventas que hizo el usuario
    private GeneUnoDM direcciones; //lista generica, en donde guardo las direcciones
    private GeneUnoDM metodosDePago; //lista generica de metodos de pago
    private float promedioVentas;

    //constructores


    public Usuario(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, Venta ventas, float promedioVentas) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.tipoUsuario = tipoUsuario;
        this.dni = dni;
        this.listaPublicaciones = new GeneDosPU<>();
        this.ventas = ventas;
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
        this.listaPublicaciones = new GeneDosPU<>();
        this.ventas = null;
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
        this.listaPublicaciones = null;
        this.ventas = null;
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

    public GeneDosPU<String, Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
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
