package Enums;

public enum E_Envio
{
    Tierra("Por tierra"), Avion ("Aereo"), Barco("Maritimo");


    private String nombre;

    E_Envio(String nombre) {
        this.nombre = nombre;
    }
}
