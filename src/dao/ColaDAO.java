package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import server.ConexionServer;

/** Clase que se encarga de realizar las sentencias SQL de la cola */
public class ColaDAO {

    /**
     * Metodo que añade al profesor en la tabla de colas, de manera que puede aceptar alumnos en dicha cola
     * @param id identificador unico del profesor que va a abrir la cola
     * @return <code>true</code> si la sentencia se ha ejecutado correctamente y si falla algo devolvera <code>false</code>
     */
	public static boolean openCola(String id) {
        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "INSERT INTO \"Colas\" VALUES ('" + id + "')";
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

    /**
     * Metodo que borra al profesor de la tabla Colas, de esta manera queda cerrada su cola, no pudiendo apuntarse nuevos alumnos
     * @param id identificador unico del profesor que va a cerrar la cola
     * @return <code>true</code> si la sentencia se ha ejecutado correctamente y si falla algo devolvera <code>false</code>
     */
	public static boolean closeCola(String id) {
		Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "DELETE FROM \"Colas\" WHERE clave='" + id + "'";
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**Metodo encargado de acceder a la base de datos y de cargar las colas al que se encuentra añadido un alumno
    *@param String id
    *@return @link ArrayList*/

    public static ArrayList<String> getColasAlumno(String idAlumno){
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<String> resultadoColas = new ArrayList<String>();     // objeto que va a contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT U.nombre FROM \"Colas C, Usuarios U\" WHERE (U.clave= C.clave) AND CONTAINS(C.colas,'"+idAlumno+"') ";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                resultadoColas.add(resultadoConsulta.getString("U.nombre"));

            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoColas;
    }
    /**Metodo que se encarga de devolver la posiciónk en la cola de un alumno
     * @param String idAlumno
     * @param String profesorCola
     * @return int posicion
     */
    public static int getPosicionColaAlumno(String idAlumno,String colaProfesor){
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        int resultadoPosicion = 0;     // objeto que va a contener el resultado que se envia al alumno, en el caso de que se produzca un error devolverá un 0
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT Colas FROM \"Colas\" WHERE clave = '"+colaProfesor+"' CONTAINS('"+idAlumno+"') ";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                String colaCompleta = resultadoConsulta.getString("colas");
                // ahora que se tiene toda la cola hay que fragmentarla 
                String[] colaFragmentada = colaCompleta.split(",");
                // se recorre la cola para buscar el termino que se quiere
                int posicion = 0;      // se utiliza la variable como un contador para saber en que posición se encuentra el alumno
                for(String elemento:colaFragmentada){
                    if(elemento.equals(",")){
                        // en el caso de que se trate de un divisor no se aumenta el contador

                    }
                    else if(elemento.equals(idAlumno)){
                        resultadoPosicion = posicion;

                    }
                    else{
                        posicion = posicion +1;     // si se trata de un alumno se 
                    }

                }

            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoPosicion;

    }
    /**Metodo encargado de conseguir la cola de alumnos de un profesor determinado
     * @param String idProfesor
     * @return {@link ArrayList}
     */
    public static ArrayList<String> getColaProfesor(String idProfesor){
        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<String> resultadoCola = new ArrayList<String>();     // objeto que va a contener el resultado que se envia al alumno
        StringBuilder stringCompuesto = new StringBuilder();            // objeto utilizado para formar un string con el nombre del alumno y su clave
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT colas FROM \"clave\" WHERE clavePROF = '"+idProfesor+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte de la base de datos
            while(resultadoConsulta.next()){
                String colaCompleta = resultadoConsulta.getString("colas");
                // ahora que se tiene toda la cola hay que fragmentarla 
                String[] colaFragmentada = colaCompleta.split(",");
                // se recorre la cola para buscar el termino que se quiere
                for(String elemento:colaFragmentada){
                    if(elemento.equals(",")){
                        // en el caso de que se trate de un divisor no se aumenta el contador

                    }
                    else{
                        // en el caso de que se trate de un alumno, se guarda la clave del alumno, y se busca en la base de datos por el nombre del alumno
                        stringCompuesto.append(UsuarioDAO.getNombreAlumno(elemento));       // se añade al string compuesto el nombre y apellido del alumno
                        stringCompuesto.append("(");
                        stringCompuesto.append(elemento);
                        stringCompuesto.append(")");

                        resultadoCola.add(stringCompuesto.toString());      // se guarda en la cola resultado para el profesor el nombre apellido y clave del alumno
                        stringCompuesto = new StringBuilder();          // de esta forma se deja el objeto vacío para el siguiente alumno
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoCola;
    }
}

