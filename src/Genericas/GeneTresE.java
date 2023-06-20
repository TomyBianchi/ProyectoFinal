package Genericas;

import Interfaces.I_ABML;
import Interfaces.I_toJSONArray;
import Interfaces.I_toJSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase generica que utiliza Publicacion, para guardar a los diferentes envios aca dentro.
 * Tambien la usa la clase GestionConsolaComandos, para hacer algunas verificaciones dentro de ella.
 */
public class GeneTresE <T extends I_toJSONObject> implements I_ABML<T>, Serializable, I_toJSONArray
{
    private ArrayList<T> lista;

    public GeneTresE() {
        this.lista = new ArrayList<>();
    }

    //getter de la lista
    public ArrayList<T> getLista() {
        return lista;
    }

    public void agregar(T element)
    {
        lista.add(element);
    }
    public void borrar(T elemento) {
        lista.remove(elemento);
    }

    public void listar()
    {
        String concanet = "";
        for(int i = 0; i < lista.size(); i++)
        {
            concanet+=lista.get(i).toString();
        }
    }

    public void modificar(T elemento, T elementoNuevo)
    {
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).equals(elemento))
            {
                lista.remove(elemento);
                lista.add(elementoNuevo);
            }
        }
    }

    /**
     * Convierte a toda la lista en un json array, lo que hace que T necesite implementar I_toJSONObject
     * @return returna el arrau
     * @throws JSONException
     */
    public JSONArray toJSONArray()throws JSONException
    {
        JSONArray array = new JSONArray();
        for(int i = 0; i < lista.size(); i++)
        {
            T variable = lista.get(i);
            JSONObject object = variable.toJSONObject();
            array.put(object);
        }
    return array;
    }


    @Override
    public String toString() {
        return "GeneTresE{" +
                "lista=" + lista +
                '}';
    }
}
