package Clases;

import java.io.Serializable;

public class Direccion implements Serializable
{
    // ATRIBUTOS
    private String provincia;
    private String ciudad;
    private String calle;
    private String altura;
    private String departamento; // piso + letra del departamento
    private String cp; // código postal

    // CONSTRUCTORES
    // Completo para departamento
    public Direccion(String provincia, String ciudad, String calle, String altura, String departamento, String cp) {
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.altura = altura;
        this.departamento = departamento;
        this.cp = cp;
    }
    // Sin piso para casa
    public Direccion(String provincia, String ciudad, String calle, String altura, String cp) {
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.altura = altura;
        this.departamento = "";
        this.cp = cp;
    }
    public Direccion() {
        provincia = "";
        ciudad = "";
        calle = "";
        altura = "";
        departamento = "";
        cp = "";
    }

    //GETTERS, SETTERS Y OTROS
    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public String getAltura() {
        return altura;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCp() {
        return cp;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean rta = false;

        if(o != null)
        {
            if(o instanceof Direccion)
            {
                Direccion aux = (Direccion) o;
                if(getCalle().equals(aux.getCalle()) && getAltura().equals(aux.getAltura()))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", calle='" + calle + '\'' +
                ", altura='" + altura + '\'' +
                ", departamento='" + departamento + '\'' +
                ", cp='" + cp + '\'' +
                '}';
    }

    public int compareTo(Object o)
    {
        int rta = 0;

        if(o != null)
        {
            if(o instanceof Direccion)
            {
                Direccion aux = (Direccion)o;
                String auxUno = new String(getAltura());  //estos dos errores no impide que compile el programa
                String auxDos = new String(aux.getAltura());
                rta = auxDos.compareTo(auxUno);
            }
        }
        return rta;
    }
}
