package dominio;

import java.util.ArrayList;


public class Profesor extends Usuario{
    /**  */
    private ArrayList<Alumno> listaAlu;
    /**  */
    private ArrayList<String> listaClases;
    /**  */
    private ArrayList<Alumno> colaAlu;

    public Profesor(String correo, String password, String nombre, String apellidos, String id) {
        super(correo, password, nombre, apellidos, id);
    }


    
}
