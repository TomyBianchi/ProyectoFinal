package Clases;

import Enums.*;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;
import Genericas.GeneTresE;
import Genericas.GeneUnoDM;
import org.json.JSONException;

import javax.swing.plaf.DesktopPaneUI;
import java.io.Serializable;
import java.util.*;
import java.util.InputMismatchException;

import static Clases.Main.teclado;

/**
 * Clase la cual se encarga de la gestión de la consola de comandos y salidas por pantalla.
 * Es la encargada de hacer la aplicacion un lugar amigable y util para el usuario
 */
public class GestionConsolaComandos implements Serializable
{
    // ATRIBUTO
    private GestionTienda tienda;

    // CONSTRUCTOR
    public GestionConsolaComandos(GestionTienda tienda) {
        this.tienda = tienda;
    }

    // MÉTODOS

    /**
     *Este metodo, antes de hacer todo, baja un archivo de usuarios y otro de publicaciones. Esos archivos son la "Base de datos" de los usuarios y de las
     * publicaciones que se van creando.
     *
     * Este metodo es el principal cuando se llama desde el main. Este mismo llama a Pagino uno que es la que se usa para iniciar sesion, crear una cuenta o
     * simplemente salir.
     *
     * En caso de iniciar sesion correctamente, se va a paginaDos(), el cual es el menu de la aplicacion una vez se haya logueado el usuario.
     */
    public void menuPrincipal() {

        String espacio = "                                                                           ";

        //cada vez que entramos al menu principal, se bajan todos los datos del archivo.
        this.tienda.bajarArchivoUsuarios("archivoUsuarios");
        this.tienda.bajarArchivoPublicaciones("archivoPublicaciones");

        Usuario ingresado = paginaUno(); //pagina uno, la cual podes iniciar sesion, crear una cuenta o simplemente salir
        //en caso de iniciar sesion, ingresado va a ser el usuario que ingreso, en caso de que no se haya iniciado sesion sera null
        if(ingresado != null)
        {
            System.out.print(espacio + "Cargando...\n");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }

            System.out.print(espacio + "Sesion iniciada con exito.\n");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }

            paginaDos(ingresado);
        }
    }

    /**
     * Este metodo es la interfaz principal para loguearse en la aplicacion.
     * Se puede crear una cuenta, iniciar sesion o simplemente salir del programa
     *
     * Al crear una cuenta, te redirige a crearCuenta(), el cual te da los pasos necesarios para crear una cuenta en la aplicacion.
     *Una vez terminada la creacion de la cuenta, te redirige denuevo a paginaUno().
     *
     * Al Iniciar sesion, te redirige a iniciarSesion(), el cual es un formulario para poder entrar a tu cuenta. Esta funcion es la que returna el usuario
     * que va a necesitar menuPrincipal, para poder confirmar que se inicio sesion. Esto llama denuevo a menuPrincipal(), el cual en caso de que se haya
     * iniciado sesion con un usuario valido, sigue a la paginaDos(), que es la interfaz de la aplicacion una vez el usuario ya esta logueado
     *
     * NECESARIAMENTE ES IMPORTANTE QUE CADA VEZ QUE SE SALGA DEL PROGRAMA SE APRETE EN SALIR. Esto hace que se guarde todo lo que se hizo en el archivo.
     * y tambien se guarden las publicaciones en un JSON
     *
     *
     * @return Returna el usuario que inicio sesion.
     */
    private Usuario paginaUno()
    {
        Usuario usuarioRetorno = null;
        int respuesta = -1;
        Usuario usuario = null; //en caso de que se haya iniciado sesion, esta funcion va a retornar el Usuario que inicio sesion.
        do {


            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            String texto = "BIENVENIDOS A LA TIENDA DE PERIFÉRICOS ";
            String espacio = "                                                                           ";
            // Códigos de escape ANSI
            String codigoNegrita = "\u001B[1m";
            String codigoSubrayado = "\u001B[4m";
            String codigoTamanioGrande = "\u001B[5m";
            String codigoReset = "\u001B[0m";

            System.out.println(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + texto + codigoReset); //texto principal

            System.out.print("\n\n\n");
            String opcion1 = "1- Iniciar sesión";
            String opcion2 = "2- Crear cuenta";
            String opcion3 = "3- Salir";
            String opcion4 = "Elección: ";
            System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion1 + codigoReset + "\n"); //opciones
            System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion2 + codigoReset + "\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion3 + codigoReset + "\n");

            System.out.print(espacio + "Aclaracion: Para que se guarde progreso de lo que haga, debe cerrar sesion, y poner 3, para salír. En caso contrario no se guardara su usuario ni las publicaciones que haya hecho.\n");


            ;

            do {
                boolean banderaAntiBolud = false;
                while (!banderaAntiBolud)
                {
                    try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                    {
                        System.out.print(espacio + opcion4);
                        respuesta = teclado.nextInt();
                        banderaAntiBolud = true;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.print("\n");
                        System.out.println(espacio +"Entrada inválida. Ingrese un numero entero por favor.");
                        teclado.next();
                    }
                }
                if (respuesta > 3 || respuesta < 0) {
                    System.out.print(espacio + "Elección no compatible, inténtelo de nuevo.\n\n");
                    System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion1 + codigoReset + "\n"); //opciones
                    System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion2 + codigoReset + "\n");
                    System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion3 + codigoReset + "\n");
                    System.out.print(espacio + opcion4);
                }
            }
            while (respuesta > 3 || respuesta < 0);

            if (respuesta == 1) //inicio de sesion, mail o dni. Con sus Excepciones correspondientes
            {
                usuarioRetorno = iniciarSesion();
            } else if (respuesta == 2) //crear cuenta y manda la cuenta a su correspondiente lista
            {
                crearCuenta();
                System.out.print(espacio + "Cuenta creada con éxito, ahora se le redirigirá a la página anterior para que pueda iniciar sesión con su cuenta.\n");
            }
            else {

                //convierto todo en un archivo una vez el usuario desea salir del programa
                this.tienda.toArchivo("archivoUsuarios","archivoPublicaciones");
                //convierto todas las publicaciones en un JSON
                try
                {
                    tienda.toJSONArray();
                }
                catch (JSONException e){};
                //salgo del programa
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                System.exit(0);
            }
        }
        while (respuesta == 2);

        return usuarioRetorno;
    }

    /**
     * Es un metodo llamado por paginaUno. Este pide los datos necesarios, para iniciar sesion, y a la vez tiene todas las verificaciones de seguridad necesarias
     * para que funcione correctamente
     * @return Returna el usuario que inicio sesion satisfactoriamente
     */
    private Usuario iniciarSesion()
    {
        String espacio = "                                                                           ";
        String ingresarDNIOMail = "";
        String contrasena = "";
        boolean existencia = false;
        Usuario auxUsuario = null;
        do {                                        //verificacion de que el usuario este intentando poner un mail o dni correcto.
            System.out.print(espacio + "Ingrese su Mail o DNI: ");
            ingresarDNIOMail= teclado.next();
            existencia = tienda.existeUsuario(ingresarDNIOMail); // es true si existe, si no existe es false
            if(!existencia)
            {
                System.out.print(espacio + "No existe usuario con ese mail / DNI. Inténtelo de nuevo.\n\n");
            }
        }
        while (!existencia); //mientras que no exista, le pedimos que lo ingrese nuevamente

        do
        {
            System.out.print(espacio + "Ingrese su contraseña: ");
            contrasena = teclado.next();
            auxUsuario = tienda.claveConfirmacion(ingresarDNIOMail,contrasena);
            if(auxUsuario == null) //si es null significa que la contrasena es incorrecta
            {
                System.out.print(espacio + "Contraseña incorrecta, inténtelo nuevamente.\n\n");
            }
        }
        while (auxUsuario == null);

        return auxUsuario;

    }

    /**
     * Es la interfaz que se usa a la hora de ingresar los datos necesarios para hacer el tipo de cuenta que el usuario desee. Una vez terminado el proceso
     * de creacion de cuenta, se redirige a paginaUno() para que el usuario pueda iniciar sesion con su cuenta.
     */
    private void crearCuenta()
    {
        String espacio = "                                                                           ";
        String mail = "";
        String contrasena = "";
        String contraAux = ""; //contrasena que ayuda a confirmar la contrasena real
        String nombre = "";
        String apellido = "";
        String numeroTelefono = "";
        String dni = "";
        String url = "";
        String cuit = "";
        E_CondFiscal  cond = null;
        int decitionUsuario = 0; //decision de que tipo de usuario va a ser, normal o solo venta
        E_TipoUsuario usuario = null;
        String decisionMonotributo = "";

        boolean banderaConfirmacionExcep = false; //es una bandera que es falsa mientras que haya excepciones, si no las hay, se vuelve true

        System.out.print(espacio + "ACLARACIONES ANTES DE CREAR LA CUENTA: \n" + espacio + "La cuenta es personal. Por lo que no se puede tener dos cuentas con el mismo mail, DNI o número telefónico.");
        System.out.print("\n");
        System.out.print(espacio + "La contraseña debe tener mínimo 8 dígitos, un caracter en mayúscula y un dígito numérico.\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        do {


            String patronEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com.*$"; //para que el usuario ingrese un formato mail
            boolean formatoCorrecto = false;
            while (!formatoCorrecto) {
                System.out.print(espacio + "Ingrese su mail: ");
                mail = teclado.next();

                if (mail.matches(patronEmail)) {
                    formatoCorrecto = true;
                } else {
                    System.out.println(espacio + "Formato de correo electrónico incorrecto. Inténtelo de nuevo.");
                }
            }

            System.out.print(espacio + "En caso de que la contrasena no cumpla con lo aclarado, al tratar de crear el usuario tendra que hacerlo denuevo.\n");
            do {
                System.out.print("\n");
                System.out.print(espacio + "Ingrese su contraseña: ");
                contrasena = teclado.next();

                System.out.print("\n");
                System.out.print(espacio + "Reingrese su contraseña: ");
                contraAux = teclado.next();


                if (!contrasena.equals(contraAux)) {
                    System.out.print("\n");
                    System.out.print(espacio + "No coinciden las contraseñas. Inténtelo de nuevo. \n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
            while (!contrasena.equals(contraAux));


            System.out.print("\n");
            System.out.print(espacio + "Ingrese su nombre: ");
            nombre = teclado.next();


            System.out.print("\n");
            System.out.print(espacio + "Ingrese su apellido: ");
            apellido = teclado.next();






            String patronNumero = "^\\d{10,}$"; // Patrón para 10 dígitos o más

            boolean formatoCorrectoNum = false;
            System.out.print("\n");
            teclado.nextLine();

            while (!formatoCorrectoNum) {

                System.out.print(espacio + "Ingrese su número de teléfono: ");
                numeroTelefono = teclado.next();
                if (numeroTelefono.matches(patronNumero)) {
                    formatoCorrectoNum = true;
                } else {
                    System.out.println(espacio + "Formato de número de teléfono incorrecto. Debe tener al menos 10 dígitos numericos\n");
                }
            }



//            System.out.print("\n");
//            System.out.print(espacio + "Ingrese su documento de identidad: ");
//            dni = teclado.next();

            String patronNumerodni = "^\\d{8,}$"; // Patrón para 10 dígitos o más

            boolean formatoCorrectodni = false;
            System.out.print("\n");
            teclado.nextLine();

            while (!formatoCorrectodni) {

                System.out.print(espacio + "Ingrese su documento de identidad: ");
                dni = teclado.next();
                if (dni.matches(patronNumerodni)) {
                    formatoCorrectodni = true;
                } else {
                    System.out.println(espacio + "Formato de DNI. Debe tener al menos 8 dígitos numericos.\n");
                }
            }


            System.out.print("\n");
            System.out.print(espacio + "Ahora elija si su cuenta va a ser compra y venta, o sólo especializada en venta. Ingrese opción 1 o 2. \n\n");
            System.out.print(espacio + "ACLARACIÓN \n" + espacio + "El usuario de compra y venta no podrá poner URl de su su pagina de venta.\n");
            System.out.print(espacio + "En el caso del usuario venta, no podrá comprar productos. A la vez también deberá agregar la URL de su página web de venta, su CUIT y la condiición fiscal.\n");
            System.out.print(espacio + "Todas las reglas mencionadas son obligatorias. En caso contrario, se podrá cerrar la cuenta de manera definitiva. \n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }

            do {
                System.out.print(espacio + "Opción: ");
                decitionUsuario = teclado.nextInt();
                if(decitionUsuario != 1 && decitionUsuario != 2)
                {
                    System.out.print(espacio + "Decisión errónea. Decida entre opción 1 u opción 2.\n");
                }
            }
            while (decitionUsuario != 1 && decitionUsuario != 2);

            if(decitionUsuario == 1)
            {
                usuario = E_TipoUsuario.NORMAL; //no hace falta mandarle mas cosas en caso de que sea un usuario normal
            }
            else
            {
                usuario = E_TipoUsuario.VENTA;

                System.out.print("\n");
                System.out.print(espacio + "Ingrese la URL de su página web de venta: ");
                url = teclado.next();




                String patronNumeroCuit = "^\\d{11,}$"; // Patrón para 10 dígitos o más

                boolean formatoCorrectoCuit = false;
                System.out.print("\n");
                teclado.nextLine();

                while (!formatoCorrectoCuit) {

                    System.out.print(espacio + "Ingrese su CUIT personal: ");
                    cuit = teclado.next();
                    if (cuit.matches(patronNumeroCuit)) {
                        formatoCorrectoCuit = true;
                    } else {
                        System.out.println(espacio + "Formato de CUIT. Debe tener al menos 11 dígitos numericos.\n");
                    }
                }





                System.out.print("\n");
                do {
                    System.out.print(espacio + "Ingrese su tipo de monotributo. A-F: ");
                    decisionMonotributo = teclado.next();

                    if (!decisionMonotributo.equals("A") && !decisionMonotributo.equals("B") && !decisionMonotributo.equals("C") && !decisionMonotributo.equals("D") && !decisionMonotributo.equals("E") && !decisionMonotributo.equals("F"))
                    {
                        System.out.print(espacio + "Error. Por favor introduzca una respuesta válida. \n");
                    }
                }
                while (!decisionMonotributo.equals("A") && !decisionMonotributo.equals("B") && !decisionMonotributo.equals("C") && !decisionMonotributo.equals("D") && !decisionMonotributo.equals("E") && !decisionMonotributo.equals("F"));

                if(decisionMonotributo.equals("A"))
                {
                    cond = E_CondFiscal.MONOTRIBUTO_A;
                }
                else if(decisionMonotributo.equals("B"))
                {
                    cond = E_CondFiscal.MONOTRIBUTO_B;

                }
                else if(decisionMonotributo.equals("C"))
                {
                    cond = E_CondFiscal.MONOTRIBUTO_C;

                }
                else if(decisionMonotributo.equals("D"))
                {
                    cond = E_CondFiscal.MONOTRIBUTO_D;

                }
                else if(decisionMonotributo.equals("E"))
                {
                    cond = E_CondFiscal.MONOTRIBUTO_E;

                }
                else
                {
                    cond = E_CondFiscal.MONOTRIBUTO_F;

                }

            }

            try
            {
                if(decitionUsuario == 1) //tipo de usuario normal
                {
                    tienda.agregar(mail,contrasena,nombre,apellido,numeroTelefono,usuario,dni);
                }
                else //tipo de usuario solo venta
                {
                    tienda.agregar(mail,contrasena,nombre,apellido,numeroTelefono,usuario,dni,url,cuit,cond);
                }
                banderaConfirmacionExcep = true;
            }
            catch (ExcepcionClaveDuplicada e){
                System.out.print(espacio + e.getMessage() +"\n");
            }
            catch (ExcepcionMailYaExiste e ){
                System.out.print(espacio + e.getMessage() +"\n");
            }
            catch (ExcepcionConstrasenaInvalida e){
                System.out.print(espacio + e.getMessage() +"\n");
            }
            catch (ExcepcionNumeroRepetido e){
                System.out.print(espacio + e.getMessage() +"\n");
            };

        }
        while (!banderaConfirmacionExcep);

        System.out.print("\n\n");
        System.out.print(espacio + "Felicitaciones! Ha creado su cuenta con éxito.\n");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Este metodo es la interfaz que se muestra de la aplicacion una vez el usuario se pudo loguear con exito a la publicacion
     * Cada opcion elegida por el usuario, lo va a redirigir al metodo necesario. Este metodo necesario tendra la interfaz de esa opcion,
     * y a la vez las utilidades necesarias para manipular usuarios, y publicaciones.
     *
     * @param usuario Usuario que se logueo en la aplicacion
     */
    private void paginaDos(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        System.out.print("\n\n");
        String bienvenidos = "Bienvenido " + usuario.getNombre() + ", este es tu menú principal\n\n";
        String opcionUno = "1- Ver publicaciones activas";
        String opcionDos = "2- Crear publicación";
        String opcionTres = "3- Ver mi carrito";
        String opcionCuatro = "4- Favoritos";
        String opcionCinco = "5- Mi cuenta"; //dentro de aca va a haber misPublicaciones, misVentas, misCompras, misDirecciones, misMetodos de pago, agregar direccion, agregar metodo de pago, verificar cuenta,
        String opcionSeis = "6- Cerrar sesión";


        String opcionElecc = "Elección: ";

        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + bienvenidos + codigoReset);
        System.out.print("\n\n\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCuatro + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCinco + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionSeis + codigoReset + "\n");

        int decision = 0;

        do {
            boolean banderaAntiBolud = false;
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + opcionElecc);
                    decision = teclado.nextInt();
                    banderaAntiBolud = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.print("\n");
                    System.out.println(espacio +"Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }

            if (decision > 6 || decision < 1) {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");

                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCuatro + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCinco + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionSeis + codigoReset + "\n");
                System.out.print(espacio + opcionElecc);
            }
        }
        while (decision > 6 || decision < 1);

        if(decision == 1)
        {
            System.out.print(espacio + "Cargando publicaciones...\n");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            verPublicacionesActivas(usuario);
        }
        else if(decision == 2)
        {            System.out.print(espacio + "Cargando...\n");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            crearPublicacion(usuario);
        }
        else if(decision == 3)
        {
            if(usuario instanceof UsuarioVenta) // no puede agregar nada al carrito
            {
                System.out.print(espacio + "Tu tipo de usuario no permite que entres a ver tu carrito.\n");
                paginaDos(usuario);
            }
            carrito(usuario);
        }
        else if(decision == 4)
        {
            if(usuario instanceof UsuarioVenta) // no puede agregar nada al carrito
            {
                System.out.print(espacio + "Tu tipo de usuario no te permite tener favoritos.\n");
                paginaDos(usuario);
            }
            favoritos(usuario);
        }
        else if(decision == 5)
        {
            miCuenta(usuario);
        }
        else
        {
            menuPrincipal();
        }




    }

    /**
     * Este metodo es la opcion 1 de paginaDos(). Esta opcion te muestra todas las publicaciones activas de la aplicacion. A la vez
     * te permite volver a la paginaDos(), agregar una publicacion al carrito (Una vez se agregar al carrito, se disminuye el stock del producto)
     *  y agregar una publicacion a favoritos
     *
     *
     * @param usuario Es el usuario logueado, se necesita para verificaciones dentro del programa
     */
    private void verPublicacionesActivas(Usuario usuario)
    {
        UsuarioNormal usuarioNormal = null;

        if(usuario instanceof  UsuarioNormal) {
             usuarioNormal = (UsuarioNormal) usuario;
        }

        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";


         HashMap<String,Publicacion> publicaciones = recorrerPublicaciones();
        int rta = -1;
        String opcion = "";
        System.out.print(espacio + "1 para volver al menú anterior, 2 para agregar un producto al carrito, o 3 para agregar una publicacion a favoritos. " + codigoNegrita + codigoSubrayado + codigoReset + "\n");

        do
        {

            boolean banderaAntiBolud = false;
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Opción: ");
                    rta = teclado.nextInt();
                    banderaAntiBolud = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.print("\n");
                    System.out.println(espacio +"Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }

            teclado.nextLine();
            if (rta != 1 && rta != 2 && rta != 3)
            {
                System.out.print(espacio + "Elección no comprendida, inténtelo de nuevo.\n\n");
            }
        }
        while (rta != 1 && rta != 2 && rta != 3);

        if(rta == 1)
        {
            paginaDos(usuario);
        }
        else if(rta == 2)
        {
            if(usuario instanceof UsuarioVenta) // no puede agregar nada al carrito
            {
                System.out.print(espacio + "Tu tipo de usuario no permite que agregues un producto al carrito.\n");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                paginaDos(usuario);
            }
            Publicacion pub = null;
            do
            {
                System.out.print(espacio + "Nombre del producto que desea agregar al carrito: ");
                opcion = teclado.nextLine();
                pub = busquedaPublicacionPorNombre(publicaciones,opcion);

                if(pub == null) //significa que no lo encontro
                {
                    System.out.print("\n" + espacio + "Nombre incorrecto, pruebe con alguno de los nombres de la lista.\n");
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
            while(pub == null);

            if(pub.getDueno().equals(usuario))
            {
                System.out.print(espacio + "Error, no podes agregar al carrito una publicacion que es tuya.\n");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                paginaDos(usuario);
            }

            //System.out.print("\nARRIBA DE AGREGARCARRITO\n\n");
            usuarioNormal.agregarCarrito(pub);
            pub.setStock(pub.getStock() - 1);
            System.out.print(espacio + pub.getNombrePublicacion() + " Agregado con exito\n");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            paginaDos(usuario);
        }
        else
        {
            if(usuario instanceof UsuarioVenta) // no puede agregar nada al carrito
            {
                System.out.print(espacio + "Tu tipo de usuario no permite que agregues un producto a favoritos.\n");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                paginaDos(usuario);
            }
            Publicacion pub = null;
            do
            {
                System.out.print(espacio + "Nombre del producto que desea agregar a favoritos: ");
                opcion = teclado.nextLine();
                pub = busquedaPublicacionPorNombre(publicaciones,opcion);

                if(pub == null) //significa que no lo encontro
                {
                    System.out.print("\n" + espacio + "Nombre incorrecto, pruebe con alguno de los nombres de la lista.\n");
                }
            }
            while(pub == null);

            if(pub.getDueno().equals(usuario))
            {
                System.out.print(espacio + "Error, no podes agregar a favoritos una publicacion que es tuya.\n");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                paginaDos(usuario);
            }


            usuarioNormal.agregarFavorito(pub);
            System.out.print(espacio + pub.getNombrePublicacion() + " Agregado con exito a tus favoritos\n");
            try {
                Thread.sleep(1400);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            paginaDos(usuario);
        }

    }

    /**
     * Es un metodo simple, que se usa en otras funciones. Recorre el mapa pasado por parametro, y compara cada uno de sus elementos con
     * el nombre pasado por parametro. Esto se usa cada vez que se quiere buscar una públicacion por un nombre en especifico
     * @param mapa coleccion de Publicaciones a recorrer
     * @param nombre Nombre que se quiere buscar
     * @return Returna la publicacion que tenia el nombre pasado por parametro. En caso de que el nombre no este, returna null
     */
    private Publicacion busquedaPublicacionPorNombre(HashMap<String,Publicacion> mapa, String nombre)
    {
        Publicacion pub = null;
        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();
        while (it.hasNext())
        {
            Publicacion aux = null;
            Map.Entry<String,Publicacion> entry = it.next();
             aux = entry.getValue();
             if(aux.getStock() >= 1) {


                 if (aux.getNombrePublicacion().equals(nombre)) {
                     pub = aux;
                     return pub;
                 }
             }
        }
        return pub;
    }

    /**
     * Metodo usado en otras funciones, sirve para recorrer todas las publicaciones de la tienda.
     * @return Returna el mapa que tuvo que recorrer.
     */
    private HashMap<String,Publicacion> recorrerPublicaciones()
    {
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        GeneDosPU<String,Publicacion> lista = tienda.getPublicaciones();
        HashMap<String,Publicacion> mapa = lista.getMapa(); //obtengo el mapa para poder recorrerlo y mostrar todas las publicaciones

        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();


        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion pub  = entry.getValue();
            Periferico aux = pub.getPeriferico();
            Usuario user = pub.getDueno();


            if(pub.getStock() >= 1)
            { // muestro solo los productos que tienen stock

                if(user instanceof UsuarioNormal) {
                    System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + pub.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
                    System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + pub.getUrlFoto() + codigoReset + "\n");
                    System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + pub.getPrecio() + codigoReset + "\n");
                    System.out.print(espacio + "Stock: " + codigoNegrita + codigoSubrayado + pub.getStock() + codigoReset + "\n");
                    System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + aux.isEstado() + codigoReset + "\n");
                    System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + pub.getDueno().getNombre() + codigoReset + "\n");
                }else
                {
                    UsuarioVenta ventaUser = (UsuarioVenta)user;
                    System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + pub.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
                    System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + pub.getUrlFoto() + codigoReset + "\n");
                    System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + pub.getPrecio() + codigoReset + "\n");
                    System.out.print(espacio + "Stock: " + codigoNegrita + codigoSubrayado + pub.getStock() + codigoReset + "\n");
                    System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + aux.isEstado() + codigoReset + "\n");
                    System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + pub.getDueno().getNombre() + codigoReset + "\n");
                    System.out.print(espacio + "URL Comercial del vendedor: " + codigoNegrita + codigoSubrayado + ventaUser.getUrl() + codigoReset + "\n");

                }
            }
        }
        return mapa;
    }

    /**
     * Metodo que es el que hace que un usuario pueda crear una publicacion. Este pide por parametro el usuario que va a crear la publicacion,
     * y dentro del metodo, pide todo los datos necesarios para que se pueda hacer una publiacion. Tambie tiene verificaciones que se les hace
     * a los usurios.
     * @param usuario Usuario que va a crear la publicacion
     */
    private void crearPublicacion(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String nombrePublicacion = "";
        float precio = 0;
        int stock = 0; //contrasena que ayuda a confirmar la contrasena real
        String urlFoto = "";
        ArrayList<Envio> envio = new ArrayList<>();

        int decition = 0;
        float precioEnvio = 0;
        E_Envio tipoEnvio = null;
        boolean express = false;
        int decitionDos = 0;

        String nombrePeri = "";
        E_Estado tipoEstado = null;
        int deciEstado = 0;
        String marca = "";
        String modelo = "";
        String origenPais = "";
        String plataformasDisponibles = "";
        String color = "";
        float peso = 0;
        boolean inalambrico = false;
        int deciInalambrico = 0;

        boolean banderaAntiBolud = false;


        if(!usuario.tieneDebitoYcredito())
        {
            System.out.print(espacio + "Necesitas agregar por lo menos una tarjeta de debito y una de credito para poder crear una publicacion.\n");
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            paginaDos(usuario);

        }


        System.out.print("\n");
        String mensaje = " Van a completar los siguientes datos para crear su publicacion";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensaje + codigoReset);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        System.out.print("\n");
        teclado.nextLine();

        System.out.print(espacio+ "Nombre de la publicacion: ");
        nombrePublicacion = teclado.nextLine();
        System.out.print("\n");


        while (!banderaAntiBolud) {
            try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
            {
                System.out.print(espacio + "Precio: ");
                precio = teclado.nextFloat();
                banderaAntiBolud = true;
            } catch (InputMismatchException e) {
                System.out.print("\n");
                System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                teclado.nextLine();
            }
        }
        banderaAntiBolud  =false;
        teclado.nextLine(); // Consumir el carácter de salto de línea pendiente
        System.out.print("\n");




        while (!banderaAntiBolud) {
            try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
            {
                System.out.print(espacio + "Cantidad disponible: ");
                stock = teclado.nextInt();
                banderaAntiBolud = true;
            } catch (InputMismatchException e) {
                System.out.print("\n");
                System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                teclado.next();
            }
        }
        banderaAntiBolud  =false;
        System.out.print("\n");

        System.out.print(espacio + "Foto (URL de la foto): ");
        urlFoto = teclado.next();
        System.out.print("\n");

        System.out.print(espacio + "Ahora va a agregar los tipos de envio que va a aceptar." + "\n" + espacio +"Obligatoriamente va a tener que tener un envio como minimo.");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        do {
            System.out.print("\n");

            do {
                while (!banderaAntiBolud)
                {
                    try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                    {
                        System.out.print(espacio + "Tipo de envio(1-Tierra, 2-Aereo, 3-Maritimo):  ");
                        decition = teclado.nextInt();
                        banderaAntiBolud = true;
                    } catch (InputMismatchException e) {
                        System.out.print("\n");
                        System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                        teclado.next();
                    }
                }
                banderaAntiBolud  =false;

                if((decition < 1 || decition > 3))
                {
                    System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
                }
            }
            while(decition < 1 || decition > 3);

            if(decition == 1)
            {
                tipoEnvio = E_Envio.Tierra;
                express = false;
            }
            else if(decition == 2)
            {
                tipoEnvio = E_Envio.Avion;
                express = true;
            }
            else
            {
                tipoEnvio = E_Envio.Barco;
                express = true;
            }

            System.out.print(espacio + "Contacte con su empresa de envios para saber el precio exacto que el comprador debera pagar por este.");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            System.out.print("\n");


            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Precio: ");
                    precioEnvio = teclado.nextFloat();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;


            Envio aux = new Envio(express,precioEnvio,tipoEnvio);
            envio.add(aux);


            System.out.print("\n");
            do {
                while (!banderaAntiBolud)
                {
                    try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                    {
                        System.out.print(espacio + "¿Desea agregar otro tipo de envio? 1-Si 2-No:  ");
                        decitionDos = teclado.nextInt();
                        banderaAntiBolud = true;
                    } catch (InputMismatchException e) {
                        System.out.print("\n");
                        System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                        teclado.next();
                    }
                }
                banderaAntiBolud  =false;

                System.out.print("\n");
                if(decitionDos < 1 || decitionDos > 2)
                {
                    System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
                }
            }
            while(decitionDos < 1 || decitionDos > 2);
        }
        while(decitionDos == 1);

        System.out.print("\n");
        String mensajeAux = " Excelente! Ahora vamos a completar una serie de datos adicionales de tu periferico...";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeAux + codigoReset);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }

        System.out.print("\n");
        teclado.nextLine();
        System.out.print(espacio + "Nombre del Periferico: ");
        nombrePeri = teclado.nextLine();

        System.out.print("\n");
        //teclado.nextLine();
        do
        {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Estado(1-Nuevo, 2-Usado): ");
                    deciEstado = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;

            if(deciEstado < 1 || deciEstado > 2)
            {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
            }
        }
        while (deciEstado < 1 || deciEstado > 2);

        if(deciEstado == 1)
        {
            tipoEstado = E_Estado.NUEVO;
        }
        else
        {
            tipoEstado = E_Estado.USADO;
        }

        System.out.print("\n");
        teclado.nextLine();
        System.out.print(espacio + "Marca: ");
        marca = teclado.nextLine();

        System.out.print("\n");
        System.out.print(espacio + "Modelo: ");
        modelo = teclado.nextLine();

        System.out.print("\n");
        System.out.print(espacio + "Pais de origen: ");
        origenPais = teclado.nextLine();

        System.out.print("\n");

        System.out.print(espacio + "Plataformas: ");
        plataformasDisponibles = teclado.nextLine();

        System.out.print("\n");
        System.out.print(espacio + "Color/es: ");
        color = teclado.nextLine();

        System.out.print("\n");

        while (!banderaAntiBolud)
        {
            try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
            {
                System.out.print(espacio + "Peso: ");
                peso = teclado.nextFloat();
                banderaAntiBolud = true;
            } catch (InputMismatchException e) {
                System.out.print("\n");
                System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                teclado.next();
            }
        }
        banderaAntiBolud  =false;



        System.out.print("\n");
        teclado.nextLine();
        do
        {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Inalambrico(1-Si, 2-No): ");
                    deciInalambrico = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;
            if(deciInalambrico < 1 || deciInalambrico > 2)
            {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
            }
        }
        while (deciInalambrico < 1 || deciInalambrico > 2);
        if(deciInalambrico == 1)
        {
            inalambrico = true;
        }
        else
        {
            inalambrico = false;
        }

        //se agrega la publicacion
        Publicacion creada = tienda.agregarPublicacion(nombrePublicacion,nombrePeri,tipoEstado,marca,modelo,origenPais,plataformasDisponibles,color,peso,inalambrico,precio,stock,usuario,urlFoto);
        for(int i = 0; i < envio.size(); i++) //se agregan todos los envios que quiso el usuario
        {
             tienda.agregarEnvioPublicacion(creada,envio.get(i));
        }

        System.out.print("\n");
        String mensajeAuxDos = " Publicacion agregada con exito! Ahora va a volver a la pagina su menu principal..";
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeAuxDos + codigoReset);
        System.out.print("\n");
        paginaDos(usuario);
    }

    /**
     * Apartado de eleccion numero 5 de paginaDos(). Este es la interfaz del usuario, en donde este puede elegir distintas opciones
     * como cambiar contraseña, ver sus compras o sus ventas, etc.
     * @param usuario Usuario al que se va acceder a sus datos
     */
    private void miCuenta(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";
        boolean banderaAntiBolud = false;

        System.out.print("\n\n");
        String bienvenidos = usuario.getNombre() + ", este es el menu de tu cuenta\n\n";
        String opcionUno = "1- Mis Metodos de pago";
        String opcionDos = "2- Mis Direcciones";
        String opcionTres = "3- Mis compras";
        String opcionCuatro = "4- Mis ventas";
        String opcionCinco = "5- Cambiar contrasena"; //dentro de aca va a haber misPublicaciones, misVentas, misCompras, misDirecciones, misMetodos de pago, agregar direccion, agregar metodo de pago, verificar cuenta,
        String opcionSeis = "6- Volver al menu anterior";


        String opcionElecc = "Eleccion: ";

        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + bienvenidos + codigoReset);
        System.out.print("\n\n\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCuatro + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCinco + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionSeis + codigoReset + "\n");

        int decision = 0;

        do {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + opcionElecc);
                    decision = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;


            if (decision > 6 || decision < 1) {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCuatro + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCinco + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionSeis + codigoReset + "\n");
                System.out.print(espacio + opcionElecc);
            }
        }
        while (decision > 6 || decision < 1);

        if(decision == 1)
        {
            metodosDePago(usuario);
        }
        else if(decision == 2)
        {
            direcciones(usuario);
        }
        else if(decision == 3)
        {
            if(usuario instanceof UsuarioVenta) // no puede agregar nada al carrito
            {
                System.out.print(espacio + "Tu tipo de usuario no permite que compres.\n");
                paginaDos(usuario);
            }
            mostrarCompras(usuario);
        }
        else if(decision == 4)
        {
            mostrarVentas(usuario);
        }
        else if(decision == 5)
        {
            cambiarContrasena(usuario);
        }
        else
        {
            paginaDos(usuario);
        }
    }

    /**
     * Es un metodo el cual muestra los metodos de pago que tiene disponibles un usuario, y a la vez tambien le deja agregar y borrar
     * metodos de pago
     * @param usuario Usuario al que se va acceder a sus metodos de pago
     */
    private void metodosDePago(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";
        boolean banderaAntiBolud = false;


        System.out.print("\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + "Sus metodos de pago" + codigoReset);

        GeneUnoDM<MetodoDePago> metodos = usuario.getMetodosDePago(); //para recorrer los metodos
        HashSet<MetodoDePago> set = metodos.getSet();
        Iterator<MetodoDePago> iterator = set.iterator();

        if(!iterator.hasNext())
        {
            System.out.print("\n");
            System.out.print(espacio + "No tiene metodos de pago agregados");
            System.out.print("\n");
        }
        else
        {

            System.out.print("\n");
            System.out.print("\n");
            while(iterator.hasNext())
            {
                MetodoDePago aux = iterator.next();
                System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n");
                System.out.print(espacio + "Tipo de tarjeta: " + aux.getTipoPago());
                System.out.print("\n");
                System.out.print(espacio +"Tarjeta: " + aux.getUltimosCuatroTargeta());
                System.out.print("\n");
                System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

        }

        String opcionUno = "1- Agregar metodo de pago";
        String opcionDos = "2- Borrar metodo de pago";
        String opcionTres = "3- Volver al menu anterior";
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }



        String opcionElecc = "Eleccion: ";

        System.out.print("\n\n\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }


        int decision = 0;

        do {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + opcionElecc);
                    decision = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;
            if (decision > 6 || decision < 1) {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
                System.out.print(espacio + opcionElecc);
            }
        }
        while (decision > 3 || decision < 1);

        if(decision == 1)
        {
            agregarMetodoDePago(usuario);
        }
        else if(decision == 2)
        {
            borrarMetodoDePago(usuario);
        }
        else
        {
            miCuenta(usuario);
        }

    }

    /**
     * Metodo usado en metodosDePago, hace que se le pueda agregar un metodo de pago al usuario.
     * @param usuario Usuario al que se le va a agregar un metodo de pago
     */
    private void agregarMetodoDePago(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String nombre = "";
        E_Pago tipoPago = null;
        int decitionTipoPago = 0;
        String numero = "";
        String cvv = "";
        String mesVencimiento = "";
        String anoVencimiento = "";

        boolean banderaAntiBolud = false;



        System.out.print("\n");
        String mensaje = " Van a completar los siguientes datos para agregar su metodo de pago";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensaje + codigoReset);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        System.out.print("\n");
        teclado.nextLine();

        System.out.print(espacio+ "Nombre de la tarjeta: ");
        nombre = teclado.nextLine();
        System.out.print("\n");

        do
        {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio+ "Tipo de tarjeta 1-Debito 2-Credito: ");
                    decitionTipoPago = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;

            teclado.nextLine();
            System.out.print("\n");

            if(decitionTipoPago < 1 || decitionTipoPago > 2)
            {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
            }
        }
        while (decitionTipoPago < 1 || decitionTipoPago > 2);
        if(decitionTipoPago == 1)
        {
            tipoPago = E_Pago.DEBITO;
        }
        else
        {
            tipoPago = E_Pago.CREDITO;

        }

        System.out.print(espacio + "Numero: ");
        numero = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "CVV: ");
        cvv = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "Mes de vencimiento: ");
        mesVencimiento = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "Ano vencimiento: ");
        anoVencimiento = teclado.nextLine();
        System.out.print("\n");

        MetodoDePago nuevo = new MetodoDePago(tipoPago,nombre,numero,cvv,mesVencimiento,anoVencimiento);

        usuario.agregarMetodoDePago(nuevo); //se agrega el metodo de pago al usuario

        metodosDePago(usuario);
    }

    /**
     * Metodo usado en metodosDePago, hace que se le pueda borrar un metodo de pago al usuario.
     * @param usuario Usuario al que se le va a borrar un metodo de pago
     */
    private void borrarMetodoDePago(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String cuatroUlt = " ";
        boolean bandera = false;

        System.out.print(espacio + "Decime los ultimos 4 numeros de la tarjeta que desea borrar: ");
        cuatroUlt = teclado.next();

        GeneUnoDM<MetodoDePago> metodos = usuario.getMetodosDePago(); //para recorrer los metodos
        HashSet<MetodoDePago> set = metodos.getSet();
        Iterator<MetodoDePago> iterator = set.iterator();

        while (iterator.hasNext())
        {
            MetodoDePago aux = iterator.next();
            if(aux.getUltimosCuatroTargeta().substring(aux.getUltimosCuatroTargeta().length() - 4).equals(cuatroUlt.substring(cuatroUlt.length() - 4)))
            {
                usuario.borrarMetodoDePago(aux);
                System.out.print("\n");
                System.out.print(espacio + "Borrado con exito.\n\n");
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                bandera = true;
            }
        }
        if(!bandera) {


            System.out.print("\n");
            System.out.print(espacio + "No se encontro un metodo de pago con esas caracteristicas\n\n");
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            System.out.print("\n");
        }


        metodosDePago(usuario);
    }
    /**
     * Usado en miCuenta(), hace que se muestren todas las direcciones del usuario, y a la vez que este pueda agreagar o borrar una direccion.
     *
     * @param usuario Usuario al que se le va a mostrar, y/o modificar una direccion.
     */
    private void direcciones(Usuario usuario)
    {

        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        boolean banderaAntiBolud = false;


        System.out.print("\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + "Sus direcciones " + codigoReset);

        GeneUnoDM<Direccion> metodos = usuario.getDirecciones(); //para recorrer los metodos
        HashSet<Direccion> set = metodos.getSet();
        Iterator<Direccion> iterator = set.iterator();

        if(!iterator.hasNext())
        {
            System.out.print("\n");
            System.out.print(espacio + "No tiene Direcciones agregadas");
            System.out.print("\n");
        }
        else
        {

            System.out.print("\n");
            System.out.print("\n");
            while(iterator.hasNext())
            {
                Direccion aux = iterator.next();
                System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n");
                System.out.print(espacio + "Provincia: " + aux.getProvincia());
                System.out.print("\n");
                System.out.print(espacio +"Ciudad: " + aux.getCiudad());
                System.out.print("\n");
                System.out.print(espacio +"Calle: " + aux.getCalle());
                System.out.print("\n");
                System.out.print(espacio +"Altura: " + aux.getAltura());
                System.out.print("\n");
                System.out.print(espacio +"Departamento: " + aux.getDepartamento());
                System.out.print("\n");
                System.out.print(espacio +"Codigo postal: " + aux.getCp());
                System.out.print("\n");
                System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

        }

        String opcionUno = "1- Agregar direccion";
        String opcionDos = "2- Borrar direccion";
        String opcionTres = "3- Volver al menu anterior";



        String opcionElecc = "Eleccion: ";

        System.out.print("\n\n\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }

        int decision = 0;

        do {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + opcionElecc);
                    decision = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;

            if (decision > 3 || decision < 1) {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
                System.out.print(espacio + opcionElecc);
            }
        }
        while (decision > 3 || decision < 1);

        if(decision == 1)
        {
            agregarDireccion(usuario);
        }
        else if(decision == 2)
        {
            borrarDireccion(usuario);
        }
        else
        {
            miCuenta(usuario);
        }
    }
    /**
     * Usado en direcciones(), hace que se pueda agregar una direccion al usuario.
     *
     * @param usuario Usuario al que se le va a agregar una direccion.
     */
    private void agregarDireccion(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String provincia = "";
        String ciudad = "";
        String calle = "";
        String altura = "";
        String departamento = "";
        String cp = "";



        System.out.print("\n");
        String mensaje = " Van a completar los siguientes datos para agregar su direccion";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensaje + codigoReset);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        System.out.print("\n");
        teclado.nextLine();

//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            System.out.print(e.getMessage());
//        }

        System.out.print(espacio+ "Provincia: ");
        provincia= teclado.nextLine();
        System.out.print("\n");


        System.out.print(espacio + "Ciudad: ");
        ciudad = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "Calle: ");
        calle = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "Altura: ");
        altura = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "Departamento (Si es una casa ponga 0): ");
        departamento = teclado.nextLine();
        System.out.print("\n");

        System.out.print(espacio + "Codigo Postal: ");
        cp = teclado.nextLine();
        System.out.print("\n");

        Direccion nuevo = new Direccion(provincia,ciudad,calle,altura,departamento,cp);

        usuario.agregarDireccion(nuevo); //se agrega el metodo de pago al usuario

        direcciones(usuario);
    }
    /**
     * Usado en direcciones(), hace que se pueda borrar una direccion al usuario.
     *
     * @param usuario Usuario al que se le va a borrar una direccion.
     */
    private void borrarDireccion(Usuario usuario) {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String calle = "";
        String altura = "";
        boolean bandera = false;

        System.out.print(espacio + "Decime la calle y altura de la direccion que queres borrar. \n");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        System.out.print(espacio + "Calle: ");
        calle = teclado.next();

        System.out.print("\n");
        teclado.nextLine();

        System.out.print(espacio + "Altura: ");
        altura = teclado.next();
        System.out.print("\n");


        GeneUnoDM<Direccion> metodos = usuario.getDirecciones(); //para recorrer los metodos
        HashSet<Direccion> set = metodos.getSet();
        Iterator<Direccion> iterator = set.iterator();

        while (iterator.hasNext()) {
            Direccion aux = iterator.next();
            if (aux.getCalle().equals(calle) && aux.getAltura().equals(altura)) {
                usuario.borrarDireccion(aux);
                System.out.print("\n");
                System.out.print(espacio + "Borrado con exito.\n\n");
                bandera = true;
            }
        }
        if (!bandera) {
            System.out.print("\n");
            System.out.print(espacio + "No se encontro una direccion con esas caracteristicas\n\n");
            System.out.print("\n");
        }

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            System.out.print(e.getMessage());
//        }
        direcciones(usuario);
    }

    /**
     * Apartado en miCuenta(), el cual muestra todas las compras del usuario pasado por parametro.
     * @param usuario Usuario al que se le van a recorrer las compras
     */
    private void mostrarCompras(Usuario usuario)
    {
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        UsuarioNormal user = (UsuarioNormal)usuario; //por que no se va a poder mostrar compras del UsuarioVenta ya que no tiene
        Compra comprasHechas = user.getCompras();

        GeneDosPU<String,Publicacion> gene = comprasHechas.getPublicaciones();
        HashMap<String,Publicacion> mapa = gene.getMapa();
        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();

        if(!it.hasNext())
        {
            System.out.print("\n");
            System.out.print(espacio + "No tiene compras registradas en esta cuenta." + codigoNegrita + codigoSubrayado + codigoReset + "\n");
            System.out.print("\n");
        }
        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion aux = entry.getValue();
            Periferico auxPer = aux.getPeriferico();

            System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + aux.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
            System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + aux.getUrlFoto() + codigoReset + "\n");
            System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + aux.getPrecio() + codigoReset + "\n");
            System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + aux.getDueno().getNombre() + codigoReset + "\n");
            System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + auxPer.isEstado() + codigoReset + "\n");
        }
        System.out.print("\n\n\n");
        System.out.print(espacio + "Total Gastado (sin contar envios): " + codigoNegrita + codigoSubrayado + comprasHechas.getTotalGastado() + codigoReset + "\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }

        miCuenta(usuario);
    }

    /**
     * Apartado en miCuenta(), el cual va s mostrar todas las ventas que tuvo el usuario pasado por parametro.
     * @param usuario Usuario al que se le van a recorrer las ventas que hizo.
     */
    private void mostrarVentas(Usuario usuario)
    {
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";


        Venta ventasHechas = usuario.getVentas();


        GeneDosPU<String,Publicacion> gene = ventasHechas.getPublicaciones();
        HashMap<String,Publicacion> mapa = gene.getMapa();
        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();


        if(!it.hasNext())
        {
            System.out.print("\n");
            System.out.print(espacio + "No tiene ventas registradas en esta cuenta." + codigoNegrita + codigoSubrayado + codigoReset + "\n");
            System.out.print("\n");
        }

        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion aux = entry.getValue();
            Periferico auxPer = aux.getPeriferico();


            System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + aux.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
            System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + aux.getUrlFoto() + codigoReset + "\n");
            System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + aux.getPrecio() + codigoReset + "\n");
            System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + aux.getDueno().getNombre() + codigoReset + "\n");
            System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + auxPer.isEstado() + codigoReset + "\n");
        }
        System.out.print(espacio + "Total Ganado: " + codigoNegrita + codigoSubrayado + ventasHechas.getTotalRecaudado() + codigoReset + "\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }

        miCuenta(usuario);
    }

    /**
     * Apartado en miCuenta(), el cual sirve para que el usuario pueda cambiar su contrasena. Se tiene que saber su anterior contrasena.
     * @param usuario Usuario al que se le va a cambiar la contrasena
     */
    private void cambiarContrasena(Usuario usuario)
    {
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String nuevaContra = "";
        String verificacion = "";

        System.out.print("\n");
        String mensaje = "Para cambiarla vas a tener conocer tu contrasena anterior.";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensaje + codigoReset);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        System.out.print("\n");

        System.out.print("\n");
        String mensajeDos = "Contrasena vieja: ";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeDos + codigoReset);
        teclado.nextLine();
        String contraVieja = teclado.next();

        if(usuario.getContrasena().equals(contraVieja))
        {
            do
            {
                System.out.print("\n\n");
                System.out.print("\n");
                String mensajeTres = "Nueva contrasena: ";
                System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeTres + codigoReset);
                teclado.nextLine();
                nuevaContra = teclado.next();
                System.out.print("\n");


                String mensajeCinco = "Repite la contrasena: ";
                System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeCinco + codigoReset);
                teclado.nextLine();
                verificacion = teclado.next();

                if(!nuevaContra.equals(verificacion))
                {
                    String mensajeAdv = "Las contrasenas no coinciden, por favor intentelo denuevo.";
                    System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeAdv + codigoReset);
                }
            }
            while (!nuevaContra.equals(verificacion));

            usuario.setContrasena(nuevaContra);
            String mensajeExito = "Cambiaste tu contrasena con exito.";
            System.out.print("\n\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeExito + codigoReset);
        }
        else
        {
            System.out.print("\n");
            String mensajeCuatro= "Contrasena incorrecta, intentelo denuevo.";
            System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeCuatro + codigoReset);
        }
        miCuenta(usuario);
    }

    /**
     * Opcion a elegir en paginaDos(), esta opcion te va a mostrar todas las publicaciones en el carrito. A la vez te da la opcion de comprar
     * UNA publicacion que hay en el carrito.
     * @param usuario
     */
    private void carrito(Usuario usuario)
    {
        UsuarioNormal usuarioNormal = (UsuarioNormal)usuario;
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        boolean banderaAntiBolud= false;


        HashMap<String,Publicacion> mapa = recorrerCarrito(usuario); //se recorre el carrito
        Iterator<Map.Entry<String,Publicacion>> iterator = mapa.entrySet().iterator();
        int rta = -1;
        String opcion = "";
        System.out.print(espacio + "1 para volver al menú anterior, 2 para comprar lo que hay en el carrito, 3 para borrar una publicacion del carrito " + codigoNegrita + codigoSubrayado + codigoReset + "\n");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        do
        {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Opción: ");
                    rta = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;
            teclado.nextLine();
            if (rta != 1 && rta != 2 && rta != 3)
            {
                System.out.print(espacio + "Elección no comprendida, inténtelo de nuevo.\n\n");
            }
        }
        while (rta != 1 && rta != 2 && rta != 3);

        if(rta == 1)
        {
            paginaDos(usuario);
        }
        else if (rta == 2)
        {
            if(!iterator.hasNext())
            {
                System.out.print("\n");
                System.out.print(espacio + "No podes comprar, el carrito esta vacio.\n");
                paginaDos(usuario);
            }

        comprarProductos(usuarioNormal,mapa);
        }
        else
        {
            if(!iterator.hasNext())
            {
                System.out.print("\n");
                System.out.print(espacio + "No podes comprar, el carrito esta vacio.\n");
                paginaDos(usuario);
            }
        borrarPublicacionCarrito(usuario,mapa);
        }
    }

    /**
     * Es una funcion la cual borra la publicacion que el usuario queda del carrito, es llamada en carrito
     * @param usuario Usuario el cual va a borrar una Publicacion del carrito
     * @param mapa El mapa en el cual estan almacenadasd las publicaciones de un carrito
     */
    private void borrarPublicacionCarrito(Usuario usuario, HashMap<String,Publicacion> mapa)
    {
        UsuarioNormal usuarioNormal = (UsuarioNormal)usuario;
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";


        String decision = "";
        boolean bandera = false;
        Publicacion aux = null;

        do
        {
            System.out.print("\n");
            System.out.print(espacio + "Decime el nombre de la publicacion del carrito que deseas borrar.\n");
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            System.out.print(espacio + "Opcion: ");
            decision = teclado.nextLine();

            Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String,Publicacion> entry = it.next();
                Publicacion publi = entry.getValue();
                if(publi.getNombrePublicacion().equals(decision))
                {
                    bandera = true;
                    publi.setStock(publi.getStock() + 1);
                    usuarioNormal.getCarrito().borrarPublicacion(publi);

                }
            }
            if(!bandera)
            {
                System.out.print(espacio + "Publicacion no encontrada. Intentelo denuevo.");
            }
        }
        while (!bandera);

        if(bandera)
        {
            System.out.print(espacio + "Publicacion borrada con exito del carrito.");
            paginaDos(usuario);
        }
    }

    /**
     * Metodo que se llama desde carrito(), este recorre todas las publicaciones que hay en el carrito
     * @param usuario
     * @return
     */
    private HashMap<String,Publicacion> recorrerCarrito(Usuario usuario)
    {
        UsuarioNormal usuNormal = (UsuarioNormal)usuario;

        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        Carrito carro = usuNormal.getCarrito();
        GeneDosPU<String,Publicacion> lista = carro.getPublicaciones();
        HashMap<String,Publicacion> mapa = lista.getMapa(); //obtengo el mapa para poder recorrerlo y mostrar todas las publicaciones

        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();

        if(!it.hasNext())
        {
            System.out.print(espacio + "El carrito esta vacio" + codigoNegrita + codigoSubrayado + codigoReset + "\n");
        }

        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion pub  = entry.getValue();
            Periferico auxPer = pub.getPeriferico();


                System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + pub.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
                System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + pub.getUrlFoto() + codigoReset + "\n");
                System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + pub.getPrecio() + codigoReset + "\n");
                System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + pub.getDueno().getNombre() + codigoReset + "\n");
                System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + auxPer.isEstado() + codigoReset + "\n");

        }
        return mapa;
    }

    /**
     * Metodo que se llama desde carrito, este hace todo el metodo necesario para que se efectue una compra.
     * @param usuarioNormal usuario que va a realizar la compra
     * @param carrito carrito del usuario que realiza la compra
     */
    private void comprarProductos(UsuarioNormal usuarioNormal, HashMap<String,Publicacion> carrito)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";
        float sumaProductos = 0;

        boolean banderaAntiBolud = false;

        Iterator<Map.Entry<String,Publicacion>> itCarrito = carrito.entrySet().iterator();


        while (itCarrito.hasNext()) { //itera con todas las publicaciones del carrito


            GeneUnoDM<MetodoDePago> metodos = usuarioNormal.getMetodosDePago(); //para recorrer los metodos
            HashSet<MetodoDePago> set = metodos.getSet();
            Iterator<MetodoDePago> it = set.iterator();

            Map.Entry<String,Publicacion>entry = itCarrito.next();
            Publicacion auxialiar = entry.getValue();

            System.out.print("\n\n");
            System.out.print(espacio + "Publicacion en proceso de compra: " + auxialiar.getNombrePublicacion());
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }


            if(!it.hasNext())
            {
                System.out.print("\n");
                System.out.print(espacio + "No tenes metodos de pago en tu cuenta. Agrega al menos uno para poder hacer una compra.. Redirigiendose al menu principal...\n");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                paginaDos(usuarioNormal);
            }

            System.out.print("\n");
            System.out.print("\n");
            System.out.print(espacio + "Tenes estos metodos de pago disponibles." + codigoNegrita + codigoSubrayado + codigoReset + "\n");
            System.out.print("\n");
            System.out.print("\n");
            while(it.hasNext())
                {
                    MetodoDePago aux = it.next();
                    System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.print("\n");
                    System.out.print(espacio + "Tipo de tarjeta: " + aux.getTipoPago());
                    System.out.print("\n");
                    System.out.print(espacio +"Tarjeta: " + aux.getUltimosCuatroTargeta());
                    System.out.print("\n");
                    System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }


            String cuatroUlt = " ";
            boolean bandera = false;

            System.out.print("\n");
            System.out.print(espacio + "Decime los ultimos 4 numeros de la tarjeta con la que desea pagar: ");
            cuatroUlt = teclado.next();

            GeneUnoDM<MetodoDePago> metodosTres = usuarioNormal.getMetodosDePago(); //para recorrer los metodos
            HashSet<MetodoDePago> setTres = metodos.getSet();
            Iterator<MetodoDePago> ite = set.iterator();

            while (ite.hasNext())
            {
                MetodoDePago auxDos = ite.next();
                if(auxDos.getUltimosCuatroTargeta().substring(auxDos.getUltimosCuatroTargeta().length() - 4).equals(cuatroUlt.substring(cuatroUlt.length() - 4)))
                {
                    System.out.print("\n");
                    System.out.print(espacio + "Metodo de pago Encontrado.\n\n");
                    bandera = true;
                }
            }
            if(!bandera) {


                System.out.print("\n");
                System.out.print(espacio + "No se encontro un metodo de pago con esas caracteristicas. Redirigiendose a ver mi carrito...\n\n");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                System.out.print("\n");
                carrito(usuarioNormal);
            }

            System.out.print("\n\n\n");
            System.out.print(espacio + "Direcciones disponibles: ");

            //se recorren las direcciones disponibles del usuario
            GeneUnoDM<Direccion> metodosCuatro = usuarioNormal.getDirecciones(); //para recorrer los metodos
            HashSet<Direccion> setCuatro = metodosCuatro.getSet();
            Iterator<Direccion> iteratorCuatro = setCuatro.iterator();

            if(!iteratorCuatro.hasNext())
            {
                System.out.print("\n");
                System.out.print(espacio + "No tiene Direcciones agregadas, agregue una direccion antes de poder avanzar. Redirigiendose al menu principal...");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                System.out.print("\n");
                paginaDos(usuarioNormal);
            }
            else
            {

                System.out.print("\n");
                System.out.print("\n");
                while(iteratorCuatro.hasNext())
                {
                    Direccion aux = iteratorCuatro.next();
                    System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.print("\n");
                    System.out.print(espacio + "Provincia: " + aux.getProvincia());
                    System.out.print("\n");
                    System.out.print(espacio +"Ciudad: " + aux.getCiudad());
                    System.out.print("\n");
                    System.out.print(espacio +"Calle: " + aux.getCalle());
                    System.out.print("\n");
                    System.out.print(espacio +"Altura: " + aux.getAltura());
                    System.out.print("\n");
                    System.out.print(espacio +"Departamento: " + aux.getDepartamento());
                    System.out.print("\n");
                    System.out.print(espacio +"Codigo postal: " + aux.getCp());
                    System.out.print("\n");
                    System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            String calle = "";
            String altura = "";
            boolean banderaDos = false;

            System.out.print("\n");
            System.out.print(espacio + "Decime la calle y altura de la direccion a la que queres mandar el envio. \n");
            System.out.print(espacio + "Calle: ");
            calle = teclado.next();

            System.out.print("\n");
            teclado.nextLine();

            System.out.print(espacio + "Altura: ");
            altura = teclado.next();
            System.out.print("\n");


            GeneUnoDM<Direccion> metodosCinco = usuarioNormal.getDirecciones(); //para recorrer los metodos
            HashSet<Direccion> setCinco = metodosCinco.getSet();
            Iterator<Direccion> iteratorCinco = setCinco.iterator();

            while (iteratorCinco.hasNext()) {
                Direccion aux = iteratorCinco.next();
                if (aux.getCalle().equals(calle) && aux.getAltura().equals(altura)) {
                    System.out.print("\n");
                    System.out.print(espacio + "Direccion encontrada.\n\n");
                    banderaDos = true;
                }
            }
            if (!banderaDos) {
                System.out.print("\n");
                System.out.print(espacio + "No se encontro una direccion con esas caracteristicas. Redirigiendose a ver carrito..\n\n");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                System.out.print("\n");
                carrito(usuarioNormal);
            }

            System.out.print("\n");
            System.out.print(espacio + "Ahora vas a seleccionar el medio de envio que mas te interese. Tenes que escribir el nombre del tipo de envio.\n");
            System.out.print("\n");


//            Map.Entry<String,Publicacion>entry = itCarrito.next();
//            Publicacion auxialiar = entry.getValue();

            GeneTresE<Envio> geneTresLista = auxialiar.getEnvios(); //bajo todos los envios disponibles de cada una de las publicaciones
            ArrayList<Envio> arrayLista = geneTresLista.getLista();
            Envio auxEnvio = null;

            for(int i = 0; i < arrayLista.size(); i++)
            {
                auxEnvio  = arrayLista.get(i);
                System.out.print(espacio + "Tipo de envio: " + auxEnvio.getTipoEnvio() + "\n");
                System.out.print(espacio + "Precio: " + auxEnvio.getPrecio() + "\n");
                String rtaSimple = "";
                if(auxEnvio.isExpress())
                {
                    rtaSimple = "si";
                }
                else
                {
                    rtaSimple = "no";
                }
                System.out.print(espacio + "EnvioExpress: " + rtaSimple + "\n");
            }

            System.out.print("\n\n");
            teclado.nextLine();
            System.out.print(espacio + "Opcion: ");
            String respuestaEnvio = teclado.next();
            boolean banderaEnvio = false;


            for(int i = 0; i < arrayLista.size(); i++) {

                auxEnvio = arrayLista.get(i);
                System.out.print("\n");
                if(respuestaEnvio.equals(auxEnvio.getTipoEnvio().getNombre()))
                {
                    System.out.print(espacio + "Envio escrito correctamente.\n");
                    banderaEnvio = true;
                }
            }
            if(!banderaEnvio)
            {
                System.out.print(espacio + "Ese envio no existe. Redirigiendose a ver carrito...\n");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.print(e.getMessage());
                }
                carrito(usuarioNormal);
            }

            sumaProductos+= auxialiar.getPrecio() + auxEnvio.getPrecio();

        }

        System.out.print("\n\n");

        int respuestaFinal = 0;
        do
        {
            System.out.print(espacio + "¿Esta seguro de hacer la compra?.1-Si 2-No. La suma de los productos es " + sumaProductos + "$.");
            System.out.print("\n");
            teclado.nextLine();
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Opcion: ");
                    respuestaFinal = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;

            if (respuestaFinal < 1 || respuestaFinal > 2)
            {
                System.out.print(espacio + "Elección no comprendida, inténtelo de nuevo.\n\n");
            }
        }
        while(respuestaFinal < 1 || respuestaFinal > 2);

        if(respuestaFinal == 1)
        {
            System.out.print(espacio + "Felicidades por realizar su compra. Los plazos de envios y caracteristicas de su compra se veran en Mis Compras.\n" + espacio +"El comprobante fue enviado a su direccion de mail " + usuarioNormal.getMail());
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
            System.out.print("\n");
            agregarAVentas(carrito);
            agregarCarritoACompras(carrito,usuarioNormal);
        }
        else
        {
            System.out.print(espacio + "Los elementos se quedaran dentro de su carrito.");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
        }
        paginaDos(usuarioNormal);
    }

    /**
     * Metodo que se llama desde comprarProdutos(), el cual hace que se pasen los productos del carrito, a compras.
     * @param carrito carrito que se va a pasar a compras
     * @param usuarioNormal usuario el cual compro esos productos
     */
    private void agregarCarritoACompras(HashMap<String,Publicacion> carrito, UsuarioNormal usuarioNormal)
    {
        Iterator<Map.Entry<String,Publicacion>> it = carrito.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion pub = entry.getValue();
            usuarioNormal.agregarcompra(pub); //se agregar a compras
            //usuarioNormal.getCarrito().borrarPublicacion(pub); //se elimina del carrito
            it.remove();
        }

    }

    /**
     * Funcion que se llama desde comprarProductos, esta hace que las publicaciones del vendedor que se hayan vendido, se vayan a vendidos.
     * @param carrito carrito el cual se compro
     */
    private void agregarAVentas(HashMap<String,Publicacion> carrito)
    {
        Iterator<Map.Entry<String,Publicacion>> it = carrito.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion pub = entry.getValue();
            Usuario usuario = pub.getDueno();
            usuario.agregarVenta(pub);
        }
    }

    /**
     * Funcion la cual hace que se muestren todas las publicaciones Favoritas de un usuario. Estos favoritos desde aca, pueden ser agregados al carrito.
     *
     * @param usuario Usuario el cual se le va a recorrer y manipular las publicaciones que esten en sus favoritos
     *
     */
    private void favoritos(Usuario usuario)
    {
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        UsuarioNormal usuarioNormal = (UsuarioNormal)usuario;
        boolean bandera = false; //para recorrer los favoritos
        String decision = " ";
        Publicacion pub = null;
        boolean banderaAntiBolud = false;

        GeneDosPU<String, Publicacion> favoritos = usuarioNormal.getFavoritas();
        HashMap<String,Publicacion>  mapa = favoritos.getMapa();
        Iterator<Map.Entry<String,Publicacion>> it = mapa.entrySet().iterator();


        if(!it.hasNext())
        {
            System.out.print(espacio + "No hay nada en tus favoritos.");
            paginaDos(usuario);
        }

        while(it.hasNext())
        {
            Map.Entry<String,Publicacion> entry = it.next();
            Publicacion publi  = entry.getValue();
            Periferico auxPer = publi.getPeriferico();

            System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + publi.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
                System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + publi.getUrlFoto() + codigoReset + "\n");
                System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + publi.getPrecio() + codigoReset + "\n");
                System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + publi.getDueno().getNombre() + codigoReset + "\n");
                System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + auxPer.isEstado() + codigoReset + "\n");

        }

        int rta = -1;
        String opcion = "";
        System.out.print(espacio + "¿Que desea hacer? 1-Volver al menu 2-Agregar Favorito a Carrito: " + codigoNegrita + codigoSubrayado + codigoReset + "\n");

        do
        {
            while (!banderaAntiBolud)
            {
                try // esto es para que el usuario en caso de que ponga un dato que no pida, no se rompa el programa.
                {
                    System.out.print(espacio + "Opción: ");
                    rta = teclado.nextInt();
                    banderaAntiBolud = true;
                } catch (InputMismatchException e) {
                    System.out.print("\n");
                    System.out.println(espacio + "Entrada inválida. Ingrese un numero entero por favor.");
                    teclado.next();
                }
            }
            banderaAntiBolud  =false;

            teclado.nextLine();
            if ((rta != 1 && rta != 2))
            {
                System.out.print(espacio + "Elección no comprendida, inténtelo de nuevo.\n\n");
            }
        }
        while (rta != 1 && rta != 2);

        if(rta == 1)
        {
            paginaDos(usuarioNormal);
        }
        else if(rta == 2)
        {

            Iterator<Map.Entry<String,Publicacion>> iterator = mapa.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Publicacion> entry = iterator.next();
                Publicacion publi = entry.getValue();
                Periferico auxPer = publi.getPeriferico();

                System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + publi.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
                System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + publi.getUrlFoto() + codigoReset + "\n");
                System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + publi.getPrecio() + codigoReset + "\n");
                System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + publi.getDueno().getNombre() + codigoReset + "\n");
                System.out.print(espacio + "Estado: " + codigoNegrita + codigoSubrayado + auxPer.isEstado() + codigoReset + "\n");

            }


            do {
                System.out.print("\n");
                System.out.print(espacio + "Nombre de la publicacion que desea pasar de favoritos al carrito: ");
                decision = teclado.nextLine();
                System.out.print("\n");

                Iterator<Map.Entry<String,Publicacion>> iteratorDos = mapa.entrySet().iterator();
                while (iteratorDos.hasNext())
                {
                    Map.Entry<String, Publicacion> entry = iteratorDos.next();
                    Publicacion publi = entry.getValue();

                    if (decision.equals(publi.getNombrePublicacion())) {
                        bandera = true; //se encontro, nombre correcto
                        pub = publi;
                    }
                }
                if(!bandera)
                {
                    System.out.print(espacio + "Esa publicacion no esta en tus favoritos. Intenta denuevo.\n");
                }
            }while (!bandera);

            usuarioNormal.agregarCarrito(pub);
            pub.setStock(pub.getStock() - 1);
            System.out.print(espacio + pub.getNombrePublicacion() + " Agregado con exito\n");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }

            paginaDos(usuarioNormal);
        }


    }


}
