package Clases;

import java.util.Objects;

public class Periferico {
    private String nombre;
    private int id; // autogenerable
    // foto
    private boolean estado; // true: nuevo, false: usado
    private String marca;
    private String modelo;
    private String origen;
    private String tags;
    private String plataformas;
    private String color;
    private float peso;
    private boolean inalambrico; // true: inalámbrico, false: con cable

    public Periferico(String nombre, int id, boolean estado, String marca, String modelo, String origen, String tags, String plataformas, String color, float peso, boolean inalambrico) {
        this.nombre = nombre;
        this.id = id;
        this.estado = estado;
        this.marca = marca;
        this.modelo = modelo;
        this.origen = origen;
        this.tags = tags;
        this.plataformas = plataformas;
        this.color = color;
        this.peso = peso;
        this.inalambrico = inalambrico;
    }
    public Periferico() {
        nombre = "";
        id = 0;
        estado = true;
        marca = "";
        modelo = "";
        origen = "";
        tags = "";
        plataformas = "";
        color = "";
        peso = 0;
        inalambrico = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(String plataformas) {
        this.plataformas = plataformas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periferico that = (Periferico) o;
        return id == that.id && estado == that.estado && Float.compare(that.peso, peso) == 0 && inalambrico == that.inalambrico && Objects.equals(nombre, that.nombre) && Objects.equals(marca, that.marca) && Objects.equals(modelo, that.modelo) && Objects.equals(origen, that.origen) && Objects.equals(tags, that.tags) && Objects.equals(plataformas, that.plataformas) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Periferico{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", estado=" + estado +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", origen='" + origen + '\'' +
                ", tags='" + tags + '\'' +
                ", plataformas='" + plataformas + '\'' +
                ", color='" + color + '\'' +
                ", peso=" + peso +
                ", inalambrico=" + inalambrico +
                '}';
    }
}
