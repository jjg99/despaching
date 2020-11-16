package dominio;

import java.util.ArrayList;
import server.Fachada;

/**Clase que encarga del control de las colas de los despachos de los profesores */
public abstract class GestorCola {

    /**
     * Metodo que abre la cola, añadiendo el id del profesor a la tabla pertiente
     * @param id identificador unico del profesor
     * @return <code>true</code> si se ha añadido satisfactoriamente el profesor, si da cualquier tipo de error devuelve false
     */
	public static boolean openCola(String id) {
        return Fachada.openCola(id);
	}

    // /**
    //  * metodo se encarga de conectarse a la fachada y recibir una lista con todos los despachos a los que se ah apuntado el alumno
    //  * @param String id
    //  * @return @ArrayList con los nombres de los profesores a los que esta apuntado */
    // public static ArrayList<String> getColasAlumno(String id){
        
    //     //el 
    //     ArrayList<String> colasAlumno = Fachada.getColas(id);
    //     return colasAlumno;
    // }
    
}
