package Clases;

import Enums.E_CondFiscal;
import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;
import Excepciones.ExcepcionNumeroRepetido;

public class Main {
    public static void main(String[] args)
    {
        GestionTienda tienda = new GestionTienda();

        try
        {
            tienda.agregar("tomy.bianchi@hotmail.com","Tometo1234","Tomas","Bianchi","2233128691", E_TipoUsuario.NORMAL,"44956816");
            tienda.agregar("Ludmila.Ippoliti@gmail.com","Ludmila1234","Ludmila","Ippiliti","2237658907", E_TipoUsuario.VENTA,"428767765",true,"www.nike.com","204287677659", E_CondFiscal.MONOTRIBUTO_B);
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

        System.out.print(tienda.toString());









    }



















}