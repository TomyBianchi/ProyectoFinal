package Enums;

/**
 * Enum que tiene los diferentes tipos de envio de la aplicaccion. Este lo usa Envio.
 */
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
