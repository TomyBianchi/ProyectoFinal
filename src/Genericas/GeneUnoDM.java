package Genericas;

import Interfaces.I_ABML;

import java.util.HashSet;


/**
 * Clase generica que en este programa la van a estar usando Usuario, con una GeneUnoDM de MetodosDePago, y otra con direcciones
 * @param <T>
 */
public class GeneUnoDM<T> implements I_ABML
{
    private HashSet<T> set;




}
