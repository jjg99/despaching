package paneluser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.awt.GridBagConstraints;


import util.Fuente;

/**
 * Clase destinada a crear el panel de incio de la aplicación
 * La clase no tiene ningún parámetro de entrada, ya que va a ser la misma para todas las entrada que le lleguen.
 * Hereda de la clase {@link JPanel}
 * Implementa la interfaz {@link PInterface}
 */
public class PnlInicio extends JPanel implements PnlInterface{
    public static PnlInicio PnlInicio = new PnlInicio();

    private PnlInicio(){
        /**Constructor de la clase, no recibe ningún parámetro*/
        this.establecerVentana();
    }
    @Override
    
    public void establecerVentana(){
        
        this.setLayout(new GridBagLayout()); //se establece el layout 
        /**Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;       // de esta forma no rellena los huevos y además no hay que volver a tocarlo a lo largo del codigo, ya que la variable c se va reciclando
        
        /** Titulo superior */ 
        JLabel lblTitulo = new JLabel("Despaching"); 
                
        Fuente.setFuenteTituloNegrita(lblTitulo);  //establece la fuente del titulo y se establece el titulo a negrita
        c.gridx= 0; // se coloca el titulo en la parte superior de la pantalla
        c.gridy = 0;
        this.add(lblTitulo,c);  // se añade el titulo al panel
        /**Imagen para poder incluir el icono por defecto al iniciar sesion */
        JLabel lblImagenDefault = null;
        try{
            lblImagenDefault = new JLabel(new ImageIcon(new URL("https://img.icons8.com/dusk/128/000000/add-user-group-man-woman.png")));
        }catch (Exception e) {
            e.printStackTrace();
        }
        c.gridx = 0;       //se coloca el icono en el centro de la pantalla
        c.gridy = 1;
        this.add(lblImagenDefault,c);  //se añade el icono al panel

        /**Area para introducir el nombre de usuario  */
        JTextField txtUsuario = new JTextField("Usuario", 20); //Se le asigna un tamaño de 20 al TextField y se pone el texto por defecto
        Fuente.setFuente(txtUsuario);      //Fijamos la fuente y tamaño de la letra
        c.gridx = 0;  // se coloca el usuario en el centro de la pantalla
        c.gridy = 2;
        //c.weighty = 0.00001;        //se le da cierta altura a todos los componentes por debajo del icono
        this.add(txtUsuario,c);  // se añade el area de usuario al panel 

        /**Area para introducir la contraseña del usuario */
        JPasswordField pswdContrasena = new JPasswordField("Contrasena", 20); //Se le asigna un tamaño de 20 al PasswordField y se pone el texto por defecto
        Fuente.setFuente(pswdContrasena);   //Fijamos la fuente y tamaño de la letra
        pswdContrasena.setEchoChar((char) 0);       //Se cambia a que salgan los caracteres en vez de los puntos
        c.gridx = 0; // se coloca la contraseña en el centro de la pantalla
        c.gridy = 3;
        this.add(pswdContrasena,c);  // se añade el area de contraseña al panel
       
        /**Boton para entrar a la aplicación */
        JButton btnEntrar = new JButton("Entrar");
        c.gridx = 0;        //se coloca el boton inferior a la contraseña
        c.gridy = 4;
        this.add(btnEntrar,c);    //se añade el boton al panel

        /**
         * Metodo que nos permite poder saber si el cuadro de texto esta seleccionado o no
         * para mostrar u ocultar el texto de "Usuario" en {@link txtUsuario}
         */
        txtUsuario.addFocusListener(new FocusListener(){
            @Override
            /**
             * Metodo para cuando se obtiene el foco en el objeto
             */
            public void focusGained(FocusEvent fe){
                if(txtUsuario.getText().equals("Usuario")){     //Se comprueba si el texto que hay es Usuario
                    // Como el texto es Usuario, por tanto no se ha modificado, por lo que se borra
                    txtUsuario.setText(null);
                }
            }
            /**
             * Metodo para cuando se pierde el foco sobre el objeto
             */
            @Override
            public void focusLost(FocusEvent fe){
                if(txtUsuario.getText().equals("")){    //Se comprueba si el texto esta vacio
                    txtUsuario.setText("Usuario");      //Se rellena con "Usuario"
                }
            }
        });

        pswdContrasena.addFocusListener(new FocusListener(){
            @Override
            /**
             * Metodo para cuando se obtiene el foco en el objeto
             */
            public void focusGained(FocusEvent fe){
                if(new String(pswdContrasena.getPassword()).equals("Contrasena")){     //Se comprueba si el texto que hay es Contrasena
                    // Como el texto es Contrasena, por tanto no se ha modificado, por lo que se borra
                    pswdContrasena.setText(null);
                    pswdContrasena.setEchoChar('\u2022');   //Se activa que salgan circulos en vez de lod caracteres
                }
            }
            /**
             * Metodo para cuando se pierde el foco sobre el objeto
             */
            @Override
            public void focusLost(FocusEvent fe){
                if(new String(pswdContrasena.getPassword()).equals("")){    //Se comprueba si el PasswordField esta vacio
                    pswdContrasena.setText("Contrasena");       //Se rellena con "Contrasena"
                    pswdContrasena.setEchoChar((char) 0);       //Se cambia a que salgan los caracteres en vez de los puntos
                }
            }
        });
    }
       

    @Override
    public void eliminar(){

    }
}
