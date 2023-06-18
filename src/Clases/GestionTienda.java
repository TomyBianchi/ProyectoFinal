package Clases;

import Enums.E_CondFiscal;
import Enums.E_Estado;
import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;
import ClasesExtra.GeneradorUUID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PrimitiveIterator;
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

    //agregar usuarios

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
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni) throws ExcepcionClaveDuplicada, ExcepcionConstrasenaInvalida, ExcepcionMailYaExiste,ExcepcionNumeroRepetido
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ExcepcionClaveDuplicada("Error: El DNI ingresado ya existe.");
        }
        else if (mailRepetido(mail))
        {
            throw new ExcepcionMailYaExiste("Error: El mail ingresado ya existe en otra cuenta. Inicia sesion con tu cuenta o proba con un mail distinto a ", mail);
        }
        else if(!verificarContrasena(contrasena))
        {
            throw new ExcepcionConstrasenaInvalida("Error: Recorda que la contraseña tiene que tener minimo 8 caracteres, una mayuscula y un numero.");
        }
        else if(numeroRepetido(numeroTelefono))
        {
            throw new ExcepcionNumeroRepetido("Error: El numero introducido ya existe en otra cuenta. Inicia sesion con tu cuenta o proba con un numero distinto a ", numeroTelefono);
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
     * @param url
     * @param cuit
     * @param condicionFiscal
     * @throws ExcepcionClaveDuplicada Se lanza esta excepción cuando la clave está duplicada.
     * @throws ExcepcionConstrasenaInvalida Se lanza esta excepción cuando la contraseña ingresada no cumple los requisitos mínimos.
     * @throws ExcepcionMailYaExiste Se lanza esta excepción cuando el mail ingresado ya existe.
     */
    public void agregar(String mail, String contrasena, String nombre, String apellido, String numeroTelefono, E_TipoUsuario tipoUsuario, String dni,String url, String cuit, E_CondFiscal condicionFiscal) throws ExcepcionClaveDuplicada, ExcepcionConstrasenaInvalida, ExcepcionMailYaExiste, ExcepcionNumeroRepetido
    {
        if(usuarios.contieneClave(dni))
        {
            throw new ExcepcionClaveDuplicada("Error: El DNI ingresado ya existe.");
        }
        else if(mailRepetido(mail))
        {
            throw new ExcepcionMailYaExiste("Error: El mail ingresado ya existe. Proba con un mail distinto a ", mail);
        }
        else if(!verificarContrasena(contrasena))
        {
            throw new ExcepcionConstrasenaInvalida("Error: Recorda que la contraseña tiene que tener minimo 8 caracteres, una mayuscula y un numero.");
        }
        else if(numeroRepetido(numeroTelefono))
        {
            throw new ExcepcionNumeroRepetido("Error: El numero introducido ya existe en otra cuenta. Inicia sesion con tu cuenta o proba con un numero distinto a ", numeroTelefono);
        }
        else {
            UsuarioVenta usuario = new UsuarioVenta(mail, contrasena, nombre, apellido, numeroTelefono, tipoUsuario, dni, url, cuit, condicionFiscal);
            usuarios.agregar(dni,usuario); //pasa como clave el dni del vendedor
        }
    }


    //agregar publicacion
    public Periferico crearPeriferico(String nombre, E_Estado estado, String marca, String modelo, String origen, String tags, String plataformas, String color, float peso, boolean inalambrico)
    {
        String id = GeneradorUUID.generarID();
        Periferico periferico = new Periferico(nombre,id,estado,marca,modelo,origen,tags,plataformas,color,peso,inalambrico);
        return periferico;
    }
    public Publicacion crearPublicacion(Periferico periferico, float precio, int stock, Usuario dueno, String urlFoto)
    {
        String id = GeneradorUUID.generarID();
        Publicacion publicacion = new Publicacion(periferico,precio,stock,dueno,urlFoto);
        return publicacion;
    }
    public void agregarPublicacion(String nombre, E_Estado estado, String marca, String modelo, String origen, String tags, String plataformas, String color, float peso, boolean inalambrico, float precio, int stock, String mailDueno)
    {
        Periferico periferico = crearPeriferico(nombre,estado,marca,modelo,origen,tags,plataformas,color,peso,inalambrico);
        //buscar dueno por mail y retornarlo
        //Publicacion publicacion = crearPublicacion(periferico,precio,stock,dueno);
        //publicaciones.agregar(publicacion.getId(), publicacion);
    }



    //agregar envio

    /**
     * Es un metodo para agregar un envio a la publicacion que especifique el usuario.
     * @param publicacion Publicacion a la que se le va a agregar el envio.
     * @param envioAgregar Envio a agregar
     */
    public void agregarEnvioPublicacion(Publicacion publicacion, Envio envioAgregar) {
        publicacion.agregarEnvio(envioAgregar); //se agregar el envio en la publicacion
        publicaciones.modificar(publicacion.getId(),publicacion);
    }

    

    //otras

    /**
     * Es una funcion la cual returna true si el mail ya esta en la base de datos, en caso contrario returna false
     * @return
     */
    public boolean mailRepetido(String mail)
    {
        HashMap<String, Usuario> mapa = usuarios.getMapa();
        Iterator<Map.Entry<String,Usuario>> it = mapa.entrySet().iterator();
        boolean rta = false;
        while(it.hasNext()) {
            Map.Entry<String, Usuario> entry = it.next();
            Usuario usuario = entry.getValue();
            if (usuario.getMail().equals(mail)) {
                rta = true;
            }
        }
        return rta;
    }

    /**
     * Es un metodo el cual returna true si la clave va con el usuario, en caso contrario returna false
     * @param clave clave a ingresar
     * @return returna true si es correcto, false si no lo es
     */
    public Usuario claveConfirmacion(String usuario, String clave) //usuario puede ser mail o dni
    {
        HashMap<String, Usuario> mapa = usuarios.getMapa();

        Iterator<Map.Entry<String,Usuario>> it = mapa.entrySet().iterator(); //para recorrerlo
        boolean rta = false;
        while(it.hasNext()) {
            Map.Entry<String, Usuario> entry = it.next();
            Usuario auxUsuario = entry.getValue();
            if (auxUsuario.getDni().equals(usuario) || auxUsuario.getMail().equals(usuario)) {
                if (auxUsuario.getContrasena().equals(clave)) {
                    return auxUsuario;
                }
            }
        }
        return null;
    }

    /**
     * Es un metodo que returna true si existe el usuario, evaluado por dni o mail pasado por parametro
     * @param metodo Mail o DNI a buscar
     * @return Returna true si existe, en caso contrario returna false
     */
    public boolean existeUsuario(String metodo)
    {
        boolean rta = false;
        if(mailRepetido(metodo) || usuarios.contieneClave(metodo)) //significa que si esta en la base de datos
        {
            rta = true;
            return rta;
        }
        return rta;

    }

    public boolean numeroRepetido(String numero)
    {
        HashMap<String, Usuario> mapa = usuarios.getMapa();
        Iterator<Map.Entry<String,Usuario>> it = mapa.entrySet().iterator();
        boolean rta = false;
        while(it.hasNext()) {
            Map.Entry<String, Usuario> entry = it.next();
            Usuario usuario = entry.getValue();
            if (usuario.getNumeroTelefono().equals(numero)) {
                rta = true;
            }
        }
        return rta;
    }




    @Override
    public String toString() {
        return "GestionTienda{\n " +
                "Usuarios = " + "\n" + usuarios +
                "\n\nPublicaciones=  \n" + publicaciones +
                '}';
    }
}
