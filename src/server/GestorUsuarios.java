package server;

import dao.UsuarioDAO;

import java.util.Random;

public class GestorUsuarios {
    public static void reestablecerContrasena(String idUsuario){
        // se realiza la consulta en el gestor de usuarios
        String correoUsuario = UsuarioDAO.getCorreoUsuario(idUsuario);
        if(correoUsuario!= null){	//se comprueba que el usuario existe en la base de datos
            // se genera una nueva contraseña para el usuario
            String contrasenaNueva = generarContrasena();
            //se cambia la contraseña en la base de datos
            UsuarioDAO.cambiarContrasena(idUsuario,contrasenaNueva);
            //se envia un email con la contraseña nueva al usuario
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("Se el ha reestablecido la contraseña de forma correcta\n");
            mensaje.append("Su nueva contraseña es: ");
            mensaje.append(contrasenaNueva);
            GestorEmail.enviarEmail(correoUsuario, "Contraseña nueva",mensaje.toString());
        }

    }
    private static String generarContrasena() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
    
        String contrasenaNueva = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    
       return contrasenaNueva;
    }
}
