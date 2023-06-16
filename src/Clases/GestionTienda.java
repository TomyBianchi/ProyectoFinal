package Clases;


import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;
import Excepciones.ClaveDuplicadaException;
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
    /**
     * Método que verifica en la lista de usuarios si ya existe la clave que se pasa por parámetro. La clave va a ser el DNI del usuario.
     * @param key - clave (DNI)
     * @return Retorna verdadero si contiene la clave, falso si no la contiene.
     */
    public boolean siExiste(String key)
    {
        return usuarios.contieneClave(key);
    }

    /**
     * Método que agrega un usuario del tipo UsuarioNormal a la lista de usuarios. Lanza la excepción ClaveDuplicadaException si el dni ya existe.
     * @param mail
     * @param contrasena
     * @param nombre
     * @param apellido
     * @param numeroTelefono
     * @param tipoUsuario
     * @param dni
     * @throws ClaveDuplicadaException Se lanza esta excepción cuando la clave está duplicada.
     */
    public void agregarUsuario(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni) throws ClaveDuplicadaException
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ClaveDuplicadaException("Error: Clave duplicada.");
        }
        else {
            UsuarioNormal usuario = new UsuarioNormal(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni);
            usuarios.agregar(dni,usuario); //pasa como clave el dni del usuario
        }
    }

    /**
     * Método que agrega un usuario del tipo UsuarioVenta a la lista de usuarios. Lanza la excepción ClaveDuplicadaException si el dni ya existe.
     * @param mail
     * @param contrasena
     * @param nombre
     * @param apellido
     * @param numeroTelefono
     * @param tipoUsuario
     * @param dni
     * @param verificado
     * @param url
     * @param cuit
     * @param condicionFiscal
     * @throws ClaveDuplicadaException Se lanza esta excepción cuando la clave está duplicada.
     */
    public void agregarPublicacion(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, boolean verificado, String url, String cuit, E_CondFiscal condicionFiscal) throws ClaveDuplicadaException
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ClaveDuplicadaException("Error: Clave duplicada.");
        }
        UsuarioVenta usuario = new UsuarioVenta(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, verificado, url, cuit, condicionFiscal);
        usuarios.agregar(dni,usuario); //pasa como clave el dni del vendedor
    }







}
