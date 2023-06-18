package Genericas;

import Interfaces.I_ABML;

import java.io.Serializable;
import java.util.ArrayList;

public class GeneTresE <T> implements I_ABML<T>, Serializable
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

    @Override
    public String toString() {
        return "GeneTresE{" +
                "lista=" + lista +
                '}';
    }
}
