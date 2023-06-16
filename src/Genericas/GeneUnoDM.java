package Genericas;

import Interfaces.I_ABML;

import java.util.HashSet;


/**
 * Clase genérica que utiliza la clase Usuario para sus listas de métodos de pago y de direcciones.
 * @param <T>
 */
public class GeneUnoDM<T> implements I_ABML
{
    private HashSet<T> set;

    //metodos

    /**
     * Método que agrega un elemento al set genérico.
     * @param elemento método de pago o dirección
     */
    public void agregar(T elemento)
    {
        set.add(elemento);
    }
}
