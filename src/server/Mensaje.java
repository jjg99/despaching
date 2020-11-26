package server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mensaje implements Serializable {
	/**
	 * Clase encargada de contener la informacióne nviada a través del socket
	 */
	private static final long serialVersionUID = 1L;
	private String context;
	private Map<String, Object> contenido;
	
	
	public Mensaje () {
		context=new String();
		contenido=new HashMap<String, Object>();	// en este campo se guardan todos los objetos que sea ncesario enviar por medio del socket, puede quedar vacio
		
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Map<String, Object> getContenido() {
		return contenido;
	}

	public void setContenido(Map<String, Object> session) {
		this.contenido = session;
	}	
}