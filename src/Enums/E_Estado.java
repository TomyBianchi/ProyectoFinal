package Enums;

public enum E_Estado
{
    NUEVO("Nuevo"), USADO("Usado");

    private String nombre;

    E_Estado(String nombre) {
        this.nombre = nombre;
    }
}
