package server;

import java.util.ArrayList;

import dao.ColaDAO;
import dao.UsuarioDAO;
import dao.HorarioDAO;
import dominio.Alumno;
import dominio.Usuario;
import dominio.Profesor;

/** Clase que se encarga de redirigir el trafico a los DAO */
public class Fachada {
    /**Metodo que se encarga de instanciar al Dao de la base de datos de colas para recibir todas las colas a la que esta aputnado un alumno
     * @param String id
     * @return  {@link ArrayList}
     */
    public static ArrayList<Profesor> getColasAlumno(String idAlumno){
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
    
    /**
     * Metodo que se encarga de realizar la comprobacion del login de un usuario, reenviandolo a la clase de gestion {@link UsuarioDAO.logIn}
     * @param user String de identificacion del usuario
     * @param pass String de contrasena del usuatio
     * @return Usuario que se ha autenticado correctamente
     */
    public static Usuario logIn(String user, String pass) {
        return UsuarioDAO.logIn(user, pass);    
    }

    /**
     * Metodo que se encarga de obtener todos los profesores de un alumno, reenviando este metodo a {@link UsuarioDAO.getProfesoresAlumno}
     * @param idAlumno String con el id del alumno
     * @return Devuelve un ArrayList de profesores
     */
    public static ArrayList<Profesor> getProfesoresAlumno(String idAlumno){
        return UsuarioDAO.getProfesoresAlumno(idAlumno);

    }

    /**
     * Metodo que devuelve todos los alumnos que se enceuntran en la cola de un profesor, reenviando este metodo a {@link ColaDAO.getCOlaProfesor}
     * @param idProfesor String con el id del profesor
     * @return Devuelve un ArrayList de alumnos
     */
    public static ArrayList<Alumno> getColaProfesor(String idProfesor){
        return ColaDAO.getColaProfesor(idProfesor);
    }

    /**
     * Metodo que obtiene todas las clases en las que esta un profesor, redirige este metodo a {@link UsuarioDAO.getClasesProfesor}
     * @param idProfesor String con el id del profesor
     * @return Devuelve un ArrayList con todas las clases que imparte
     */
    public static ArrayList<String> getClasesProfesor(String idProfesor){
        return UsuarioDAO.getClasesProfesor(idProfesor);

    }

    /**
     * Metodo que obtiene el horario de un profesor, redirigiendo este metodo a {@link HorarioDAO.idProfesor}
     * @param idProfesor
     * @return
     */
    public static String getHorario(String idProfesor){
        return HorarioDAO.getHorario(idProfesor);
    }
    
    /**Metodo encargado de solicitar a {@link ColaDao} que añada un alumno a la cola de un profesor
     * @param Alumno alumno
     * @param Profesor profesor
     */
    public static void addAlumnoCola(Alumno alumno, Profesor profesor){
        ColaDAO.addAlumnoCola(alumno,profesor);
    }
    public static void delAlumnoCola(Alumno alumno, Profesor profesor){
        ColaDAO.delAlumnoCola(alumno, profesor);
    }
    public static String getNombreAlumno(String idAlumno){
        return UsuarioDAO.getNombreAlumno(idAlumno);

    }

    /**
     * Metodo que comprueba si la cola de un profesor esta abierta o no
     * @param idProfesor String que indica el id de un profesor
     * @return Boolean que devuelve si la cola esta abierta
     */
    public static Boolean isColaAbierta(String idProfesor) 
    {
        return ColaDAO.isColaAbierta(idProfesor);
    }
}