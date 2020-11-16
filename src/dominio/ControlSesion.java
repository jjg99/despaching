package dominio;

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
        System.out.println(usuario);
        System.out.println(usuario instanceof Profesor);
        if(usuario instanceof Profesor){
            PnlProf.setProfesor((Profesor) usuario);
            return true;
        } else {
            return false;
        }
        
    }
}
