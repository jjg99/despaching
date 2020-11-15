package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionServer{
    public static Connection conexion;
    private static final String BD_URL = "jdbc:postgresql://jonan.hopto.org:5432/Despaching";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static void startConnection(){
        try {
            Class.forName("org.postgresql.Driver"); // se carga el driver de la BD
            conexion = DriverManager.getConnection(BD_URL, USER, PASS); // Se realiza la conexion a la BD
        } catch (Exception e) {
            util.GestionMensajes.msgErrorBD();
        }
    }

    public static void endConnection(){
        try {
            conexion.close();
        } catch (SQLException e) {
            //TODO: Usar error generico cuando este creado
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
