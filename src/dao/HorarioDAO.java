package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import server.ConexionServer;

public class HorarioDAO {



    /**Metodo encargado de conseguir el horario asociado a un profesor
     * @param Stirng idProfesor
     * @return {@link ArrayList}
     */
    public static String getHorario(String idProfesor){

        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        String horario = null;    // objeto que va a contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"horario\" FROM \"Horarios\" WHERE clave = '"+idProfesor+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                horario = resultadoConsulta.getString("horario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;

    }

    /**Metodo encargado de establecer el horario asociado a un profesor
     * @param Stirng idProfesor
     * @param string horario
     * @return {@link ArrayList}
     */
    public static void setHorario(String idProfesor,String horario){
        
        Statement stmt;     // se crea el statement sobre el que trabajar
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "UPDATE \"Horarios\" SET \"horario\" = '" + horario + "' WHERE \"clave\" = '"+idProfesor+"'";
            stmt.executeUpdate(sql);    // se ejecuta la solicitud sql
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
