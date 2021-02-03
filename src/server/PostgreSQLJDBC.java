/*
Esta esta para entender la integracion de sql en java y realizar pruebas de conectividad a la BD
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
   private static final String BD_URL = "jdbc:postgresql://localhost:5432/Despaching";
   private static final String USER = "postgres";
   private static final String PASS = "admin";

   public static void main(String args[]) {
      Connection connection = null;
      try {
         Class.forName("org.postgresql.Driver");      //se carga el driver de la BD
         connection = DriverManager.getConnection(BD_URL, USER, PASS);     //Se realiza la conexion a la BD
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      // pruebas de conectividad
      Statement stmt = null;
      try {
         stmt = connection.createStatement();      //Se prepara para la ejecucion de de querys
      } catch (SQLException e) {
         e.printStackTrace();
      }

      String sql = "SELECT nombre FROM \"Usuarios\" WHERE rol='prof'";  //Se fija la String de la query

      ResultSet rs = null;
      try {
         rs = stmt.executeQuery(sql);     //Se ejecuta la query y se guardan los resultados
         System.out.println("Ejecutando la sentencia: SELECT nombre FROM \"Usuarios\" WHERE rol='prof' \n Se obtienen todos los profes de la BD:");
         while (rs.next()) { 
            System.out.println("Nombre: " + rs.getString("nombre"));    //Extrae y muestra el nombre de los resultados
         }
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }

      sql = "SELECT nombre FROM \"Usuarios\" WHERE rol='alu'";  //Se fija la String de otra query

      try {
         rs = stmt.executeQuery(sql);
         System.out.println("\nEjecutando la sentencia: SELECT nombre FROM \"Usuarios\" WHERE rol='alu' \n Se obtienen todos los alumnos de la BD:");
         while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre"));    //Extrae y muestra el nombre de los resultados
         }
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}