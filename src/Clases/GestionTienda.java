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
import Interfaces.I_toJSONArray;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase envoltorio que sirve para gestionar el sistema de la tienda.
 */
public class GestionTienda implements Serializable, I_toJSONArray
{
    private GeneDosPU<String,Usuario> usuarios;
    private GeneDosPU<String,Publicacion> publicaciones;

    // CONSTRUCTOR
    public GestionTienda() {
        this.usuarios = new GeneDosPU<>();
        this.publicaciones = new GeneDosPU<>();
    }

    // MÉTODOS

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
     * Método que agrega un usuario del tipo UsuarioNormal a la lista de usuarios. Lanza la excepción ExcepcionClaveDuplicada si el dni ya existe.
     * Lanza la excepción ExcepcionContrasenaInvalida si la contraseña ingresada no cumple los requisitos mínimos. Lanza la excepción ExcepcionMailYaExiste
     * si el mail ingresado ya existe en la lista de usuarios.
     * @param mail
     * @param contrasena
     * @param nombre
     * @param apellido
     * @param numeroTelefono
     * @param tipoUsuario
     * @param dni
     * @throws ExcepcionClaveDuplicada Se lanza esta excepción cuando la clave está duplicada.
     * @throws ExcepcionConstrasenaInvalida Se lanza esta excepción cuando la contraseña ingresada no cumple los requisitos mínimos.
     * @throws ExcepcionMailYaExiste Se lanza esta excepción cuando el mail ingresado ya existe en la lista de usuarios.
     * @throws ExcepcionNumeroRepetido Se lanza esta excepción cuando número de teléfono ingresado ya existe.
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
     * Lanza la excepción ExcepcionContrasenaInvalida si la contraseña ingresada no cumple los requisitos mínimos. Lanza la excepción ExcepcionMailYaExiste
     * si el mail ingresado ya existe en la lista de usuarios.
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
     * @throws ExcepcionMailYaExiste Se lanza esta excepción cuando el mail ingresado ya existe en la lista de usuarios.
     * @throws ExcepcionNumeroRepetido Se lanza esta excepción cuando número de teléfono ingresado ya existe.
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

    /**
     * Método que crea un periférico para después agregarlo a una publicación.
     * @param nombre
     * @param estado
     * @param marca
     * @param modelo
     * @param origen
     * @param plataformas
     * @param color
     * @param peso
     * @param inalambrico
     * @return Retorna el periférico creado.
     */
    public Periferico crearPeriferico(String nombre, E_Estado estado, String marca, String modelo, String origen, String plataformas, String color, float peso, boolean inalambrico)
    {
        String id = GeneradorUUID.generarID();
        Periferico periferico = new Periferico(nombre,id,estado,marca,modelo,origen,plataformas,color,peso,inalambrico);
        return periferico;
    }

    /**
     * Método que crea una publicación.
     * @param nombre
     * @param periferico
     * @param precio
     * @param stock
     * @param dueno
     * @param urlFoto
     * @return Retorna la publicación.
     */
    public Publicacion crearPublicacion(String nombre,Periferico periferico, float precio, int stock, Usuario dueno, String urlFoto)
    {
        String id = GeneradorUUID.generarID();
        Publicacion publicacion = new Publicacion(nombre,periferico,precio,stock,dueno,urlFoto,id);
        return publicacion;
    }
    public Publicacion agregarPublicacion(String nombrePu,String nombrePe, E_Estado estado, String marca, String modelo, String origen,String plataformas, String color, float peso, boolean inalambrico, float precio, int stock, Usuario dueno, String urlFoto)
    {
        Periferico periferico = crearPeriferico(nombrePe,estado,marca,modelo,origen,plataformas,color,peso,inalambrico);
        Publicacion publicacion = crearPublicacion(nombrePu,periferico,precio,stock,dueno,urlFoto);
        publicaciones.agregar(publicacion.getId(), publicacion);
        dueno.agregarPublicacion(publicacion);
        return publicacion;
    }


    /**
     * Se agrega una direccion al usuario especificado por parametro
     * @param usuario Usuario a agregar direccion
     * @param direccion direccion a agregar al usuario
     */
    public void agregarDireccion(Usuario usuario, Direccion direccion)
    {
        usuario.agregarDireccion(direccion);
    }

    /**
     * Método para agregar un envío a la publicación que especifique el usuario.
     * @param publicacion Publicación a la que se le va a agregar el envío.
     * @param envioAgregar Envío a agregar.
     */
    public void agregarEnvioPublicacion(Publicacion publicacion, Envio envioAgregar) {
        publicacion.agregarEnvio(envioAgregar); //se agregar el envio en la publicacion
        publicaciones.modificar(publicacion.getId(),publicacion);
    }

    /**
     * Es un método que verifica si el mail ingresado está repetido en la lista de usuarios.
     * @return Retorna true si el mail está repetido, sino false.
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
     * Es un método verifica que la clave ingresada corresponda al usuario ingresado.
     * @param clave Clave a ingresar
     * @return Retorna true si corresponde, sino false.
     */
    public Usuario claveConfirmacion(String usuario, String clave) // usuario puede ser mail o dni
    {
        HashMap<String, Usuario> mapa = usuarios.getMapa();

        Iterator<Map.Entry<String,Usuario>> it = mapa.entrySet().iterator(); // para recorrerlo
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
     * Es un método que verifica si el usuario ingresado existe en la lista de usuarios, evaluando por dni o mail pasado por parámetro.
     * @param metodo Mail o DNI a buscar
     * @return Retorna true si existe, sino false.
     */
    public boolean existeUsuario(String metodo)
    {
        boolean rta = false;
        if(mailRepetido(metodo) || usuarios.contieneClave(metodo)) // significa que está en la base de datos
        {
            rta = true;
            return rta;
        }
        return rta;
    }

    /**
     * Es un método que verifica si el número de teléfono ingresado ya existe en la lista de usuarios.
     * @param numero
     * @return Retorna true si el número ya existe, sino false.
     */
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

    /**
     * Mete a todos los usuarios y a todas las publicaciones en dos diferentes archivos.
     * @param nameUsuario nombre del archivo de usuarios
     * @param namePublicacion nombre del archivo de publicaciones
     */
    public void toArchivo(String nameUsuario, String namePublicacion)
    {
        //para hacer esto tengo que recorrer enteras las dos listas
        HashMap<String,Usuario> mapaUsuarios = usuarios.getMapa();
        HashMap<String,Publicacion> mapaPublicaciones = publicaciones.getMapa();

        Iterator<Map.Entry<String, Usuario>> itUsuarios = mapaUsuarios.entrySet().iterator();
        Iterator<Map.Entry<String, Publicacion>> itPublicaciones = mapaPublicaciones.entrySet().iterator();


            try
            {
                FileOutputStream archivoUsuario = new FileOutputStream(nameUsuario + ".dat");
                FileOutputStream archivoPublicacion = new FileOutputStream(namePublicacion + ".dat");

                ObjectOutputStream objetoUsuarios = new ObjectOutputStream(archivoUsuario);
                ObjectOutputStream objetoPublicaciones = new ObjectOutputStream(archivoPublicacion);

                while(itUsuarios.hasNext()) //se escriben los usuarios
                {
                    Map.Entry<String,Usuario> entryU = itUsuarios.next();
                    Usuario auxUsuario = entryU.getValue();
                    objetoUsuarios.writeObject(auxUsuario);
                }
                while(itPublicaciones.hasNext()) //se escriben las publicaciones
                {
                    Map.Entry<String,Publicacion> entryP = itPublicaciones.next();
                    Publicacion auxUsuario = entryP.getValue();
                    objetoPublicaciones.writeObject(auxUsuario);
                }
                objetoUsuarios.close();
                objetoPublicaciones.close();
                archivoUsuario.close();
                archivoPublicacion.close();
            }
            catch(EOFException e){}
            catch(IOException e){}
            catch(Exception e){};
    }

    /**
     *
     * Baja el archivo del usuario, y lo introduce a la lista generica.
     * @param nameUsuario nombre del archivo
     *
     */
    public void bajarArchivoUsuarios(String nameUsuario)
    {
        try
        {
            FileInputStream archivoUsuario = new FileInputStream(nameUsuario  + ".dat");
            ObjectInputStream objetoUsuarios = new ObjectInputStream(archivoUsuario);

            while (true)
            {
                Object object = objetoUsuarios.readObject();
                Usuario usuario = (Usuario) object;
                usuarios.agregar(usuario.getDni(),usuario);
            }
        }
        catch(EOFException e){}
        catch(IOException e){}
        catch(Exception e){};
    }
    /**
     *
     * Baja el archivo de publicaciones, y lo introduce a la lista generica.
     * @param namePublicacion nombre del archivo
     *
     */
    public void bajarArchivoPublicaciones(String namePublicacion)
    {
        try
        {
            FileInputStream archivoPubli = new FileInputStream(namePublicacion  + ".dat");
            ObjectInputStream objetoPubli = new ObjectInputStream(archivoPubli);

            while (true)
            {
                Object object = objetoPubli.readObject();
                Publicacion publicacion = (Publicacion) object;
                publicaciones.agregar(publicacion.getId(),publicacion);
            }
        }
        catch(EOFException e){}
        catch(IOException e){}
        catch(Exception e){};
    }



    // GETTERS Y TOSTRING
    public GeneDosPU<String, Publicacion> getPublicaciones() {
        return publicaciones;
    }
    public GeneDosPU<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Se ingresan todas las publicaciones a JSONArray, el cual puede ser exportado.
     */
    public JSONArray toJSONArray() throws JSONException
    {
        HashMap<String,Publicacion> mapaPub = getPublicaciones().getMapa();
        Iterator<Map.Entry<String,Publicacion>> it = mapaPub.entrySet().iterator();

        JSONArray array = new JSONArray();

        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion publicacion = entry.getValue();

            array.put(publicacion.toJSONObject());
        }
        JsonUtiles.grabar(array,"JSONPublicaciones");
        return array;
    }

    @Override
    public String toString() {
        return "GestionTienda{\n " +
                "Usuarios = " + "\n" + usuarios +
                "\n\nPublicaciones=  \n" + publicaciones +
                '}';
    }
}
