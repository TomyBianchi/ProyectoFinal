package Interfaces;


/**
 * Interfaz que van a implementar las clases en las cuales sea necesario agregar, borrar, modificar y listar elementos.
 * @author  tomy
 */
public interface I_ABML <T> {

    public void agregar(T elemento);

    public void borrar(T elemento);
    public void modificar(T elemento, T elementoNuevo);

    public void listar();





}
