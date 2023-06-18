package Excepciones;

public class ExcepcionNumeroRepetido extends Exception
{
    private String numero;

    public ExcepcionNumeroRepetido(String message, String numero) {
        super(message);
        this.numero = numero;
    }

    public String getMessage(){
        return super.getMessage() + numero;
    }
}
