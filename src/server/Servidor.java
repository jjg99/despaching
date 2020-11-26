package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



/**Clase encargada de la parte servidor de la aplicación, se encarga de escuchar por nuevos mensaje y responder */

public class Servidor extends Thread {
	public static final int PORT_NUMBER = 8081;

	protected Socket socket;

	private Servidor(Socket socket) {
		/*
		 se crea un socket por conexión exterior
		 cada vez que se intenta conectar alquien desde fuera, la conexión se acepta y se crea un nuevo socket
		*/
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
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
		    //se analiza el mensaje y se devuelve la respuesta
		    Mensaje mensajeRespuesta=new Mensaje();
		    switch (mensajeEntrada.getContext()) {
		    	case "/getColasAlumno":
		    				    		
		    	break;
		    	
		    	
		    	default:
		    		System.out.println("\nParámetro no encontrado");
		    		break;
		    }
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