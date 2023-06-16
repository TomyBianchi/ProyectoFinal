package Clases;


import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;
import Excepciones.ClaveDuplicadaException;
import Excepciones.ConstrasenaInvalidaException;
import Genericas.GeneDosPU;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Método para verificar si la contraseña contiene al menos una letra mayúsucla.
     * @param contrasena
     * @return - buscar
     */
    private boolean verificarMayuscula(String contrasena)
    {
        Pattern pattern = Pattern.compile("(?=.*[A-Z])");
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.find();
    }

    /**
     * Método para verificar si la contraseña contiene al menos un número.
     * @param contrasena
     * @return - buscar
     */
    private boolean verificarNumero(String contrasena)
    {
        Pattern pattern = Pattern.compile("(?=.*\\d)");
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.find();
    }
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
     * Método que agrega un usuario del tipo UsuarioNormal a la lista de usuarios. Lanza la excepción ClaveDuplicadaException si el dni o el mail ya existen.
     * @param mail
     * @param contrasena
     * @param nombre
     * @param apellido
     * @param numeroTelefono
     * @param tipoUsuario
     * @param dni
     * @throws ClaveDuplicadaException Se lanza esta excepción cuando la clave está duplicada.
     */
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni) throws ClaveDuplicadaException, ConstrasenaInvalidaException
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ClaveDuplicadaException("Error: El DNI ingresado ya existe.");
        }
        else if (usuarios.contieneClave(mail))
        {
            throw new ClaveDuplicadaException("Error: El mail ingresado ya existe.");
        }
        else if(contrasena.length() < 8)
        {
            throw new ConstrasenaInvalidaException("Error: La contraseña debe tener por lo menos 8 caracteres.");
        }
        else if (!verificarMayuscula(contrasena))
        {
            throw new ConstrasenaInvalidaException("Error: La contraseña debe contener al menos una mayúscula.");
        }
        else if (!verificarNumero(contrasena))
        {
            throw new ConstrasenaInvalidaException("Error: La contraseña debe contener al menos un número.");
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
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, boolean verificado, String url, String cuit, E_CondFiscal condicionFiscal) throws ClaveDuplicadaException, ConstrasenaInvalidaException
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ClaveDuplicadaException("Error: El DNI ingresado ya existe.");
        }
        else if(usuarios.contieneClave(mail))
        {
            throw new ClaveDuplicadaException("Error: El mail ingresado ya existe.");
        }
        else if(contrasena.length() < 8)
        {
            throw new ConstrasenaInvalidaException("Error: La contraseña debe tener por lo menos 8 caracteres.");
        }
        else if (!verificarMayuscula(contrasena))
        {
            throw new ConstrasenaInvalidaException("Error: La contraseña debe contener al menos una mayúscula.");
        }
        else if (!verificarNumero(contrasena))
        {
            throw new ConstrasenaInvalidaException("Error: La contraseña debe contener al menos un número.");
        }
        else {
            UsuarioVenta usuario = new UsuarioVenta(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, verificado, url, cuit, condicionFiscal);
            usuarios.agregar(dni,usuario); //pasa como clave el dni del vendedor
        }
    }







}
