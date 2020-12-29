package dominio;

import java.util.ArrayList;

import server.Fachada;

public class Profesor extends Usuario{
    private static final long serialVersionUID = 1L;
    /**  */
    private ArrayList<String> listaClases;
    /**  */
    private ArrayList<Alumno> colaAlu;

    public Profesor(String correo, String nombre, String apellido, String id) {
        super(correo, nombre, apellido, id);
    }

    /**Utiliza el metodo de la clase @link Fachada, para conectarse a la base de datos y pedir las colas en las que se encuentra el alumno */
    private void cargarCola(){
        colaAlu = GestorCola.getColaProfesor(super.getId());      // le pide al gestor de colas que le devuelva la lista de colas a la que se encuentra apuntado el alumno
    }
    private void cargarClases(){
        listaClases = Fachada.getClasesProfesor(super.getId());
    }


    public ArrayList<String> getListaClases() {
        this.cargarClases();
        return listaClases;
    }


    public ArrayList<Alumno> getColaAlu() {
        this.cargarCola();
        return colaAlu;
    }

    

    
}
