package Excepciones;

/**
 * Excepción que se lanza al encontrar una clave duplicada.
 * @author Ludmila
 */
public class ExcepcionClaveDuplicada extends Exception {

    public ExcepcionClaveDuplicada(String info)
    {
        super(info);
    }


}
