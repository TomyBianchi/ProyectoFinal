package Clases;


import Genericas.G_Publicacion;
import Genericas.G_Usuario;

/**
 * Envoltorio
 */
public class Env_Tienda
{
    private G_Usuario<String, Usuario> usuarios;
    private G_Publicacion<String,Publicacion> publicaciones;

    //constructor


    public Env_Tienda() {
        this.usuarios = new G_Usuario<>();
        this.publicaciones = new G_Publicacion<>();
    }




    
}
