package Enums;

public enum E_Conector
{
    USBC("USB C"), USBA("USB A"), HDMI("HDMI"), DISPLAYPORT("Display Port"), JACK("Jack entrada y salida");

    private String nombre;

    E_Conector(String nombre) {
        this.nombre = nombre;
    }
}
