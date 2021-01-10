package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

import dominio.Alumno;
import dominio.Profesor;
import dominio.Usuario;
import util.Fecha;

import java.util.ArrayList;
import java.util.HashMap;

import dao.ColaDAO;
import dao.HorarioDAO;
import dao.UsuarioDAO;
import dao.CitasDAO;

import java.sql.Date;


/**Clase encargada de la parte servidor de la aplicación, se encarga de escuchar por nuevos mensaje y responder */

public class Servidor extends Thread {
	public static final int PORT_NUMBER = 2345;

	protected Socket socket;

	private Servidor(Socket socket) {
		/*
		 se crea un socket por conexión exterior
		 cada vez que se intenta conectar alquien desde fuera, la conexión se acepta y se crea un nuevo socket
		*/
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress()+" "+ Fecha.fechaHoraString());
		// se arranca la conexion con la base de datos
		start();
	}

	public void run() {
		// funcion que se corre todo el rato, es un thread
		InputStream in = null;
		OutputStream out = null;
		try {
			in = socket.getInputStream();		// carga la información que le ha mandado el socket
			out = socket.getOutputStream();
			
			//objetos de entrada y salida del servidor
			ObjectInputStream entrada = new ObjectInputStream(in);
			ObjectOutputStream salida = new ObjectOutputStream(out);
			out.flush();
			salida.flush();


			Mensaje mensajeEntrada= (Mensaje)entrada.readObject();
			System.out.println("Mensaje recibido:");
			System.out.println(mensajeEntrada.getContenido().toString());
		    //se analiza el mensaje y se devuelve la respuesta
			Mensaje mensajeRespuesta=new Mensaje();
			
			// la respuesta es común a todos los alumnos
			mensajeRespuesta.setContext("/respuesta");
			HashMap<String,Object> HashMapRespuesta = new HashMap<String,Object>();
						
			switch (mensajeEntrada.getContext()) {
				case "/getColasAlumno":
					// se carga el id del alumno
					String idAlumno = (String)mensajeEntrada.getContenido().get("idAlumno");
					// se realiza la consulta en el gestor de colas
					ArrayList<Profesor> colaAlumno = ColaDAO.getColasAlumno(idAlumno);
					// array para contener la respuesta 
					ArrayList<Object> arrayRespuesta = new ArrayList<Object>();
					// se recorre el array de respuesta para castear de Profesor a Object
					for(Profesor profesor :colaAlumno){
						arrayRespuesta.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
					}
					HashMapRespuesta.put("Colas",arrayRespuesta);
					break;
				case "/getPosicionAlumno":
					// se carga el id del alumno
					String idAlumnoPos = (String)mensajeEntrada.getContenido().get("idAlumno");
					String idProfPos = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					Integer posicion = ColaDAO.getPosicionColaAlumno(idAlumnoPos,idProfPos);
					HashMapRespuesta.put("Posicion",posicion);
					break;
				case "/openCola":
					// se carga el id de de la cola
					String idColaOpen = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					Boolean isOpenned = ColaDAO.openCola(idColaOpen);
					HashMapRespuesta.put("Resultado",isOpenned);
					break;
				case "/closeCola":
					// se carga el id de de la cola
					String idColaClose = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					Boolean isClossed= ColaDAO.closeCola(idColaClose);
					HashMapRespuesta.put("Resultado",isClossed);
					break;
				case "/login":
					// se carga el id de de la cola
					String user = (String)mensajeEntrada.getContenido().get("Usuario");
					String pass = (String)mensajeEntrada.getContenido().get("Contrasena");
					
					// se realiza la consulta en el gestor de colas
					Usuario usuario = UsuarioDAO.logIn(user,pass);
					HashMapRespuesta.put("Usuario",usuario);
					break;
				case "/getProfesoresAlumno":
					// se carga el id del alumno
					String idAlumnoProf = (String)mensajeEntrada.getContenido().get("idAlumno");
					// se realiza la consulta en el gestor de colas
					ArrayList<Profesor> profesoresAlumno = UsuarioDAO.getProfesoresAlumno(idAlumnoProf);
					ArrayList<Object> arrayRespuestaProfesores = new ArrayList<Object>();
					// se recorre el array de respuesta para castear de Profesor a Object
					for(Profesor profesor :profesoresAlumno){
						arrayRespuestaProfesores.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
					}
					HashMapRespuesta.put("Profesores",arrayRespuestaProfesores);
					break;
				case "/getColaProfesor":
					// se carga el id del alumno
					String idProfCola = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					ArrayList<Alumno> alumnosProfesor = ColaDAO.getColaProfesor(idProfCola);
					ArrayList<Object> arrayRespuestaCola = new ArrayList<Object>();
					// se recorre el array de respuesta para castear de Profesor a Object
					for(Alumno profesor :alumnosProfesor){
						arrayRespuestaCola.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
					}
					HashMapRespuesta.put("Alumnos",arrayRespuestaCola);
					break;
				case "/getHorario":
					// se carga el id del alumno
					String idProfHorario = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					String horarioProfesor = HorarioDAO.getHorario(idProfHorario);
					HashMapRespuesta.put("Horario",horarioProfesor);
					break;
				case "/addAlumnoCola":
					// se carga el id del alumno
					Alumno alumnoAdd = (Alumno)mensajeEntrada.getContenido().get("Alumno");
					Profesor profesorAdd = (Profesor)mensajeEntrada.getContenido().get("Profesor");
					// se realiza la consulta en el gestor de colas
					ColaDAO.addAlumnoCola(alumnoAdd,profesorAdd);
					break;
				case "/delAlumnoCola":
					// se carga el id del alumno
					Alumno alumnoBorrar = (Alumno)mensajeEntrada.getContenido().get("Alumno");
					Profesor profesorBorrar = (Profesor)mensajeEntrada.getContenido().get("Profesor");
					// se realiza la consulta en el gestor de colas
					ColaDAO.delAlumnoCola(alumnoBorrar,profesorBorrar);
					break;
				case "/getNombreAlumno":
					//se carga el ID
					String idAlumnoNombre = (String)mensajeEntrada.getContenido().get("idAlumno");
					//se realiza la consulta en la base de datos
					String nombreAlumno = UsuarioDAO.getNombreAlumno(idAlumnoNombre);
					HashMapRespuesta.put("Nombre",nombreAlumno);
					break;
				case "/getClasesProfesor":
					// se carga el id del alumno
					String idProfClases = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					ArrayList<String> clasesProfesor = UsuarioDAO.getClasesProfesor(idProfClases);
					ArrayList<Object> arrayRespuestaClases = new ArrayList<Object>();
					// se recorre el array de respuesta para castear de Profesor a Object
					for(String profesor :clasesProfesor){
						arrayRespuestaClases.add(profesor);	// se guarda el objeto en el arrayList de respuesta
					}
					HashMapRespuesta.put("Clases",arrayRespuestaClases);
					break;
				case "/isColaAbierta":
					String idProfColaAbierta = (String)mensajeEntrada.getContenido().get("idProfesor");
					// se realiza la consulta en el gestor de colas
					Boolean isColaAbierta = ColaDAO.isColaAbierta(idProfColaAbierta);
					HashMapRespuesta.put("Resultado",isColaAbierta);
					break;
				case "/reestablecerContrasena":
					String idUsuario = (String)mensajeEntrada.getContenido().get("idUsuario");
					GestorUsuarios.reestablecerContrasena(idUsuario);
					HashMapRespuesta.put("Resultado",true);//se envia el mensaje de vuelta
					break;
					
				case "/getCitasAlumno":
					String idAlumnoCitas = (String)mensajeEntrada.getContenido().get("idAlumno");
					ArrayList<Timestamp> citasAlumno =  CitasDAO.getCitasAlumno(idAlumnoCitas);//se llama al gestor de usuario para que se encargue de resetearlo
					HashMapRespuesta.put("Resultado",citasAlumno);//se envia el mensaje de vuelta
					break;
				case "/getCitasProfesor":
					String idProfesorCitas = (String)mensajeEntrada.getContenido().get("idProfesor");
					ArrayList<Timestamp> citasProfesor =  CitasDAO.getCitasProf(idProfesorCitas);
					HashMapRespuesta.put("Resultado",citasProfesor);//se envia el mensaje de vuelta
					break;
				case "/crearTutoria":
					Profesor profesorTutoria = (Profesor)mensajeEntrada.getContenido().get("Profesor");
					Date fechaInicioTutoria = (Date)mensajeEntrada.getContenido().get("fechaInicio");
					Date fechaFinTutoria = (Date)mensajeEntrada.getContenido().get("fechaFin");
					Boolean isTutoria =  ControladorCitas.crearTutoriaProfesor(profesorTutoria,fechaInicioTutoria,fechaFinTutoria); 
					HashMapRespuesta.put("Resultado",isTutoria);//se envia el mensaje de vuelta
					break;

				case "/eliminarCitaProfesor":
					Profesor profesorEliminarCita = (Profesor)mensajeEntrada.getContenido().get("Profesor");
					Date fechaInicioEliminarCita = (Date)mensajeEntrada.getContenido().get("fechaInicio");
					Date fechaFinEliminarCita = (Date)mensajeEntrada.getContenido().get("fechaFin");
					Boolean eliminado =  ControladorCitas.crearTutoriaProfesor(profesorEliminarCita,fechaInicioEliminarCita,fechaFinEliminarCita); 
					HashMapRespuesta.put("Resultado",eliminado);//se envia el mensaje de vuelta
					break;
				case "/crearCita":
					Profesor profesorCrearCita = (Profesor)mensajeEntrada.getContenido().get("Profesor");
					Alumno alumnoCrearCita = (Alumno)mensajeEntrada.getContenido().get("Alumno");
					Date fechaInicioCrearCita = (Date)mensajeEntrada.getContenido().get("fechaInicio");
					Date fechaFinCrearCita = (Date)mensajeEntrada.getContenido().get("fechaFin");
					Boolean citaCreada =  ControladorCitas.crearCita(profesorCrearCita,alumnoCrearCita,fechaInicioCrearCita,fechaFinCrearCita); 
					HashMapRespuesta.put("Resultado",citaCreada);//se envia el mensaje de vuelta
					break;
				default:
					System.out.println("\nParámetro no encontrado");
					System.out.println(mensajeEntrada.getContext());
						
			}
			// se carga la respuesta en el mensaje
			mensajeRespuesta.setContenido(HashMapRespuesta);
			System.out.println("Se va a enviar");
			System.out.print(mensajeRespuesta.getContenido().toString());

			// se envia el mensaje de respuesta
			salida.writeObject(mensajeRespuesta);
		} catch (IOException ex) {
			System.out.println("Unable to get streams from client");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ServerSocket server = null;
		ConexionServer.startConnection();
		try {
			server = new ServerSocket(PORT_NUMBER);
			while (true) {
				/**
				 * create a new {@link SocketServer} object for each connection
				 * this will allow multiple client connections
				 */
				new Servidor(server.accept());
			}
		} catch (IOException ex) {
			System.out.println("Unable to start server.");
		} finally {
			try {
				if (server != null)
					server.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}