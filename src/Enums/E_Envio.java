package Enums;

public enum E_Envio
{
    Tierra("Tierra"), Avion ("Avion"), Barco("Barco");


    private String nombre;

    E_Envio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
