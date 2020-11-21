package dominio;

import java.util.ArrayList;

import server.Fachada;

public class Alumno extends Usuario  {
    /**Atributos de la clase alumno */
    
    private ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();        // arrayList que va a contener todos los porfesores del alumno
    private ArrayList<Profesor> listaColas = new ArrayList<Profesor>();        // arrayList que contiene los nombres de 
    
    public Alumno (String correo,String password){
        super(correo);  // se inicia el objeto, instanciando al objeto superior     
    }
    
    public Alumno(String nombre, String apellido, String id) {
        super(nombre, apellido, id);
    }
    
    /** constructor que inicia todos los atributos de un usuario */
    public Alumno (String correo, String nombre, String apellido, String id){
        super(correo,nombre,apellido,id);     // se inicia el objeto, instanciando al objeto superior
    }

    /**Metodo para conseguir una lista de todos los profesores del alumno, crea un {@link ArrayList} de @Profesor */
    private void cargarProfesores(){ 
        listaProfesores = Fachada.getProfesoresAlumno(super.getId());
    }

    /**Utiliza el metodo de la clase @link Fachada, para conectarse a la base de datos y pedir las colas en las que se encuentra el alumno */
    private void cargarColas(){
        listaColas = GestorCola.getColasAlumno(super.getId());      // le pide al gestor de colas que le devuelva la lista de colas a la que se encuentra apuntado el alumno
    }

    public ArrayList<Profesor> getListaProfesores() {
        this.cargarProfesores();
        return listaProfesores;
    }

    public ArrayList<String> getListaColas() {
        this.cargarColas();
        return listaColas;
    }

    
}
