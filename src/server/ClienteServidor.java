package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;



public class ClienteServidor {
	private static final String HOST = "192.168.1.2";	// variables para tener la direccion destino y el puerto destino
    private static final int PORT = 881;
    private static Socket conexionSocket; 		
	private static boolean conectado = false;	// variable que controla si se ha conectado ya al servidor o no
	}
	/**Metodo encargado de realizar la conexion con el servidor remoto,
	 * para ello incializa el {@link Socket}
	 */
    public static boolean iniciarConexion(){
		try{
			conexionSocket = new Socket(HOST, PORT);		// intenta conectarse al socket del servidor
			return true;
		}catch(Exception error){
			error.printStackTrace();
			return false;
		}
	}
	public static boolean cerrarConexion(){
		try{
			conexionSocket.close();// cierra el socket
			return conexionSocket.isConnected();	// comprueba si sigue conectado
		}catch(Exception error){
			error.printStackTrace();
		}
	}
	/**Metodo utilizado para enviar un mensaje al servidor destino,
	 * envia {@link Mensaje}
	*/
    public static void enviarMensaje(Mensaje mensajeEnviar,Mensaje mensajeRespuesta){
		OutputStream out = null;
		InputStream in = null;
		try{
			if(conexionSocket.isConnected()){
				in = conexionSocket.getInputStream();		// canal para recibir y mandar los mensajes
				out = conexionSocket.getOutputStream();

				ObjectOutputStream salida = new ObjectOutputStream(out);	// se pasa a canal de objetos
				ObjectInputStream entrada = new ObjectInputStream(in);		// objeto que va a recibir los mensajes
				// se envia el mensaje
				salida.writeObject(mensajeEnviar);
				// se lee el mensaje de respuesta, y se carga en el mensaje de respuesta
				mensajeRespuesta = (Mensaje)entrada.readObject();

			}
			
		}catch(Exception error){
			error.printStackTrace();
		}
	}
}