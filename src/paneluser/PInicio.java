package paneluser;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



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
        /**Titulo superior */ 
        JLabel titulo = new JLabel("Despaching"); 
                
        titulo.setFont(new Font());   //establece la fuente del titulo
        this.add(titulo);  // se añade el titulo al panel

    }
    @Override
    public void eliminar(){

    }
}
