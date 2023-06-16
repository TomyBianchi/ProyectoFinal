package Interfaces;

public interface I_IBML_Map<T,K>
{
    public void agregar(K key,T elemento);

    public void borrar(K key);
    public void modificar(K key, T elementoNuevo);

    public void listar();
}
