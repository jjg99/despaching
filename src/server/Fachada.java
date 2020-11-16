package server;

import dao.ColaDAO;

import java.util.ArrayList;

/** Clase que se encarga de redirigir el trafico a los DAO */
public class Fachada {
    /**Metodo que se encarga de instanciar al Dao de la base de datos de colas para recibir todas las colas a la que esta aputnado un alumno
     * @param String idAlumno
     * @return  {@link ArrayList}
     */
    public static ArrayList<String> getColasAlumno(String idAlumno){
        return ColaDAO.getColasAlumno(idAlumno);
    }

    /**
     * Metodo que abre la cola de un profesor añadiendolo en la tabla de Colas de la base de datos llamado a {@link ColaDAO.openCola}.
     * @param id Identificacion del profesor que va a abrir cola
     * @return <code>true</code> si el profesor se agrego a la base de datos satisfactoriamente
     */
    public static boolean openCola(String id){
        return ColaDAO.openCola(id);
        
    }
    /**
     * Metodo que cierra al cola de un profesor, removiendolo de la tabla de Colas llamando a {@link ColaDAO.closeCola} 
     * @param id Identificacion del profesor que va a cerrar cola
     * @return <code>true</code> si el profesor se borro de la base de datos satisfactoriamente
     */
	public static boolean closeCola(String id) {
		return ColaDAO.closeCola(id);
    }
    public static ArrayList<String> getProfesores(String idAlumno){
        return ClaseDAO.getProfesores(idAlumno);

    }
}