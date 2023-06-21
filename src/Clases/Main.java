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

            //usuarios ya agregados en el archivo, cada uno tiene varias publicaciones
//        "tomy.bianchi@hotmail.com","Tomas1234"
//        "Ludmila.Ippoliti@gmail.com","Ludmila1234"
//        "Mayi.Mauro@gmail.com","Matias1234"


        GestionConsolaComandos consola = new GestionConsolaComandos(tienda);
        consola.menuPrincipal();


    }

}