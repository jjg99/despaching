

package paneluser;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.Colores;
import util.Fuente;

/**
 * Clase que crea un panel que muestra la cola de un alumno y le da opciones para 
 * apuntarse y modificar su situacion en ella
 */
public class PnlCola {
    public static PnlCola pnlCola = new PnlCola();

    /** Constructor que llama al metodo {@link initComponentes} */
    private PnlCola()
    {
        initComponentes();
    }

    private void initComponentes()
    {
        // Creacion del panel inferior que presenta todos los elementos
        JPanel pnlInfCola = new JPanel();
        
        // Ajustes de la disposicion de los elementos dentro del panel
        pnlInfCola.setLayout(new GridBagLayout());
        Colores.setBGColor(pnlInfCola);

        // Creacion de los contraints que determinarán la posicion y estilo de los objetos
        GridBagConstraints c = new GridBagConstraints();

        // Creacion de todos los componentes
        // Creacion del boton atras
        JButton btnAtras = new JButton("Volver");
        try{
            btnAtras.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/return.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnAtras.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAtras.setVerticalTextPosition(SwingConstants.CENTER);
        Colores.setBGTransparente(btnAtras);
        btnAtras.setBorder(null);
        Fuente.setFuente(btnAtras);

        // Creacion del titulo
        JLabel lblCola = new JLabel("Cola");
        Fuente.setFuenteNegrita(lblCola);

        // Creacion de la etiqueta del profesor
        JLabel lblProf = new JLabel("Profesor generico"); // Añadir el nombre del profesor pasado por el constructor
        Fuente.setFuenteNegrita(lblProf);


        // Creacion de la etiqueta cola abierta
        JLabel lblAbierta = new JLabel("Abierta");

        // Creacion del boton actualizar
        JButton btnActualizar = new JButton("Actualizar");
        try{
            btnActualizar.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/30/000000/synchronize.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnActualizar.setHorizontalTextPosition(SwingConstants.RIGHT); // ponemos el texto en el centro
        btnActualizar.setVerticalTextPosition(SwingConstants.CENTER); // ponemos el texto abajo
        btnActualizar.setOpaque(false);
        Colores.setBGTransparente(btnActualizar);
        btnActualizar.setBorder(null);
        Fuente.setFuente(btnActualizar);

        // Creacion de la etiqueta de gente a la espera
        JLabel lblGenteEspera = new JLabel("Gente a la espera: ");

        // Creacion de la etiqueta de posicion en la cola
        JLabel lblPosCola = new JLabel("Su posicion en la cola");

        // Creacion del boton salir de la cola
        JButton btnSalirCola = new JButton("Salir de la cola");

        try{
            btnSalirCola.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/return.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnSalirCola.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSalirCola.setVerticalTextPosition(SwingConstants.BOTTOM);
        Colores.setBGTransparente(btnSalirCola);
        btnSalirCola.setBorder(null);
        Fuente.setFuente(btnSalirCola);

        // Creacion del boton entrar a la cola
        JButton btnEntrarCola = new JButton("Entrar a la cola");

        try{
            btnEntrarCola.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/return.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnEntrarCola.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEntrarCola.setVerticalTextPosition(SwingConstants.BOTTOM);
        Colores.setBGTransparente(btnEntrarCola);
        btnEntrarCola.setBorder(null);
        Fuente.setFuente(btnEntrarCola);


    }
}
