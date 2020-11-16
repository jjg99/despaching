package dao;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import dominio.Profesor;
import dominio.Usuario;
import server.ConexionServer;

import java.util.ArrayList;

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
    
    public static ArrayList<String> getProfesores(String idAlumno){

        Statement stmt;     // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<String> resultadoColas = new ArrayList<String>();     // objeto que va a contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT clavePROF FROM \"Clases\" WHERE claveALU = '"+idAlumno+"'";
            resultadoConsulta =stmt.executeQuery(sql);    // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while(resultadoConsulta.next()){
                resultadoColas.add(resultadoConsulta.getString("clavePROF"));

            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoColas;

    }
    
}
