package Clases;

import Enums.*;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;
import org.json.JSONException;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;

public class Main implements Serializable
{
    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, URISyntaxException
    {
        GestionTienda tienda = new GestionTienda();

        try
        {
            tienda.agregar("tomy.bianchi@hotmail.com","Tomas1234","Tomas","Bianchi Silvestre","2233128691", E_TipoUsuario.NORMAL,"44956816");
            tienda.agregar("Ludmila.Ippoliti@gmail.com","Ludmila1234","Ludmila","Ippoliti","2237658907", E_TipoUsuario.VENTA,"41876554","www.grupoLudmila.com.ar","20418765543", E_CondFiscal.MONOTRIBUTO_B);
            tienda.agregar("Mayi.Mauro@gmail.com","Matias1234","Matias","Mauro","2234321123", E_TipoUsuario.NORMAL,"43345778");
        }
        catch (ExcepcionClaveDuplicada e){
            System.out.print(e.getMessage());
        }
        catch (ExcepcionMailYaExiste e ){
            System.out.print(e.getMessage());
        }
        catch (ExcepcionConstrasenaInvalida e){
            System.out.print(e.getMessage());
        }
        catch (ExcepcionNumeroRepetido e){
            System.out.print(e.getMessage());
        }


        GeneDosPU<String,Usuario> usuarios = tienda.getUsuarios();
        HashMap<String,Usuario> mapa = usuarios.getMapa();

        Usuario tomas = mapa.get("44956816");
        Usuario ludmila = mapa.get("41876554");
        Usuario matias = mapa.get("43345778");

        tomas.agregarMetodoDePago(new MetodoDePago(E_Pago.DEBITO,"Tomas Bianchi Silvestre", "4234778699087654","876","06","27"));
        tomas.agregarMetodoDePago(new MetodoDePago(E_Pago.CREDITO,"Tomas Bianchi Silvestre", "4248446877659087","321","08","25"));
        tomas.agregarDireccion(new Direccion("Buenos aires","Mar del plata","Constitucion","2340", "0","7600"));

        ludmila.agregarMetodoDePago(new MetodoDePago(E_Pago.DEBITO,"Ludmila Ippoliti", "5658776543231189","004","01","24"));
        ludmila.agregarMetodoDePago(new MetodoDePago(E_Pago.CREDITO,"Ludmila Ippoliti", "9897996544567781","021","12","23"));
        ludmila.agregarDireccion(new Direccion("Buenos aires","Mar del plata","Cardiel","213", "0","7600"));

        matias.agregarMetodoDePago(new MetodoDePago(E_Pago.DEBITO,"Matias Mauro", "4234778699087654","876","06","27"));
        matias.agregarMetodoDePago(new MetodoDePago(E_Pago.CREDITO,"Matias Mauro", "4248446877659087","321","08","25"));
        matias.agregarDireccion(new Direccion("Buenos aires","Chivilcoy","Dunas","9456", "0","B6620"));



        Publicacion auris = tienda.agregarPublicacion("Auriculares Hyperx Cloud Flight, perfecto estado","Hyperx Cloud Flight", E_Estado.USADO,"Hyperx","Cloud Flight","EEUU","PS4/PS5/PC","Rojo",500,true,35000,1,tomas,"https://row.hyperx.com/es/products/hyperx-cloud-flight?variant=40853229633741");
        tienda.agregarEnvioPublicacion(auris,new Envio(true,1250, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(auris,new Envio(false,550, E_Envio.Tierra));

        Publicacion joystick = tienda.agregarPublicacion("Joystick PS4","Joystick", E_Estado.USADO,"Sony","Joystick","EEUU","PS3/PS4/PS5/PC","Negro",250,true,12000,2,tomas,"https://store.sony.com.ar/ps4-dualshock-4-black/p");
        tienda.agregarEnvioPublicacion(joystick,new Envio(true,1050, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(joystick,new Envio(false,350, E_Envio.Barco));
        tienda.agregarEnvioPublicacion(joystick,new Envio(false,600, E_Envio.Tierra));

        Publicacion tecladoRazer = tienda.agregarPublicacion("Teclado Gamer Razer Ornata V3, luz LED RGB","Razer Ornata V3",E_Estado.NUEVO,"Razer","Ornata V3","Canadá","PC","Negro Clásico",670,false,16900,3,ludmila,"https://www.razer.com/latam-es/gaming-keyboards/razer-ornata-v3");
        tienda.agregarEnvioPublicacion(tecladoRazer,new Envio(true,1000, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(tecladoRazer,new Envio(false,520, E_Envio.Tierra));

        Publicacion MightySkins = tienda.agregarPublicacion("MightySkins importados NUEVOS","MightySkins",E_Estado.NUEVO,"Mighty Skins","Oculus Quest 2","Japón","Oculus Quest 2","Algodón de azúcar",800,true,60000,2,ludmila,"https://mightyskins.com/");
        tienda.agregarEnvioPublicacion(MightySkins,new Envio(true,1600, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(MightySkins,new Envio(false,800, E_Envio.Tierra));

        Publicacion aurisJBLReflect = tienda.agregarPublicacion("Auriculares JBL Reflect Aero - Como nuevos","JBL REFLECT",E_Estado.USADO,"JBL","Reflect Aero TWS","Australia","Productos con Bluetooth 5.0","Negro",120,true,4700,1,matias,"https://www.jbl.es/auriculares/REFLECT-AERO.html?dwvar_REFLECT-AERO_color=Black-GLOBAL-Current&cgid=headphones");
        tienda.agregarEnvioPublicacion(aurisJBLReflect,new Envio(true,1250, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(aurisJBLReflect,new Envio(false,550, E_Envio.Tierra));

        Publicacion aurisJBLTune = tienda.agregarPublicacion("JBL Tune 510BT inalambricos","JBL Tune",E_Estado.NUEVO,"JBL","Tune 510BT","EEUU","Productos con Bluetooth 5.0","Negro",1,true,6000,4,matias,"https://www.jbl.com.ar/TUNE510BT-.html");
        tienda.agregarEnvioPublicacion(aurisJBLTune,new Envio(true,950, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(aurisJBLTune,new Envio(false,450, E_Envio.Tierra));

        Publicacion appleMagicMouse = tienda.agregarPublicacion("The Apple Magic Mouse 2, superficie multi-touch","Apple Magic Mouse 2",E_Estado.NUEVO,"Apple","Magic Mouse 2","EEUU","PC, teclados","Amarillo",120,true,20700,2,tomas,"https://www.apple.com/es/shop/product/MK2E3ZM/A/magic-mouse-superficie-multi%E2%80%91touch-blanca");
        tienda.agregarEnvioPublicacion(appleMagicMouse,new Envio(true,650, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(appleMagicMouse,new Envio(false,450, E_Envio.Tierra));
        tienda.agregarEnvioPublicacion(appleMagicMouse,new Envio(false,250, E_Envio.Barco));


        Publicacion OstentJoystick = tienda.agregarPublicacion("OSTENT Joystick Retro Gamer con cable PS2- Buen estado","OSTENT Joystick",E_Estado.USADO,"OSTENT","Video Games","China","PS2/PS1/PS/PS one/PSX","Plateado",240,false,3000,1,ludmila,"https://www.amazon.com/-/es/OSTENT-Classic-Controller-Compatible-Nintendo-Console/dp/B00FJ2LQ3Y");
        tienda.agregarEnvioPublicacion(OstentJoystick,new Envio(true,1150, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(OstentJoystick,new Envio(false,650, E_Envio.Tierra));

        Publicacion moveMotionPs4 = tienda.agregarPublicacion("Control Move Motion para PS4","Move Motion PS4",E_Estado.NUEVO,"Sony","Move Motion","EEUU","PS4/PS3","Negro/Azul",350,true,39000,2,matias,"https://www.amazon.com/-/es/PlayStation-Move-Motion-Controllers-Pack-4/dp/B07B4BFC6S");
        tienda.agregarEnvioPublicacion(moveMotionPs4,new Envio(true,1250, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(moveMotionPs4,new Envio(false,550, E_Envio.Tierra));

        Publicacion mousePadHyperx = tienda.agregarPublicacion("Mouse pad Hyperx Fury S Pro Speed (XL) ","Alfombrilla de ratón gaming FURY S Pro",E_Estado.USADO,"Hyperx","Fury S Pro","EEUU","Todas","Rojo/Negro",300,true,5000,1,tomas,"https://row.hyperx.com/es/products/hyperx-fury-s-gaming-mouse-pad-speed-xl?variant=40853230125261");
        tienda.agregarEnvioPublicacion(mousePadHyperx,new Envio(true,1250, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(mousePadHyperx,new Envio(false,550, E_Envio.Tierra));

        Publicacion monitorAsus = tienda.agregarPublicacion("Monitor Asus 144hz sellado","Monitor Gamer Asus",E_Estado.NUEVO,"Asus","ROG Strix XG256Q","EEUU","Todas","Negro/Gris",3500,true,140000,1,matias,"https://rog.asus.com/latin/monitors/23-to-24-5-inches/rog-strix-xg256q-model/");
        tienda.agregarEnvioPublicacion(monitorAsus,new Envio(true,1250, E_Envio.Avion));
        tienda.agregarEnvioPublicacion(monitorAsus,new Envio(false,550, E_Envio.Tierra));







//        tienda.toArchivo("archivoUsuarios","archivoPublicaciones");
//        tienda.bajarArchivoUsuarios("archivoUsuarios");
//        tienda.bajarArchivoPublicaciones("archivoPublicaciones");



        GestionConsolaComandos consola = new GestionConsolaComandos(tienda);
        consola.menuPrincipal();


    }

}