package Clases;

import Enums.*;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;
import Genericas.GeneTresE;
import Genericas.GeneUnoDM;

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

            System.out.print(espacio + "Cargando...\n");

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                System.out.print(e.getMessage());
//            }

            System.out.print(espacio + "Sesion iniciada con exito.\n");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                System.out.print(e.getMessage());
//            }

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
            System.out.print(espacio + opcion4);

            do {
                respuesta = teclado.nextInt();
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

        System.out.print(espacio + "ACLARACIONES ANTES DE CREAR LA CUENTA: \n" + espacio + "La cuenta es personal. Por lo que no se puede tener dos cuentas con el mismo mail, DNI o número telefónico.");
        System.out.print("\n");
        System.out.print(espacio + "La contraseña debe tener mínimo 8 dígitos, un caracter en mayúscula y un dígito numérico.\n");
        do {


            System.out.print(espacio +"Ingrese su mail: ");
            mail = teclado.next();

            do
            {
                System.out.print("\n");
                System.out.print(espacio + "Ingrese su contraseña: ");
                contrasena = teclado.next();

                System.out.print("\n");
                System.out.print(espacio + "Reingrese su contraseña: ");
                contraAux = teclado.next();

                if(!contrasena.equals(contraAux))
                {
                    System.out.print("\n");
                    System.out.print(espacio + "No coinciden las contraseñas. Inténtelo de nuevo. \n");
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
            System.out.print(espacio + "Ingrese su numero de teléfono: ");
            numeroTelefono = teclado.next();

            System.out.print("\n");
            System.out.print(espacio + "Ingrese su documento de identidad: ");
            dni = teclado.next();

            System.out.print("\n");
            System.out.print(espacio + "Ahora elija si su cuenta va a ser compra y venta, o sólo especializada en venta. Ingrese opción 1 o 2. ");
            System.out.print(espacio + "ACLARACIÓN \n" + espacio + "El usuario de compra y venta no puede verificarse como página de venta oficial.\n");
            System.out.print(espacio + "En el caso del usuario venta, no podrá comprar productos. A la vez también deberá agregar la URL de su página web de venta, su CUIT y la condiición fiscal.\n");
            System.out.print(espacio + "Todas las reglas mencionadas son obligatorias. En caso contrario, se podrá cerrar la cuenta de manera definitiva. \n");
            System.out.print(espacio + "Opción: ");
            do {
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

                System.out.print("\n");
                System.out.print(espacio + "Ingrese su CUIT personal: ");
                cuit = teclado.next();

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
        System.out.print(espacio + "Felicitaciones! Ha creado su cuenta con éxito.\n");
    }

    public void paginaDos(Usuario usuario)
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
        System.out.print(espacio + opcionElecc);
        int decision = 0;

        do {
            decision = teclado.nextInt();

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
            verPublicacionesActivas(usuario);
        }
        else if(decision == 2)
        {
            crearPublicacion(usuario);
        }
        else if(decision == 3)
        {

        }
        else if(decision == 4)
        {

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
        System.out.print(espacio + "1 para volver al menú anterior, 2 para agregar un producto al carrito. " + codigoNegrita + codigoSubrayado + codigoReset + "\n");

        do
        {
            System.out.print(espacio + "Opción: ");
            rta = teclado.nextInt();
            teclado.nextLine();
            if ((rta != 1 && rta != 2))
            {
                System.out.print(espacio + "Elección no comprendida, inténtelo de nuevo.\n\n");
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
                    System.out.print("\n" + espacio + "Nombre incorrecto, pruebe con alguno de los nombres de la lista.\n");
                }
            }
            while(pub == null);

            usuarioNormal.agregarCarrito(pub);
            pub.setStock(pub.getStock() - 1);

            System.out.print(espacio + pub.getNombrePublicacion() + " Agregado con exito\n");
            paginaDos(usuario);
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
    public void crearPublicacion(Usuario usuario)
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
        float precioEnvio;
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


        System.out.print("\n");
        String mensaje = " Van a completar los siguientes datos para crear su publicacion";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensaje + codigoReset);
        System.out.print("\n");
        teclado.nextLine();

        System.out.print(espacio+ "Nombre de la publicacion: ");
        nombrePublicacion = teclado.nextLine();
        System.out.print("\n");


        System.out.print(espacio + "Precio: ");
        precio = teclado.nextFloat();
        teclado.nextLine(); // Consumir el carácter de salto de línea pendiente
        System.out.print("\n");

        System.out.print(espacio + "Cantidad disponible: ");
        stock = teclado.nextInt();
        System.out.print("\n");

        System.out.print(espacio + "Foto (URL de la foto): ");
        urlFoto = teclado.next();
        System.out.print("\n");

        System.out.print(espacio + "Ahora va a agregar los tipos de envio que va a aceptar." + "\n" + espacio +"Obligatoriamente va a tener que tener un envio como minimo.");
        do {
            System.out.print("\n");
            System.out.print(espacio + "Tipo de envio(1-Tierra, 2-Aereo, 3-Maritimo):  ");
            do {
                 decition = teclado.nextInt();
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
            System.out.print("\n");
            System.out.print(espacio + "Precio: ");
            precioEnvio = teclado.nextFloat();

            Envio aux = new Envio(express,precioEnvio,tipoEnvio);
            envio.add(aux);


            System.out.print("\n");
            do {
                System.out.print(espacio + "¿Desea agregar otro tipo de envio? 1-Si 2-No:  ");
                decitionDos = teclado.nextInt();
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

        System.out.print("\n");
        System.out.print(espacio + "Nombre del Periferico: ");
        nombrePeri = teclado.nextLine();

        System.out.print("\n");
        //teclado.nextLine();
        do
        {
            System.out.print(espacio + "Estado(1-Nuevo, 2-Usado): ");
            deciEstado = teclado.nextInt();
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
        System.out.print(espacio + "Peso: ");
        peso = teclado.nextFloat();

        System.out.print("\n");
        teclado.nextLine();
        do
        {
            System.out.print(espacio + "Inalambrico(1-Si, 2-No): ");
            deciInalambrico = teclado.nextInt();
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
        String mensajeAuxDos = " Publicacion agregada con exito! Ahora va a voler a la pagina su menu principal..";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensajeAuxDos + codigoReset);
        System.out.print("\n");
        paginaDos(usuario);
    }
    public void miCuenta(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";

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
        System.out.print(espacio + opcionElecc);
        int decision = 0;

        do {
            decision = teclado.nextInt();
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
    public void metodosDePago(Usuario usuario)
    {
        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";


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



        String opcionElecc = "Eleccion: ";

        System.out.print("\n\n\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionUno + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionDos + codigoReset + "\n");
        System.out.print(espacio + codigoNegrita + codigoSubrayado + opcionTres + codigoReset + "\n");

        System.out.print(espacio + opcionElecc);
        int decision = 0;

        do {
            decision = teclado.nextInt();
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
    public void agregarMetodoDePago(Usuario usuario)
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



        System.out.print("\n");
        String mensaje = " Van a completar los siguientes datos para agregar su metodo de pago";
        System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + mensaje + codigoReset);
        System.out.print("\n");
        teclado.nextLine();

        System.out.print(espacio+ "Nombre de la tarjeta: ");
        nombre = teclado.nextLine();
        System.out.print("\n");

        do
        {
            System.out.print(espacio+ "Tipo de tarjeta 1-Debito 2-Credito: ");
            decitionTipoPago = teclado.nextInt();
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
    public void borrarMetodoDePago(Usuario usuario)
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
                bandera = true;
            }
        }
        if(!bandera) {


            System.out.print("\n");
            System.out.print(espacio + "No se encontro un metodo de pago con esas caracteristicas\n\n");
            System.out.print("\n");
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }

        metodosDePago(usuario);
    }
    public void direcciones(Usuario usuario)
    {
        //va a mostrar direcciones, vas a poder agregar y borrar direcciones.

        String espacio = "                                                                           ";
        // Códigos de escape ANSI
        String codigoNegrita = "\u001B[1m";
        String codigoSubrayado = "\u001B[4m";
        String codigoTamanioGrande = "\u001B[5m";
        String codigoReset = "\u001B[0m";


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

        System.out.print(espacio + opcionElecc);
        int decision = 0;

        do {
            decision = teclado.nextInt();
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
    public void agregarDireccion(Usuario usuario)
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
    public void borrarDireccion(Usuario usuario) {
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
    public void mostrarCompras(Usuario usuario)
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

            System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + aux.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
            System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + aux.getUrlFoto() + codigoReset + "\n");
            System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + aux.getPrecio() + codigoReset + "\n");
            System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + aux.getDueno().getNombre() + codigoReset + "\n");
            System.out.print(espacio + "Rating: " + codigoNegrita + codigoSubrayado + aux.getRating() + codigoReset + "\n");
        }
        System.out.print(espacio + "Total Gastado: " + codigoNegrita + codigoSubrayado + comprasHechas.getTotalGastado() + codigoReset + "\n");
        miCuenta(usuario);
    }
    public void mostrarVentas(Usuario usuario)
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

            System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.print(espacio + codigoNegrita + codigoSubrayado + codigoTamanioGrande + aux.getNombrePublicacion() + codigoReset + "\n\n"); //nombre de la publicacion
            System.out.print(espacio + "URL Foto: " + codigoNegrita + codigoSubrayado + aux.getUrlFoto() + codigoReset + "\n");
            System.out.print(espacio + "Precio: " + codigoNegrita + codigoSubrayado + aux.getPrecio() + codigoReset + "\n");
            System.out.print(espacio + "Vendedor: " + codigoNegrita + codigoSubrayado + aux.getDueno().getNombre() + codigoReset + "\n");
            System.out.print(espacio + "Rating: " + codigoNegrita + codigoSubrayado + aux.getRating() + codigoReset + "\n");
        }
        System.out.print(espacio + "Total Ganado: " + codigoNegrita + codigoSubrayado + ventasHechas.getTotalRecaudado() + codigoReset + "\n");
        miCuenta(usuario);
    }
    public void cambiarContrasena(Usuario usuario)
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


}
