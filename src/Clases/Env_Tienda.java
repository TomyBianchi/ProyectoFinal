package Clases;


import Genericas.GeneDosPU;

/**
 * Envoltorio
 */
public class Env_Tienda
{
    private GeneDosPU<String, Usuario> usuarios;
    private GeneDosPU<String,Publicacion> publicaciones;

    //constructor


    public Env_Tienda() {
        this.usuarios = new GeneDosPU<>();
        this.publicaciones = new GeneDosPU<>();
    }





}
