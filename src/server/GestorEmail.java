package server;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GestorEmail {
    static String origen = "despaching@gmail.com";// direccion desde la que se envia el correo
    static String  contrasena = "Despaching123";        // contraseña del correro electronico
    public static void enviarEmail(String destinatario,String cabecera,String mensaje){
        //se establecen las propiedades de la conexion
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        // se crea la sesión para enviar los mensajes
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(origen, contrasena);
                    }
                });
        try {
            // se crea el mensaje a enviar
            Message message = new MimeMessage(session);
            // se establece el origen del mensaje
            message.setFrom(new InternetAddress(origen));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject(cabecera);
            message.setText(mensaje);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public static void enviarEmailCita(String idDestinatario){
        // se carga primero el correo electronico de la persona que va a recibir el email

    }
    
}