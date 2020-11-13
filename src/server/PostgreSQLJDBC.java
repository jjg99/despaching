/*
Por ahora esta clase esta diseña para funcionar de manera autonoma e independiente para asegurar el correcto funcionamiento
de esta clase y de un comportamiento correcto de la base de datos, tras estas pruebas sera debidamente comentada y configurada
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
   private final static String BD_URL = "jdbc:postgresql://jonan.hopto.org:5432/Despaching";
   private static final String USER = "postgres";
   private static final String PASS = "admin";

   public static void main(String args[]) {
      Connection connection = null;
      try {
         Class.forName("org.postgresql.Driver");
         connection = DriverManager.getConnection(BD_URL, USER, PASS);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      // pruebas de conectividad
      Statement stmt = null;
      try {
         stmt = connection.createStatement();
      } catch (SQLException e) {
         e.printStackTrace();
      }

      String sql = "SELECT nombre FROM \"Usuarios\" WHERE rol='prof'";  //Obtiene todos los nombres de los profesores

      ResultSet rs = null;
      try {
         rs = stmt.executeQuery(sql);
         System.out.println("Ejecuntado la sentencia: SELECT nombre FROM \"Usuarios\" WHERE rol='prof' \n Se obtienen todos los profes de la BD:");
         while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}