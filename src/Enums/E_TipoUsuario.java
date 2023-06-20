package Enums;

/**
 * Enum de los tipos de usuarios que se pueden generar en la aplicacion.
 */
public enum E_TipoUsuario
{
    NORMAL("Usuario normal (compra y venta)"), VENTA("Usuario sólo de venta");


    private String nombre;

    E_TipoUsuario(String nombre) {
        this.nombre = nombre;
    }
}
