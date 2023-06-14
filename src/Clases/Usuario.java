package Clases;

import Enums.E_TipoUsuario;

public class Usuario {
    private String mail;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private E_TipoUsuario tipoUsuario;
    private String dni; // vale como ID
    private boolean verificado; // true: identidad verificada, false: no verificada
    // lista ventas
    // lista direcciones
    // lista metodos de pago
    private float promedioVentas;
}
