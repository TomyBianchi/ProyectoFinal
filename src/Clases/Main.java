package Clases;

import Enums.E_TipoUsuario;
import Excepciones.ClaveDuplicadaException;
import Excepciones.ConstrasenaInvalidaException;

public class Main {
    public static void main(String[] args)
    {
        GestionTienda MercadoLibre = new GestionTienda();
        try
        {
            MercadoLibre.agregar("ludmila@hotmail.com","Chester27","Ludmila","Ippoliti","22369", E_TipoUsuario.NORMAL,"41149");
        }
        catch (ClaveDuplicadaException e)
        {
            System.out.println("ERROR: El DNI o mail ya está registrado.");
        }
        catch (ConstrasenaInvalidaException e)
        {
            System.out.println("ERROR: Constraseña poco segura.");
        }










    }



















}