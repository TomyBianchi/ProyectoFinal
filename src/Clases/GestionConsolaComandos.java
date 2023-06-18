package Clases;

import java.util.Scanner;

import static Clases.Main.teclado;


public class GestionConsolaComandos
{


    private GestionTienda tienda;

    public GestionConsolaComandos(GestionTienda tienda) {
        this.tienda = tienda;
    }

    public void menuPrincipal() {
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
        System.out.print(espacio +codigoNegrita + codigoSubrayado+ opcion1 + codigoReset + "\n"); //opciones
        System.out.print(espacio + codigoNegrita + codigoSubrayado+ opcion2 + codigoReset  + "\n");

        String respuesta = teclado.next();



    }









}
