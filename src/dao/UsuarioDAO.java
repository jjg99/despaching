package dao;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import dominio.Profesor;
import dominio.Usuario;
import server.ConexionServer;

/** Clase que se encarga de realizar las sentencias SQL de los usuarios */
public class UsuarioDAO {

	public static Usuario logIn(String user, String pass) {
        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            ResultSet rs;
            String sql = "SELECT \"clave\",\"correo\",\"rol\",\"nombre\",\"apellidos\" " +
                    "FROM \"Usuarios\"" +
                    "WHERE clave='" + user + "' and contrasena='" + pass + "'";
            rs = stmt.executeQuery(sql);
            Usuario usuario=null;
            while (rs.next()){
                if(rs.getString("rol").equals(new String("prof"))){
                   usuario = new Profesor(rs.getString("correo"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("clave"));
                } else {
                
                }
            }
            return usuario;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
    
}
