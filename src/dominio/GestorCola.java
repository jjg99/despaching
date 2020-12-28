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
	public static boolean closeCola(String id, boolean first) {
        if(first){
            System.out.print("Se cierra la cola");
            return Fachada.closeCola(id);
        } else if(0 == GestionMensajes.msg2OpcionesGenerico(new Object[] {"Si", "No"}, "Al cerrar la cola se borraran todos los alumnos que estan en ella\n¿Seguro que desea cerrar la cola?"
            , "¿Desea cerrar la cola?")){
                return Fachada.closeCola(id);
        }else{
            return false;
        }
	}

    /**
    * metodo se encarga de conectarse a la fachada y recibir una lista con todos los despachos a los que se ha apuntado el alumno
    * @param String id
    * @return ArrayList con los nombres de los profesores a los que esta apuntado */
    public static ArrayList<Profesor> getColasAlumno(String id){
        
        ArrayList<Profesor> colasAlumno = Fachada.getColasAlumno(id);
        return colasAlumno;
    }
    /**Metodo que deuelve al profesor la cola de alumnos que tiene */
    public static ArrayList<Alumno>getColaProfesor(String idProfesor){
        return Fachada.getColaProfesor(idProfesor);

    }

    /**
     * Metodo que se encarga de obtener la posicion de un alumno en la cola 
     * @param idAlumno id del alumno
     * @param profesorCola id del profesor
     * @return int con la posicion del alumno en la cola
     */

    public static int getPosicionAlumno(String idAlumno, String idProfesor)
    {
        return Fachada.getPosicionAlumno(idAlumno, idProfesor);
    }
    /**Metodo encargado de solicitar a {@link Fachada} que añada un alumno a la cola de un profesor
     * @param Alumno alumno
     * @param Profesor profesor
     */
    public static void addAlumnoCola(Alumno alumno, Profesor profesor){
        // se le pide a la fachada que añada un alumno
        Fachada.addAlumnoCola(alumno, profesor);

    }

    /**
     * Metodo encargado de solicitar a {@link Fachada} que elimine un alumno de la cola de un profesor
     * @param alumno Atributo Alumno
     * @param profesor Atributo Profesor
     */
    public static void delAlumno(Alumno alumno, Profesor profesor){
        Fachada.delAlumnoCola(alumno,profesor);

    }
    
    /**
     * Metodo que comprueba si la cola de un profesor esta abierta o no
     * @param idProfesor String que indica el id de un profesor
     * @return Boolean que devuelve si la cola esta abierta
     */
    public static Boolean isColaAbierta(String idProfesor) {
        return Fachada.isColaAbierta(idProfesor);
    }
    
}
