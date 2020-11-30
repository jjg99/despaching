package server;

import java.util.ArrayList;


import dominio.Alumno;
import dominio.Profesor;
import dominio.Usuario;

/** Clase que se encarga de redirigir el trafico a los DAO */
public class Fachada {
    /**Metodo que se encarga de instanciar al Dao de la base de datos de colas para recibir todas las colas a la que esta aputnado un alumno
     * @param String id
     * @return  {@link ArrayList}
     */
    
    public static ArrayList<Profesor> getColasAlumno(String idAlumno){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getColasAlumno");        // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar

        ArrayList<Profesor> respuesta = new ArrayList<Profesor>();  // array para almacenar la respuesta del servidor
        // se añade el contenido al mensaje
        contenido.add(new String(idAlumno));
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se recibe un array con las colas de alumno
        ArrayList<Object> ArrayRespuesta =mensajeRespuesta.getContenido();
        for(Object elemento: ArrayRespuesta){
            Profesor profesor = (Profesor)elemento;
            respuesta.add(profesor);
        }
        
        return respuesta;
    }
    
    /**Metodo que se encarga de instanciar al Dao de la base de datos de colas para recibir la posición de un alumno en una cola
    * @param String id
    * @param String profesorCola
    * @return  {@link ArrayList}
    */
    public static int getPosicionAlumno(String idAlumno,String profesorCola){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getPosicionAlumno");     // se coloca el tipo de consulta
        // se añade el contenido del mensaje a enviar al servidor
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        contenido.add(new String(idAlumno));
        contenido.add(new String(profesorCola));
        mensajeEnviar.setContenido(contenido);
        // se envia la consulta al servidor
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se carga el primer elemento del array, el cual contiene la posicion del alumno en la cola
        int posicion = (int)mensajeRespuesta.getContenido().get(0);
        return posicion;
    }

    /**
     * Metodo que abre la cola de un profesor añadiendolo en la tabla de Colas de la base de datos llamado a {@link ColaDAO.openCola}.
     * @param id Identificacion del profesor que va a abrir cola
     * @return <code>true</code> si el profesor se agrego a la base de datos satisfactoriamente
     */
    public static boolean openCola(String id){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/openCola");      // se coloca el tipo de consulta
        // se añade el contenido del mensaje a enviar al servidor
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        contenido.add(new String(id));
        mensajeEnviar.setContenido(contenido);
        // se envia la consulta al servidor
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se carga el primer elemento del array, el cual contiene el boolean indicando si se ha realizado de forma correcta la apertura de la cola
        boolean result = (boolean)mensajeRespuesta.getContenido().get(0);

        return result;
        
    }
    /**
     * Metodo que cierra al cola de un profesor, removiendolo de la tabla de Colas llamando a {@link ColaDAO.closeCola} 
     * @param id Identificacion del profesor que va a cerrar cola
     * @return <code>true</code> si el profesor se borro de la base de datos satisfactoriamente
     */
	public static boolean closeCola(String id) {
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/closeCola");     // se coloca el tipo de consulta
        // se añade el contenido del mensaje a enviar al servidor
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        contenido.add(new String(id));
        mensajeEnviar.setContenido(contenido);
        // se envia la consulta al servidor
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se carga el primer elemento del array, el cual contiene el boolean indicando si se ha realizado de forma correcta el cierre de la cola
        boolean result = (boolean)mensajeRespuesta.getContenido().get(0);
		return result;
    }
    
    /**
     * Metodo que se encarga de realizar la comprobacion del login de un usuario, reenviandolo a la clase de gestion {@link UsuarioDAO.logIn}
     * @param user String de identificacion del usuario
     * @param pass String de contrasena del usuatio
     * @return Usuario que se ha autenticado correctamente
     */
    public static Usuario logIn(String user, String pass) {
        
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        // se inicia la conexion con el servidor
        ClienteServidor.iniciarConexion(); 
        mensajeEnviar.setContext("/login");
        // se añade el contenido del mensaje a enviar al servidor
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        contenido.add((Object)new String(user));
        contenido.add((Object)new String(pass));
        mensajeEnviar.setContenido(contenido);
        // se envia la consulta al servidor
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se carga el primer elemento del array, el cual contiene el boolean indicando si se ha realizado de forma correcta el cierre de la cola
        Usuario result = (Usuario)mensajeRespuesta.getContenido().get(0);
        return result;  
    }

    /**
     * Metodo que se encarga de obtener todos los profesores de un alumno, reenviando este metodo a {@link UsuarioDAO.getProfesoresAlumno}
     * @param idAlumno String con el id del alumno
     * @return Devuelve un ArrayList de profesores
     */
    public static ArrayList<Profesor> getProfesoresAlumno(String idAlumno){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getProfesoresAlumno");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar

        ArrayList<Profesor> respuesta = new ArrayList<Profesor>();  // array para almacenar la respuesta del servidor
        // se añade el contenido al mensaje
        contenido.add(new String(idAlumno));
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se recibe un array con todos los profesores del alumno
        ArrayList<Object> ArrayRespuesta =mensajeRespuesta.getContenido();
        for(Object elemento: ArrayRespuesta){
            Profesor profesor = (Profesor)elemento;
            respuesta.add(profesor);
        }
        
        return respuesta;

    }

    /**
     * Metodo que devuelve todos los alumnos que se enceuntran en la cola de un profesor, reenviando este metodo a {@link ColaDAO.getCOlaProfesor}
     * @param idProfesor String con el id del profesor
     * @return Devuelve un ArrayList de alumnos
     */
    public static ArrayList<Alumno> getColaProfesor(String idProfesor){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getColaProfesor");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar

        ArrayList<Alumno> respuesta = new ArrayList<Alumno>();  // array para almacenar la respuesta del servidor
        // se añade el contenido al mensaje
        contenido.add(new String(idProfesor));
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se recibe un array con la cola del profesor
        ArrayList<Object> ArrayRespuesta =mensajeRespuesta.getContenido();
        for(Object elemento: ArrayRespuesta){
            Alumno alumno = (Alumno)elemento;
            respuesta.add(alumno);
        }
        
        return respuesta;
    }

    /**
     * Metodo que obtiene todas las clases en las que esta un profesor, redirige este metodo a {@link UsuarioDAO.getClasesProfesor}
     * @param idProfesor String con el id del profesor
     * @return Devuelve un ArrayList con todas las clases que imparte
     */
    public static ArrayList<String> getClasesProfesor(String idProfesor){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getClasesProfesor");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar

        ArrayList<String> respuesta = new ArrayList<String>();  // array para almacenar la respuesta del servidor
        // se añade el contenido al mensaje
        contenido.add(new String(idProfesor));
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se recibe un array con todas las clases del profesor
        ArrayList<Object> ArrayRespuesta =mensajeRespuesta.getContenido();
        for(Object elemento: ArrayRespuesta){
            String clase = (String)elemento;
            respuesta.add(clase);
        }
        
        return respuesta;

    }

    /**
     * Metodo que obtiene el horario de un profesor, redirigiendo este metodo a {@link HorarioDAO.idProfesor}
     * @param idProfesor String con el id del profesor
     * @return Devuelve un String con todo el horario del profesor
     */
    public static String getHorario(String idProfesor){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getHorario");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar

        // se añade el contenido al mensaje
        contenido.add(new String(idProfesor));
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se recibe el horario del profesor
        String respuesta = (String)mensajeRespuesta.getContenido().get(0);
    
        return respuesta;
    }
    
    /**Metodo encargado de solicitar a {@link ColaDao} que añada un alumno a la cola de un profesor
     * @param Alumno alumno
     * @param Profesor profesor
     */
    public static void addAlumnoCola(Alumno alumno, Profesor profesor){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/addAlumnoCola");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        // se añade el contenido al mensaje
        contenido.add(alumno);
        contenido.add(profesor);
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        
    }

    /**
     * Metodo que elimina a un alumno de una cola, redirige a {@link ColaDAO.delAlumnoCola}
     * @param alumno Objeto Alumno a eliminar
     * @param profesor Objeto Profesor al que pertenece la cola
     */
    public static void delAlumnoCola(Alumno alumno, Profesor profesor){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/delAlumnoCola");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        // se añade el contenido al mensaje
        contenido.add(alumno);
        contenido.add(profesor);
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        
    }

    /**
     * Metodo que obtiene el nombre de un alumno/profesor conocido su id, redirige este metodo a {@link UsuarioDAO.getNombreAlumno}
     * @param idAlumno String que contiene el id del usuario
     * @return Devuelve un string con el nombre + apellidos de la persona
     */
    public static String getNombreAlumno(String idAlumno){
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/getNombreAlumno");       // se coloca el tipo de consulta
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar

        // se añade el contenido al mensaje
        contenido.add(new String(idAlumno));
        mensajeEnviar.setContenido(contenido);
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se recibe el nombre del alumno
        String respuesta = (String)mensajeRespuesta.getContenido().get(0);
    
        return respuesta;
    }

    /**
     * Metodo que comprueba si la cola de un profesor esta abierta o no
     * @param idProfesor String que indica el id de un profesor
     * @return Boolean que devuelve si la cola esta abierta
     */
    public static Boolean isColaAbierta(String idProfesor) {
        // se crea el mensaje para enviar toda la información 
        Mensaje mensajeEnviar = new Mensaje();
        Mensaje mensajeRespuesta = new Mensaje();
        mensajeEnviar.setContext("/isColaAbierta");     // se coloca el tipo de consulta
        // se añade el contenido del mensaje a enviar al servidor
        ArrayList<Object> contenido = new ArrayList<Object>();     // array para almacenar el contenido de la consulta que se va a enviar
        contenido.add(new String(idProfesor));
        mensajeEnviar.setContenido(contenido);
        // se envia la consulta al servidor
        mensajeRespuesta = ClienteServidor.enviarMensaje(mensajeEnviar);  
        // se carga el primer elemento del array, el cual contiene el boolean indicando si se ha realizado de forma correcta el cierre de la cola
        boolean result = (boolean)mensajeRespuesta.getContenido().get(0);
		return result;
    }
}