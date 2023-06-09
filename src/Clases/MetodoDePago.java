package Clases;

import java.io.Serializable;

import Enums.E_Pago;

/**
 * Se usa en Usuario, con una lista generica. Sirve para saber que tipos de metodos de pago tienen los distintos tipos
 * de usuarios. Tambien para verificar compras dentro de la clase GestionConsolaComandos.
 */
public class MetodoDePago implements Serializable
{
    // ATRIBUTOS
    private E_Pago tipoPago; // tipo de método de pago
    private String nombre; // nombre que aparece en la tarjeta
    private String numeroTarjeta;
    private String cvv;
    private String mesVencimiento;
    private String anoVencimiento;

    // CONSTRUCTORES
    public MetodoDePago(E_Pago tipoPago, String nombre, String numeroTarjeta, String cvv, String mesVencimiento, String anoVencimiento) {
        this.tipoPago = tipoPago;
        this.nombre = nombre;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.mesVencimiento = mesVencimiento;
        this.anoVencimiento = anoVencimiento;
    }
    public MetodoDePago() {
        this.tipoPago = null;
        this.nombre = "";
        this.numeroTarjeta = "";
        this.cvv = "";
        this.mesVencimiento = "";
        this.anoVencimiento = "";
    }

    // GETTERS, SETTERS Y OTROS
    public E_Pago getTipoPago() {
        return tipoPago;
    }

    public String getNombre() {
        return nombre;
    }

    private String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Te devuelve un String, que se completa con X, menos los ultimos cuatro digitos que son de la tarjeta.
     * @return Returna el String lleno de X, menos los ultimos cuatro digitos que son de la tarjeta
     */
    public String getUltimosCuatroTargeta()
    {
        String aux = "XXXX XXXX XXXX  ";

        aux += this.numeroTarjeta.substring(this.numeroTarjeta.length() - 4);
        return aux; //returna los ultimos 4 numeros de la tarjeta.
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof MetodoDePago)
            {
                MetodoDePago aux = (MetodoDePago) o;
                if(getUltimosCuatroTargeta().equals(aux.getTipoPago()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 6;
    }

    @Override
    public String toString() {
        return "MetodoDePago{" +
                "tipoPago=" + tipoPago +
                ", nombre='" + nombre + '\'' +
                ", numeroTarjeta='" + getUltimosCuatroTargeta() + '\'' +
                '}';
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof MetodoDePago)
            {
                MetodoDePago aux = (MetodoDePago)o;
                String auxUno = new String(getNumeroTarjeta());  //estos dos errores no impide que compile el programa
                String auxDos = new String(aux.getNumeroTarjeta());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
