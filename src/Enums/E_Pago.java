package Enums;

public enum E_Pago
{
    DEBITO("Debito"), CREDITO("Credito");


    private String nombre;

    E_Pago(String nombre) {
        this.nombre = nombre;
    }
}
