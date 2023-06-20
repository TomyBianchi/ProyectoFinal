package Enums;

/**
 * Enum de los dos diferentes tipos de estados que pueden tener los Perifericos.
 */
public enum E_Estado
{
    NUEVO("Nuevo"), USADO("Usado");

    private String nombre;

    E_Estado(String nombre) {
        this.nombre = nombre;
    }
}
