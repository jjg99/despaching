package dominio;

import java.util.ArrayList;

import server.Fachada;
import util.GestionMensajes;

/**Clase que se encarga del control de las colas de los despachos de los profesores */
public abstract class GestorCola {

    /**
     * Metodo que abre la cola, añadiendo el id del profesor a la tabla pertiente
     * @param id identificador unico del profesor que desea abrir la cola
     * @return <code>true</code> si se ha añadido satisfactoriamente el profesor, si da cualquier tipo de error devuelve <code>false</code>
     */
	public static boolean openCola(String id) {
        return Fachada.openCola(id);
	}

    /**
     * Metodo que confirma que el {@link Profesor} desea cerrar la cola y si confirma la cierra llamando a {@link Fachada.closeCola}
     * @param id dentificador unico del profesor que desea cerrar la cola
     * @return <code>true</code> si se ha añadido satisfactoriamente el profesor, si da cualquier tipo de error devuelve <code>false</code>
     */
	public static boolean closeCola(String id) {
        if(0 == GestionMensajes.msg2OpcionesGenerico(new Object[] {"Si", "No"}, "Al cerrar la cola se borraran todos los alumnos que estan en ella\n¿Seguro que desea cerrar la cola?"
        , "¿Desea cerrar la cola?")){
            return Fachada.closeCola(id);
        }else{
            return false;
        }
	}

    /**
    * metodo se encarga de conectarse a la fachada y recibir una lista con todos los despachos a los que se ah apuntado el alumno
    * @param String id
    * @return @ArrayList con los nombres de los profesores a los que esta apuntado */
    public static ArrayList<String> getColasAlumno(String id){
        
        //el 
        ArrayList<String> colasAlumno = Fachada.getColas(id);
        return colasAlumno;
    }
    /**Metoodo que deuelve al profesor la cola de alumnos que tiene */
    public static ArrayList<String>getColaProfesor(String idProfesor){
        return Fachada.getColaProfesor(idProfesor);

    }

    /**
     * Metodo que se encarga de obtener la posicion de un alumno en la cola 
     * @param idAlumno id del alumno
     * @param profesorCola id del profesor
     * @return int con la posicion del alumno en la cola
     */

    public static int getPosicionAlumno(String idAlumno, String profesorCola)
    {
        return Fachada.getPosicionAlumno(idAlumno, profesorCola);
    }
    
}
