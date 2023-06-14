package Genericas;

import Interfaces.I_ABML;

import java.util.HashMap;

/**
 * Esta va a ser una clase generica que se va a usar en la envoltorio general.
 * @author tomas
 */
public class G_Usuario<T,K> implements I_ABML
{
    private HashMap<T,K> mapa;

    public G_Usuario() {
        this.mapa = new HashMap<>();
    }



}
