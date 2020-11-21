package util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de la gestión de mensajes de errores, emitiendolos una
 * vez llamados sus metodos o interpretando el tipo de error presentado en el
 * programa.
 */
public class GestionMensajes {

    /**
     * Metodo que se encarga de emitir un mensaje cuando el usuario y/o contraseña
     * introducidos son erroneos.
     */
    public static void msgErrorInicioSesion() {
        try {
            String mensaje = "Usuario y/o contrasena incorrectos";
            Object[] opciones = {"Aceptar"};
            JOptionPane.showOptionDialog(null, mensaje, "Error Inicio Sesión", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/error.png")), opciones, opciones[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que emite un mensaje cuando un alumno intenta realizar una cita cuando
     * no hay disponibilidad
     */
    public static void msgErrorDisponibilidad() {
        JOptionPane.showMessageDialog(null, "No existe disponibilidad para reservar con este profesor", "Error",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo que emite un mensaje de error cuando no se ha podido conectar con la
     * base de datos
     */
    public static int msgErrorBD() {
        String mensaje = "No se ha podido conectar con la base de datos, compruebe su conexion a Internet\no intentelo de nuevo mas tarde";
        Object[] opciones = {"Reintentar","Salir"};
        try {
            return JOptionPane.showOptionDialog(null, mensaje, "Error", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/error.png")), opciones, opciones[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Metodo que crea un mensaje de error con la informacion del StackTrace
     * @param e la excepcion que se ha generado es pasada como parametro
     */

    public static void msgErrorGenerico(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String mensaje_error = sw.toString();

        JOptionPane.showMessageDialog(null, mensaje_error, "Error", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metodo que muestra realiza una pregunta y da al usuario dos opciones para responder.
     * @param opciones es de tipo <code>Object[]</code> y debera tener dos objetos, estos seran los que se mostrara al usuario
     *                 para elegir, es importante que el que segundo sea la opcion que se quiere por defecto.
     * @param mensaje es el texto que se el mostrara al usuario en la ventana.
     * @param titulo sera el titulo de la ventana emerjente
     * @return la posicion del array <code>opciones</code> que ha sido selecionado.
     */
    public static int msg2OpcionesGenerico(Object[] opciones, String mensaje, String titulo) {
        try {
            return JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/question-mark.png")), opciones, opciones[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        
    }

    /**
     * Metodo que indica que la cola se encuentra cerrada en el momento de realizar la solicitud.
     */
    public static void msgColaCerrada() 
    {
        try {
            String mensaje = "La cola del profesor se encuentra en este momento cerrada";
            Object[] opciones = {"Aceptar"};
            JOptionPane.showOptionDialog(null, mensaje, "Error Cola Profesor", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/error.png")), opciones, opciones[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
