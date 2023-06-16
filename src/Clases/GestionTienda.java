package Clases;


import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;
import Genericas.GeneDosPU;

/**
 * Envoltorio
 */
public class GestionTienda
{
    private GeneDosPU<String,Usuario> usuarios;
    private GeneDosPU<String,Publicacion> publicaciones;

    //constructor
    public GestionTienda() {
        this.usuarios = new GeneDosPU<>();
        this.publicaciones = new GeneDosPU<>();
    }

    //métodos

    //agregar usuario normal
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni)
    {
        UsuarioNormal usuario = new UsuarioNormal(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni);
        usuarios.agregar(dni,usuario); //pasa como clave el dni del usuario
    }

    //agregar usuario venta
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, boolean verificado, String url, String cuit, E_CondFiscal condicionFiscal)
    {
        UsuarioVenta usuario = new UsuarioVenta(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, verificado, url, cuit, condicionFiscal);
        usuarios.agregar(cuit,usuario); //pasa como clave el cuit del vendedor
    }
}
