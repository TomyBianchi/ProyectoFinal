package Genericas;

import Interfaces.I_ABML;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Clase genérica que utiliza la clase Usuario para sus listas de métodos de pago y de direcciones.
 * @param <T>
 */
public class GeneUnoDM<T> implements I_ABML<T>, Serializable
{
    private HashSet<T> set;

    //constructor
    public GeneUnoDM() {
        this.set = new HashSet<>();
    }

    //getter del set
    public HashSet<T> getSet() {
        return set;
    }

    //metodos
    /**
     * Método que agrega un elemento al set genérico.
     * @param elemento método de pago o dirección
     */

    public void agregar(T elemento)
    {
        set.add(elemento);
    }

    /**
     * Borra un elemento de la lista, hay que pasarle el elemento a eliminiar.
     * @param elemento
     */
    public void borrar(T elemento)
    {
        this.set.remove(elemento);
    }

    public void listar()
    {
        String list = "";
        Iterator<T> it = this.set.iterator();
        while (it.hasNext())
        {
            list += it.next().toString();
        }
    }

    /**
     * Pasa el elem
     * @param elemento Es el elemento que se quiere modificar
     * @param elementoNuevo Aca se pasa el nuevo elemento ya modificado, por lo que se va a sacar el elemento viejo y se va a poner el nuevo
     * @return retur
     */
    public void modificar(T elemento, T elementoNuevo)
    {

        Iterator<T> it = this.set.iterator();
        while (it.hasNext())
        {
            T element = it.next();
            if(element.equals(elemento))
            {
                it.remove();
                set.add(elementoNuevo);
            }
        }
    }

    @Override
    public String toString() {
        return "GeneUnoDM{" +
                "set=" + set +
                '}';
    }
}
