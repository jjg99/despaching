package dominio;

import java.util.ArrayList;


public class Alumno extends Usuario  {
    /**Atributos de la clase alumno */

    private ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();        // arrayList que va a contener todos los porfesores del alumno
    private ArrayList<String> listaColas = new ArrayList<String>();        // arrayList que contiene los nombres de 

    public Alumno (String correo,String password){
        super(correo, password);  // se inicia el objeto, instanciando al objeto superior     
    }

    /** constructor que inicia todos los atributos de un usuario */
    public Alumno (String correo, String password, String nombre, String apellidos, String id){
        super(correo,password,nombre,apellidos,id);     // se inicia el objeto, instanciando al objeto superior
    }

    /**Metodo para conseguir una lista de todos los profesores del alumno, crea un {@link ArrayList} de @Profesor */
    public void getProfesores(){ 
        listaProfesores = Conexion.getProfesores();
    }

    /**Utiliza el metodo de la clase @link Fachada, para conectarse a la base de datos y pedir las colas en las que se encuentra el alumno */
    public void getColas(){
        listaColas = GestorCola.getColasAlumno(super.getId());      // le pide al gestor de colas que le devuelva la lista de colas a la que se encuentra apuntado el alumno
    }
}
