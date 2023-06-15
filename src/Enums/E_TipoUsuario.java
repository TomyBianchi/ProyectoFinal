package Enums;

public enum E_TipoUsuario
{
    NORMAL("Usuario normal (compra y venta)"), VENTA("Usuario sólo de venta");


    private String nombre;

    E_TipoUsuario(String nombre) {
        this.nombre = nombre;
    }
}
