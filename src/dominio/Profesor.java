package dominio;

import java.util.ArrayList;

import server.Fachada;

public class Profesor extends Usuario{
    /**  */
    private ArrayList<String> listaAlu;
    /**  */
    private ArrayList<String> listaClases;
    /**  */
    private ArrayList<String> colaAlu;

    public Profesor(String correo, String nombre, String apellidos, String id) {
        super(correo, nombre, apellidos, id);
    }
    /**Metodo para conseguir una lista de todos los profesores del alumno
     * crea un {@link ArrayList} de @Profesor */
    private void cargarAlumnos(){ 
        listaAlu = Fachada.getProfesoresAlumno(super.getId());
    }

    /**Utiliza el metodo de la clase @link Fachada, para conectarse a la base de datos y pedir las colas en las que se encuentra el alumno */
    private void cargarCola(){
        colaAlu = GestorCola.getColaProfesor(super.getId());      // le pide al gestor de colas que le devuelva la lista de colas a la que se encuentra apuntado el alumno
    }
    private void cargarClases(){
        listaClases = Fachada.getClasesProfesor(super.getId());
    }

    public ArrayList<String> getListaAlu() {
        this.cargarAlumnos();
        return listaAlu;
    }

    public ArrayList<String> getListaClases() {
        this.cargarClases();
        return listaClases;
    }


    public ArrayList<String> getColaAlu() {
        this.cargarCola();
        return colaAlu;
    }

    

    
}
