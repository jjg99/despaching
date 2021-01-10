package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import server.ConexionServer;

public class CitasDAO {



    /**
     * Metodo encargado de añadir una cita a la base de datos
     * @param idprof contiene el id del profesor
     * @param idAlu  contiene el id de un alumno
     * @param ini fecha con el momento de inicio de la cita
     * @param fin fecha con el momento de fin de la cita
     */
    public static void addCita(String idProf, String idAlu, Date ini, Date fin) {

        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "INSERT INTO \"Citas\" VALUES ('" + idProf + "','" + idAlu + "','" + ini + "','" + fin + "','" + "ok" +"')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Metodo encargado de eliminar una cita a la base de datos ejecutada por el profesor
     * @param idprof contiene el id del profesor
     * @param ini fecha con el momento de inicio de la cita
     */
    public static void removeCitaProfesor(String idProf, Date ini) {

        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "DELETE FROM \"Citas\" WHERE \"clavePROF\" = '" + idProf + "' AND \"fechaINI\" = '" + ini + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo encargado de eliminar una cita a la base de datos ejecutada por el profesor
     * @param idAlu contiene el id del profesor
     * @param ini fecha con el momento de inicio de la cita
     */
    public static void removeCitaAlumno(String idAlu, Date ini) {

        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "DELETE FROM \"Citas\" WHERE \"claveALU\" = '" + idAlu + "' AND \"fechaINI\" = '" + ini + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo encargado de conseguir una cita de la base de datos
     * @param idalu contiene el id del alumno
     * @return devuelve un arraylist con las distintas citas de un alumno
     */
    public static ArrayList<Timestamp> getCitasAlumno(String idAlu) {

        Statement stmt;
        ResultSet resultadoConsulta = null;
        ArrayList<Timestamp> citas = new ArrayList<Timestamp>();    // objeto que va a contener el resultado que se envia
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"fechaINI\", \"fechaFIN\" FROM \"Citas\" WHERE \"claveALU\" = '"+idAlu+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            while(resultadoConsulta.next()){
                citas.add(resultadoConsulta.getTimestamp("fechaINI"));
                citas.add(resultadoConsulta.getTimestamp("fechaFIN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citas;

    }

    /**
     * Metodo encargado de conseguir una cita de la base de datos
     * @param idProf contiene el id del profesor
     * @return devuelve un arraylist con las distintas citas de un alumno
     */
    public static ArrayList<Timestamp> getCitasProf(String idProf) {

        Statement stmt;
        ResultSet resultadoConsulta = null;
        ArrayList<Timestamp> citas = new ArrayList<Timestamp>();    // objeto que va a contener el resultado que se envia
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"fechaINI\", \"fechaFIN\" FROM \"Citas\" WHERE \"clavePROF\" = '"+idProf+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            while(resultadoConsulta.next()){
                citas.add(resultadoConsulta.getTimestamp("fechaINI"));
                citas.add(resultadoConsulta.getTimestamp("fechaFIN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citas;

    }

}
