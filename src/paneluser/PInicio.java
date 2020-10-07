package paneluser;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import util.Fuente;

/**
 * Clase destinada a crear el panel de incio de la aplicación
 * La clase no tiene ningún parámetro de entrada, ya que va a ser la misma para todas las entrada que le lleguen.
 * Hereda de la clase {@link JPanel}
 * Implementa la interfaz {@link PInterface}
 */
public class PInicio extends JPanel implements PInterface{
    PInicio(){
        /**Constructor de la clase, no recibe ningún parámetro*/
        this.establecerVentana();
    }
    @Override
    
    public void establecerVentana(){
        
        this.setLayout(new GridBagLayout()); //se establece el layout 
        /**Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;       // de esta forma no rellena los huevos y además no hay que volver a tocarlo a lo largo del codigo, ya que la variable c se va reciclando
        
        /**Titulo superior */ 
        JLabel titulo = new JLabel("Despaching"); 
                
        Fuente.setFuenteTituloNegrita(titulo);  //establece la fuente del titulo y se establece el titulo a negrita
        c.gridx= 0; // se coloca el titulo en la parte superior de la pantalla
        c.gridy = 0;
        this.add(titulo,c);  // se añade el titulo al panel

        /**Area para introducir el nombre de usuario  */
        JTextField usuario = new JTextField("Usuario");
        c.gridx = 0;  // se coloca el usuario en el centro de la pantalla
        c.gridy = 2;
        this.add(usuario,c);  // se añade el area de usuario al panel 

        /**Area para introducir la contraseña del usuario */
        JTextField psswd = new JTextField("psswd");
        c.gridx = 0; // se coloca la contraseña en el centro de la pantalla
        c.gridy = 3;
        this.add(psswd,c);  // se añade el area de contraseña al panel

        /**Boton para entrar a la aplicación */
        JButton botonEntrar = new JButton("Entrar");
        c.gridx = 0;        //se coloca el boton inferior a la contraseña
        c.gridy = 4;
        this.add(botonEntrar,c);    //se añade el boton al panel


    }
    @Override
    public void eliminar(){

    }
}
