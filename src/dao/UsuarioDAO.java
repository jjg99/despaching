package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import dominio.Alumno;
import dominio.Profesor;
import dominio.Usuario;
import server.ConexionServer;

/** Clase que se encarga de realizar las sentencias SQL de los usuarios */
public class UsuarioDAO {

    /**
     * Metodo que ejecutara la sentencia SQL para ver si un usuario con una contraseña estan en el sistema, si lo esta lo creara
     * en funcion de su rol.
     * @param user el nombre de usuario a comprobar en el sistema
     * @param pass contraseña del usario a comprobar en el sistema
     * @return un <code>Usuario</code> que contendra la informacion del usuario que ha iniciado sesion si los parametros de entrada se 
     *          encontraban en el sistema
     */
	public static Usuario logIn(String user, String pass) {
        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            ConexionServer.startConnection();
            stmt = ConexionServer.conexion.createStatement();
            ResultSet rs;
            //Sentencia SQL
            String sql = "SELECT \"clave\",\"correo\",\"rol\",\"nombre\",\"apellido\" " +
                    "FROM \"Usuarios\"" +
                    "WHERE clave='" + user + "' and contrasena='" + pass + "'";
            rs = stmt.executeQuery(sql);
            Usuario usuario=null;
            while (rs.next()){
                if(rs.getString("rol").equals(new String("prof"))){
                    usuario = new Profesor(rs.getString("correo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("clave"));
                } else if(rs.getString("rol").equals(new String("alu"))){
                    usuario = new Alumno(rs.getString("correo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("clave"));
                }
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**Metodo encargado de conseguir todos los profesores de un alumno determinado
     * @param Stirng idALumno
     * @return {@link ArrayList}
     */
    public static ArrayList<Profesor> getProfesoresAlumno(String idAlumno){

        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<Profesor> resultadoColas = new ArrayList<Profesor>();     // objeto que va a contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"correo\",\"nombre\",\"apellido\",\"clavePROF\" FROM \"Clases\"FULL OUTER JOIN \"Usuarios\" ON  \"clave\" = \"clavePROF\"  WHERE \"claveALU\" = '"+idAlumno+"'";

            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                // se cargan todos los datos necesarios de la base de datos para crear el profesor
                String correo = resultadoConsulta.getString("correo");
                String nombre = resultadoConsulta.getString("nombre");
                String apellido = resultadoConsulta.getString("apellido");
                String id = resultadoConsulta.getString("clavePROF");
                //se añade al resultado
                resultadoColas.add(new Profesor(correo,nombre,apellido,id));

            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoColas;

    }
    /**Metodo para obtener el nombre de un alumno en concreto
     * @param String idAlumno
     * @return String nombreAlumno
     */
    public static String getNombreAlumno(String idAlumno){
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        StringBuilder resultadoNombre = new StringBuilder() ;     // objeto que va a contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"nombre\", \"apellido\" FROM \"Usuarios\" WHERE \"clave\" = '"+idAlumno+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                resultadoNombre.append(resultadoConsulta.getString("nombre"));      // se construye el String con el nombrey apellidos del alumno
                resultadoNombre.append(" ");
                resultadoNombre.append(resultadoConsulta.getString("apellido"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoNombre.toString();
    }
     /**Metodo para obtener las clases de un profesor
     * @param String idProfesor
     * @return ArrayList clasesProfesor
     */
    public static ArrayList<String> getClasesProfesor(String idProfesor){
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<String> resultadoClases = new ArrayList<String>();     // objeto que va a contener el resultado que se envia al profesor
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"grupo\" FROM \"Clases\" WHERE \"clavePROF\" = '"+idProfesor+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                resultadoClases.add(resultadoConsulta.getString("grupo"));      // se construye el String con el nombrey apellidos del alumno
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoClases;
    }
     /**Metodo para obtener el correo de un usuario en concreto
     * @param String idUsuario
     * @return String correo
     */
    public static String getCorreoUsuario(String idUsuario){
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        StringBuilder resultadoCorreo = new StringBuilder();     // objeto que va a contener el resultado que se envia al profesor
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"correo\" FROM \"Usuarios\" WHERE \"clave\" = '"+idUsuario+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                resultadoCorreo.append(resultadoConsulta.getString("correo"));      // se construye el String con el nombrey apellidos del alumno
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoCorreo.toString();
    }
     /**Metodo para obtener el nombre de un alumno en concreto
     * @param String idAlumno
     * @return String nombreAlumno
     */
    public static void cambiarContrasena(String idUsuario, String contrasenaNueva){
        Statement stmt;     // se crea el statement sobre el que trabajar
        
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "UPDATE \"Usuarios\" SET contrasena ='"+contrasenaNueva+"' WHERE \"clave\" ='"+idUsuario+"'";
            stmt.executeUpdate(sql);  // se ejecuta la actualización sql
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAlumnosProf(String idProf) {
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<String> resultado = new ArrayList<String>();     // objeto que va a contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"claveALU\" FROM \"Clases\" WHERE \"clavePROF\" = '"+idProf+"'";

            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                // se cargan todos los datos necesarios de la base de datos para crear el profesor
                //se añade al resultado
                resultado.add(resultadoConsulta.getString("claveALU"));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
