package server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;




public class ClienteServidor {
	private static final String HOST = "localhost";	// variables para tener la direccion destino y el puerto destino
    private static final int PORT = 2345;
    private static Socket conexionSocket; 		
	private static OutputStream out = null;
	private static InputStream in = null;
	private static ObjectOutputStream salida;	// se pasa a canal de objetos
	private static ObjectInputStream entrada;		// objeto que va a recibir los mensajes
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
			// se cierra el canal
			in.close();
			out.close();
			conexionSocket.close();// cierra el socket
			
			return true;
		}catch(Exception error){
			error.printStackTrace();
			return false;
		}
	}
	/**Metodo utilizado para enviar un mensaje al servidor destino,
	 * @param {@link Mensaje} mensajeEnviar
	 * @param {@link Mensaje} mensajeRespuesta
	 * envia {@link Mensaje}
	*/
    public static Mensaje enviarMensaje(Mensaje mensajeEnviar){
		Mensaje mensajeRespuesta = new Mensaje();
		
		try{
			iniciarConexion();
			in = null;
			out = null;
			in = conexionSocket.getInputStream();		// canal para recibir y mandar los mensajes
			out = conexionSocket.getOutputStream();
			entrada = null;
			salida = null;
			
			salida = new ObjectOutputStream(out);
			entrada = new ObjectInputStream(in);
			out.flush();
			salida.flush();
			if(conexionSocket.isConnected()){
				// se envia el mensaje
				salida.writeObject(mensajeEnviar);
				// se lee el mensaje de respuesta, y se carga en el mensaje de respuesta
				mensajeRespuesta = (Mensaje)entrada.readObject();
				
			}
			cerrarConexion();
			
		}catch(Exception error){
			error.printStackTrace();
		}
		return mensajeRespuesta;
	}
}