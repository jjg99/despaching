package dao;

import java.sql.Statement;

import server.ConexionServer;


/** Clase que se encarga de realizar las sentencias SQL de la cola */
public class ColaDAO {

    /**
     * Metodo que añade al profesor en la tabla de colas, de manera que puede aceptar alumnos en dicha cola
     * @param id identificador unico del profesor
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
}
