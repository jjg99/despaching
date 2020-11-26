package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


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
			
			//first read the object that has been sent
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
		    Mensaje mensajeIn= (Mensaje)objectInputStream.readObject();
		    //Object to return informations 
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
		    Mensaje mensajeOut=new Mensaje();
		    switch (mensajeIn.getContext()) {
		    	case "/getCustomer":
		    				    		
		    	break;
		    	
		    	
		    	default:
		    		System.out.println("\nParámetro no encontrado");
		    		break;
		    }
		    
		    //Lógica del controlador 
		    //System.out.println("\n1.- He leído: "+mensaje.getContext());
		    //System.out.println("\n2.- He leído: "+(String)mensaje.getSession().get("Nombre"));
		    
		    
		    
		    //Prueba para esperar
		    /*try {
		    	System.out.println("Me duermo");
				Thread.sleep(15000);
				System.out.println("Me levanto");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			// create an object output stream from the output stream so we can send an object through it
			/*ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
			
			//Create the object to send
			String cadena=((String)mensaje.getSession().get("Nombre"));
			cadena+=" añado información";
			mensaje.getSession().put("Nombre", cadena);
			//System.out.println("\n3.- He leído: "+(String)mensaje.getSession().get("Nombre"));
			objectOutputStream.writeObject(mensaje);*
			*/

		} catch (IOException ex) {
			System.out.println("Unable to get streams from client");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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