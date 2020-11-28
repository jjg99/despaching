package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dominio.Alumno;
import dominio.Profesor;
import dominio.Usuario;
import util.Fecha;

import java.util.ArrayList;

import dao.ColaDAO;
import dao.HorarioDAO;
import dao.UsuarioDAO;



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
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress()+ Fecha.fechaHoraString());
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

			Mensaje mensajeEntrada= (Mensaje)entrada.readObject();
			System.out.print(mensajeEntrada.getContenido().toString());
		    //se analiza el mensaje y se devuelve la respuesta
			Mensaje mensajeRespuesta=new Mensaje();
			if(mensajeRespuesta == null){
				int a = 1;

			}else{
				System.out.println("Se ha recibido algo");
				// la respuesta es común a todos los alumnos
				mensajeRespuesta.setContext("/respuesta");
				ArrayList<Object> respuesta = new ArrayList<Object>();	// array para devolver el resultado de la consulta

				switch (mensajeEntrada.getContext()) {
					case "/getColasAlumno":
						// se carga el id del alumno
						String idAlumno = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						ArrayList<Profesor> colaAlumno = ColaDAO.getColasAlumno(idAlumno);
						// se recorre el array de respuesta para castear de Profesor a Object
						for(Profesor profesor :colaAlumno){
							respuesta.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
						}
						break;
					case "/getPosicionAlumno":
						// se carga el id del alumno
						String idAlumnoPos = (String)mensajeEntrada.getContenido().get(0);
						String idProfPos = (String)mensajeEntrada.getContenido().get(1);
						// se realiza la consulta en el gestor de colas
						Integer posicion = ColaDAO.getPosicionColaAlumno(idAlumnoPos,idProfPos);
						respuesta.add((Object)posicion);
						break;
					case "/openCola":
						// se carga el id de de la cola
						String idColaOpen = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						Boolean isOpenned = ColaDAO.openCola(idColaOpen);
						respuesta.add((Object)isOpenned);
						break;
					case "/closeCola":
						// se carga el id de de la cola
						String idColaClose = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						Boolean isClossed= ColaDAO.openCola(idColaClose);
						respuesta.add((Object)isClossed);
						break;
					case "/login":
						// se carga el id de de la cola
						String user = (String)mensajeEntrada.getContenido().get(0);
						String pass = (String)mensajeEntrada.getContenido().get(1);
						// se realiza la consulta en el gestor de colas
						Usuario usuario = UsuarioDAO.logIn(user,pass);
						respuesta.add((Object)usuario);
						break;
					case "/getProfesoresAlumno":
						// se carga el id del alumno
						String idAlumnoProf = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						ArrayList<Profesor> profesoresAlumno = UsuarioDAO.getProfesoresAlumno(idAlumnoProf);
						// se recorre el array de respuesta para castear de Profesor a Object
						for(Profesor profesor :profesoresAlumno){
							respuesta.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
						}
						break;
					case "/getColaProfesor":
						// se carga el id del alumno
						String idProfCola = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						ArrayList<Alumno> alumnosProfesor = ColaDAO.getColaProfesor(idProfCola);
						// se recorre el array de respuesta para castear de Profesor a Object
						for(Alumno profesor :alumnosProfesor){
							respuesta.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
						}
						break;
					case "/getHorario":
						// se carga el id del alumno
						String idProfHorario = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						String horarioProfesor = HorarioDAO.getHorario(idProfHorario);
						respuesta.add(horarioProfesor);
						break;
					case "/addAlumnoCola":
						// se carga el id del alumno
						Alumno alumnoAdd = (Alumno)mensajeEntrada.getContenido().get(0);
						Profesor profesorAdd = (Profesor)mensajeEntrada.getContenido().get(1);
						// se realiza la consulta en el gestor de colas
						ColaDAO.addAlumnoCola(alumnoAdd,profesorAdd);
						break;
					case "/delAlumnoCola":
						// se carga el id del alumno
						Alumno alumnoBorrar = (Alumno)mensajeEntrada.getContenido().get(0);
						Profesor profesorBorrar = (Profesor)mensajeEntrada.getContenido().get(1);
						// se realiza la consulta en el gestor de colas
						ColaDAO.delAlumnoCola(alumnoBorrar,profesorBorrar);
						break;
					case "/getClasesProfesor":
						// se carga el id del alumno
						String idProfClases = (String)mensajeEntrada.getContenido().get(0);
						// se realiza la consulta en el gestor de colas
						ArrayList<String> clasesProfesor = UsuarioDAO.getClasesProfesor(idProfClases);
						// se recorre el array de respuesta para castear de Profesor a Object
						for(String profesor :clasesProfesor){
							respuesta.add((Object)profesor);	// se guarda el objeto en el arrayList de respuesta
						}
						break;
					default:
						System.out.println("\nParámetro no encontrado");
						System.out.println(mensajeEntrada.getContext());
						break;
				}
				// se carga la respuesta en el mensaje
				mensajeRespuesta.setContenido(respuesta);
				// se envia el mensaje de respuesta
				salida.writeObject(mensajeRespuesta);
			}
		

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