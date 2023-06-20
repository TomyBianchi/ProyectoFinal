package Interfaces;


/**
 * Interfaz que van a implementar las clases genericas en las cuales sea necesario agregar, borrar, modificar y listar elementos.
 * Estas clases genericas van a tener que usar una coleccion. No puede usar un map.
 */
public interface I_ABML <T> {

    public void agregar(T elemento);

    public void borrar(T elemento);
    public void modificar(T elemento, T elementoNuevo);

    public void listar();





}
