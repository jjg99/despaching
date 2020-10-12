package paneluser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;

import gui.JVentana;
import panelAlu.PnlAlumno;
import panelProf.PnlProf;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.net.URL;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import util.Fuente;
import util.Colores;

/**
 * Clase destinada a crear el panel de incio de la aplicación
 * La clase no tiene ningún parámetro de entrada, ya que va a ser la misma para todas las entrada que le lleguen.
 * Hereda de la clase {@link JPanel}
 * Implementa la interfaz {@link PnlInterface}
 */
public class PnlInicio extends JPanel implements PnlInterface{
    public static PnlInicio PnlInicio = new PnlInicio();
    JLabel lblImgOjo; 

    /**
     * Constructor de la clase, llamara al metodo {@link establecerVentana} para inicializar sus componentes
     */
    private PnlInicio(){
        this.establecerVentana();
    }
    
    /**
     * Metodo que inicializara y mostrara todos sus componentes
     */
    @Override
    public void establecerVentana(){
        this.setLayout(new GridBagLayout()); //se establece el layout 
        /**Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;       // de esta forma no rellena los huecos y además no hay que volver a tocarlo a lo largo del codigo, ya que la variable c se va reciclando
        
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
        this.add(txtUsuario,c);  // se añade el area de usuario al panel 

        /**Area para introducir la contraseña del usuario */
        JPasswordField pswdContrasena = new JPasswordField("Contrasena", 20); //Se le asigna un tamaño de 20 al PasswordField y se pone el texto por defecto
        Fuente.setFuente(pswdContrasena);   //Fijamos la fuente y tamaño de la letra
        pswdContrasena.setEchoChar((char) 0);       //Se cambia a que salgan los caracteres en vez de los puntos
        c.gridx = 0; // se coloca la contraseña en el centro de la pantalla
        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);    //Se fija un margen
        this.add(pswdContrasena,c);  //Se añade el area de contraseña al panel

        /** Imagen para poder mostrar la contraseña mientras se mantiene dicha imagen*/
        try{
            lblImgOjo = new JLabel(new ImageIcon(new URL("https://img.icons8.com/dusk/25/000000/closed-eye.png")));
        }catch (Exception e) {
            e.printStackTrace();
        }
        c.gridx = 1;       //Se coloca el icono a la derecha del campo de contrasena
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;     //Fijamos que se ancle a la izquierda
        this.add(lblImgOjo,c);  //se añade el icono al panel
        c.anchor = GridBagConstraints.CENTER;   //Se vuelve a poner el valor por defecto para el resto de componentes
       
        /**Boton para entrar a la aplicación */
        JButton btnEntrar = new JButton("Entrar");
        Colores.setBGAzul(btnEntrar);
        Fuente.setFuente(btnEntrar);
        c.gridx = 0;        //se coloca el boton debajo del de la contraseña
        c.gridy = 4;
        this.add(btnEntrar,c);    //se añade el boton al panel

        /**Boton para rersetear la contrasena */
        JButton btnReset = new JButton("Olvidaste tu contrasena?");
        //Hacemos que solo se vea el texto
        btnReset.setOpaque(false);
        Colores.setBGTransparente(btnReset);
        btnReset.setBorder(null);
        Colores.setFGAzulOsc(btnReset);
        Fuente.setFuente(btnReset);
        c.gridx = 0;        //se coloca el boton debajo del de iniciar sesion
        c.gridy = 5;
        this.add(btnReset,c);
        c.insets = new Insets(0,0,0,0);

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
                    pswdContrasena.setEchoChar('\u2022');   //Se activa que salgan circulos en vez de los caracteres
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

        pswdContrasena.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                //Hara que cuando el foco este en el cuadro de la contrasena y se de al "ENTER" se haga click en el boton de entrar
				if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
					btnEntrar.doClick();
				}
			}
        });

        lblImgOjo.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                try{
                    lblImgOjo.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/25/000000/ophthalmology.png")));
                    pswdContrasena.setEchoChar((char) 0);   //Se pone que se pueda ver la contrasena
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void mouseReleased(MouseEvent me){
                try{
                    lblImgOjo.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/25/000000/closed-eye.png")));
                    if(!new String(pswdContrasena.getPassword()).equals("Contrasena")){
                        pswdContrasena.setEchoChar('\u2022');   //Se activa que salgan circulos en vez de los caracteres
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /**Metodo que permite añadir funcionalidad al boton de entrar {@link btnEntrar} */
        btnEntrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                /**En el caso de que se pulse el boton de entrar a la aplicación, 
                 * se llama al metodo {@link cambiarPanel}
                 */
                JVentana.cambiarPanel(PnlAlumno.PnlAlumno); // se establece el nuevo panel de la aplicación  
            }
        });

        btnReset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                /**En el caso de que se pulse el boton de entrar a la aplicación, 
                 * se llama al metodo {@link cambiarPanel} solo version sprint1
                 */
                JVentana.cambiarPanel(PnlProf.pnlProf); // se establece el nuevo panel de la aplicación
            }
        });


        btnReset.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                // Hacemos que cuando el raton este encima del componente se ponga mas clarito
                Colores.setFGAzulOscTrans(btnReset);
            }
            public void mouseExited(MouseEvent e){
                // Hacemos que cuando el raton no este encima del componete, vuelva al color original
                Colores.setFGAzulOsc(btnReset);
            }
        });
    }
}
