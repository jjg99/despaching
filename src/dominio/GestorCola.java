package dominio;

import java.util.ArrayList;
import server.Fachada;

/**Clase que encarga del control de las colas de los despachos de los profesores */
public abstract class GestorCola {

    /**
     * metodo se encarga de conectarse a la fachada y recibir una lista con todos los despachos a los que se ah apuntado el alumno
     * @param String id
     * @return @ArrayList con los nombres de los profesores a los que esta apuntado */
    public static ArrayList<String> getColasAlumno(String id){
        
        //el 
        ArrayList<String> colasAlumno = Fachada.getColas(id);
        return colasAlumno;
    }
    
}
