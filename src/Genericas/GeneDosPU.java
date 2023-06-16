package Genericas;

import Interfaces.I_ABML;

import java.util.HashMap;

/**
 * Clase genérica que va a utilizar la clase envoltorio GestionTienda para su lista de usuarios,
 * y la clase Usuario para su lista de publicaciones.
 * @author tomas
 */
public class GeneDosPU<K,T> implements I_ABML
{
    private HashMap<K,T> mapa;

    //metodos

    public void agregar(K key, T elemento)
    {
        mapa.put(key,elemento);
    }
}
