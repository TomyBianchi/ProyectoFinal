package Enums;

public enum E_Envio
{
    Tierra("Por tierra"), Avion ("Aéreo"), Barco("Marítimo");


    private String nombre;

    E_Envio(String nombre) {
        this.nombre = nombre;
    }
}
