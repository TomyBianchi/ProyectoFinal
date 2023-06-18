package Excepciones;

/**
 * Invalida en cuanto a nuestras necesidades de hacer una contraseña medianamente segura
 */
public class ExcepcionConstrasenaInvalida extends Exception {
    public ExcepcionConstrasenaInvalida(String info)
    {
        super(info);
    }
}
