package Clases;

import Enums.E_CondFiscal;
import Enums.E_Estado;
import Enums.E_Pago;
import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, URISyntaxException
    {
        GestionTienda tienda = new GestionTienda();

        try
        {
            tienda.agregar("tomy","Aa1234567","Tomas","Bianchi","2233128691", E_TipoUsuario.NORMAL,"1");
            tienda.agregar("Ludmila.Ippoliti@gmail.com","Ludmila1234","Ludmila","Ippoliti","2237658907", E_TipoUsuario.VENTA,"2","www.nike.com","204287677659", E_CondFiscal.MONOTRIBUTO_B);
            tienda.agregar("Mayi.Mauro@gmail.com","Mati1234","Matias","Mauro","2234321123", E_TipoUsuario.NORMAL,"3");
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
        Usuario tomas = mapa.get("1");
        tomas.agregarMetodoDePago(new MetodoDePago(E_Pago.DEBITO,"Tomas Bianchi Silvestre", "4234778699087654","876","06","27"));
        Usuario ludmila = mapa.get("2");
        Usuario matias = mapa.get("3");


        tienda.agregarPublicacion("Auriculares Hyperx Cloud Flight, perfecto estado","Hyperx Cloud Flight", E_Estado.USADO,"Hyperx","Cloud Flight","EEUU","PS4/PS5/PC","Rojo",200,true,35000,1,tomas,"foto.com");
        tienda.agregarPublicacion("Joystick PS4","Joystick", E_Estado.USADO,"Sony","Joystick","EEUU","PS3/PS4/PS5/PC","Negro",150,true,12000,1,tomas,"fotito.com");
        tienda.agregarPublicacion("Teclado Gamer Razer Ornata V3, luz LED RGB","Razer Ornata V3",E_Estado.NUEVO,"Razer","Ornata V3","Canadá","PC","Negro Clásico",3,false,16900,3,ludmila,"fotaza.com");
        tienda.agregarPublicacion("MightySkins importados NUEVOS","MightySkins",E_Estado.NUEVO,"Mighty Skins","Oculus Quest 2","Japón","Oculus Quest 2","Algodón de azúcar",1,true,60000,2,ludmila,"foton.com");
        tienda.agregarPublicacion("Auriculares JBL Reflect Aero - Como nuevos","JBL REFLECT",E_Estado.USADO,"JBL","Reflect Aero TWS","Australia","Productos con Bluetooth 5.0","Negro",1,true,4700,1,matias,"fotoncito.com");
        tienda.agregarPublicacion("JBL Tune 510BT inalambricos","JBL Tune",E_Estado.NUEVO,"JBL","Tune 510BT","EEUU","Productos con Bluetooth 5.0","Negro",1,true,6000,4,matias,"fotis.com");
        tienda.agregarPublicacion("The Apple Magic Mouse 2, superficie multi-touch","Apple Magic Mouse 2",E_Estado.NUEVO,"Apple","Magic Mouse 2","EEUU","PC, teclados","Amarillo",2,true,20700,2,tomas,"fotos.com");
        tienda.agregarPublicacion("OSTENT Joystick Retro Gamer con cable PS2- Buen estado","OSTENT Joystick",E_Estado.USADO,"OSTENT","Video Games","China","PS2/PS1/PS/PS one/PSX","Plateado",2,false,3000,1,ludmila,"photo.com");
        tienda.agregarPublicacion("Control Move Motion para PS4","Move Motion PS4",E_Estado.NUEVO,"Sony","Move Motion","EEUU","PS4/PS3","Negro/Azul",2,true,39000,2,matias,"pics.com");


//        System.out.print(tienda.toString());

        GestionConsolaComandos consola = new GestionConsolaComandos(tienda);
        consola.menuPrincipal();

    }

}