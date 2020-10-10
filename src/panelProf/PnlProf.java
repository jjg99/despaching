package panelProf;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.BorderLayout;


import paneluser.PnlEncabezado;
import paneluser.PnlInterface;
import util.Fuente;

/**Clase para el panel del profesor */
public class PnlProf  extends JPanel implements PnlInterface{

    public static PnlProf pnlProf = new PnlProf(); // se crea la variable que se instanciará desde fuera

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana}
     */
    private PnlProf(){
        this.establecerVentana();
    }
    @Override
    public void establecerVentana(){
        this.setLayout(new BorderLayout()); //se establece el layout de tipo borderLayout
        this.add(new PnlEncabezado(),BorderLayout.NORTH);   //se añade el panel de encabezado en la parte superior de la pantalla

        /**{@linl JPanel} que va a guardar todos los elementos propios del panel, con los que se va a interactuar */
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridBagLayout());       // se establece el layout del panel inferior a gridLayout
        /**Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints c = new GridBagConstraints();


        /**Variable para establecer el titulo superior a la lista de reservas */
        JLabel lblTituloReservas = new JLabel("Reservas");
        Fuente.setFuenteNegrita(lblTituloReservas);      //se cambia la fuente del titulo a la predeterminada y en negrita
        c.gridx = 0;       // se situa en la parte superior izquierda de la pantalla
        c.gridy = 0;
        panelInferior.add(lblTituloReservas,c);

        /**Variable de tipo {@link ListReservas} para guardar */
        ListReservas lstReservas = new ListReservas();
        lstReservas.actualizarValores();      //se actualizan las citas
        c.gridy = 1;     //se coloca en la parte de la izquierda, debajo del titulo de reserva 
        panelInferior.add(lstReservas,c);

        /**Variable para establecer el titulo superior a la cola */
        JLabel lblTituloCola = new JLabel("Cola");
        Fuente.setFuenteNegrita(lblTituloCola);      //se cambia la fuente del titulo a la predeterminada y en negrita
        c.gridx = 1;       // se situa en la parte superior izquierda de la pantalla
        c.gridy = 0;
        panelInferior.add(lblTituloCola,c);

        /**Variable de tipo {@link ListCola} para guardar toda la cola */
        ListCola listaCola = new ListCola();
        listaCola.actualizarValores();      //se actualizan las citas
        c.gridy = 1;     //se coloca en la parte de la izquierda, debajo del titulo de reserva 
        panelInferior.add(listaCola,c);
        
        this.add(panelInferior,BorderLayout.CENTER);    //se añade el panel inferior para ser pintado en el centro de la pantalla

    }
    @Override
    public void eliminar(){
        this.setVisible(false);
    }
    
}
