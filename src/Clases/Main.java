package Clases;

import Enums.E_CondFiscal;
import Enums.E_Estado;
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
        Usuario ludmila = mapa.get("2");
        Usuario matias = mapa.get("3");


        tienda.agregarPublicacion("Auriculares Hyperx Cloud Flight, perfecto estado", E_Estado.USADO,"Hyperx","Cloud Flight","EEUU","PS4/PS5/PC","Rojo",200,true,35000,1,tomas,"foto.com");
        tienda.agregarPublicacion("Joystick PS4", E_Estado.USADO,"Sony","Joystick","EEUU","PS3/PS4/PS5/PC","Negro",150,true,12000,1,tomas,"fotito.com");
        tienda.agregarPublicacion("Teclado Gamer Razer Ornata V3, luz LED RGB",E_Estado.NUEVO,"Razer","Ornata V3","EEUU","PC","Negro Clásico",3,false,6900,3,ludmila,"fotaza.com");
        tienda.agregarPublicacion("MightySkins importados NUEVOS",E_Estado.NUEVO,"Mighty Skins","Oculus Quest 2","EEUU","Oculus Quest 2","Algodón de azúcar",1,true,60000,2,ludmila,"foton.com");
        tienda.agregarPublicacion("Auriculares JBL Reflect Aero - Como nuevos",E_Estado.USADO,"JBL","Reflect Aero TWS","EEUU","Productos con Bluetooth 5.0","Negro",1,true,4000,1,matias,"fotoncito.com");
        tienda.agregarPublicacion("JBL Tune 510BT inalambricos",E_Estado.NUEVO,"JBL","Tune 510BT","EEUU","Productos con Bluetooth 5.0","Negro",1,true,6000,4,matias,"fotin.com");


//        System.out.print(tienda.toString());

        GestionConsolaComandos consola = new GestionConsolaComandos(tienda);
        consola.menuPrincipal();

    }

}