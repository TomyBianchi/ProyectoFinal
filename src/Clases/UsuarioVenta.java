package Clases;

import java.io.Serializable;

import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;

/**
 * Es una clase que hereda de Usuario. Esta clase es la especialidad que hace que este tipo de usuario solo pueda
 * vender articulos, y no comprarlos. A la vez tiene esta serie de atributos que lo hacen diferente, y mejor posicionado
 * a la hora de vender que UsuarioNormal.
 */
public class UsuarioVenta extends Usuario implements Serializable
{
    // ATRIBUTOS
    private String url;
    private String cuit;
    private E_CondFiscal condicionFiscal;

    // CONSTRUCTORES
    public UsuarioVenta(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, Venta ventas, float promedioVentas, boolean verificado, String url, String cuit, E_CondFiscal condicionFiscal) {
        super(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, ventas, promedioVentas);
        this.url = url;
        this.cuit = cuit;
        this.condicionFiscal = condicionFiscal;
    }
    public UsuarioVenta(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, String url, String cuit, E_CondFiscal condicionFiscal) {
        super(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni);
        this.url = url;
        this.cuit = cuit;
        this.condicionFiscal = condicionFiscal;
    }
    public UsuarioVenta() {
        super();
        this.url = "";
        this.cuit = "";
        this.condicionFiscal = null;
    }

    //GETTERS, SETTERS Y OTROS

    public String getUrl() {
        return url;
    }

    public String getCuit() {
        return cuit;
    }

    public E_CondFiscal getCondicionFiscal() {
        return condicionFiscal;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof UsuarioVenta)
            {
                UsuarioVenta aux = (UsuarioVenta) o;
                if(getCuit().equals(aux.getCuit()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 4;
    }

    @Override
    public String toString() {
        return "UsuarioVenta{" +
                "url='" + url + '\'' +
                ", cuit='" + cuit + '\'' +
                ", condicionFiscal=" + condicionFiscal +
                '}';
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof UsuarioVenta)
            {
                UsuarioVenta aux = (UsuarioVenta)o;
                String auxUno = new String(getCuit());  //estos dos errores no impide que compile el programa
                String auxDos = new String(aux.getCuit());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
