package ClasesExtra;

import java.util.UUID; //La clase UUID proporciona métodos para generar identificadores únicos universales.

/**
 * Clase creada con el objetivo de crear un generador de ID's.
 * @author Ludmila
 */
public class GeneradorUUID {
    /**
     * Este método generará un ID único.
     * @return Retorna un valor del tipo String.
     */
    public static String generarID() {
        // Se crea un nuevo objeto UUID utilizando el método estático randomUUID() de la clase UUID.
        // Esto generará un nuevo identificador único.
        UUID uuid = UUID.randomUUID();
        // Convierte el objeto UUID en una representación de cadena (string) del identificador único generado.
        return uuid.toString();
    }
}
