package Clases;

import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;

import java.util.*;

import static Clases.Main.teclado;

/**
 * Clase la cual se encarga de la gestión de la consola de comandos y salidas por pantalla.
 */
public class GestionConsolaComandos
{
    // ATRIBUTO
    private GestionTienda tienda;

    // CONSTRUCTOR
    public GestionConsolaComandos(GestionTienda tienda) {
        this.tienda = tienda;
    }

    // MÉTODOS
    public void menuPrincipal() {

        String espacio = "                                                                           ";


        //aca va a haber una porcion de codigo que baje todo una archivo completo para que nos de todas las publicaciones y los usuarios ya creados
        Usuario ingresado = paginaUno(); //pagina uno, la cual podes iniciar sesion, crear una cuenta o simplemente salir
        //en caso de iniciar sesion, ingresado va a ser el usuario que ingreso, en caso de que no se haya iniciado sesion sera null
        if(ingresado != null)
        {
            System.out.print(espacio + "Sesion iniciada con exito.\n");
            paginaDos(ingresado);
        }




    }
    public Usuario paginaUno()
    {
        Usuario usuarioRetorno = null;
        int respuesta = -1;
        Usuario usuario = null; //en caso de que se haya iniciado sesion, esta funcion va a retornar el Usuario que inicio sesion.
        do {


            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            String texto = "BIENVENIDOS A LA TIENDA DE PERIFERICOS ";
            String espacio = "                                                                           ";
            // Códigos de escape ANSI
            String codigoNegrita = "\u001B[1m";
            String codigoSubrayado = "\u001B[4m";
            String codigoTamanioGrande = "\u001B[5m";
            String codigoReset = "\u001B[0m";

            System.out.println(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + texto + codigoReset); //texto principal

            System.out.print("\n\n\n");
            String opcion1 = "1- Iniciar sesion";
            String opcion2 = "2- Crear cuenta";
            String opcion3 = "3- Salír";
            String opcion4 = "Eleccion: ";
            System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion1 + codigoReset + "\n"); //opciones
            System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion2 + codigoReset + "\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + opcion3 + codigoReset + "\n");
            System.out.print(espacio + opcion4);

            do {
                respuesta = teclado.nextInt();
                if (respuesta > 3 || respuesta < 0) {
                    System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
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
                System.out.print(espacio + "Cuenta creada con exito, ahora se le redirigira a la pagina anterior para que pueda Iniciar sesion con su cuenta.\n");
            } else {
                System.exit(0);
            }
        }
        while (respuesta == 2);

        return usuarioRetorno;
    }

    public Usuario iniciarSesion()
    {
        String espacio = "                                                                           ";
        String ingresarDNIOMail = "";
        String contrasena = "";
        boolean existencia = false;
        Usuario auxUsuario = null;
        do {                                        //verificacion de que el usuario este intentando poner un mail correcto.
            System.out.print(espacio + "Ingrese su Mail o DNI: ");
            ingresarDNIOMail= teclado.next();
            existencia = tienda.existeUsuario(ingresarDNIOMail); // es true si existe, si no existe es false
            if(!existencia)
            {
                System.out.print(espacio + "No existe usuario con ese Mail / DNI, Intentelo denuevo.\n\n");
            }
        }
        while (!existencia); //mientras que no exista, le pedimos que lo ingrese nuevamente

        do
        {
            System.out.print(espacio + "Ingrese su Contrasena: ");
            contrasena = teclado.next();
            auxUsuario = tienda.claveConfirmacion(ingresarDNIOMail,contrasena);
            if(auxUsuario == null) //si es null significa que la contrasena es incorrecta
            {
                System.out.print(espacio + "Contrasena incorrecta, intentelo nuevamente.\n\n");
            }
        }
        while (auxUsuario == null);

        return auxUsuario;

    }

    public void crearCuenta()
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

        System.out.print(espacio + "ACLARACIONES ANTES DE CREAR LA CUENTA: \n" + espacio + "La cuenta es personal. Por lo que no se puede tener dos cuentas con el mismo mail, DNI o numero telefonico");
        System.out.print("\n");
        System.out.print(espacio + "La contrasena debe tener minimo 8 digitos, un caracter en Mayuscula y un digito numerico\n");
        do {


            System.out.print(espacio +"Ingrese su mail: ");
            mail = teclado.next();

            do
            {
                System.out.print("\n");
                System.out.print(espacio + "Ingrese su contrasena: ");
                contrasena = teclado.next();

                System.out.print("\n");
                System.out.print(espacio + "ReIngrese su contrasena: ");
                contraAux = teclado.next();

                if(!contrasena.equals(contraAux))
                {
                    System.out.print("\n");
                    System.out.print(espacio + "No coinciden las contrasenas. Intentelo denuevo. \n");
                }
            }
            while (!contrasena.equals(contraAux));


            System.out.print("\n");
            System.out.print(espacio + "Ingrese su nombre: ");
            nombre = teclado.next();

            System.out.print("\n");
            System.out.print(espacio + "Ingrese su apellido: ");
            apellido = teclado.next();

            System.out.print("\n");
            System.out.print(espacio + "Ingrese su numero de telefono: ");
            numeroTelefono = teclado.next();

            System.out.print("\n");
            System.out.print(espacio + "Ingrese su documento de identidad: ");
            dni = teclado.next();

            System.out.print("\n");
            System.out.print(espacio + "Ahora elija si su cuenta va a ser compra y venta, o solo especializada en venta. Opcion 1 o 2. ");
            System.out.print(espacio + "ACLARACION \n" + espacio + "El usuario de compra y venta no puede verificarse como pagina de venta especializada.\n");
            System.out.print(espacio + "En el caso del usuario venta, no podra comprar otras publicaciones. A la vez tambien debera agergar la URL de su pagina de venta, su CUIT y la condiicion fiscal.\n");
            System.out.print(espacio + "Todas las reglas mencionadas son obligatorias. En caso contrario, se podrá cerrar la cuenta de manera definitiva. \n");
            System.out.print(espacio + "Opcion: ");
            do {
                decitionUsuario = teclado.nextInt();
                if(decitionUsuario != 1 && decitionUsuario != 2)
                {
                    System.out.print(espacio + "Decicion erronea. Decida entre Opcion 1 u Opcion 2.\n");
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
                System.out.print(espacio + "Ingrese la URL de su pagina de venta: ");
                url = teclado.next();

                System.out.print("\n");
                System.out.print(espacio + "Ingrese su CUIT personal: ");
                cuit = teclado.next();

                System.out.print("\n");
                do {
                    System.out.print(espacio + "Ingrese su tipo de Monotributo. A-F: ");
                    decisionMonotributo = teclado.next();
                    if (!decisionMonotributo.equals("A") && !decisionMonotributo.equals("B") && !decisionMonotributo.equals("C") && !decisionMonotributo.equals("D") && !decisionMonotributo.equals("E") && !decisionMonotributo.equals("F"))
                    {
                        System.out.print(espacio + "Error. Por favor introducí una respuesta valida. \n");
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
                if(decitionUsuario == 1)
                {
                    tienda.agregar(mail,contrasena,nombre,apellido,numeroTelefono,usuario,dni);
                }
                else
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
        System.out.print(espacio + "Felicitaciones ! A creado su cuenta con exito.\n");
    }

    public void paginaDos(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

        String bienvenidos = "\n\nBienvenido " + usuario.getNombre() + ", este es tu menu principal\n\n";
        String opcionUno = "1- Ver Publicaciones activas";
        String opcionDos = "2- Crear publicacion";
        String opcionTres = "3- Ver mi carrito";
        String opcionCuatro = "4- Favoritos";
        String opcionCinco = "5- Mi cuenta"; //dentro de aca va a haber misPublicaciones, misVentas, misCompras, misDirecciones, misMetodos de pago, agregar direccion, agregar metodo de pago, verificar cuenta,
        String opcionSeis = "6- Cerrar sesion";


        String opcionElecc = "Eleccion: ";

        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + bienvenidos + codigoReset);
        System.out.print("\n\n\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCuatro + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionCinco + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionSeis + codigoReset + "\n");
        System.out.print(espacio + opcionElecc);
        int decision = 0;

        do {
            decision = teclado.nextInt();
            if (decision > 3 || decision < 0) {
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
            verPublicacionesActivas(usuario);
        }



    }

    public void verPublicacionesActivas(Usuario usuario)
    {
        UsuarioNormal usuarioNormal = (UsuarioNormal)usuario;
        String espacio = "                                                                           ";
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";


         HashMap<String,Publicacion> publicaciones = recorrerPublicaciones();
        int rta = -1;
        String opcion = "";
        System.out.print(espacio + "1 para volver al menu anterior, 2 para agregar algun producto al carrito. " + codigoNegrita + codigoSubrayado + codigoReset + "\n");

        do
        {
            System.out.print(espacio + "Opcion: ");
            rta = teclado.nextInt();
            teclado.nextLine();
            if ((rta != 1 && rta != 2))
            {
                System.out.print(espacio + "Eleccion no comprendida, intentelo denuevo.\n\n");
            }
        }
        while (rta != 1 && rta != 2);

        if(rta == 1)
        {
            paginaDos(usuario);
        }
        else
        {
            Publicacion pub = null;
            recorrerPublicaciones();
            do
            {
                System.out.print(espacio + "Nombre del producto que desea agregar al carrito: ");
                opcion = teclado.nextLine();
                pub = busquedaPublicacionPorNombre(publicaciones,opcion);

                if(pub == null) //significa que no lo encontro
                {
                    System.out.print("\n" + espacio + "Nombre Incorrecto, proba con alguno de los nombres de la lista.\n");
                }
            }
            while(pub == null);

            usuarioNormal.agregarCarrito(pub);
            pub.setStock(pub.getStock() - 1);

            System.out.print(espacio + pub.getNombrePublicacion() + " Agregado con exito\n");
            verPublicacionesActivas(usuario);
        }

    }
    public Publicacion busquedaPublicacionPorNombre(HashMap<String,Publicacion> mapa, String nombre)
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
    public HashMap<String,Publicacion> recorrerPublicaciones()
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
            if(pub.getStock() >= 1) { // muestro solo los productos que tienen stock


                System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + pub.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
                System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + pub.getUrlFoto() + codigoReset + "\n");
                System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + pub.getPrecio() + codigoReset + "\n");
                System.out.print(espacio + "Stock: " + codigoNegrita + codigoSubrayado + pub.getStock() + codigoReset + "\n");
                System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + pub.getDueno().getNombre() + codigoReset + "\n");
                System.out.print(espacio + "Rating: " + codigoNegrita + codigoSubrayado + pub.getRating() + codigoReset + "\n");
            }
        }
        return mapa;
    }






}
