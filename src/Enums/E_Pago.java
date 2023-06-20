package Enums;

/**
 * Enum de las diferentes modalidades de pago que aceptan los usuarios de la aplicacion.
 */
public enum E_Pago
{
    DEBITO("Débito"), CREDITO("Crédito");


    private String nombre;

    E_Pago(String nombre) {
        this.nombre = nombre;
    }
}
