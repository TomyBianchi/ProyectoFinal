package Clases;

import java.io.Serializable;

import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;

public class UsuarioVenta extends Usuario
implements Serializable{

    //atributos
    private boolean verificado; // true: identidad verificada, false: no verificada
    private String url;
    private String cuit;
    private E_CondFiscal condicionFiscal;

    public UsuarioVenta(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, Venta ventas, float promedioVentas, boolean verificado, String url, String cuit, E_CondFiscal condicionFiscal) {
        super(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, ventas, promedioVentas);
        this.verificado = verificado;
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
        this.verificado = false;
        this.url = "";
        this.cuit = "";
        this.condicionFiscal = null;
    }

    //getters y setters
    public boolean isVerificado() {
        return verificado;
    }

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
        return super.toString() + "     UsuarioVenta{" +
                "verificado=" + verificado +
                ", url='" + url + '\'' +
                ", cuit='" + cuit + '\'' +
                ", condicionFiscal=" + condicionFiscal + "\n\n\n" +
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
