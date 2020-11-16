package dominio;

import gui.JVentana;
import panelProf.PnlProf;
import server.Fachada;

/** Clase que se encarga de gestionar el inicio de sesion */
public class ControlSesion {

    /**
     * Metodo que asignara un usuario al panel correspondiente y lo abrira.
     * @param user Nombre de usuario de la persona que va a intentar logearse
     * @param pass Contraseña de la persona que va a intentar logearse
     * @return <code>true</code> si el inicio de sesion se ha realizado satisfactoriamente, si da cualquier tipo de fallo devolvera <code>false</code>
     */
    public static boolean logIn(String user, String pass){
        Usuario usuario = Fachada.logIn(user,pass);
        if(usuario instanceof Profesor){
            JVentana.cambiarPanel(PnlProf.pnlProf = new PnlProf((Profesor) usuario)); // se establece el nuevo panel de la aplicación
            return true;
        } else {
            return false;
        }
        
    }
}
