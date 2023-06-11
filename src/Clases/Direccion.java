package Clases;

import java.util.Objects;

public class Direccion {
    private String provincia;
    private String ciudad;
    private String calle;
    private String altura;
    private String departamento; // piso + letra del departamento
    private String cp; // código postal

    // Constructor completo para departamento
    public Direccion(String provincia, String ciudad, String calle, String altura, String departamento, String cp) {
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.altura = altura;
        this.departamento = departamento;
        this.cp = cp;
    }
    // Constructor completo sin piso para casa
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(provincia, direccion.provincia) && Objects.equals(ciudad, direccion.ciudad) && Objects.equals(calle, direccion.calle) && Objects.equals(altura, direccion.altura) && Objects.equals(departamento, direccion.departamento) && Objects.equals(cp, direccion.cp);
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
}
