package Clases;


import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
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
     * @return Retorna true si encuentra al menos una mayúscula en el string, sino false.
     */
    private boolean verificarMayuscula(String contrasena)
    {
        // El objeto Pattern representa un patrón de expresión regular que se utiliza junto con el objeto Matcher para realizar
        // operaciones de búsqueda y manipulación de strings.
        // "(?=.*[A-Z])" es una expresión regular que busca al menos una letra mayúscula en el string.
        Pattern pattern = Pattern.compile("(?=.*[A-Z])");
        Matcher matcher = pattern.matcher(contrasena);
        // El método find() busca el patrón en el string y devuelve true si encuentra una coincidencia, sino false.
        return matcher.find();
    }

    /**
     * Método para verificar si la contraseña contiene al menos un número.
     * @param contrasena
     * @return Retorna true si encuentra al menos un número en la cadena de texto, sino false.
     */
    private boolean verificarNumero(String contrasena)
    {
        // "(?=.*\\d)" es una expresión regular que busca al menos un dígito numérico en el string.
        Pattern pattern = Pattern.compile("(?=.*\\d)");
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.find();
    }

    /**
     * Método para verificar que la contraseña cumpla los requisistos mínimos.
     * @param contrasena
     * @return Retorna true si la contraseña cumple los requisitos, sino false.
     */
    public boolean verificarContrasena(String contrasena)
    {
        if(contrasena.length() < 8)
        {
            return false;
        }
        else if (!verificarMayuscula(contrasena))
        {
            return false;
        }
        else if (!verificarNumero(contrasena))
        {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Método que agrega un usuario del tipo UsuarioNormal a la lista de usuarios. Lanza la excepción ClaveDuplicadaException si el dni o el mail ya existen.
     * Lanza la excepción ContrasenaInvalidaException si la contraseña ingresada no cumple los requisitos mínimos.
     * @param mail
     * @param contrasena
     * @param nombre
     * @param apellido
     * @param numeroTelefono
     * @param tipoUsuario
     * @param dni
     * @throws ExcepcionClaveDuplicada Se lanza esta excepción cuando la clave está duplicada.
     * @throws ExcepcionConstrasenaInvalida Se lanza esta excepción cuando la contraseña ingresada no cumple los requisitos mínimos.
     * @throws ExcepcionMailYaExiste Se lanza esta excepción cuando el mail ingresado ya existe.
     */
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni) throws ExcepcionClaveDuplicada, ExcepcionConstrasenaInvalida, ExcepcionMailYaExiste
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ExcepcionClaveDuplicada("Error: El DNI ingresado ya existe.");
        }
        else if (usuarios.contieneClave(mail))
        {
            throw new ExcepcionMailYaExiste("Error: El mail ingresado ya existe.");
        }
        else if(!verificarContrasena(contrasena))
        {
            throw new ExcepcionConstrasenaInvalida("Error: La contraseña debe tener por lo menos 8 caracteres.");
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
     * @throws ExcepcionClaveDuplicada Se lanza esta excepción cuando la clave está duplicada.
     * @throws ExcepcionConstrasenaInvalida Se lanza esta excepción cuando la contraseña ingresada no cumple los requisitos mínimos.
     * @throws ExcepcionMailYaExiste Se lanza esta excepción cuando el mail ingresado ya existe.
     */
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni, boolean verificado, String url, String cuit, E_CondFiscal condicionFiscal) throws ExcepcionClaveDuplicada, ExcepcionConstrasenaInvalida, ExcepcionMailYaExiste
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ExcepcionClaveDuplicada("Error: El DNI ingresado ya existe.");
        }
        else if(usuarios.contieneClave(mail))
        {
            throw new ExcepcionMailYaExiste("Error: El mail ingresado ya existe.");
        }
        else if(!verificarContrasena(contrasena))
        {
            throw new ExcepcionConstrasenaInvalida("Error: La contraseña debe tener por lo menos 8 caracteres.");
        }
        else {
            UsuarioVenta usuario = new UsuarioVenta(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, verificado, url, cuit, condicionFiscal);
            usuarios.agregar(dni,usuario); //pasa como clave el dni del vendedor
        }
    }



}
