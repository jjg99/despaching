package util;

import javax.swing.JOptionPane;

/**
* Esta clase se encarga de la gestión de mensajes de errores, emitiendolos una vez
* llamados sus metodos o interpretando el tipo de error presentado en el programa.
*/
public class GestionMensajes {
  
    /**
     * Metodo que se encarga de emitir un mensaje cuando el usuario
     * y/o contraseña introducidos son erroneos.
     */
    public static void msgErrorInicioSesion()
    {
        JOptionPane.showMessageDialog(null, "Usuario y/o contrasena incorrectos", "Error Inicio Sesión", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metodo que emite un mensaje cuando un alumno intenta realizar una cita
     * cuando no hay disponibilidad
     */
    public static void msgErrorDisponibilidad()
    {
        JOptionPane.showMessageDialog(null, "No existe disponibilidad para reservar con este porfesor", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo que emite un mensaje de error cuando no se ha podido conectar
     * con la base de datos
     */
    public static void msgErrorBD()
    {
        JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos, compruebe su conexion a Internet", "Error", JOptionPane.WARNING_MESSAGE);
    }

}
