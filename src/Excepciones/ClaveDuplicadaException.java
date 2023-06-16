package Excepciones;

/**
 * Excepción que se lanza al encontrar una clave duplicada.
 * @author Ludmila
 */
public class ClaveDuplicadaException extends Exception {
    public ClaveDuplicadaException(String info)
    {
        super(info);
    }
}
