package Genericas;

import Interfaces.I_ABML;
import Interfaces.I_IBML_Map;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase genérica que va a utilizar la clase envoltorio GestionTienda para su lista de usuarios,
 * y la clase Usuario para su lista de publicaciones.
 * @author tomas
 */
public class GeneDosPU<K,T> implements I_IBML_Map<T,K>, Serializable
{
    private HashMap<K,T> mapa;

    public GeneDosPU() {
        this.mapa = new HashMap<>();
    }


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

    /**
     * Borra el elemento pasado por parametro
     */
    public void borrar(K key) //fijarse no se si funciona esto
    {
        mapa.remove(key);
    }

    public void listar()
    {
        Iterator<Map.Entry<K,T>> it = mapa.entrySet().iterator();
        String concanet = "";
        while(it.hasNext())
        {
            Map.Entry<K,T> entry = it.next();
            concanet += entry.toString();
        }
    }

    /**
     * Pasa el elem
     * @param key Es la llave, con la que vamos a eliminar eso del mapa, y vamos a poner el nuevo elemento
     * @param elementoNuevo Aca se pasa el nuevo elemento ya modificado, por lo que se va a sacar el elemento viejo y se va a poner el nuevo
     * @return retur
     */
    public void modificar(K key, T elementoNuevo)
    {

        Iterator<Map.Entry<K,T>> it = mapa.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<K,T> entry = it.next();
            if(entry.getKey().equals(key))
            {
                mapa.remove(key);
                mapa.put(key, elementoNuevo);
            }
        }
    }


}
