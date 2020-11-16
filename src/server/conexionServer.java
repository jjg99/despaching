package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.GestionMensajes;

/**
 * Clase que se encarga de la conexion a la base de datos
 */
public class ConexionServer{
    /** Variable estatica que en donde se almacenara la conexion */
    public static Connection conexion;
    /** Variable estatica y final en donde se almacenara la URL de la base de datos */
    private static final String BD_URL = "jdbc:postgresql://jonan.hopto.org:5432/Despaching";
    /** Variable estatica y final en donde se almacenara el usuario con el que se conectara a la base de datos*/
    private static final String USER = "postgres";
    /** Variable estatica y final en donde se almacenara la contrasena con la que se conectara a la base de datos */
    private static final String PASS = "admin";

    /** 
     * Metodo el cual intentara conectarse a la base de datos llamando a {@link GestionMensajes.msgErrorBD} si hubiera un error.
     */
    public static void startConnection(){
        try {
            Class.forName("org.postgresql.Driver"); // se carga el driver de la BD
            conexion = DriverManager.getConnection(BD_URL, USER, PASS); // Se realiza la conexion a la BD
        } catch (Exception e) {
            GestionMensajes.msgErrorBD();
        }
    }

    /**
     * Metodo el cual cierra la conexion con la base de datos
     */
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
