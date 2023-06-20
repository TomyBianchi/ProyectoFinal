package Interfaces;


/**
 * Interface la cual sirva para que la implementen clases genericas, que usen especialemente HashMap como tipo de coleccion principal
 * @param <K>
 * @param <T>
 */
public interface I_IBML_Map<K,T>
{
    public void agregar(K key,T elemento);

    public void borrar(K key);
    public void modificar(K key, T elementoNuevo);

    public void listar();
}
