package Clases;

import Enums.E_CondFiscal;
import Enums.E_Estado;
import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;
import Genericas.GeneDosPU;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, URISyntaxException {


        GestionTienda tienda = new GestionTienda();

        try
        {
            tienda.agregar("tomy","Aa1234567","Tomas","Bianchi","2233128691", E_TipoUsuario.NORMAL,"1");
            tienda.agregar("Ludmila.Ippoliti@gmail.com","Ludmila1234","Ludmila","Ippiliti","2237658907", E_TipoUsuario.VENTA,"428767765","www.nike.com","204287677659", E_CondFiscal.MONOTRIBUTO_B);
            tienda.agregar("Mayi.Mauro@gmail.com","Mati1234","Matias","Mauro","2234321123", E_TipoUsuario.NORMAL,"432348890");
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
        };


        GeneDosPU<String,Usuario> usuarios = tienda.getUsuarios();
        HashMap<String,Usuario> mapa = usuarios.getMapa();
        Usuario tomas = mapa.get("1");


        tienda.agregarPublicacion("Auriculares Hyperx Cloud Flight, perfecto estado", E_Estado.USADO,"Hyperx","Cloud Flight","EEUU","PS4/PS5/PC","Rojo",200,true,35000,1,tomas,"foto.com");
        tienda.agregarPublicacion("Joystick ps4", E_Estado.USADO,"Sony","Joystick","EEUU","PS3/PS4/PS5/PC","Negro",150,true,12000,1,tomas,"fotito.com");






//        System.out.print(tienda.toString());
//
        GestionConsolaComandos consola = new GestionConsolaComandos(tienda);
        consola.menuPrincipal();









    }



















}