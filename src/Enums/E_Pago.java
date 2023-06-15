package Enums;

public enum E_Pago
{
    DEBITO("Débito"), CREDITO("Crédito");


    private String nombre;

    E_Pago(String nombre) {
        this.nombre = nombre;
    }
}
