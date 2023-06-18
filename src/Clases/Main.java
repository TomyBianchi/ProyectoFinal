package Clases;

import Enums.E_TipoUsuario;
import Excepciones.ExcepcionClaveDuplicada;
import Excepciones.ExcepcionConstrasenaInvalida;
import Excepciones.ExcepcionMailYaExiste;

public class Main {
    public static void main(String[] args)
    {
        GestionTienda MercadoLibre = new GestionTienda();
        try
        {
            MercadoLibre.agregar("ludmila@hotmail.com","Chester27","Ludmila","Ippoliti","22369", E_TipoUsuario.NORMAL,"41149");
        }
        catch (ExcepcionClaveDuplicada e)
        {
            System.out.println("ERROR: El DNI o mail ya está registrado.");
        }
        catch (ExcepcionConstrasenaInvalida e)
        {
            System.out.println("ERROR: Constraseña poco segura.");
        }
        catch (ExcepcionMailYaExiste e)
        {
            System.out.println("ERROR: El mail ingresado ya está registrado.");
        }










    }



















}