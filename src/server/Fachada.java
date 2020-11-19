package server;

import java.util.ArrayList;

import dao.ColaDAO;
import dao.UsuarioDAO;
import dao.HorarioDao;
import dominio.Alumno;
import dominio.Usuario;
import dominio.Profesor;

/** Clase que se encarga de redirigir el trafico a los DAO */
public class Fachada {
    /**Metodo que se encarga de instanciar al Dao de la base de datos de colas para recibir todas las colas a la que esta aputnado un alumno
     * @param String id
     * @return  {@link ArrayList}
     */
    public static ArrayList<String> getColasAlumno(String idAlumno){
        return ColaDAO.getColasAlumno(idAlumno);


    }
    
    /**Metodo que se encarga de instanciar al Dao de la base de datos de colas para recibir la posición de un alumno en una cola
    * @param String id
    * @param String profesorCola
    * @return  {@link ArrayList}
    */
    public static int getPosicionAlumno(String idAlumno,String profesorCola){
        return ColaDAO.getPosicionColaAlumno(idAlumno, profesorCola);


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
    
    public static Usuario logIn(String user, String pass) {
        return UsuarioDAO.logIn(user, pass);    
    }
    public static ArrayList<String> getProfesoresAlumno(String idAlumno){
        return UsuarioDAO.getProfesoresAlumno(idAlumno);

    }
    public static ArrayList<Usuario> getColaProfesor(String idProfesor){
        return ColaDAO.getColaProfesor(idProfesor);
    }
    public static ArrayList<String> getClasesProfesor(String idProfesor){
        return UsuarioDAO.getClasesProfesor(idProfesor);

    }

    public static String getHorario(String idProfesor){
        return HorarioDao.getHorario(idProfesor);
    }
    
    /**Metodo encargado de solicitar a {@link ColaDao} que añada un alumno a la cola de un profesor
     * @param Alumno alumno
     * @param Profesor profesor
     */
    public static void addAlumnoCola(Alumno alumno, Profesor profesor){
        ColaDAO.addAlumnoCola(alumno,profesor);
    }
    public static void delAlumno(Usuario alumno, Profesor profesor){
        ColaDAO.delAlumnoCola(alumno, profesor);
    }

}