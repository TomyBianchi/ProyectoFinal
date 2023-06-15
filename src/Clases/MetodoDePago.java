package Clases;

import Enums.E_Pago;

public class MetodoDePago
{
    private E_Pago tipoPago; //tipo de metodo de pago
    private String nombre; //nombre que aparece en la tarjeta
    private String numeroTarjeta;
    private String cvv;
    private String mesVencimiento;
    private String anoVencimiento;

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

    //getters y setters


    public E_Pago getTipoPago() {
        return tipoPago;
    }

    public String getNombre() {
        return nombre;
    }

    private String getNumeroTarjeta() {
        return numeroTarjeta;
    }

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
                if(getNumeroTarjeta().equals(aux.getNumeroTarjeta()))
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
