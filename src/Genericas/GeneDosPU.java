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

    /**
     * Método que agrega un elemento al mapa genérico. La clave va a ser el DNI del usuario.
     * @param key clave (DNI)
     * @param elemento - usuario o publicacion
     */
    public void agregar(K key, T elemento)
    {
        mapa.put(key,elemento);
    }

    /**
     * Método que retorna si el mapa contiene una clave pasada por parámetro. La clave va a ser el DNI del usuario.
     * @param key clave (DNI)
     * @return Retorna verdadero si contiene la clave, falso si no la contiene.
     */
    public boolean contieneClave(K key)
    {
        return mapa.containsKey(key);
    }
}
